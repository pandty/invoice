package com.invoice.system.service.impl;

import com.google.common.collect.Maps;
import com.invoice.system.domain.szmodel.SysIssueInvoiceDetails;
import com.invoice.system.domain.szmodel.SysIssueInvoicePush;
import com.invoice.system.mapper.SysIssueInvoiceDetailsMapper;
import com.invoice.system.mapper.SysIssueInvoicePushMapper;
import com.invoice.system.service.SysIssueInvoicePushService;
import com.invoice.system.shuzuservice.InvoiceAPIService;
import com.invoice.system.shuzuservice.WebServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Desoription:发票开具接口业务实现
 * @Auther:panyong
 * @create:2020/6/13 9:32
 */
@Service
public class SysIssueInvoicePushServiceImpl implements SysIssueInvoicePushService {
    @Autowired
    private SysIssueInvoicePushMapper szIssueInvoicePushMapper;
    @Autowired
    private SysIssueInvoiceDetailsMapper szIssueInvoiceDetailsMapper;
    @Autowired
    private InvoiceAPIService invoiceAPIService;

    /***
     *@Description //添加开具发票任务到推送表
     *@Params [szIssueInvoicePush]
     *@Return void
     *@Author panyong
     *@Date 2020/6/13 9:35
    */
    @Override
    @Transactional
    public Map insertIssueInvoicePush(SysIssueInvoicePush szIssueInvoicePush,StringBuilder message) {
        Map map= Maps.newHashMap();
        //根据发票编号查询
        SysIssueInvoicePush sii=szIssueInvoicePushMapper.selectByInvoiceNumber(szIssueInvoicePush.getInvoiceNumber());
        if(sii==null){
            try {
                //生成发票流水号
                String fpqqlsh = creatFplsh(szIssueInvoicePush.getSprsjh(), szIssueInvoicePush.getFplxdm());
                szIssueInvoicePush.setFpqqlsh(fpqqlsh);
                String result = invoiceAPIService.issueVATGeneralDzInvoice2(szIssueInvoicePush);
                String returncode = WebServiceUtil.readXMLValue(result, "returncode");
                if (returncode.equals("0")) {//推送成功修改状态
                    List<SysIssueInvoiceDetails> fyxmList = szIssueInvoicePush.getFyxm();
                    for (SysIssueInvoiceDetails fyxm : fyxmList) {
                        fyxm.setFpqqlsh(fpqqlsh);
                    }
                    //插入开具明细
                    szIssueInvoiceDetailsMapper.insertList(fyxmList);
                    //插入开具推送任务
                    szIssueInvoicePush.setStatus(1);
                    szIssueInvoicePushMapper.insert(szIssueInvoicePush);
                    //返回发票编码和流水号
                    map.put("invoiceNumber", szIssueInvoicePush.getInvoiceNumber());
                    map.put("fpqqlsh", szIssueInvoicePush.getFpqqlsh());
                    return map;
                } else {
                    String returnmsg = WebServiceUtil.readXMLValue(result, "returnmsg");
                    message.append(returnmsg);
                    return null;
                }
            }catch (Exception e){
                message.append("程序异常！");
            }
        }else {
            message.append("该发票编码已存在！");
        }
        return null;
    }
    /***
     *@Description //发票流水号生成规则 收票人手机号后两位+发票类型代码+时间
     *@Params [sprsjh, fplxdm]
     *@Return java.lang.String
     *@Author panyong
     *@Date 2020/6/24 10:06
    */
    private String creatFplsh(String sprsjh, String fplxdm) {
        SimpleDateFormat df = new SimpleDateFormat("MMDDhhmmssSSS");
        String fplsh=sprsjh.substring(sprsjh.length()-2,sprsjh.length())+fplxdm+df.format(new Date());
        return fplsh;
    }

    public static void main(String[] args) {
        SysIssueInvoicePushServiceImpl service=new SysIssueInvoicePushServiceImpl();
        service.creatFplsh("18070357795","026");
    }
    /***
     *@Description //跑发票开具任务
     *@Params []
     *@Return void
     *@Author panyong
     *@Date 2020/6/23 18:52
    */
    @Override
    public void issueInvoicePushRun() {
        List<SysIssueInvoicePush> siList = szIssueInvoicePushMapper.selectByStatus(0);
        for (SysIssueInvoicePush si : siList) {
            synchronized (si) {
                //将状态修改成执行状态
                si.setStatus(2);
                szIssueInvoicePushMapper.updateStatus(si);
                String result = invoiceAPIService.issueVATGeneralDzInvoice(si);
                String returncode = WebServiceUtil.readXMLValue(result, "returncode");
                if (returncode.equals("0")) {//推送成功修改状态
                    si.setStatus(1);
                    szIssueInvoicePushMapper.updateStatus(si);
                } else {
                    //将状态修改成推送失败状态
                    si.setStatus(3);
                    szIssueInvoicePushMapper.updateStatus(si);
                }
            }
        }
    }
}
