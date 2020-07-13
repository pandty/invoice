package com.invoice.quartz.task;

import com.invoice.system.service.SysInvoiceService;
import com.invoice.system.service.SysIssueInvoicePushService;
import com.invoice.system.service.SysPushInfoService;
import com.invoice.system.shuzuservice.InvoiceAPIService;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * @Desoription:发票定时任务
 * @Auther:panyong
 * @create:2020/6/23 15:34
 */
@Component("invoiceTask")
public class invoiceTask {
    @Autowired
    private InvoiceAPIService invoiceAPIService;
    @Autowired
    private SysInvoiceService sysInvoiceService;
    @Autowired
    private SysIssueInvoicePushService sysIssueInvoicePushService;
    @Autowired
    private SysPushInfoService sysPushInfoService;
    Logger logger = Logger.getLogger(ClientInfoTask.class);

    /***
     *@Description //定时跑发票任务
     *@Params []
     *@Return void
     *@Author panyong
     *@Date 2020/6/23 18:56
    */
    public void issueInvoicePushRun(){
        logger.info("启动跑发票任务");
        sysIssueInvoicePushService.issueInvoicePushRun();
    }
    /***
     *@Description //跑发票查询任务
     *@Params []
     *@Return void
     *@Author panyong
     *@Date 2020/6/23 18:56
     */
    public void getInvoiceRun(){
        logger.info("启动跑发票查询任务");
        sysInvoiceService.getInvoiceRun();
    }
    /***
     *@Description //启动跑日志中调用接口推送任务
     *@Params []
     *@Return void
     *@Author panyong
     *@Date 2020-07-01 10:47
    */
    public void pushNomalInfoTask(){
        logger.info("启动跑日志中调用接口推送任务");
        sysPushInfoService.pushNomalInfoTask();
    }
}
