package com.invoice.system.service.impl;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.invoice.system.domain.szmodel.SysInvoice;
import com.invoice.system.domain.szmodel.SysIssueInvoicePush;
import com.invoice.system.mapper.SysInvoiceMapper;
import com.invoice.system.mapper.SysIssueInvoicePushMapper;
import com.invoice.system.service.SysInvoiceService;
import com.invoice.system.shuzuservice.InvoiceAPIService;
import com.invoice.system.shuzuservice.QytApiService;
import com.invoice.system.shuzuservice.WebServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Desoription:发票业务层
 * @Auther:panyong
 * @create:2020/6/17 15:15
 */
@Service
public class SysInvoiceServiceImpl implements SysInvoiceService {
    @Autowired
    private SysInvoiceMapper sysInvoiceMapper;
    @Autowired
    private InvoiceAPIService invoiceAPIService;
    @Autowired
    private SysIssueInvoicePushMapper sysIssueInvoicePushMapper;
    @Autowired
    private QytApiService qytApiService;
    /***
     *@Description //插入
     *@Params [sysInvoice]
     *@Return void
     *@Author panyong
     *@Date 2020/6/28 14:49
    */
    @Override
    public void insert(SysInvoice sysInvoice) {
        sysInvoiceMapper.insert(sysInvoice);
    }
    /***
     *@Description //如果发票流水号存在则修改，不存在则插入
     *@Params [sysInvoice]
     *@Return void
     *@Author panyong
     *@Date 2020/6/29 9:12
    */
    @Override
    public void insertOrUp(SysInvoice sysInvoice) {
        SysInvoice invoice=selectByFpqqlsh(sysInvoice.getFpqqlsh());
        if (invoice==null){
            sysInvoiceMapper.insert(sysInvoice);
        }
        sysInvoiceMapper.updataByFpqqlsh(sysInvoice);
    }
    /***
     *@Description //根据流水号查询
     *@Params [fpqqlsh]
     *@Return com.invoice.system.domain.szmodel.SysInvoice
     *@Author panyong
     *@Date 2020/6/17 18:26
    */
    @Override
    public SysInvoice selectByFpqqlsh(String fpqqlsh) {
        SysInvoice sysInvoice=sysInvoiceMapper.selectByFpqqlsh(fpqqlsh);
        return sysInvoice;
    }
    /**
     * 根据参数查询发票
     * @author lijialin
     * @date 2020/6/19 15:59
     * @param sysInvoice
     * @return java.util.List<com.invoice.system.domain.szmodel.SysInvoice>
     */
    @Override
    public List<SysInvoice> selInvoiceInfosByParams(SysInvoice sysInvoice) {
        return sysInvoiceMapper.selInvoiceInfosByParams(sysInvoice);
    }

    /***
     *@Description //发票查询
     *@Params [jsbh机身编号, fplxdm发票类型代码, cxfs查询方式, cxtj查询条件]
     *@Return com.invoice.system.domain.szmodel.SysInvoice
     *@Author panyong
     *@Date 2020/6/23 14:38
    */
    @Override
    public SysInvoice getInvoice(String jsbh, String fplxdm, String cxfs, String cxtj, StringBuilder message) {
        SysInvoice invoice=null;
        if(cxfs.equals("1")) {//按发票流水号查询
            //查询数据库是否存在
            invoice= selectByFpqqlsh(cxtj);

        }else if (cxfs.equals("0")){//按发票号码查询
            invoice= sysInvoiceMapper.selectByFphm(cxtj);
        }else if (cxfs.equals("2")){//按数组发票编码查询
            SysIssueInvoicePush sysIssueInvoicePush= sysIssueInvoicePushMapper.selectByInvoiceNumber(cxtj);
            invoice= sysInvoiceMapper.selectByFpqqlsh(sysIssueInvoicePush.getFpqqlsh());
            cxtj=sysIssueInvoicePush.getFpqqlsh();
            cxfs="0";
        }
        if(invoice==null){//不存在则调接口查询
            String result=invoiceAPIService.getInvoice(jsbh,fplxdm,cxfs,cxtj);
            Map map = Maps.newHashMap();
            try {
                map=WebServiceUtil.parseXml(result);
                String returncode =WebServiceUtil.getValToMap(map, "returncode");
                String returnmsg =WebServiceUtil.getValToMap(map, "returnmsg");
                if (!returncode.equals("0")){
                    //查询失败则返回错误信息
                    message.append(returnmsg);
                    return null;
                }else {
                    List spbmlist = WebServiceUtil.getValToMap(map, "kpxx");
                    Map msap = (Map)spbmlist.get(0);
                    Map mapc=WebServiceUtil.transformUpperCase(msap);
                    invoice= JSON.parseObject(JSON.toJSONString(mapc), SysInvoice.class);
                    //查询开具发票信息
                    SysIssueInvoicePush sysIssueInvoicePush=sysIssueInvoicePushMapper.selectByFpqqlsh(invoice.getFpqqlsh());
                    if (sysIssueInvoicePush!=null){//关联企友通发票编号
                        invoice.setInvoiceNumber(sysIssueInvoicePush.getInvoiceNumber());
                    }
                    insertOrUp(invoice);
                    return invoice;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return invoice;
    }
    /***
     *@Description //跑发票查询任务
     *@Params []
     *@Return void
     *@Author panyong
     *@Date 2020/6/24 18:56
    */
    @Override
    public void getInvoiceRun() {
        List<SysIssueInvoicePush> siList=sysIssueInvoicePushMapper.selectByStatus(1);
        for (SysIssueInvoicePush si : siList) {
            synchronized (si) {
                //将状态修改成执行状态
                si.setStatus(2);
                sysIssueInvoicePushMapper.updateStatus(si);
                String result = invoiceAPIService.getInvoice(si.getJsbh(),si.getFplxdm(),"1",si.getFpqqlsh());
                Map map = Maps.newHashMap();
                try {
                    map=WebServiceUtil.parseXml(result);
                    String returncode =WebServiceUtil.getValToMap(map, "returncode");
                    if (returncode.equals("0")){//开票成功
                        List spbmlist = WebServiceUtil.getValToMap(map, "kpxx");
                        Map msap = (Map)spbmlist.get(0);
                        Map mapc=WebServiceUtil.transformUpperCase(msap);
                        SysInvoice invoice= JSON.parseObject(JSON.toJSONString(mapc), SysInvoice.class);
                        invoice.setInvoiceNumber(si.getInvoiceNumber());
                        insertOrUp(invoice);
                        //推送到qyt
                        qytApiService.pushInvoice(invoice);
                        si.setStatus(5);
                        sysIssueInvoicePushMapper.updateStatus(si);
                    }else if (returncode.equals("300077")){//开具中将状态改回1，下次继续执行
                        si.setStatus(1);
                        sysIssueInvoicePushMapper.updateStatus(si);
                    }else{//开票失败
                        String returnmsg =WebServiceUtil.getValToMap(map, "returnmsg");
                        si.setStatus(4);
                        qytApiService.pushInvoice(si.getInvoiceNumber(),returnmsg);
                        sysIssueInvoicePushMapper.updateStatus(si);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
