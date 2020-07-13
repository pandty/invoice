package com.invoice.system.domain.szmodel;

import java.io.Serializable;

/***
 *@Description //商品编码实体类
 *@Author panyong
 *@Date 2020/6/18 9:24
*/
public class SysCommodity implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;

    /**
     * 版本号
     */
    private String bbh;

    private String business;

    /**
     * 启用时间
     */
    private String qysj;

    /**
     * 过渡期截止时间
     */
    private String gdqjzsj;

    /**
     * 版本
     */
    private String bb;

    /**
     * 商品编码
     */
    private String spbm;

    /**
     * 商品名称
     */
    private String spmc;

    /**
     * 货物
     */
    private String spbmjc;

    /**
     * 说明
     */
    private String sm;

    /**
     * 增值税税率
     */
    private String zzssl;

    /**
     * 关键字
     */
    private String gjz;

    /**
     * 汇总项
     */
    private String hzx;

    /**
     * 可用状态
     */
    private String kyzt;

    /**
     * 增值税特殊管理
     */
    private String zzstsgl;

    /**
     * 增值税政策依据
     */
    private String zzszcyj;

    /**
     * 增值税特殊管理代码
     */
    private String zzstsnrdn;

    /**
     * 消费税特殊管理
     */
    private String xfsgl;

    /**
     * 消费税政策依据
     */
    private String xfszcyj;

    /**
     * 消费税政策依据代码
     */
    private String xfstsnrdm;

    /**
     * 商品编码的上级节点
     */
    private String pid;

    /**
     * 更新时间
     */
    private String gxsj;

    private String tjjbm;

    private String hgjcksppm;

    public String getHgjcksppm() {
        return hgjcksppm;
    }

    public void setHgjcksppm(String hgjcksppm) {
        this.hgjcksppm = hgjcksppm;
    }

    public String getTjjbm() {
        return tjjbm;
    }

    public void setTjjbm(String tjjbm) {
        this.tjjbm = tjjbm;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBbh() {
        return bbh;
    }

    public void setBbh(String bbh) {
        this.bbh = bbh;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public String getQysj() {
        return qysj;
    }

    public void setQysj(String qysj) {
        this.qysj = qysj;
    }

    public String getGdqjzsj() {
        return gdqjzsj;
    }

    public void setGdqjzsj(String gdqjzsj) {
        this.gdqjzsj = gdqjzsj;
    }

    public String getBb() {
        return bb;
    }

    public void setBb(String bb) {
        this.bb = bb;
    }

    public String getSpbm() {
        return spbm;
    }

    public void setSpbm(String spbm) {
        this.spbm = spbm;
    }

    public String getSpmc() {
        return spmc;
    }

    public void setSpmc(String spmc) {
        this.spmc = spmc;
    }

    public String getSpbmjc() {
        return spbmjc;
    }

    public void setSpbmjc(String spbmjc) {
        this.spbmjc = spbmjc;
    }

    public String getSm() {
        return sm;
    }

    public void setSm(String sm) {
        this.sm = sm;
    }

    public String getZzssl() {
        return zzssl;
    }

    public void setZzssl(String zzssl) {
        this.zzssl = zzssl;
    }

    public String getGjz() {
        return gjz;
    }

    public void setGjz(String gjz) {
        this.gjz = gjz;
    }

    public String getHzx() {
        return hzx;
    }

    public void setHzx(String hzx) {
        this.hzx = hzx;
    }

    public String getKyzt() {
        return kyzt;
    }

    public void setKyzt(String kyzt) {
        this.kyzt = kyzt;
    }

    public String getZzstsgl() {
        return zzstsgl;
    }

    public void setZzstsgl(String zzstsgl) {
        this.zzstsgl = zzstsgl;
    }

    public String getZzszcyj() {
        return zzszcyj;
    }

    public void setZzszcyj(String zzszcyj) {
        this.zzszcyj = zzszcyj;
    }

    public String getZzstsnrdn() {
        return zzstsnrdn;
    }

    public void setZzstsnrdn(String zzstsnrdn) {
        this.zzstsnrdn = zzstsnrdn;
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

    public String getXfstsnrdm() {
        return xfstsnrdm;
    }

    public void setXfstsnrdm(String xfstsnrdm) {
        this.xfstsnrdm = xfstsnrdm;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getGxsj() {
        return gxsj;
    }

    public void setGxsj(String gxsj) {
        this.gxsj = gxsj;
    }
}