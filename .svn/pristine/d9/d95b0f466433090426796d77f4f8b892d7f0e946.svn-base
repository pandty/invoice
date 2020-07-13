package com.invoice.system.mapper;

import com.invoice.system.domain.szmodel.SysIssueInvoiceDetails;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysIssueInvoiceDetailsMapper {
    //插入明细
    public int insert(SysIssueInvoiceDetails szIssueInvoiceDetails);


    public List<SysIssueInvoiceDetails> selectList(SysIssueInvoiceDetails szIssueInvoiceDetails);

    public SysIssueInvoiceDetails selectById(Integer id);

    public void insertList(List<SysIssueInvoiceDetails> fyxm);

    public List<SysIssueInvoiceDetails> selectByFpqqlsh(String fpqqlsh);

    /**
     * 查询发票明细
     * @author lijialin
     * @date 2020/6/19 17:16
     * @param szIssueInvoiceDetails
     */
    public List<SysIssueInvoiceDetails> selInvoiceDetailInfosByParams(SysIssueInvoiceDetails szIssueInvoiceDetails);
}