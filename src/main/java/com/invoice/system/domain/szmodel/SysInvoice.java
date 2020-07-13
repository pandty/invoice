package com.invoice.system.domain.szmodel;

import java.io.Serializable;
import java.util.Date;
/***
 *@Description //发票实体类
 *@Author panyong
 *@Date 2020/6/17 18:27
*/
public class SysInvoice implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    /**
     * 发票编号 关联企友通发票编号
     */
    private String invoiceNumber;
    /**
     * 流水号
     */
    private String fpqqlsh;

    /**
     * 数族流水号
     */
    private String szfpqqlsh;

    /**
     * 发票代码
     */
    private String fpdm;

    /**
     * 发票号码
     */
    private String fphm;

    /**
     * 发票状态
     */
    private String fpzt;

    /**
     * 税控设备编号
     */
    private String sksbbh;

    /**
     * 开票日期
     */
    private String kprq;

    /**
     * 发票类型代码
     */
    private String fplxdm;

    /**
     * 机身编号
     */
    private String jsbh;

    /**
     * 购货单位识别号
     */
    private String ghdwsbh;

    /**
     * 纳税人识别号
     */
    private String nsrsbh;

    /**
     * 购货单位名称
     */
    private String ghdwmc;

    /**
     * 合计金额
     */
    private String hjje;

    /**
     * 合计税额
     */
    private String hjse;

    /**
     * 价税合计
     */
    private String jshj;

    /**
     * 校验码
     */
    private String jym;

    /**
     * 税控码
     */
    private String skm;
    /**
     * 查询条件
     */
    private String cxtj;

    /**
     * 电票下载地址
     */
    private String url;

    /**
     * 收票人手机号
     */
    private String sprsjh;

    /**
     * 收款人
     */
    private String skr;

    /**
     * 复核人
     */
    private String fhr;

    /**
     * 开票人
     */
    private String kpr;

    /**
     * 支付人
     */
    private String zfr;
    /**
     * 插入时间
     */
    private Date insertTime;
    /**
     *查询方式
     * 0：按发票号码查询
     * 1：按请求流水号查询
     */
    private Integer cxfs;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCxtj() {
        return cxtj;
    }

    public void setCxtj(String cxtj) {
        this.cxtj = cxtj;
    }

    public String getFpqqlsh() {
        return fpqqlsh;
    }

    public void setFpqqlsh(String fpqqlsh) {
        this.fpqqlsh = fpqqlsh;
    }

    public String getSzfpqqlsh() {
        return szfpqqlsh;
    }

    public void setSzfpqqlsh(String szfpqqlsh) {
        this.szfpqqlsh = szfpqqlsh;
    }

    public String getFpdm() {
        return fpdm;
    }

    public void setFpdm(String fpdm) {
        this.fpdm = fpdm;
    }

    public String getFphm() {
        return fphm;
    }

    public void setFphm(String fphm) {
        this.fphm = fphm;
    }

    public String getFpzt() {
        return fpzt;
    }

    public void setFpzt(String fpzt) {
        this.fpzt = fpzt;
    }

    public String getSksbbh() {
        return sksbbh;
    }

    public void setSksbbh(String sksbbh) {
        this.sksbbh = sksbbh;
    }

    public String getKprq() {
        return kprq;
    }

    public void setKprq(String kprq) {
        this.kprq = kprq;
    }

    public String getFplxdm() {
        return fplxdm;
    }

    public void setFplxdm(String fplxdm) {
        this.fplxdm = fplxdm;
    }

    public String getJsbh() {
        return jsbh;
    }

    public void setJsbh(String jsbh) {
        this.jsbh = jsbh;
    }

    public String getGhdwsbh() {
        return ghdwsbh;
    }

    public void setGhdwsbh(String ghdwsbh) {
        this.ghdwsbh = ghdwsbh;
    }

    public String getNsrsbh() {
        return nsrsbh;
    }

    public void setNsrsbh(String nsrsbh) {
        this.nsrsbh = nsrsbh;
    }

    public String getGhdwmc() {
        return ghdwmc;
    }

    public void setGhdwmc(String ghdwmc) {
        this.ghdwmc = ghdwmc;
    }

    public String getHjje() {
        return hjje;
    }

    public void setHjje(String hjje) {
        this.hjje = hjje;
    }

    public String getHjse() {
        return hjse;
    }

    public void setHjse(String hjse) {
        this.hjse = hjse;
    }

    public String getJshj() {
        return jshj;
    }

    public void setJshj(String jshj) {
        this.jshj = jshj;
    }

    public String getJym() {
        return jym;
    }

    public void setJym(String jym) {
        this.jym = jym;
    }

    public String getSkm() {
        return skm;
    }

    public void setSkm(String skm) {
        this.skm = skm;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSprsjh() {
        return sprsjh;
    }

    public void setSprsjh(String sprsjh) {
        this.sprsjh = sprsjh;
    }

    public String getSkr() {
        return skr;
    }

    public void setSkr(String skr) {
        this.skr = skr;
    }

    public String getFhr() {
        return fhr;
    }

    public void setFhr(String fhr) {
        this.fhr = fhr;
    }

    public String getKpr() {
        return kpr;
    }

    public void setKpr(String kpr) {
        this.kpr = kpr;
    }

    public String getZfr() {
        return zfr;
    }

    public void setZfr(String zfr) {
        this.zfr = zfr;
    }

    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    public Integer getCxfs() {
        return cxfs;
    }

    public void setCxfs(Integer cxfs) {
        this.cxfs = cxfs;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }
}