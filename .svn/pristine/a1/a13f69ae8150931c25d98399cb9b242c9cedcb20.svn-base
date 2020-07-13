package com.invoice.system.shuzuservice;

import com.google.common.collect.Maps;
import com.invoice.common.utils.StringUtils;
import com.invoice.system.domain.SysClientInfoPush;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName ClienterService
 * @Description 商户信息管理
 * @Author ZZJ
 * @Date 2020/6/1 10:48
 **/
@Service
public class ClienterService {

    //调用数族商户请求地址 商户模块的用
    private static final String  clientInfoUrl = "http://shanghu.shuzutech.com/";
    //调用数族商户业务请求路径
    private static final String  clientInfoBusinessUrl ="api/merchant/business";

    //调用数族发票请求地址
    private static final String  invoiceUrl = "https://paymgmt.shuzutech.com/";
    //调用数族发票业务请求路径
    private static final String invoiceBusinessUrl ="invoice/business";

    @Autowired
    private CommunalService communalService;

    /**
     * 商户信息推送接口
     * @return
     */
    public String pushClientInfo(SysClientInfoPush sysClientInfoPush){
        Map<String,String> params = Maps.newHashMap();
        //服务商 ID
        params.put("channelId",StringUtils.valueOf(sysClientInfoPush.getChannelId()));
        //商户名称
        params.put("clientName",StringUtils.valueOf(sysClientInfoPush.getClientName()));
        //省份编号
        params.put("provinceno",StringUtils.valueOf(sysClientInfoPush.getProvinceno()));
        //市级编号
        params.put("cityno",StringUtils.valueOf(sysClientInfoPush.getCityno()));
        //按策略分配商户到子服务商
        params.put("dispatch",StringUtils.valueOf(sysClientInfoPush.getDispatch()));
        //纳税人识别号
        params.put("nsrsbh",StringUtils.valueOf(sysClientInfoPush.getNsrsbh()));
        //注册地址
        params.put("businessAddress",StringUtils.valueOf(sysClientInfoPush.getBusinessAddress()));
        //电话
        params.put("tel",StringUtils.valueOf(sysClientInfoPush.getTel()));
        //开户银行
        params.put("openBank",StringUtils.valueOf(sysClientInfoPush.getOpenBank()));
        //账号
        params.put("accountNo",StringUtils.valueOf(sysClientInfoPush.getAccountNo()));
        //绑定手机
        params.put("phone",StringUtils.valueOf(sysClientInfoPush.getPhone()));
        //联系人
        params.put("contact",StringUtils.valueOf(sysClientInfoPush.getContact()));
        //身份证号码
        params.put("idno",StringUtils.valueOf(sysClientInfoPush.getIdno()));
        //业务员手机号
        params.put("salerMobile",StringUtils.valueOf(sysClientInfoPush.getSalerMobile()));
        //设备编号或白钥匙编号
        params.put("sbbh",StringUtils.valueOf(sysClientInfoPush.getSbbh()));
        //核心板编号
        params.put("hxbbh",StringUtils.valueOf(sysClientInfoPush.getHxbbh()));
        //项目名称
        params.put("xmmc",StringUtils.valueOf(sysClientInfoPush.getXmmc()));
        //默认商品
        params.put("defautGoods",StringUtils.valueOf(sysClientInfoPush.getDefautGoods()));
        //默认商品编码
        params.put("goodsId",StringUtils.valueOf(sysClientInfoPush.getGoodsId()));
        //默认商品税率
        params.put("taxRate",StringUtils.valueOf(sysClientInfoPush.getTaxRate()));
        //开票人
        params.put("kpr",StringUtils.valueOf(sysClientInfoPush.getKpr()));
        return communalService.reqIntputUrl(SzBusinessConstants.KHXXTS,params,clientInfoUrl+clientInfoBusinessUrl,2);
    }

    /**
     * 商户信息查询
     * @param nsrsbh 纳税人识别号
     * @return
     */
    public String selClientInfo(String nsrsbh){
        Map<String,String> params = Maps.newHashMap();
        params.put("nsrsbh",nsrsbh);
        //return WebServiceUtil.reqIntputUrl(SzBusinessConstants.MERCHANTSEARCH,params);
        return communalService.reqIntputUrl(SzBusinessConstants.MERCHANTSEARCH,params,clientInfoUrl+clientInfoBusinessUrl,7);
    }

    /**
     * 终端查询
     * @param nsrsbh 纳税人识别号
     * @return
     */
    public String selTerminalManage(String nsrsbh){
        Map<String,String> params = Maps.newHashMap();
        params.put("nsrsbh",nsrsbh);
        return WebServiceUtil.reqIntputUrl(SzBusinessConstants.TERMINALMANAGE,params);
    }

    /**
     * 查询设备状态
     * @param jsbh 机身编号
     * @return
     */
    public String selTerminalStatus(String jsbh){
        Map<String,String> params = Maps.newHashMap();
        params.put("jsbh",jsbh);
        //return WebServiceUtil.reqIntputUrl(SzBusinessConstants.CXSBZT,params);
        return communalService.reqIntputUrl(SzBusinessConstants.CXSBZT,params,invoiceUrl+invoiceBusinessUrl,6);
    }

    public static void main(String [] args){
        ClienterService cc  = new ClienterService ();
        //cc.pushClientInfo(Maps.<String, String>newHashMap());
        String SS =cc.selClientInfo("91350205051157585F~~661526945031");//91350205051157585F~~661526945031
        System.out.println(SS);
        /*Map map = WebServiceUtil.parseXml(SS);
        WebServiceUtil.getValToMap(map, "");*/
        //cc.selTerminalManage("title");
        //cc.selTerminalStatus("title");
        /*while (true){
            String SS =cc.selTerminalStatus("91350205051157585F~~661526945031");
            Map map =WebServiceUtil.parseXml(SS);
            String returnCode =WebServiceUtil.getValToMap(map, "returncode");
            System.out.println(SS);
            if ("0".equals(returnCode)){
               return;
            }
            try {
                Thread.sleep(20000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }*/
    }
}
