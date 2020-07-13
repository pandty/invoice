package com.invoice.system.shuzuservice;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.invoice.common.utils.CastUtils;
import com.invoice.common.utils.StringUtils;
import com.invoice.system.domain.szmodel.SysCommodity;
import com.invoice.system.domain.szmodel.SysCommodityCode;
import com.invoice.system.domain.szmodel.SysCommodityCodeAdd;
import com.invoice.system.service.SysCommoditService;
import com.invoice.system.service.SysCommodityCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Desoription:商品管理业务接口
 * @Auther:panyong
 * @create:2020/6/1 16:47
 */
@Service
public class CommodityService {
    @Autowired
    private SysCommoditService szCommoditService;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private SysCommodityCodeService sysCommodityCodeService;
    @Autowired
    private CommunalService communalService;

    //调用数族发票请求地址
    private static final String  invoiceUrl = "https://paymgmt.shuzutech.com/invoice/business";
    /***
     *@Description //编码查询
     *@Params [cxtj, cxlx]
     *@Return java.lang.String
     *@Author panyong
     *@Date 2020/6/19 14:39
    */
    public String getCommodityCode(String cxtj,Integer cxlx){
        Map map = Maps.newHashMap();
        HashMap<String,String> param=new HashMap<String,String>();
        if (cxlx==2){//根据机身编号查询
            param.put("jsbh",cxtj);//机身编号
        }else {//纳税人识别号
            param.put("nsrsbh",cxtj);//纳税人识别号
        }
        param.put("whbz","1");//标识
        String result =communalService.reqIntputUrl(SzBusinessConstants.SPBM,param,invoiceUrl,4);
        return result;
    }
    /***
     *@Description //获取商品编码
     *@Params []
     *@Return java.lang.String
     *@Author panyong
     *@Date 2020/6/16 17:20
    */
    public String getCommodity(){
        Map map = Maps.newHashMap();
        HashMap<String,String> param=new HashMap<String,String>();
        param.put("nsrsbh","91350205051157585F");//纳税人识别号
        //param.put("jsbh","91350205051157585F~~661526945031");//机身编号
        param.put("whbz","1");//版本号
        String result=WebServiceUtil.reqIntputUrl("SPBM",param);
        try {
            map=WebServiceUtil.parseXml(result);
            List spbmlist = WebServiceUtil.getValToMap(map, "spbm");
            Map msap = (Map)spbmlist.get(0);
            List list =(List) msap.get("group");
            List<SysCommodityCode> listsc=new ArrayList<>();
            for (int i=0;i<list.size();i++){
                System.out.println(i+"--->"+list.get(i));
                Map bmxxmap=(Map)list.get(i);
                //将map的key转换成小写和将值转成String
                Map mapc=WebServiceUtil.transformUpperCase(bmxxmap);
                SysCommodityCode sysCommodityCode = JSON.parseObject(JSON.toJSONString(mapc), SysCommodityCode.class);
                System.out.println(sysCommodityCode);
                listsc.add(sysCommodityCode);
            }
            //sysCommodityCodeService.insetList(listsc);
            System.out.println(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    /***
     *@Description //查询税局商品编码表
     *@Params [businessId, bbh]//bbh-版本号
     *@Return java.lang.String
     *@Author panyong
     *@Date 2020/6/1 17:00
    */
    public String getSJCommodity(final String business, final String bbh){
        Map map = Maps.newHashMap();
        HashMap<String,String> param=new HashMap<String,String>();
        param.put("bbh",bbh);//版本号
        String result=WebServiceUtil.reqIntputUrl(business,param);
        try {
            map=WebServiceUtil.parseXml(result);
            //List list=(List) WebServiceUtil.getValToMap(map,"BMXX");
            List list= CastUtils.maps2ListObj((List) WebServiceUtil.getValToMap(map,"BMXX"), new CastUtils.Map2Obj<SysCommodity, Map>() {
                @Override
                public SysCommodity getObj(Map map) {
                    Map mapc=WebServiceUtil.transformUpperCase(map);
                    mapc.put("bbh",bbh);
                    mapc.put("business",business);
                    return JSON.parseObject(JSON.toJSONString(mapc), SysCommodity.class);
                }
            });
            szCommoditService.insetList(list);
            //redisTemplate.opsForValue().set(business,JSON.toJSONString(list));
            System.out.println(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    /***
     *@Description //自定义商品编码设置 添加
     *@Params [SysCommodityCodeAdd]
     *@Return java.lang.String
     *@Author panyong
     *@Date 2020/6/22 9:30
    */
    public String addCommodity(SysCommodityCodeAdd sysCommodityCodeAdd){
        HashMap<String,String> param=new HashMap<String,String>();
        param.put("jsbh",StringUtils.valueOf(sysCommodityCodeAdd.getJsbh()));//机身编号
        param.put("nsrsbh",StringUtils.valueOf(sysCommodityCodeAdd.getNsrsbh()));//纳税人识别号
        param.put("bm",StringUtils.valueOf(sysCommodityCodeAdd.getBm()));//商品编码
        param.put("pid",StringUtils.valueOf(sysCommodityCodeAdd.getPid()));//商品编码的上级节点
        param.put("mc",StringUtils.valueOf(sysCommodityCodeAdd.getMc()));//商品编码名称
        param.put("spbmjc",StringUtils.valueOf(sysCommodityCodeAdd.getSpbmjc()));//商品编码简称
        param.put("sm",StringUtils.valueOf(sysCommodityCodeAdd.getSm()));//说明
        param.put("zzstsgl",StringUtils.valueOf(sysCommodityCodeAdd.getZzstsgl()));//增值税特殊管理
        param.put("zzszcyj",StringUtils.valueOf(sysCommodityCodeAdd.getZzszcyj()));//消费税政策依据
        param.put("zzstsgldm",StringUtils.valueOf(sysCommodityCodeAdd.getZzstsgldm()));//增值税特殊内容代码
        param.put("xfsgl",StringUtils.valueOf(sysCommodityCodeAdd.getXfsgl()));//消费税管理
        param.put("xfszcyj",StringUtils.valueOf(sysCommodityCodeAdd.getXfszcyj()));//消费税政策依据
        param.put("xfstsgldm",StringUtils.valueOf(sysCommodityCodeAdd.getXfstsgldm()));//消费税特殊内容代码
        param.put("gjz",StringUtils.valueOf(sysCommodityCodeAdd.getGjz()));//关键字
        param.put("kyzt",StringUtils.valueOf(sysCommodityCodeAdd.getKyzt()));//可用状态
        param.put("bb",StringUtils.valueOf(sysCommodityCodeAdd.getBb()));//编码版本号
        param.put("gdqjzsj",StringUtils.valueOf(sysCommodityCodeAdd.getGdqjzsj()));//过渡期截止时间
        param.put("qysj",StringUtils.valueOf(sysCommodityCodeAdd.getQysj()));//商品编码或商品编码表的启用时间
        param.put("gxsj",StringUtils.valueOf(sysCommodityCodeAdd.getGxsj()));//商品编码的入库时间
        param.put("yhlx",StringUtils.valueOf(sysCommodityCodeAdd.getYhlx()));//用户选择的优惠类型
        param.put("mslx",StringUtils.valueOf(sysCommodityCodeAdd.getMslx()));//免税类型
        param.put("kysl",StringUtils.valueOf(sysCommodityCodeAdd.getKysl()));//可用税率
        param.put("sl",StringUtils.valueOf(sysCommodityCodeAdd.getSl()));//税率
        param.put("ggxh",StringUtils.valueOf(sysCommodityCodeAdd.getGgxh()));//规格型号
        param.put("jldw",StringUtils.valueOf(sysCommodityCodeAdd.getJldw()));//计量单位
        param.put("dj",StringUtils.valueOf(sysCommodityCodeAdd.getDj()));//单价
        param.put("hsbz",StringUtils.valueOf(sysCommodityCodeAdd.getHsbz()));//含税标志
        param.put("zzssl",StringUtils.valueOf(sysCommodityCodeAdd.getZzssl()));// 增值税税率
        param.put("hgpm",StringUtils.valueOf(sysCommodityCodeAdd.getHgpm()));//海关进出口商品品目
        param.put("gmtjdm",StringUtils.valueOf(sysCommodityCodeAdd.getGmtjdm()));// 国民统计代码
        String result =communalService.reqIntputUrl(SzBusinessConstants.SPBMSZ,param,invoiceUrl,5);
        return result;
    }
    /***
     *@Description //自定义商品编码设置，修改等操作。
     *@Params [business]
     *@Return java.lang.String
     *@Author panyong
     *@Date 2020/6/1 17:45
    */
    public String editCommodity(String business){
        HashMap<String,String> param=new HashMap<String,String>();
        param.put("jsbh","91350205051157585F~~661526945031");//机身编号
        param.put("nsrsbh","91350205051157585F");//纳税人识别号
        param.put("bm","");//商品编码
        param.put("pid","3040802000000000000");//商品编码的上级节点
        param.put("mc","番石榴");//商品编码名称
        param.put("spbmjc","番石榴");//商品编码简称
        param.put("sm","番石榴");//说明
        param.put("zzstsgl","");//增值税特殊管理
        param.put("zzszcyj","");//消费税政策依据
        param.put("zzstsgldm","");//增值税特殊内容代码
        param.put("xfsgl","");//消费税管理
        param.put("xfszcyj","");//消费税政策依据
        param.put("xfstsgldm","");//消费税特殊内容代码
        param.put("gjz","");//关键字
        param.put("kyzt","Y");//可用状态
        param.put("bb","");//编码版本号
        param.put("gdqjzsj","");//过渡期截止时间
        param.put("qysj","");//商品编码或商品编码表的启用时间
        param.put("gxsj","");//商品编码的入库时间
        param.put("yhlx","");//用户选择的优惠类型
        param.put("mslx","");//免税类型
        param.put("kysl","0.03");//可用税率
        param.put("sl","0.03");//税率
        param.put("ggxh","dd");//规格型号
        param.put("jldw","个");//计量单位
        param.put("dj","100");//单价
        param.put("hsbz","");//含税标志
        param.put("zzssl","");// 增值税税率
        param.put("hgpm","");//海关进出口商品品目
        param.put("gmtjdm","");// 国民统计代码
        String result=WebServiceUtil.reqIntputUrl(business,param);;
        return result;
    }

    /***
     *@Description //自定义商品编码删除..客户通过调用此接口删除自行编码
     *@Params [business, bm]
     *@Return java.lang.String
     *@Author panyong
     *@Date 2020/6/1 18:07
    */
    public String removeCommodity(String bm,String jsbh,String nsrsbh){
        HashMap<String,String> param=new HashMap<String,String>();
        param.put("bm",bm);//商品编码
        param.put("jsbh",jsbh);//机身编号
        param.put("nsrsbh",nsrsbh);//纳税人识别号
        String result=WebServiceUtil.reqIntputUrl(SzBusinessConstants.SPBMSC,param);;
        return result;
    }
    /***
     *@Description //商品编码默认值设置  用于设置当前默认的商品编码。
     *@Params [business, bm]
     *@Return java.lang.String
     *@Author panyong
     *@Date 2020/6/1 18:10
    */
    public String editCommodityCode(String business,String bm){
        HashMap<String,String> param=new HashMap<String,String>();
        param.put("bm","");//商品编码
        param.put("param","");//机身编号
        param.put("nsrsbh","");//纳税人识别号
        String result=WebServiceUtil.reqIntputUrl(business,param);;
        return result;
    }
    /***
     *@Description //获取商品编码,  通过该接口获取税控盘维护的商品信息
     *@Params [business, bm]
     *@Return java.lang.String
     *@Author panyong
     *@Date 2020/6/2 9:11
    */
    public String getCommodityCode(String business){
        HashMap<String,String> param=new HashMap<String,String>();
        param.put("nsrsbh","");//纳税人识别号
        param.put("jsbh","");//机身编号
        param.put("whbz","");//维护标志
        String result=WebServiceUtil.reqIntputUrl(business,param);
        return result;
    }

    private static Map<String,Object> rtnRespon(String rtnXml){

        return null;
    }
    
    public static void main(String[] args) {

        CommodityService cs=new CommodityService();
        //cs.getCommodity();
        String re=cs.editCommodity("SPBMSZ");
    }
}
