package com.invoice.system.service;

/**
 * @Desoription:推送日志业务接口
 * @Auther:panyong
 * @create:2020/6/15 14:08
 */

public interface SysPushInfoService {
    /***
     *@Description //插入推送记录
     *@Params [result, url, s, optype, state, type]
     *@Return void
     *@Author panyong
     *@Date 2020/6/15 14:47
    */
    public void insert(String result, String url, String s, Integer optype,Integer state,String type);
    /***
     *@Description //后台跑推送 主要调用接口失败进行补偿推送
     *@Params []
     *@Return void
     *@Author panyong
     *@Date 2020-07-01 10:45
    */
    public void pushNomalInfoTask();
}
