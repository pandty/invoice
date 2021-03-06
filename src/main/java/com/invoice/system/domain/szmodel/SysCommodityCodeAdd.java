package com.invoice.system.domain.szmodel;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

public class SysCommodityCodeAdd implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 机身编号
     */
    private String jsbh;

    /**
     * 纳税人识别号
     */
    private String nsrsbh;

    /**
     * 商品编码
     */
    private String bm;

    /**
     * 商品名称
     */
    private String spmc;

    /**
     * 商品编码
     */
    private String spbm;

    /**
     * 商品编码简称
     */
    private String spbmjc;

    /**
     * 规格型号
     */
    private String ggxh;

    /**
     * 税率
     */
    @NotBlank(message="税率不能为空")
    private String sl;

    /**
     * 单价
     */
    private Double dj;

    /**
     * 可用税率
     */
    @NotBlank(message="可用税率不能为空")
    private String kysl;

    /**
     * 含税标志
     */
    private String hsbz;

    /**
     * 商品编码的上级节点
     */
    @NotBlank(message="父id不能为空")
    private String pid;

    /**
     * 计量单位
     */
    private String jldw;

    /**
     * 商品编码名称
     */
    @NotBlank(message="商品编码名称不能为空")
    private String mc;

    /**
     * 可用状态
     */
    @NotBlank(message="可用状态不能为空")
    private String kyzt;

    /**
     * 国民统计代码
     */
    private String gmtjdm;

    /**
     * 海关进出口商品品目
     */
    private String hgpm;

    /**
     * 用户选择的免税类型
     */
    private String mslx;

    /**
     * 用户选择的优惠类型
     */
    private String yhlx;

    /**
     * 消费税管理
     */
    private String xfsgl;

    /**
     * 消费税政策依据
     */
    private String xfszcyj;

    /**
     * 消费税特殊内容代码
     */
    private String xfstsgldm;

    /**
     * 关键字
     */
    private String gjz;

    /**
     * 编码版本号
     */
    private String bb;

    /**
     * 过渡期截止时间
     */
    private String gdqjzsj;

    /**
     * 商品编码或商品编码表的启用时间
     */
    private String qysj;

    /**
     * 商品编码的入库时间
     */
    private String gxsj;

    /**
     * 增值税特殊内容代码
     */
    private String zzstsgldm;

    /**
     * 消费税政策依据
     */
    private String zzszcyj;

    /**
     * 增值税特殊管理
     */
    private String zzstsgl;

    /**
     * 插入时间
     */
    private Date insertTime;

    /**
     * 说明
     */
    private String sm;
    /**
     * 增值税税率
     */
    private String zzssl;
    /**
     * 修改时间
     */
    private String updateTime;

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getZzssl() {
        return zzssl;
    }

    public void setZzssl(String zzssl) {
        this.zzssl = zzssl;
    }

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

    public String getJsbh() {
        return jsbh;
    }

    public void setJsbh(String jsbh) {
        this.jsbh = jsbh;
    }

    public String getNsrsbh() {
        return nsrsbh;
    }

    public void setNsrsbh(String nsrsbh) {
        this.nsrsbh = nsrsbh;
    }

    public String getBm() {
        return bm;
    }

    public void setBm(String bm) {
        this.bm = bm;
    }

    public String getSpmc() {
        return spmc;
    }

    public void setSpmc(String spmc) {
        this.spmc = spmc;
    }

    public String getSpbm() {
        return spbm;
    }

    public void setSpbm(String spbm) {
        this.spbm = spbm;
    }

    public String getSpbmjc() {
        return spbmjc;
    }

    public void setSpbmjc(String spbmjc) {
        this.spbmjc = spbmjc;
    }

    public String getGgxh() {
        return ggxh;
    }

    public void setGgxh(String ggxh) {
        this.ggxh = ggxh;
    }

    public String getSl() {
        return sl;
    }

    public void setSl(String sl) {
        this.sl = sl;
    }

    public Double getDj() {
        return dj;
    }

    public void setDj(Double dj) {
        this.dj = dj;
    }

    public String getKysl() {
        return kysl;
    }

    public void setKysl(String kysl) {
        this.kysl = kysl;
    }

    public String getHsbz() {
        return hsbz;
    }

    public void setHsbz(String hsbz) {
        this.hsbz = hsbz;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getJldw() {
        return jldw;
    }

    public void setJldw(String jldw) {
        this.jldw = jldw;
    }

    public String getMc() {
        return mc;
    }

    public void setMc(String mc) {
        this.mc = mc;
    }

    public String getKyzt() {
        return kyzt;
    }

    public void setKyzt(String kyzt) {
        this.kyzt = kyzt;
    }

    public String getGmtjdm() {
        return gmtjdm;
    }

    public void setGmtjdm(String gmtjdm) {
        this.gmtjdm = gmtjdm;
    }

    public String getHgpm() {
        return hgpm;
    }

    public void setHgpm(String hgpm) {
        this.hgpm = hgpm;
    }

    public String getMslx() {
        return mslx;
    }

    public void setMslx(String mslx) {
        this.mslx = mslx;
    }

    public String getYhlx() {
        return yhlx;
    }

    public void setYhlx(String yhlx) {
        this.yhlx = yhlx;
    }

    public String getXfsgl() {
        return xfsgl;
    }

    public void setXfsgl(String xfsgl) {
        this.xfsgl = xfsgl;
    }

    public String getXfszcyj() {
        return xfszcyj;
    }

    public void setXfszcyj(String xfszcyj) {
        this.xfszcyj = xfszcyj;
    }

    public String getXfstsgldm() {
        return xfstsgldm;
    }

    public void setXfstsgldm(String xfstsgldm) {
        this.xfstsgldm = xfstsgldm;
    }

    public String getGjz() {
        return gjz;
    }

    public void setGjz(String gjz) {
        this.gjz = gjz;
    }

    public String getBb() {
        return bb;
    }

    public void setBb(String bb) {
        this.bb = bb;
    }

    public String getGdqjzsj() {
        return gdqjzsj;
    }

    public void setGdqjzsj(String gdqjzsj) {
        this.gdqjzsj = gdqjzsj;
    }

    public String getQysj() {
        return qysj;
    }

    public void setQysj(String qysj) {
        this.qysj = qysj;
    }

    public String getGxsj() {
        return gxsj;
    }

    public void setGxsj(String gxsj) {
        this.gxsj = gxsj;
    }

    public String getZzstsgldm() {
        return zzstsgldm;
    }

    public void setZzstsgldm(String zzstsgldm) {
        this.zzstsgldm = zzstsgldm;
    }

    public String getZzszcyj() {
        return zzszcyj;
    }

    public void setZzszcyj(String zzszcyj) {
        this.zzszcyj = zzszcyj;
    }

    public String getZzstsgl() {
        return zzstsgl;
    }

    public void setZzstsgl(String zzstsgl) {
        this.zzstsgl = zzstsgl;
    }

    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    public String getSm() {
        return sm;
    }

    public void setSm(String sm) {
        this.sm = sm;
    }
}