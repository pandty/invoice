package com.invoice.system.domain.szmodel;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
/***
 *@Description //发票开具主数据实体类
 *@Params
 *@Author panyong
 *@Date 2020/6/15 17:46
 */
public class SysIssueInvoicePush extends SignatureToken implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;


    /**
     * 状态 默认0 ，已推送成功：1 ; 执行中：2 ;推送失败：3  ; 开票成功：5 ; 开票失败4
     */
    private Integer status;

    /**
     * 发票请求流水号，数字或字母或两者的组合
     */
    @NotBlank(message = "发票请编号不能为空")
    private String invoiceNumber;

    private String fpqqlsh;

    /**
     * 销方纳税人识别号
     */
    @NotBlank(message = "销方纳税人识别号不能为空")
    private String shnsrsbh;

    /**
     * 纳税人识别号+开票终端标识(使用英文~~符号分隔)
     */
    private String jsbh;

    /**
     * 发票类型代码
     */
    @NotBlank(message = "发票类型代码不能为空")
    private String fplxdm;

    /**
     * 购货单位识别号,如果不为空必须为 15-20 位,数字或大写字母
     */
    private String ghdwsbh;

    /**
     * 开票类型:0：正数票开具 1：负数票开具
     */
    @NotNull(message = "开票类型不能为空")
    private Integer kplx;

    /**
     * 用户类型: 0：接口开票
     */
    @NotNull(message = "用户类型不能为空")
    private Integer yhlx;

    /**
     * 购货单位名称
     */
    @NotBlank(message = "购货单位名称不能为空")
    private String ghdwmc;

    /**
     * 购货单位地址电话
     */
    private String ghdwdzdh;

    /**
     * 购货单位银行帐号
     */
    private String ghdwyhzh;

    /**
     * 备注,差额征税时长度为 142
     */
    private String bz;

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
    @NotBlank(message = "开票人不能为空")
    private String kpr;

    /**
     * 原发票代码
     */
    private String yfpdm;

    /**
     * 原发票号码
     */
    private String yfphm;

    /**
     * 收票人手机号，用于版式文件的发送或微信的推送。
     */
    @NotBlank(message = "收票人手机号不能为空")
    private String sprsjh;

    /**
     * 插入时间
     */
    private Date insertTime;

    //商品明细
    @NotEmpty(message = "商品明细不能为空")
    private List<SysIssueInvoiceDetails> fyxm;

    public List<SysIssueInvoiceDetails> getFyxm() {
        return fyxm;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public void setFyxm(List<SysIssueInvoiceDetails> fyxm) {
        this.fyxm = fyxm;
    }

    /**
     * 推送时间时间
     */
    private Date pushTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getFpqqlsh() {
        return fpqqlsh;
    }

    public void setFpqqlsh(String fpqqlsh) {
        this.fpqqlsh = fpqqlsh;
    }

    public String getShnsrsbh() {
        return shnsrsbh;
    }

    public void setShnsrsbh(String shnsrsbh) {
        this.shnsrsbh = shnsrsbh;
    }

    public String getJsbh() {
        return jsbh;
    }

    public void setJsbh(String jsbh) {
        this.jsbh = jsbh;
    }

    public String getFplxdm() {
        return fplxdm;
    }

    public void setFplxdm(String fplxdm) {
        this.fplxdm = fplxdm;
    }

    public String getGhdwsbh() {
        return ghdwsbh;
    }

    public void setGhdwsbh(String ghdwsbh) {
        this.ghdwsbh = ghdwsbh;
    }

    public Integer getKplx() {
        return kplx;
    }

    public void setKplx(Integer kplx) {
        this.kplx = kplx;
    }

    public Integer getYhlx() {
        return yhlx;
    }

    public void setYhlx(Integer yhlx) {
        this.yhlx = yhlx;
    }

    public String getGhdwmc() {
        return ghdwmc;
    }

    public void setGhdwmc(String ghdwmc) {
        this.ghdwmc = ghdwmc;
    }

    public String getGhdwdzdh() {
        return ghdwdzdh;
    }

    public void setGhdwdzdh(String ghdwdzdh) {
        this.ghdwdzdh = ghdwdzdh;
    }

    public String getGhdwyhzh() {
        return ghdwyhzh;
    }

    public void setGhdwyhzh(String ghdwyhzh) {
        this.ghdwyhzh = ghdwyhzh;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
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

    public String getYfpdm() {
        return yfpdm;
    }

    public void setYfpdm(String yfpdm) {
        this.yfpdm = yfpdm;
    }

    public String getYfphm() {
        return yfphm;
    }

    public void setYfphm(String yfphm) {
        this.yfphm = yfphm;
    }

    public String getSprsjh() {
        return sprsjh;
    }

    public void setSprsjh(String sprsjh) {
        this.sprsjh = sprsjh;
    }

    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    public Date getPushTime() {
        return pushTime;
    }

    public void setPushTime(Date pushTime) {
        this.pushTime = pushTime;
    }

    @Override
    public String toString() {
        return "SzIssueInvoicePush{" +
                "id=" + id +
                ", status=" + status +
                ", fpqqlsh='" + fpqqlsh + '\'' +
                ", shnsrsbh='" + shnsrsbh + '\'' +
                ", jsbh='" + jsbh + '\'' +
                ", fplxdm='" + fplxdm + '\'' +
                ", ghdwsbh='" + ghdwsbh + '\'' +
                ", kplx=" + kplx +
                ", yhlx=" + yhlx +
                ", ghdwmc='" + ghdwmc + '\'' +
                ", ghdwdzdh='" + ghdwdzdh + '\'' +
                ", ghdwyhzh='" + ghdwyhzh + '\'' +
                ", bz='" + bz + '\'' +
                ", skr='" + skr + '\'' +
                ", fhr='" + fhr + '\'' +
                ", kpr='" + kpr + '\'' +
                ", yfpdm='" + yfpdm + '\'' +
                ", yfphm='" + yfphm + '\'' +
                ", sprsjh='" + sprsjh + '\'' +
                ", insertTime=" + insertTime +
                ", fyxm=" + fyxm +
                ", pushTime=" + pushTime +
                '}';
    }
}