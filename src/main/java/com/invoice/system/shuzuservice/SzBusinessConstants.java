package com.invoice.system.shuzuservice;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * 数族业务Id 常量
 * @author ljl
 */
public class SzBusinessConstants {

    /**
     * 商户信息推送
     */
    public static final String KHXXTS ="KHXXTS";

    /**
     * 商户信息查询
     */
    public static final String MERCHANTSEARCH ="MERCHANTSEARCH";

    /**
     * 终端查询
     */
    public static final String TERMINALMANAGE ="TERMINALMANAGE";

    /**
     * 查询设备状态
     */
    public static final String CXSBZT ="CXSBZT";

    /**
     * 企业信息设置
     */
    public static final String QYXXSZ ="QYXXSZ";

    /**
     * 企业信息查询
     */
    public static final String QYXXCX ="QYXXCX";

    /**
     * 企业抬头联想
     */
    public static final String QYTTXXCX ="QYTTXXCX";

    /**
     * 增值税普通电子发票
     */
    public static final String FPKJ ="FPKJ";
    /**
     * 发票查询
     */
    public static final String FPCX ="FPCX";
    /**
     * 获取商品编码
     */
    public static final String SPBM = "SPBM";
    /**
     * 自定义商品编码设置
     */
    public static final String SPBMSZ ="SPBMSZ" ;
    /**
     * 自定义商品编码删除
     */
    public static final String SPBMSC = "SPBMSC";

    /**
     * 数族业务Id所属商户模块
     * @author lijialin
     * @date 2020/6/12 9:31
     */
    public static List<String> clientInfoModles = Lists.newArrayList(KHXXTS,MERCHANTSEARCH);
    


}
