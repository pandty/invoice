package com.invoice.system.shuzuservice;

import com.invoice.common.utils.StringUtils;
import com.invoice.system.domain.szmodel.SysInvoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class QytApiService {
    @Autowired
    private CommunalService communalService;
    //调用数族发票请求地址
    private static final String  qytUrl = "http://api.crm.hky.qyt.com/";

    /**
     * 推送发票到qyt
     * @param sysInvoice
     */
    public void pushInvoice(SysInvoice sysInvoice) {
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("orderNo", StringUtils.valueOf(sysInvoice.getInvoiceNumber()) );// qyt发票订单号
        paramMap.put("billUrl", StringUtils.valueOf(sysInvoice.getUrl()));// 下载地址
        paramMap.put("status", "1");// 代理商ID
        paramMap.put("errMsg", "");
        String url=qytUrl+"time_task/bill_receive";
        communalService.qytPostRequest(paramMap,url,100);
    }
    /***
     *@Description //失败通知qyt
     *@Params [orderNo]
     *@Return void
     *@Author panyong
     *@Date 2020-07-02 15:03
    */
    public void pushInvoice(String orderNo,String errMsg) {
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("orderNo", orderNo);// qyt发票订单号
        paramMap.put("billUrl", "");// 下载地址
        paramMap.put("status", "4");// 代理商ID
        paramMap.put("errMsg", errMsg);
        String url=qytUrl+"time_task/bill_receive";
        communalService.qytPostRequest(paramMap,url,100);
    }
}
