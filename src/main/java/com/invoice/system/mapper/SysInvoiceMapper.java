package com.invoice.system.mapper;

import com.invoice.system.domain.szmodel.SysInvoice;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Desoription:
 * @Auther:panyong
 * @create:2020/6/17 15:24
 */
@Repository
public interface SysInvoiceMapper {
    void insert(SysInvoice sysInvoice);
    //根据流水号查询
    SysInvoice selectByFpqqlsh(String fpqqlsh);

    /**
     * 根据参数查询发票
     * @author lijialin
     * @date 2020/6/19 15:59
     * @param sysInvoice
     */
    public List<SysInvoice> selInvoiceInfosByParams(SysInvoice sysInvoice);
    //
    SysInvoice selectByFphm(String fphm);

    void updataByFpqqlsh(SysInvoice sysInvoice);
}
