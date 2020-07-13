package com.invoice.web.controller.shuzu;

import com.invoice.common.core.controller.BaseController;
import com.invoice.common.core.page.TableDataInfo;
import com.invoice.system.domain.szmodel.SysInvoice;
import com.invoice.system.domain.szmodel.SysIssueInvoiceDetails;
import com.invoice.system.service.SysInvoiceService;
import com.invoice.system.service.SysIssueInvoiceDetailsService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author lijialin
 * @description 发票管理
 * @date 2020/6/19
 */
@Controller
@RequestMapping("/shuzu/invoice")
public class InvoiceController  extends BaseController {

    private String prefix = "shuzu/invoice";

    @Autowired
    private SysInvoiceService sysInvoiceService;

    @Autowired
    private SysIssueInvoiceDetailsService sysIssueInvoiceDetailsService;

    /**
     * 页面跳转
     * @author lijialin
     * @date 2020/6/19 15:54
     */
    @RequiresPermissions("shuzu:invoice:view")
    @GetMapping()
    public String dictType() {
        return prefix + "/invoice";
    }

    /**
     * 查询发票信息
     * @author lijialin
     * @date 2020/6/19 9:21
     */
    @RequiresPermissions("shuzu:invoice:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysInvoice sysInvoice){
        startPage();
        List<SysInvoice> list = sysInvoiceService.selInvoiceInfosByParams(sysInvoice);
        return getDataTable(list);
    }


    /**
     * 查询发票商品明细
     */
    @RequiresPermissions("shuzu:invoice:detail:view")
    @GetMapping("/detail/{fpqqlsh}")
    public String detail(@PathVariable("fpqqlsh") String fpqqlsh, ModelMap mmap) {
        mmap.put("fpqqlsh", fpqqlsh);
        return prefix + "/detail";
    }

    /**
     * 查询发票商品明细
     * @author lijialin
     * @date 2020/6/19 9:21
     */
    @RequiresPermissions("shuzu:invoice:detail:list")
    @PostMapping("/detail/list")
    @ResponseBody
    public TableDataInfo detailList(SysIssueInvoiceDetails sysIssueInvoiceDetails){
        startPage();
        List<SysIssueInvoiceDetails> list = sysIssueInvoiceDetailsService.selInvoiceDetailInfosByParams(sysIssueInvoiceDetails);
        return getDataTable(list);
    }



}
