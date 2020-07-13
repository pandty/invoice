package com.invoice.system.service;

import com.invoice.system.domain.szmodel.SysIssueInvoiceDetails;

import java.util.List;

/**
 * @Desoription:发票开具清单明细业务接口
 * @Auther:panyong
 * @create:2020/6/12 17:46
 */
public interface SysIssueInvoiceDetailsService {
    //插入发票开具清单明细
    public void insert(SysIssueInvoiceDetails szIssueInvoiceDetails);

    /**
     * 查询发票明细
     * @author lijialin
     * @date 2020/6/19 17:16
     * @param szIssueInvoiceDetails
     */
    List<SysIssueInvoiceDetails> selInvoiceDetailInfosByParams(SysIssueInvoiceDetails szIssueInvoiceDetails);

}
