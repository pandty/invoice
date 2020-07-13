package com.invoice.system.shuzuservice;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.invoice.system.domain.szmodel.SysInvoice;
import com.invoice.system.domain.szmodel.SysIssueInvoiceDetails;
import com.invoice.system.domain.szmodel.SysIssueInvoicePush;

import com.invoice.system.mapper.SysIssueInvoiceDetailsMapper;
import com.invoice.system.mapper.SysIssueInvoicePushMapper;
import com.invoice.system.service.SysInvoiceService;
import com.invoice.system.service.SysPushInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Desoription:发票相关接口
 * @Auther:panyong
 * @create:2020/6/2 9:23
 */
@Service
public class InvoiceAPIService {
    @Autowired
    private SysIssueInvoicePushMapper szIssueInvoicePushMapper;
    @Autowired
    private SysIssueInvoiceDetailsMapper szIssueInvoiceDetailsMapper;
    @Autowired
    private SysPushInfoService szPushInfoService;
    @Autowired
    private CommunalService communalService;
    @Autowired
    private SysInvoiceService sysInvoiceService;
    //请求地址
    private static final String  url = "https://paymgmt.shuzutech.com/";
    //调用数族具体业务请求路径
    private static final String businessUrl ="invoice/business";
    /***
     *@Description //发票打印
     *@Params [business]
     *@Return java.lang.String
     *@Author panyong
     *@Date 2020/6/2 9:26
     */
    public String printInvoice(String business){
        HashMap<String,String> param=new HashMap<String,String>();
        param.put("jsbh","");//机身编号
        param.put("fplxdm","");//发票类型代码
        param.put("fpdm","");//发票代码
        param.put("fphm","");//发票号码
        String result=WebServiceUtil.reqIntputUrl(business,param);
        return result;
    }

