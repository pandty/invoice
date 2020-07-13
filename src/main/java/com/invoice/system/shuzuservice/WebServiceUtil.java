package com.invoice.system.shuzuservice;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.invoice.common.utils.MD5;
import com.invoice.common.utils.StringUtils;
import com.invoice.common.utils.SzHttpUtil;
import com.invoice.system.domain.szmodel.SignatureToken;
import com.invoice.system.domain.szmodel.SysCommodity;
import com.invoice.system.utils.DateHelper;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections.map.MultiValueMap;
import org.apache.logging.log4j.util.Strings;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @ClassName WebServiceUtil
 * @Description 调用数族公共请求类
 * @Author ljl
 * @Date 2020/6/1 17:42
 **/
public class WebServiceUtil {

    private static final Logger logger= LoggerFactory.getLogger(WebServiceUtil.class);
    //调用数族发票请求地址
    private static final String  invoiceUrl = "https://paymgmt.shuzutech.com/";
    //调用数族发票业务请求路径
    private static final String invoiceBusinessUrl ="invoice/business";

    //调用数族商户请求地址 商户模块的用
    private static final String  clientInfoUrl = "http://shanghu.shuzutech.com/";
    //调用数族商户业务请求路径
    private static final String  clientInfoBusinessUrl ="api/merchant/business";

    //调用数族具体token请求路径
    private static final String tokenUrl ="invoice/token";

    private static final String  appid = "ad3db22a0e37fdf1286c97e867a6b9fe"; //自己的appid
    private static String access_token;//token令牌
    private static final String appSecret ="58ffd8d279c8941ec12300cf173477fc671f7b8e";
    /**
     * 秘钥
     */
    public static String API_KEY = "[HFP POWER BY BOOKSIR CO.LTD]";
    static {
        access_token=getSZToken();
    }

    /**
     *  调用数族请求统一入口(Input节点)
     * @param bussness 调用的服务名
     * @param params 请求公共入参
     * @return
     */
    public static String reqIntputUrl(String bussness, Map  params){
        //1.组装 body 部分
        StringBuilder stringBuilder =new StringBuilder("<?xml version=\"1.0\" encoding=\"utf-8\" ?><business id=\""+bussness+"\"><body><input>");
        rtnMapXml(params,stringBuilder);
        //添加尾部节点
        stringBuilder.append("</input></body></business>");
        logger.info("REQ XML 的请求报文=" + stringBuilder.toString());
        //2.组装 headers 部分
        Map<String, String> headers =rtnHeaders(stringBuilder.toString());
        String result = SzHttpUtil.httpRequest(rtnSzUrl(bussness),stringBuilder.toString(), headers);
        logger.info("RESPON 返回报文 =" +result);
        return result;
    }
    /**
     *  调用数族请求统一入口(无input节点)
     * @param bussness 调用的服务名
     * @param params 请求公共入参
     * @return
     */
    public static String reqUrl(String bussness, Map params){
        //1.组装 body 部分
        StringBuilder stringBuilder =new StringBuilder("<?xml version=\"1.0\" encoding=\"utf-8\"?><business id=\""+bussness+"\"><body>");
        rtnMapXml(params,stringBuilder);
        //添加尾部节点
        stringBuilder.append("</body></business>");
        logger.info("REQ XML 的请求报文=" + stringBuilder.toString());
        //2.组装 headers 部分
        Map<String, String> headers =rtnHeaders(stringBuilder.toString());
        return SzHttpUtil.httpRequest(rtnSzUrl(bussness),stringBuilder.toString(), headers);
    }
    /***
     *@Description //传人完整xml,和url 发送请求
     *@Params [xml, url]
     *@Return java.lang.String
     *@Author panyong
     *@Date 2020-06-30 18:22
    */
    public static String httpRequest(String xml,String url){
        //组装 headers 部分
        Map<String, String> headers =rtnHeaders(xml);
        return SzHttpUtil.httpRequest(url,xml, headers);
    }

    /*/**
     * 根据业务判断调用的Url
     * @author lijialin
     * @date 2020/6/12 9:39
     * @param bussness
     */
    private static String rtnSzUrl(String bussness){
        String rtnUrl = Strings.EMPTY;
        if (SzBusinessConstants.clientInfoModles.contains(bussness)){
            //判断业务类型是否所属商户模块
            rtnUrl = clientInfoUrl +clientInfoBusinessUrl;
        }else {
            rtnUrl = invoiceUrl +invoiceBusinessUrl;
        }
        return rtnUrl;
    }

