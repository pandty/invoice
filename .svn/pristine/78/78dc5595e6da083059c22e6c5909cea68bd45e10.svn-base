package com.invoice.web.controller.api;

import com.invoice.common.config.Global;
import com.invoice.common.core.controller.BaseController;
import com.invoice.common.core.domain.AjaxResult;
import com.invoice.system.domain.SysUser;
import com.invoice.system.service.SysIssueInvoicePushService;
import com.invoice.system.shuzuservice.CommodityService;
import com.invoice.system.shuzuservice.InvoiceAPIService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName ApiTestController
 * @Description TODO
 * @Author ZZJ
 * @Date 2020/5/30 9:47
 **/
@Api("测试API接口")
@RestController
@RequestMapping("/api")
public class ApiTestController extends BaseController {
    @Autowired
    private  InvoiceAPIService invoiceAPIService;
    @Autowired
    private CommodityService commodityService;
    @Autowired
    SysIssueInvoicePushService sysIssueInvoicePushService;
    @ApiOperation("测试案例")
    @GetMapping("/test")
    public AjaxResult test(){
        String testss = "Test!";
        //testss=commodityService.getCommodity();
        testss=commodityService.getSJCommodity("SSBMQQ","33.0");
        //sysIssueInvoicePushService.issueInvoicePushRun();
        //testss=invoiceAPIService.getInvoice("110101201707010057~~661526945065","026","1","123456789938");
        System.out.println(testss);
        return AjaxResult.success(testss);
    }
}
