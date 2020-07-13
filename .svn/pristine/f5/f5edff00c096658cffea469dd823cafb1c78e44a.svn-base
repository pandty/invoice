package com.invoice.system.service.impl;

import com.invoice.system.domain.szmodel.SysIssueInvoiceDetails;
import com.invoice.system.mapper.SysIssueInvoiceDetailsMapper;
import com.invoice.system.service.SysIssueInvoiceDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Desoription:发票开具清单明细业务接口实现类
 * @Auther:panyong
 * @create:2020/6/12 19:17
 */
@Service
public class SysIssueInvoiceDetailsServiceImpl implements SysIssueInvoiceDetailsService {
    @Autowired
    private SysIssueInvoiceDetailsMapper szIssueInvoiceDetailsMapper;
    /***
     *@Description //插入发票开具清单明细
     *@Params [szIssueInvoiceDetails]
     *@Return void
     *@Author panyong
     *@Date 2020/6/15 15:42
    */
    @Override
    public void insert(SysIssueInvoiceDetails szIssueInvoiceDetails) {
        szIssueInvoiceDetailsMapper.insert(szIssueInvoiceDetails);
    }

    /**
     * 查询发票明细
     * @author lijialin
     * @date 2020/6/19 17:16
     * @param szIssueInvoiceDetails
     */
    @Override
    public List<SysIssueInvoiceDetails> selInvoiceDetailInfosByParams(SysIssueInvoiceDetails szIssueInvoiceDetails) {
       return szIssueInvoiceDetailsMapper.selInvoiceDetailInfosByParams(szIssueInvoiceDetails);
    }
}