    /***
     *@Description //智能发票打印
     *@Params [business]
     *@Return java.lang.String
     *@Author panyong
     *@Date 2020/6/2 9:29
     */
    public String printInvoiceAptitude(String business){
        HashMap<String,String> param=new HashMap<String,String>();
        param.put("jsbh","");//机身编号
        param.put("fplxdm","");//发票类型代码
        param.put("fpdm","");//发票代码
        param.put("fphm","");//发票号码
        param.put("dylx","");//打印类型
        param.put("dyfs","");//打印方式
        String result=WebServiceUtil.reqIntputUrl(business,param);
        return result;
    }
    /***
     *@Description //发票打印查询
     *@Params [business]
     *@Return java.lang.String
     *@Author panyong
     *@Date 2020/6/2 9:34
     */
    public String getPrintInvoice(String business){
        HashMap<String,String> param=new HashMap<String,String>();
        param.put("shnsrsbh","");//销方纳税人识别号
        param.put("jsbh","");//机身编号
        param.put("cxtj","");//查询条件
        String result=WebServiceUtil.reqIntputUrl(business,param);
        return result;
    }
    /***
     *@Description //发票查询
     *@Params [business]
     *@Return java.lang.String
     *@Author panyong
     *@Date 2020/6/2 9:42
     */
    public SysInvoice getSZInvoice(SysInvoice invoice){

        HashMap<String,String> param=new HashMap<String,String>();
        param.put("jsbh",invoice.getJsbh());//机身编号
        param.put("fplxdm",invoice.getFplxdm());//发票类型代码
        param.put("cxfs",invoice.getCxfs()+"");//查询方式
        param.put("cxtj",invoice.getCxtj());//查询条件

        String result=communalService.reqIntputUrl(SzBusinessConstants.FPCX,param,url+businessUrl,3);
        Map map = Maps.newHashMap();
        SysInvoice sysInvoice=null;
        try {
            map=WebServiceUtil.parseXml(result);
            List spbmlist = WebServiceUtil.getValToMap(map, "kpxx");
            Map msap = (Map)spbmlist.get(0);
            Map mapc=WebServiceUtil.transformUpperCase(msap);
            sysInvoice= JSON.parseObject(JSON.toJSONString(mapc), SysInvoice.class);
            sysInvoiceService.insert(sysInvoice);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sysInvoice;
    }
    /***
     *@Description //发票查询
     *@Params [business]
     *@Return java.lang.String
     *@Author panyong
     *@Date 2020/6/2 9:42
     */
    public String getInvoice(String jsbh,String fplxdm,String cxfs,String cxtj){

        HashMap<String,String> param=new HashMap<String,String>();
        param.put("jsbh",jsbh);//机身编号
        param.put("fplxdm",fplxdm);//发票类型代码
        param.put("cxfs",cxfs);//查询方式
        param.put("cxtj",cxtj);//查询条件
        String result=communalService.reqIntputUrl(SzBusinessConstants.FPCX,param,url+businessUrl,3);
        return result;
    }

    /***
     *@Description //页边距设置(智能打印)
     *@Params [business]
     *@Return java.lang.String
     *@Author panyong
     *@Date 2020/6/2 9:36
     */
    public String printMargin(String business){
        HashMap<String,String> param=new HashMap<String,String>();
        param.put("jsbh","");//机身编号
        param.put("fplxdm","");//发票类型代码
        param.put("top","");//打印上边距
        param.put("left","");//打印左边距
        String result=WebServiceUtil.reqIntputUrl(business,param);
        return result;
    }
    /***
     *@Description //发票作废
     *@Params [business]
     *@Return java.lang.String
     *@Author panyong
     *@Date 2020/6/2 9:41
     */
    public String removeInvoice(String business){
        HashMap<String,String> param=new HashMap<String,String>();
        param.put("fphm","99612773");//发票号码
        param.put("jsbh","110101201707010057~~499000152456");//机身编号
        param.put("fplxdm","026");//发票类型代码
        param.put("zflx","1");//作废类型
        param.put("hjje","100");//合计金额
        param.put("fpdm","050003521111");//发票代码
        param.put("zfr","开票人");//作废人
        String result=WebServiceUtil.reqIntputUrl(business,param);
        return result;
    }
    /***
     *@Description //发票开具结果通知
     *@Params [business]
     *@Return java.lang.String
     *@Author panyong
     *@Date 2020/6/2 9:49
     */
    public String InvoiceIssuedNotice(String business){
        HashMap<String,Object> param=new HashMap<String,Object>();
        param.put("returnmsg","");//消息描述
        param.put("returncode","");//消息码
        param.put("fpqqlsh","");//发票请求流水号

        Map<String,Object> returndata = Maps.newHashMap();
        HashMap<String,Object> kpxxMap=new HashMap<>();
        kpxxMap.put("fphm","");//发票号码
        kpxxMap.put("fpdm","");//发票代码
        kpxxMap.put("fpzt","");//发票状态
        kpxxMap.put("sksbbh","");//税控设备编号
        kpxxMap.put("kprq","");//票日期 YYYYMMDDHHMMSS
        kpxxMap.put("ghdwsbh","");//购货单位识别号
        kpxxMap.put("hjje","");//合计金额
        kpxxMap.put("hjse","");//合计税额
        kpxxMap.put("jshj","");//作废人
        kpxxMap.put("jym","");//07512295604424007028
        kpxxMap.put("ghdwmc","");//![CDATA[购货单位名称]]
        kpxxMap.put("skm","");//![CDATA[税控码]]
        kpxxMap.put("url","");//![CDATA[电子发票下载地址]]
        returndata.put("kpxx",kpxxMap);
        param.put("returndata",returndata);
        String result=WebServiceUtil.reqUrl(business,param);
        return result;
    }
    /***
     *@Description //用于智能获取税收编码   智能赋码
     *@Params [business]
     *@Return java.lang.String
     *@Author panyong
     *@Date 2020/6/2 10:15
     */
    public String  getTaxCode(String business){
        HashMap<String,String> param=new HashMap<String,String>();
        param.put("name","");//商品名称
        String result=WebServiceUtil.reqIntputUrl(business,param);
        return result;
    }
    /***
     *@Description // 发票抬头补全   用于纸质发票自助开具时，补充提交购方的发票抬头。
     *@Params [business]
     *@Return java.lang.String
     *@Author panyong
     *@Date 2020/6/2 10:18
     */
    public String invoiceCompletion(String business){
        HashMap<String,String> param=new HashMap<String,String>();
        param.put("shnsrsbh","");//纳税人识别号
        param.put("fpqqlsh","");//发票请求流水号
        param.put("ghdwsbh","");//购货单位识别号
        param.put("ghdwmc","");//购货单位名称
        param.put("ghdwdzdh","");//购货单位地址电话
        param.put("ghdwyhzh","");//购货单位银行帐号
        String result=WebServiceUtil.reqIntputUrl(business,param);
        return result;
    }
    /***
     *@Description //查询当前未开票号码信息。  当前未开票号码查询
     *@Params [business]
     *@Return java.lang.String
     *@Author panyong
     *@Date 2020/6/2 10:28
     */
    public String  getNotTaxCode(String business){
        HashMap<String,String> param=new HashMap<String,String>();
        param.put("jsbh","");//机身编号
        param.put("fplxdm","");//发票类型代码
        String result=WebServiceUtil.reqIntputUrl(business,param);
        return result;
    }
    /***
     *@Description //发票查询统计
     *@Params [business]
     *@Return java.lang.String
     *@Author panyong
     *@Date 2020/6/2 10:31
     */
    public String  getInvoiceStatistics(String business){
        HashMap<String,String> param=new HashMap<String,String>();
        param.put("jsbh","");//机身编号
        param.put("fplxdm","");//发票类型代码
        param.put("qsrq","");//起始日期
        param.put("zzrq","");//终止日期
        String result=WebServiceUtil.reqIntputUrl(business,param);
        return result;
    }
    /***
     *@Description //购票信息查询
     *@Params [business]
     *@Return java.lang.String
     *@Author panyong
     *@Date 2020/6/2 10:35
     */
    public String  getBuyInvoice(String business){
        HashMap<String,String> param=new HashMap<String,String>();
        param.put("jsbh","");//机身编号
        param.put("fplxdm","");//发票类型代码
        param.put("sblx","");//设备类型
        param.put("fpzt","");//发票状态

        param.put("nsrsbh","");//纳税人识别号
        param.put("qtzd","");//其他字段
        param.put("lgqxx","");//领购全信息
        String result=WebServiceUtil.reqIntputUrl(business,param);
        return result;
    }

    //发票本地打印插件
    /*协议： JSON/post
    接口地址: http://127.0.0.1:23334/api/print
    接口地址: http://127.0.0.1:23334/api/qdprint,(清单打印地址)*/
    public String  invoiceLocalPrinter(String business){
        HashMap<String,String> param=new HashMap<String,String>();
        param.put("fpqqlsh","");//
        param.put("fpdm","");//
        param.put("fphm","");//

        param.put("left","");//
        param.put("top","");//
        param.put("qdbz","");//
        String result=WebServiceUtil.reqIntputUrl(business,param);
        return result;
    }

    /***
     *@Description //红字信息表上传(兼容税控服务器)
     *@Params [business]
     *@Return java.lang.String
     *@Author panyong
     *@Date 2020/6/2 11:23
     */
    public String  redSubinformationUpload(String business){
        HashMap<String,Object> param=new HashMap<String,Object>();
        param.put("jsbh","");//机身编号
        param.put("xxblx","");//信息报类型
        param.put("yfpdm","");//原发票代码
        param.put("yfphm","");//原发票号码
        param.put("szlb","");//税种类别
        param.put("dslbs","");//多税率标识

        param.put("tkrq","");//填开日期
        param.put("ghdwmc","");//购货单位名称
        param.put("ghdwdm","");//购货单位代码
        param.put("xhdwmc","");//销货单位名称
        param.put("xhdwdm","");//销货单位代码
        param.put("hjje","");//合计金额

        param.put("zhsl","");//税率
        param.put("hjse","");//合计税额
        param.put("sqsm","");//十位数字的申请说明：含正常和逾期
        param.put("sqly","");//申请理由
        param.put("lxdh","");//联系电话
        param.put("hsslbs","");//含税税率标示

        param.put("bmbbbh","");//编码表版本号
        param.put("kprq","");//开票日期
        List<Map<String, Object>> fyxmList=new ArrayList<>();
        HashMap<String,Object> group=new HashMap<String,Object>();
        group.put("spmc","");//商品名称
        group.put("dw","");//单位
        group.put("dj","");//单价
        group.put("sl","");//税率
        group.put("ggxh","");//规格型号
        group.put("je","");//金额
        group.put("spsl","");//商品数量
        fyxmList.add(group);
        param.put("fyxm",fyxmList);//含税税率标示
        String result=WebServiceUtil.reqIntputUrl(business,param);
        return result;
    }
    /***
     *@Description //发票列表查询
     *@Params [business]
     *@Return java.lang.String
     *@Author panyong
     *@Date 2020/6/2 15:16
     */
    public String  getInvoiceList(String business){
        HashMap<String,String> param=new HashMap<String,String>();
        param.put("nsrsbh","");//纳税人识别号
        param.put("start_date","");//开始日期
        param.put("end_date","");//结束日期
        param.put("page","");//页码
        param.put("pagesize","");//每页数量
        String result=WebServiceUtil.reqIntputUrl(business,param);
        return result;
    }
    /***
     *@Description //红字信息表下载(兼容税控服务器)
     *@Params [business]
     *@Return java.lang.String
     *@Author panyong
     *@Date 2020/6/2 15:26
     */
    public String  redSubinformationDown(String business){
        HashMap<String,String> param=new HashMap<String,String>();
        param.put("jsbh","");//机身编号
        param.put("yqzt","");//逾期状态
        param.put("tkrq_q","");//填开日期起
        param.put("tkrq_z","");//填开日期止
        param.put("ghdwdm","");//购方税号
        param.put("xhdwdm","");//销方税号
        param.put("xxbbh","");//信息表编号
        param.put("xxbfw","");//信息表下载范围
        param.put("pageno","");//页号，从 1 开始
        param.put("pagesize","");//每页记录数
        String result=WebServiceUtil.reqIntputUrl(business,param);
        return result;
    }

    public String  printInvoiceList(String business){
        HashMap<String,String> param=new HashMap<String,String>();
        param.put("jsbh","");//机身编号
        param.put("fplxdm","");//发票类型代码
        param.put("fpdm","");//发票代码
        param.put("fphm","");//发票号码
        String result=WebServiceUtil.reqIntputUrl(business,param);
        return result;
    }

    /**
     * 增值税普通电子发票
     * @return
     */
    public String issueVATGeneralDzInvoice(SysIssueInvoicePush si){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("fpqqlsh", si.getFpqqlsh());//发票请求流水号
        params.put("shnsrsbh", si.getShnsrsbh());//纳税人识别号
        params.put("jsbh", si.getJsbh());//机身编号
        params.put("fplxdm", si.getFplxdm());//发票类型代码
        params.put("kplx", si.getKplx());//开票类型
        params.put("yhlx", si.getYhlx());//用户类型
        params.put("ghdwsbh", si.getGhdwsbh());//购货单位识别号
        params.put("ghdwmc", si.getGhdwmc());//购货单位名称
        params.put("ghdwdzdh", si.getGhdwdzdh());//购货单位地址电话
        params.put("ghdwyhzh", si.getGhdwyhzh());//购货单位银行帐号
        params.put("bz", si.getBz());//备注
        params.put("skr", si.getSkr());//收款人
        params.put("fhr", si.getFhr());//复核人
        params.put("kpr", si.getKpr());//开票人
        params.put("yfpdm", si.getYfpdm() == null ? "" : si.getYfpdm());//原发票代码
        params.put("yfphm", si.getYfphm() == null ? "" : si.getYfphm());//原发票号码
        params.put("sprsjh", si.getSprsjh());//收票人手机号
        Map<String, Object> fyxmParams = Maps.newHashMap();
        List<Map<String, Object>> listParams = Lists.newArrayList();
        List<SysIssueInvoiceDetails> sdList = szIssueInvoiceDetailsMapper.selectByFpqqlsh(si.getFpqqlsh());
        for (SysIssueInvoiceDetails sd : sdList) {
            fyxmParams.put("fphxz", sd.getFphxz());
            fyxmParams.put("spmc", sd.getSpmc());
            fyxmParams.put("ggxh", sd.getGgxh());//
            fyxmParams.put("dw", sd.getDw());
            fyxmParams.put("spsl", sd.getSpsl());//商品数量
            fyxmParams.put("dj", sd.getDj());//单价
            fyxmParams.put("je", sd.getJe());//金额
            fyxmParams.put("sl", sd.getSl());//税率
            fyxmParams.put("se", sd.getSe());//税额
            fyxmParams.put("spbm", sd.getSpbm());//商品编码
            fyxmParams.put("zxbm", sd.getZxbm());//自行编码
            fyxmParams.put("yhzcbs", sd.getYhzcbs());//优惠政策标识
            fyxmParams.put("lslbs", sd.getLslbs());//税率标识
            fyxmParams.put("zzstsgl", sd.getZzstsgl());//增值税特殊管理
            listParams.add(fyxmParams);
        }
        params.put("fyxm", listParams);
        String result = communalService.reqIntputUrl(SzBusinessConstants.FPKJ, params,url+businessUrl,1);
        return result;
    }
    /**
     * 增值税普通电子发票
     * @return
     */
    public String issueVATGeneralDzInvoice2(SysIssueInvoicePush si){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("fpqqlsh", si.getFpqqlsh());//发票请求流水号
        params.put("shnsrsbh", si.getShnsrsbh());//纳税人识别号
        params.put("jsbh", si.getJsbh());//机身编号
        params.put("fplxdm", si.getFplxdm());//发票类型代码
        params.put("kplx", si.getKplx());//开票类型
        params.put("yhlx", si.getYhlx());//用户类型
        params.put("ghdwsbh", si.getGhdwsbh());//购货单位识别号
        params.put("ghdwmc", si.getGhdwmc());//购货单位名称
        params.put("ghdwdzdh", si.getGhdwdzdh());//购货单位地址电话
        params.put("ghdwyhzh", si.getGhdwyhzh());//购货单位银行帐号
        params.put("bz", si.getBz());//备注
        params.put("skr", si.getSkr());//收款人
        params.put("fhr", si.getFhr());//复核人
        params.put("kpr", si.getKpr());//开票人
        params.put("yfpdm", si.getYfpdm() == null ? "" : si.getYfpdm());//原发票代码
        params.put("yfphm", si.getYfphm() == null ? "" : si.getYfphm());//原发票号码
        params.put("sprsjh", si.getSprsjh());//收票人手机号
        Map<String, Object> fyxmParams = Maps.newHashMap();
        List<Map<String, Object>> listParams = Lists.newArrayList();
        List<SysIssueInvoiceDetails> sdList = si.getFyxm();
        for (SysIssueInvoiceDetails sd : sdList) {
            fyxmParams.put("fphxz", sd.getFphxz());
            fyxmParams.put("spmc", sd.getSpmc());
            fyxmParams.put("ggxh", sd.getGgxh());//
            fyxmParams.put("dw", sd.getDw());
            fyxmParams.put("spsl", sd.getSpsl());//商品数量
            fyxmParams.put("dj", sd.getDj());//单价
            fyxmParams.put("je", sd.getJe());//金额
            fyxmParams.put("sl", sd.getSl());//税率
            fyxmParams.put("se", sd.getSe());//税额
            fyxmParams.put("spbm", sd.getSpbm());//商品编码
            fyxmParams.put("zxbm", sd.getZxbm());//自行编码
            fyxmParams.put("yhzcbs", sd.getYhzcbs());//优惠政策标识
            fyxmParams.put("lslbs", sd.getLslbs());//税率标识
            fyxmParams.put("zzstsgl", sd.getZzstsgl());//增值税特殊管理
            listParams.add(fyxmParams);
        }
        params.put("fyxm", listParams);
        String result = communalService.reqIntputUrl(SzBusinessConstants.FPKJ, params,url+businessUrl,1);
        return result;
    }


    /***
     *@Description //增值税普通卷式发票
     *@Params [param]
     *@Return java.lang.String
     *@Author panyong
     *@Date 2020/6/2 16:34
    */
    public String issueVATGeneralJsInvoice(Map<String,Object> param){
        param.put("fpqqlsh","");//发票请求流水号
        param.put("shnsrsbh","");//纳税人识别号
        param.put("jsbh","");//机身编号
        param.put("fplxdm","");//发票类型代码
        param.put("kplx","");//开票类型
        param.put("yhlx","");//用户类型
        param.put("ghdwsbh","");//购货单位识别号
        param.put("ghdwmc","");//购货单位名称
        param.put("bz","");//备注
        param.put("skr","");//收款人
        param.put("fhr","");//复核人
        param.put("kpr","");//开票人
        param.put("yfpdm","");//原发票代码
        param.put("yfphm","");//原发票号码
        param.put("sprsjh","");//收票人手机号
        param.put("notify_url","");//通知地址
        Map<String,Object> fyxmParams =Maps.newHashMap();
        List<Map<String,Object>> listParams = Lists.newArrayList();
        fyxmParams.put("fphxz","");//发票行性质
        fyxmParams.put("spmc","");//商品名称
        fyxmParams.put("spsm","");//商品税目
        fyxmParams.put("ggxh","");//规格型号
        fyxmParams.put("dw","");//单位
        fyxmParams.put("spsl","");//商品数量
        fyxmParams.put("dj","");//单价
        fyxmParams.put("je","");//金额
        fyxmParams.put("sl","");//税率
        fyxmParams.put("se","");//税额
        fyxmParams.put("spbm","");//商品编码
        fyxmParams.put("zxbm","");//自行编码
        fyxmParams.put("yhzcbs","");//优惠政策标识
        fyxmParams.put("lslbs","0");//税率标识
        fyxmParams.put("zzstsgl","");//增值税特殊管理
        listParams.add(fyxmParams);
        param.put("fyxm",listParams);
        return WebServiceUtil.reqIntputUrl(SzBusinessConstants.FPKJ,param);
    }
    /***
     *@Description // 增值税普通发票
     *@Params [params]
     *@Return java.lang.String
     *@Author panyong
     *@Date 2020/6/2 16:03
     */
    public String issueVATGeneralInvoice(Map<String,Object> param){
        param.put("fpqqlsh","");//发票请求流水号
        param.put("shnsrsbh","");//纳税人识别号
        param.put("jsbh","");//机身编号
        param.put("fplxdm","");//发票类型代码
        param.put("kplx","");//开票类型
        param.put("yhlx","");//用户类型
        param.put("ghdwsbh","");//购货单位识别号
        param.put("ghdwmc","");//购货单位名称
        param.put("ghdwdzdh","");//购货单位地址电话
        param.put("ghdwyhzh","");//购货单位银行帐号
        param.put("qdbz","");//清单标志
        param.put("bz","");//备注
        param.put("skr","");//收款人
        param.put("fhr","");//复核人
        param.put("kpr","");//开票人
        param.put("yfpdm","");//原发票代码
        param.put("yfphm","");//原发票号码
        param.put("sprsjh","");//收票人手机号
        param.put("notify_url","");//通知地址
        Map<String,Object> fyxmParams =Maps.newHashMap();
        List<Map<String,Object>> listParams = Lists.newArrayList();
        fyxmParams.put("fphxz","");//发票行性质
        fyxmParams.put("spmc","");//商品名称
        fyxmParams.put("spsm","");//商品税目
        fyxmParams.put("ggxh","");//规格型号
        fyxmParams.put("dw","");//单位
        fyxmParams.put("spsl","");//商品数量
        fyxmParams.put("dj","");//单价
        fyxmParams.put("je","");//金额
        fyxmParams.put("sl","");//税率
        fyxmParams.put("se","");//税额
        fyxmParams.put("spbm","");//商品编码
        fyxmParams.put("zxbm","");//自行编码
        fyxmParams.put("yhzcbs","");//优惠政策标识
        fyxmParams.put("lslbs","0");//税率标识
        fyxmParams.put("zzstsgl","");//增值税特殊管理
        listParams.add(fyxmParams);
        param.put("fyxm",listParams);
        return WebServiceUtil.reqIntputUrl(SzBusinessConstants.FPKJ,param);
    }

    /***
     *@Description // 增值税专用发票
     *@Params [params]
     *@Return java.lang.String
     *@Author panyong
     *@Date 2020/6/2 16:03
     */
    public String issueVATDedicatedInvoice(Map<String,Object> param){
        param.put("fpqqlsh","");//发票请求流水号
        param.put("shnsrsbh","");//纳税人识别号
        param.put("jsbh","");//机身编号
        param.put("fplxdm","");//发票类型代码
        param.put("kplx","");//开票类型
        param.put("yhlx","");//用户类型
        param.put("ghdwsbh","");//购货单位识别号
        param.put("ghdwmc","");//购货单位名称
        param.put("ghdwdzdh","");//购货单位地址电话
        param.put("ghdwyhzh","");//购货单位银行帐号
        param.put("qdbz","");//清单标志
        param.put("bz","");//备注
        param.put("skr","");//收款人
        param.put("fhr","");//复核人
        param.put("kpr","");//开票人
        param.put("tzdbh","");//通知单编号
        param.put("yfpdm","");//原发票代码
        param.put("yfphm","");//原发票号码
        param.put("sprsjh","");//收票人手机号
        param.put("notify_url","");//通知地址
        Map<String,Object> fyxmParams =Maps.newHashMap();
        List<Map<String,Object>> listParams = Lists.newArrayList();
        fyxmParams.put("fphxz","");//发票行性质
        fyxmParams.put("spmc","");//商品名称
        fyxmParams.put("spsm","");//商品税目
        fyxmParams.put("ggxh","");//规格型号
        fyxmParams.put("dw","");//单位
        fyxmParams.put("spsl","");//商品数量
        fyxmParams.put("dj","");//单价
        fyxmParams.put("je","");//金额
        fyxmParams.put("sl","");//税率
        fyxmParams.put("se","");//税额
        fyxmParams.put("spbm","");//商品编码
        fyxmParams.put("zxbm","");//自行编码
        fyxmParams.put("yhzcbs","");//优惠政策标识
        fyxmParams.put("lslbs","0");//税率标识
        fyxmParams.put("zzstsgl","");//增值税特殊管理
        listParams.add(fyxmParams);
        param.put("fyxm",listParams);
        return WebServiceUtil.reqIntputUrl(SzBusinessConstants.FPKJ,param);
    }

    /***
     *@Description // 自助增值税发票
     *@Params [params]
     *@Return java.lang.String
     *@Author panyong
     *@Date 2020/6/2 16:03
     */
    public String issueVATSelfInvoice(Map<String,Object> param){
        param.put("fpqqlsh","");//发票请求流水号
        param.put("order_type","");//收单类型
        param.put("order_no","");//收单流水号
        param.put("shnsrsbh","");//纳税人识别号
        param.put("jsbh","");//机身编号
        param.put("fplxdm","");//发票类型代码
        param.put("kplx","");//开票类型
        param.put("yhlx","");//用户类型
        param.put("ghdwsbh","");//购货单位识别号
        param.put("ghdwmc","");//购货单位名称
        param.put("ghdwdzdh","");//购货单位地址电话
        param.put("ghdwyhzh","");//购货单位银行帐号
        param.put("bz","");//备注
        param.put("skr","");//收款人
        param.put("fhr","");//复核人
        param.put("kpr","");//开票人
        param.put("tzdbh","");//通知单编号
        param.put("yfpdm","");//原发票代码
        param.put("yfphm","");//原发票号码
        param.put("sprsjh","");//收票人手机号
        param.put("notify_url","");//通知地址
        Map<String,Object> fyxmParams =Maps.newHashMap();
        List<Map<String,Object>> listParams = Lists.newArrayList();
        fyxmParams.put("fphxz","");//发票行性质
        fyxmParams.put("spsm","");//商品税目
        fyxmParams.put("ggxh","");//规格型号
        fyxmParams.put("dw","");//单位
        fyxmParams.put("spsl","");//商品数量
        fyxmParams.put("dj","");//单价
        fyxmParams.put("je","");//金额
        fyxmParams.put("sl","");//税率
        fyxmParams.put("se","");//税额
        fyxmParams.put("spbm","");//商品编码
        fyxmParams.put("zxbm","");//自行编码
        fyxmParams.put("yhzcbs","");//优惠政策标识
        fyxmParams.put("lslbs","0");//税率标识
        fyxmParams.put("zzstsgl","");//增值税特殊管理
        listParams.add(fyxmParams);
        param.put("fyxm",listParams);
        return WebServiceUtil.reqIntputUrl(SzBusinessConstants.FPKJ,param);
    }


    /**
     * 增值税普通电子发票
     * @return
     */
    public String issueVATGeneralDzInvoice2(){
       // List<SysIssueInvoicePush> siList=szIssueInvoicePushMapper.selectByStatus(0);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("fpqqlsh","12345678992");//发票请求流水号
        params.put("shnsrsbh", "110101201707010057");//纳税人识别号
        params.put("jsbh", "110101201707010057~~499000152456");//机身编号
        params.put("fplxdm", "026");//发票类型代码
        params.put("kplx", "0");//开票类型
        params.put("yhlx", "0");//用户类型
        params.put("ghdwsbh", "110101201707010057");//购货单位识别号
        params.put("ghdwmc", "成都市成华区库夏默默鞋帽经营部");//购货单位名称
        params.put("ghdwdzdh", "成都市成华区库夏默默鞋帽经营部18059053999");//购货单位地址电话
        params.put("ghdwyhzh", "");//购货单位银行帐号
        params.put("bz","测试");//备注
        params.put("skr", "xxx");//收款人
        params.put("fhr", "xxx");//复核人
        params.put("kpr", "xxx");//开票人
        params.put("yfpdm", "");//原发票代码
        params.put("yfphm", "");//原发票号码
        params.put("sprsjh", "18059053999");//收票人手机号
        Map<String, Object> fyxmParams = Maps.newHashMap();
        List<Map<String, Object>> listParams = Lists.newArrayList();
        fyxmParams.put("fphxz", "0");
        fyxmParams.put("spmc", "马铃薯");
        fyxmParams.put("ggxh", "阿比");//
        fyxmParams.put("dw", "");
        fyxmParams.put("spsl", "1");//商品数量
        fyxmParams.put("dj", "2.9");//单价
        fyxmParams.put("je", "2.90");//金额
        fyxmParams.put("sl", "0");//税率
        fyxmParams.put("se", "0");//税额
        fyxmParams.put("spbm", "1010102010000000000");//商品编码
        fyxmParams.put("zxbm", "01");//自行编码
        fyxmParams.put("yhzcbs", "1");//优惠政策标识
        fyxmParams.put("lslbs", "");//税率标识
        fyxmParams.put("zzstsgl", "免税");//增值税特殊管理
        listParams.add(fyxmParams);
        params.put("fyxm", listParams);

        return "ok";
    }
    public static void main(String [] args){
        InvoiceAPIService invoiceAPIService =  new InvoiceAPIService();
        //invoiceAPIService.InvoiceIssuedNotice("FPTZ");
        //String result=invoiceAPIService.issueVATGeneralDzInvoice();
       // String result=invoiceAPIService.getInvoice("FPCX");
        String result= invoiceAPIService.removeInvoice("FPZF");
        //String result=invoiceAPIService.issueVATGeneralDzInvoice2();
        System.out.println("result-->");
    }

}