    /**
     * 解析Map 参数 转化成 xml body 部分
     * @param mapParam
     * @param stringBuilder
     */
    public static void rtnMapXml(Map<String,Object> mapParam,StringBuilder stringBuilder){
        for (Map.Entry entry : mapParam.entrySet()){
            if (entry.getValue() instanceof Map){
                stringBuilder.append("<"+entry.getKey()+">");
                rtnMapXml((Map<String, Object>) entry.getValue(),stringBuilder);
            }else if (entry.getValue() instanceof List){
                stringBuilder.append("<"+entry.getKey()+" count = \""+((List) entry.getValue()).size()+"\">");
                List<Map<String, Object>> maoLists = (List<Map<String, Object>>) entry.getValue();
                for (int i =0 ; i< ((List) entry.getValue()).size();i++ ){
                    stringBuilder.append("<group xh=\""+(i+1)+"\">");
                    rtnMapXml(maoLists.get(i),stringBuilder);
                    stringBuilder.append("</group>");
                }
            }else {
                stringBuilder.append("<"+entry.getKey()+">");
                stringBuilder.append(StringUtils.valueOf(entry.getValue()));
            }
            stringBuilder.append("</"+entry.getKey()+">");
        }
    }

    /***
     *@Description //将map 进行封装成xml
     *@Params [map]
     *@Return java.lang.String
     *@Author panyong
     *@Date 2020/6/2 14:18
     */
    public static String mapToXMLString(HashMap<String,String> map) {
        StringBuilder stringBuilder =new StringBuilder();
        for (Map.Entry entry:map.entrySet()){
            stringBuilder.append("<"+entry.getKey()+">").append(entry.getValue()).append("</" +entry.getKey()+">");

        }
        return stringBuilder.toString();
    }

    //组装headers
    public static Map<String, String> rtnHeaders(String body){
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
        String date = ft.format(new Date());
        //获取token
        String token = access_token;
        //获取签名
        String sign = Base64.encodeBase64String(DigestUtils.md5Hex(body + date + token).getBytes());
        Map<String,String> headers = new HashMap<String,String>();
        headers.put("AppId",appid);
        headers.put("Date",date);
        headers.put("Content-MD5",sign);
        headers.put("Content-Type", "application/xml");
        return headers;
    }

