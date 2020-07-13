package com.invoice.system.shuzuservice;

import com.alibaba.fastjson.JSON;
import com.invoice.common.utils.SzHttpUtil;
import com.invoice.common.utils.http.HttpUtils;
import com.invoice.system.service.SysPushInfoService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Desoription:公共方法业务层
 * @Auther:panyong
 * @create:2020/6/15 17:03
 */
@Service
public class CommunalService {
    @Autowired
    private SysPushInfoService szPushInfoService;
    /***
     *@Description //请求数族接口公用方法
     *@Params [bussness, params, url, optype]
     *@Return java.lang.String
     *@Author panyong
     *@Date 2020/6/15 14:32
     */
    public String reqIntputUrl(String bussness, Map params, String url, Integer optype, Integer ...count){
        if (count.length==0){
            count =new Integer[1];
            count[0] = 0;
        }
        //1.组装 body 部分
        StringBuilder stringBuilder =new StringBuilder("<?xml version=\"1.0\" encoding=\"utf-8\"?><business id=\""+bussness+"\"><body><input>");
        WebServiceUtil.rtnMapXml(params,stringBuilder);
        //添加尾部节点
        stringBuilder.append("</input></body></business>");
        System.out.println(stringBuilder.toString());
        //2.组装 headers 部分
        Map<String, String> headers =WebServiceUtil.rtnHeaders(stringBuilder.toString());
        String result=SzHttpUtil.httpRequest(url,stringBuilder.toString(), headers);
        Map map = WebServiceUtil.parseXml(result);
        String rtnCode = WebServiceUtil.getValToMap(map, "returncode");
        if (!"0".equals(rtnCode) && count[0] == 0){
            count[0] ++;
            WebServiceUtil.getSZToken();
            return reqIntputUrl(bussness,  params,  url,  optype,count);
        }
        //插入日志表
        szPushInfoService.insert(result,url,stringBuilder.toString(),optype,3,"post");
        return result;
    }

    /**
     * Qyt通用POST请求方法
     * @param params
     * @param url
     * @param optype
     * @return
     */
    public String qytPostRequest(Map params, String url, Integer optype){
        String result="";
        try {
            result= HttpUtils.postMap(url,params);
            //插入日志表
            szPushInfoService.insert(result,url, JSON.toJSONString(params),optype,3,"post");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
