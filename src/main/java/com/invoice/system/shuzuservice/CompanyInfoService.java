package com.invoice.system.shuzuservice;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * @ClassName CompanyInfoService
 * @Description 企业信息
 * @Author ljl
 * @Date 2020/6/2 10:48
 **/
public class CompanyInfoService {

    /**
     * 企业信息设置
     * @param jsbh 机身编号
     * @return
     */
    public String companyInfoInstall(String jsbh){
        Map<String,String> params = Maps.newHashMap();
        params.put("jsbh","机身编号");
        params.put("dz","营业地址");
        params.put("dh","电话");
        params.put("yh","银行名称");
        params.put("zh","银行账号");
        return WebServiceUtil.reqIntputUrl(SzBusinessConstants.QYXXSZ,params);
    }

    /**
     * 企业信息查询
     * @param jsbh 机身编号
     * @return
     */
    public String selCompanyInfo(String jsbh){
        Map<String,String> params = Maps.newHashMap();
        params.put("jsbh","机身编号");
        return WebServiceUtil.reqIntputUrl(SzBusinessConstants.QYXXCX,params);
    }

    /**
     * 企业抬头联想
     * @param title 需联想字段
     * @return
     */
    public String companyRiseAssociation(String title){
        Map<String,String> params = Maps.newHashMap();
        params.put("title","<![CDATA["+title+"]]>");
        return WebServiceUtil.reqIntputUrl(SzBusinessConstants.QYTTXXCX,params);
    }

    public static void main(String [] args){
        CompanyInfoService cc  = new CompanyInfoService ();
        //cc.companyInfoInstall("111");
        //cc.selCompanyInfo("111");
        cc.companyRiseAssociation("title");
    }

}