    /**
     * 获取 token
     * @return
     */
    public static String token(){
        String para = "appId=" + appid + "&appSecret=" + appSecret;
        Map<String,String> headers = new HashMap<String,String>();
        headers.put("Content-Type", "application/x-www-form-urlencoded");
        return SzHttpUtil.httpRequest(invoiceUrl+tokenUrl,para, headers);
    }
    /***
     *@Description //TODO 传人xml格式和指定标签输出标签的值
     *@Params [xml, label]
     *@Return java.lang.String
     *@Author panyong
     *@Date 2020/6/2 18:35
     */
    public static String readXMLValue(String xml,String label){
        // 1.将xml格式字符串转化为DOM对象
        Document  document = null;
        try {
            document = DocumentHelper.parseText(xml);
            // 2.获取根结点对象:dataset
            Element rootElement = document.getRootElement();
            for (Iterator iter = rootElement.elementIterator(); iter.hasNext();) {
                Element element = (Element) iter.next(); // 获取标签对象
                // 6.循环第二层节点，获取其子节点
                for (Iterator iterInner = element.elementIterator(); iterInner.hasNext();) {
                    // 获取标签对象
                    Element elementOption = (Element) iterInner.next();
                    // 获取该标签对象的名称
                    String tagName = elementOption.getName();
                    if (label.equals(tagName)){
                        String tagContent = elementOption.getTextTrim();
                        return tagContent;
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }
    /***
     *@Description //获取token方法
     *@Params []
     *@Return java.lang.String
     *@Author panyong
     *@Date 2020/6/3 9:10
     */
    public static String getSZToken(){
        String token =token();
        return access_token=readXMLValue(token,"access_token");
    }
    /***
     *@Description //循环迭代map 获取想要的Key
     *@Params [map, key]
     *@Return java.lang.String
     *@Author panyong
     *@Date 2020/6/3 17:47
    */
    public static  <T> T getMapValueBykey(Map<String,Object> map,String key){
        Iterator<Map.Entry<String, Object>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Object> entry = it.next();
            System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
            //判断是否是想要的字段
            if(entry.getKey().equals(key)){
                if(entry.getValue() instanceof List){
                    List<Object> list=(List<Object>)entry.getValue();
                    return (T)list;
                }
            }
            //没有则继续往下找map
            if ( entry.getValue() instanceof Map){
                map=(Map<String, Object>) entry.getValue();
                //递归获取
                return getMapValueBykey(map,key);
            }else if(entry.getValue() instanceof List){//找list
                List<Object> list=(List<Object>)entry.getValue();

                for(int i=0;i<list.size();i++){
                    map=(Map<String, Object>) list.get(i);
                    //递归获取
                    return getMapValueBykey(map,key);
                }
            }
        }
        return null;
    }
    public static void main(String [] args){
        Map map = Maps.newHashMap();

       /* String token =token();
        System.out.println(token);*/
       /* String access_token=readXMLValue(token,"access_token");
        System.out.println("access_token-----:"+access_token);*/

       /*String xml ="<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
               "<business id=\"TOKEN\"><body><returnmsg>获取成功</returnmsg><returncode>0</returncode><access_token>32616b82c6386fe392039254d97239cd</access_token><expires>7200</expires></body></business>";
*/


        String xml= "";
        BufferedReader br = null;//构造一个BufferedReader类来读取文件
        InputStreamReader isr;
        try {
            isr = new InputStreamReader(new FileInputStream("C:\\Users\\Administrator\\Desktop\\101.txt"), "gbk");
            br = new BufferedReader(isr);
            String s = null;
            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
                xml = xml +s;
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        map =parseXml(xml);
        List list = WebServiceUtil.getValToMap(map, "spbm");
        Map msap = (Map)list.get(0);
        List dddd =(List) msap.get("group");
        //List list= WebServiceUtil.getValToMap(map,"group");
        System.out.println("list#"+list);
        for (int i=0;i<list.size();i++){
            System.out.println(i+"--->"+list.get(i));
            Map bmxxmap=(Map)list.get(i);
            Map mapc=transformUpperCase(bmxxmap);
            SysCommodity szCommodity = JSON.parseObject(JSON.toJSONString(mapc), SysCommodity.class);
            System.out.println(szCommodity);

        }

    }


    /**
     * xml转为map,map中有list（节点相同时候)，list中有map
     * @param xml
     * @return
     * @throws DocumentException
     */
    public static Map<String, Object> parseXml(String xml)  {
        Map map = new HashMap();
        try {
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(new ByteArrayInputStream(xml.getBytes("UTF-8")));//xml串第一行不能有空格，否则报错
            Element root = document.getRootElement();//得到xml文档根节点元素，即最上层的"<xml>"
            elementTomap(root, map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 递归遍历xml所有子节点
     * @param outele
     * @param outmap
     * @return
     */
    public static Map<String, Object> elementTomap(Element outele, Map<String, Object> outmap) {
        List<Element> eles = outele.elements();
        if (eles.size() == 0) {//没有子节点 直接取 name text
            outmap.put(outele.getName(), outele.getTextTrim());
        } else {
            MultiValueMap innermap =new MultiValueMap();
            for (Element childEle : eles) { //开始遍历子节点
                elementTomap(childEle, innermap);
            }
            outmap.put(outele.getName(), innermap);
        }
        return outmap;
    }

    /*/**
     * Map多层嵌套获取key的val值
     * @author lijialin
     * @date 2020/6/4 11:39
     * @param [val, key]
     * @return T
     */
    public static <T> T getValToMap(Object val,String key){
        if (val instanceof Map){
            Map<String,Object> mapParam= (Map<String, Object>) val;
            if (mapParam.get(key)==null){
                for (Map.Entry entry:mapParam.entrySet()) {
                    return (T) getValToMap(entry.getValue(),key);
                }
            }else{
                if (mapParam.get(key) instanceof List ){
                    List<String> rtnList = (List) mapParam.get(key);
                    if (rtnList.size() ==1){
                        if (rtnList.get(0) instanceof String){
                            return (T) rtnList.get(0);
                        }
                    }
                }
                return (T) mapParam.get(key);
            }
        }else if (val instanceof List){
            List list = (List) val;
            for (Object obj :list){
                return (T)getValToMap(obj,key);
            }
        }
        return null;
    }
    /***
     *@Description //将nap的key转换成小写，将Objet类型转换成String
     *@Params [orgMap]
     *@Return java.util.Map<java.lang.String,java.lang.String>
     *@Author panyong
     *@Date 2020/6/5 9:49
    */
    public static Map<String, String> transformUpperCase(Map<String, Object> orgMap) {
        Map<String, String> resultMap = new HashMap<>();
        if (orgMap == null || orgMap.isEmpty()) {
            return resultMap;
        }
        Set<String> keySet = orgMap.keySet();
        for (String key : keySet) {
            String newKey = key.toLowerCase();
            if (orgMap.get(key) instanceof List){
               List list = (List) orgMap.get(key);
               resultMap.put(newKey, StringUtils.valueOf(list.get(0)));
            }else {
                resultMap.put(newKey, StringUtils.valueOf(orgMap.get(key)));
            }

        }
        return resultMap;
    }
    /***
     *@Description //验证秘钥
     *@Params [token, message, time]
     *@Return boolean
     *@Author panyong
     *@Date 2020/6/29 14:48
    */
    public static boolean verifyToken(SignatureToken signature, StringBuilder message) {
        if (signature.getTime()==null){
            message.append("时间不能为空");
            return false;
        }
        if (signature.getToken()==null&&signature.getToken().isEmpty()){
            message.append("toke不能为空");
            return false;
        }
        //判断有效时间
        int diffTime = DateHelper.getTimestamp(DateHelper
                .addMin(new Date(), -5));
        int laterTime=DateHelper.getTimestamp(DateHelper
                .addMin(new Date(), 5));

		if (signature.getTime() < diffTime||signature.getTime()>laterTime) {
            message.append("已超时！");
			return false;
		}
        String keyMsg = API_KEY  + signature.getTime();
        String key = MD5.getMD5(keyMsg.getBytes());
        if (!signature.getToken().equals(key)) {
            message.append("秘钥错误！");
            return false;
        }
        return true;
    }
}
