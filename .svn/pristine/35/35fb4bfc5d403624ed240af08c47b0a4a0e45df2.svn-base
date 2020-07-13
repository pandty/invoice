package com.invoice.system.domain.szmodel;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
/***
 *@Description //发票开具明细实体类
 *@Params
 *@Return
 *@Author panyong
 *@Date 2020/6/15 17:45
*/
public class SysIssueInvoiceDetails extends SignatureToken implements Serializable{

    private Integer id;

    /**
     * 发票请求流水号，数字或字母或两者的组合
     */
    private String fpqqlsh;

    /**
     * 发票行性质:0 正常行 , 1 折扣行, 2 被折扣行
     */
    private Integer fphxz;

    /**
     * 商品名称,如果为折扣行，商品名称须
     */
    @NotBlank(message = "商户名称不能为空")
    private String spmc;

    /**
     * 规格型号
     */
    private String ggxh;

    /**
     * 单位
     */
    private String dw;

    /**
     * 商品数量,校验:商品数量*单价=金额
     */
    @NotNull(message = "金额不能为空")
    private Double spsl;

    /**
     * 单价,误差正负 0.01（不含税）
     */
    private Double dj;

    /**
     * 金额,小数点后 2 位（不含税）
     */
    @NotNull(message = "金额不能为空")
    private Double je;

    /**
     * 税率,小数点最多 3 位
     */
    @NotNull(message = "税率不能为空")
    private Double sl;

    /**
     * 税额,小数点后 2 位正负校验:金额*税率=税额误差正负 0.06
     */
    @NotNull(message = "税额不能为空")
    private Double se;

    /**
     * 商品编码,总局固定编码
     */
    private String spbm;

    /**
     * 纳税人自行编码,自定义编码以 2 位为一级，共 10 级，每级可用编码值为 00-99
     */
    private String zxbm;

    /**
     * 优惠政策标示, 0 未使用，1 使用
     */
    private Integer yhzcbs;

    /**
     * 0 税率标示: 1 出口免税和其他免税优惠政策（免税）2 不征增值税（不征税）3 普通零税率（0%）
     */
    private Integer lslbs;

    /**
     * 增值税特殊管理,如果 yhzcbs 为 1 时，此项必填。
     */
    private String zzstsgl;

    /**
     * 插入时间
     */
    private Date insertTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFpqqlsh() {
        return fpqqlsh;
    }

    public void setFpqqlsh(String fpqqlsh) {
        this.fpqqlsh = fpqqlsh;
    }

    public Integer getFphxz() {
        return fphxz;
    }

    public void setFphxz(Integer fphxz) {
        this.fphxz = fphxz;
    }

    public String getSpmc() {
        return spmc;
    }

    public void setSpmc(String spmc) {
        this.spmc = spmc;
    }

    public String getGgxh() {
        return ggxh;
    }

    public void setGgxh(String ggxh) {
        this.ggxh = ggxh;
    }

    public String getDw() {
        return dw;
    }

    public void setDw(String dw) {
        this.dw = dw;
    }

    public Double getSpsl() {
        return spsl;
    }

    public void setSpsl(Double spsl) {
        this.spsl = spsl;
    }

    public Double getDj() {
        return dj;
    }

    public void setDj(Double dj) {
        this.dj = dj;
    }

    public Double getJe() {
        return je;
    }

    public void setJe(Double je) {
        this.je = je;
    }

    public Double getSl() {
        return sl;
    }

    public void setSl(Double sl) {
        this.sl = sl;
    }

    public Double getSe() {
        return se;
    }

    public void setSe(Double se) {
        this.se = se;
    }

    public String getSpbm() {
        return spbm;
    }

    public void setSpbm(String spbm) {
        this.spbm = spbm;
    }

    public String getZxbm() {
        return zxbm;
    }

    public void setZxbm(String zxbm) {
        this.zxbm = zxbm;
    }

    public Integer getYhzcbs() {
        return yhzcbs;
    }

    public void setYhzcbs(Integer yhzcbs) {
        this.yhzcbs = yhzcbs;
    }

    public Integer getLslbs() {
        return lslbs;
    }

    public void setLslbs(Integer lslbs) {
        this.lslbs = lslbs;
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
}