package com.invoice.system.domain.szmodel;

import com.invoice.common.annotation.Excel;

import java.io.Serializable;
import java.util.Date;

/***
 *@Description //商品列表
 *@Author panyong
 *@Date 2020/6/16 16:37
*/
public class SysCommodityCode implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;

    /**
     * 机身编号
     */
    @Excel(name = "机身编号", cellType = Excel.ColumnType.STRING)
    private String jsbh;

    /**
     * 纳税人识别号
     */
    @Excel(name = "纳税人识别号", cellType = Excel.ColumnType.STRING)
    private String nsrsbh;

    /**
     * 编码
     */
    @Excel(name = "商品编码", cellType = Excel.ColumnType.STRING)
    private String bm;

    /**
     * 商品名称
     */
    @Excel(name = "商品名称", cellType = Excel.ColumnType.STRING)
    private String spmc;
    /**
     * 商品编码
     */
    @Excel(name = "商品编码", cellType = Excel.ColumnType.STRING)
    private String spbm;

    /**
     * 商品编码简称
     */
    @Excel(name = "商品编码简称", cellType = Excel.ColumnType.STRING)
    private String spbmjc;

    /**
     * 规格型号
     */
    private String ggxh;

    /**
     * 税率
     */
    private String sl;

    /**
     * 单价
     */
    private String dj;

    /**
     * 可用税率
     */
    private String kysl;

    /**
     * 含税标志
     */
    private String hsbz;

    /**
     * 零税率标识
     */
    private String lslbs;

    /**
     * 说明
     */
    private String sm;

    /**
     * 增值税特殊管理
     */
    private String zzstsgl;

    /**
     * 优惠政策标识
     */
    private String yhzcbs;

    /**
     * 海关品目
     */
    private String hgjcksppm;

    /**
     * 商品编码的上级节点
     */

    private String pid;

    /**
     * 计量单位
     */
    private String jldw;

    private String zxbm;

    private String mrz;
    //插入时间
    private Date insertTime;
    public Date getInsertTime() {
        return insertTime;
    }


    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    public String getSpbm() {
        return spbm;
    }

    public void setSpbm(String spbm) {
        this.spbm = spbm;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getDj() {
        return dj;
    }

    public void setDj(String dj) {
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

    public String getLslbs() {
        return lslbs;
    }

    public void setLslbs(String lslbs) {
        this.lslbs = lslbs;
    }

    public String getSm() {
        return sm;
    }

    public void setSm(String sm) {
        this.sm = sm;
    }

    public String getZzstsgl() {
        return zzstsgl;
    }

    public void setZzstsgl(String zzstsgl) {
        this.zzstsgl = zzstsgl;
    }

    public String getYhzcbs() {
        return yhzcbs;
    }

    public void setYhzcbs(String yhzcbs) {
        this.yhzcbs = yhzcbs;
    }

    public String getHgjcksppm() {
        return hgjcksppm;
    }

    public void setHgjcksppm(String hgjcksppm) {
        this.hgjcksppm = hgjcksppm;
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

    public String getZxbm() {
        return zxbm;
    }

    public void setZxbm(String zxbm) {
        this.zxbm = zxbm;
    }

    public String getMrz() {
        return mrz;
    }

    public void setMrz(String mrz) {
        this.mrz = mrz;
    }
}