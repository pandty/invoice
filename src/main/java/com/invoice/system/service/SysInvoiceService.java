package com.invoice.system.service;

import com.invoice.system.domain.szmodel.SysInvoice;

import java.util.List;

/**
 * @Desoription:发票业务接口
 * @Auther:panyong
 * @create:2020/6/17 15:10
 */
public interface SysInvoiceService {
    //插入
    void insert(SysInvoice sysInvoice);
    //根据发票流水号查询
    SysInvoice selectByFpqqlsh(String fpqqlsh);
    /**
     * 根据参数查询发票
     * @author lijialin
     * @date 2020/6/19 15:59
     * @param sysInvoice
     * @return java.util.List<com.invoice.system.domain.szmodel.SysInvoice>
     */
    List<SysInvoice> selInvoiceInfosByParams(SysInvoice sysInvoice);

    //发票查询
    SysInvoice getInvoice(String jsbh, String fplxdm, String cxfs, String cxtj, StringBuilder message);
    //如果发票流水号存在则修改，不存在则插入
    public void insertOrUp(SysInvoice sysInvoice);

    //跑发票查询任务
    void getInvoiceRun();
}
