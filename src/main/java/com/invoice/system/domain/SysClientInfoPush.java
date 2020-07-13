package com.invoice.system.domain;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class SysClientInfoPush implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增主键
     */
    private Integer id;

    /**
     * 税控盘号
     */
    @NotBlank(message = "机身编号不能为空")
    private String jsbh;

    /**
     * 商户所属服务商 服务商在平台分配的 ID
     */
    private String channelId;

    /**
     * 商户名称
     */
    @NotBlank(message = "商户名称不能为空")
    private String clientName;

    /**
     * 纳税人识别号
     */
    @NotBlank(message = "纳税人识别号不能为空")
    private String nsrsbh;

    /**
     * 所属省份
     */
    private String provinceno;

    /**
     * 所属省份名称
     */
    @NotBlank(message = "所属省份不能为空")
    private String provincename;

    /**
     * 所属市
     */
    private String cityno;

    /**
     * 所属市名称
     */
    private String cityname;

    /**
     * 是否按地址分派给子服务商  
1：按配置的分派策略分配商户到子服务商；
0：不按策略分配。默认为 0.
     */
    private String dispatch;

    /**
     * 注册地址
     */
    @NotBlank(message = "注册地址不能为空")
    private String businessAddress;

    /**
     * 电话
     */
    @NotBlank(message = "电话不能为空")
    private String tel;

    /**
     * 开户银行
     */
    private String openBank;

    /**
     * 银行账号
     */
    private String accountNo;

    /**
     * 联系人手机号
     */
    @NotBlank(message = "联系人手机号不能为空")
    private String phone;

    /**
     * 联系人 旋极平台非旋极省
份必填
     */
    private String contact;

    /**
     * 联系人身份证  旋极平台非旋极省份必填
     */
    private String idno;

    /**
     * 业务员手机号
     */
    private String salerMobile;

    /**
     * 商品编码
     */
    private String goodsId;

    /**
     * 税率
     */
    private Double taxRate;

    /**
     * 默认商品名称  广东商户必填
     */
    private String defautGoods;

    /**
     * 项目名称  广东商户必填
     */
    private String xmmc;

    /**
     * 开票人 广东商户必填
     */
    private String kpr;

    /**
     * 设备编号或白钥匙编号
 广东商户必填
     */
    private String sbbh;

    /**
     * 核心板编号  广东商户必填
     */
    private String hxbbh;

    /**
     * 商户 ID
     */
    private String merchantid;

    /**
     * 托管服务标识
     0：否；1：是
     */
    private String zdytgwfbs;

    /**
     * 开通状态
     */
    private String ktzt;

    /**
     * 电子签章状态
     0 为等待审核
     1 为审核通过
     2 为驳回
     99：未申请
     */
    private String eSignet;

    /**
     * 返回代码
     */
    private String rtnCode;

    /**
     * 返回信息
     */
    private String rtnMsg;

    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * 修改时间
     */
    private Long updateTime;

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

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getNsrsbh() {
        return nsrsbh;
    }

    public void setNsrsbh(String nsrsbh) {
        this.nsrsbh = nsrsbh;
    }

    public String getProvinceno() {
        return provinceno;
    }

    public void setProvinceno(String provinceno) {
        this.provinceno = provinceno;
    }

    public String getProvincename() {
        return provincename;
    }

    public void setProvincename(String provincename) {
        this.provincename = provincename;
    }

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    public String getCityno() {
        return cityno;
    }

    public void setCityno(String cityno) {
        this.cityno = cityno;
    }

    public String getDispatch() {
        return dispatch;
    }

    public void setDispatch(String dispatch) {
        this.dispatch = dispatch;
    }

    public String getBusinessAddress() {
        return businessAddress;
    }

    public void setBusinessAddress(String businessAddress) {
        this.businessAddress = businessAddress;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getOpenBank() {
        return openBank;
    }

    public void setOpenBank(String openBank) {
        this.openBank = openBank;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getIdno() {
        return idno;
    }

    public void setIdno(String idno) {
        this.idno = idno;
    }

    public String getSalerMobile() {
        return salerMobile;
    }

    public void setSalerMobile(String salerMobile) {
        this.salerMobile = salerMobile;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public Double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(Double taxRate) {
        this.taxRate = taxRate;
    }

    public String getDefautGoods() {
        return defautGoods;
    }

    public void setDefautGoods(String defautGoods) {
        this.defautGoods = defautGoods;
    }

    public String getXmmc() {
        return xmmc;
    }

    public void setXmmc(String xmmc) {
        this.xmmc = xmmc;
    }

    public String getKpr() {
        return kpr;
    }

    public void setKpr(String kpr) {
        this.kpr = kpr;
    }

    public String getSbbh() {
        return sbbh;
    }

    public void setSbbh(String sbbh) {
        this.sbbh = sbbh;
    }

    public String getHxbbh() {
        return hxbbh;
    }

    public void setHxbbh(String hxbbh) {
        this.hxbbh = hxbbh;
    }

    public String getMerchantid() {
        return merchantid;
    }

    public void setMerchantid(String merchantid) {
        this.merchantid = merchantid;
    }

    public String getZdytgwfbs() {
        return zdytgwfbs;
    }

    public void setZdytgwfbs(String zdytgwfbs) {
        this.zdytgwfbs = zdytgwfbs;
    }

    public String getKtzt() {
        return ktzt;
    }

    public void setKtzt(String ktzt) {
        this.ktzt = ktzt;
    }

    public String geteSignet() {
        return eSignet;
    }

    public void seteSignet(String eSignet) {
        this.eSignet = eSignet;
    }

    public String getRtnCode() {
        return rtnCode;
    }

    public void setRtnCode(String rtnCode) {
        this.rtnCode = rtnCode;
    }

    public String getRtnMsg() {
        return rtnMsg;
    }

    public void setRtnMsg(String rtnMsg) {
        this.rtnMsg = rtnMsg;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

}