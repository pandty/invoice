package com.invoice.system.service.impl;

import com.invoice.system.domain.szmodel.SysPushInfo;
import com.invoice.system.mapper.SysPushInfoMapper;
import com.invoice.system.service.SysPushInfoService;
import com.invoice.system.shuzuservice.WebServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Desoription:推送日志业务实现
 * @Auther:panyong
 * @create:2020/6/15 14:40
 */
@Service
public class SysPushInfoServiceImpl implements SysPushInfoService {
    @Autowired
    private SysPushInfoMapper szPushInfoMapper;
    /***
     *@Description //插入推送记录实现
     *@Params [result返回值, url, s 参数, optype 推送类型, state 状态 1位待推送 3为已推送,  type请求类型]
     *@Return void
     *@Author panyong
     *@Date 2020/6/15 14:49
    */
    @Override
    public void insert(String result, String url, String s, Integer optype,Integer state,String type ) {
        SysPushInfo szPushInfo=new SysPushInfo();
        szPushInfo.setData(s);
        szPushInfo.setOpType(optype);
        szPushInfo.setResult(result);
        szPushInfo.setUrl(url);
        szPushInfo.setState(3);
        szPushInfo.setPushType(type);
        szPushInfo.setPushCount(1);
        //插入操作
        szPushInfoMapper.insert(szPushInfo);
    }
    /***
     *@Description //后台跑推送
     *@Params []
     *@Return void
     *@Author panyong
     *@Date 2020-06-30 9:15
    */
    @Override
    public void pushNomalInfoTask() {
        List<SysPushInfo> normalLists = szPushInfoMapper.getListsByState(1);
        if(normalLists.size()>0){
            for (SysPushInfo pushInfo:normalLists){
                synchronized (pushInfo){
                    String result=WebServiceUtil.httpRequest(pushInfo.getData(),pushInfo.getUrl());
                    pushInfo.setState(3);
                    pushInfo.setResult(result);
                    szPushInfoMapper.update(pushInfo);
                }
            }
        }
    }
}
