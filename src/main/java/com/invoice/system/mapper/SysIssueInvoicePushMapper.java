package com.invoice.system.mapper;

import com.invoice.system.domain.szmodel.SysIssueInvoicePush;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
/***
 *@Description //发票开具推送主表数据层
 *@Params
 *@Return
 *@Author panyong
 *@Date 2020/6/24 9:27
*/
@Repository
public interface SysIssueInvoicePushMapper {

    public int insert(SysIssueInvoicePush sysIssueInvoicePush);
    public int insertSelective(SysIssueInvoicePush sysIssueInvoicePush);
    public List<SysIssueInvoicePush> selectList(SysIssueInvoicePush sysIssueInvoicePush);

    public SysIssueInvoicePush selectByFpqqlsh(String fpqqlsh);

    public List<SysIssueInvoicePush> selectByStatus(Integer status);

    int update(SysIssueInvoicePush sysIssueInvoicePush);
    int updateStatus(SysIssueInvoicePush sysIssueInvoicePush);
    //根据发票编号查询
    SysIssueInvoicePush selectByInvoiceNumber(String invoiceNumber);
}