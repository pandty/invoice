package com.invoice.system.domain.szmodel;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
/***
 *@Description //推送接口日志
 *@Params
 *@Author panyong
 *@Date 2020/6/15 17:46
*/
public class SysPushInfo {
    public static Map<Integer, String> opTypeMap = new HashMap<Integer, String>();
    static{
        opTypeMap.put(1,"增值税普通电子发票");
        opTypeMap.put(2, "商户信息推送");
        opTypeMap.put(3, "发票查询");
        opTypeMap.put(4, "获取商品编码");
        opTypeMap.put(5, "商品编码添加");
        opTypeMap.put(6, "查询终端状态");
        opTypeMap.put(7, "查询商户信息");
        opTypeMap.put(100, "推送发票到qyt");
    }

    private Integer id;

    /**
     * 推送接口
     */
    private String url;

    /**
     * Post时的json
     */
    private String data;

    /**
     * 推送模式：post,get
     */
    private String pushType;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 状态1-待推送 2-推送有误 3已推送
     */
    private Integer state;

    /**
     * 推送次数
     */
    private Integer pushCount;

    /**
     * 插入时间
     */
    private Date insertTime;

    /**
     * 推送时间
     */
    private Date pushTime;

    /**
     * 花费时间
     */
    private Integer takeTime;

    /**
     * 接口类型
     */
    private Integer opType;

    /**
     * 返回信息
     */
    private String result;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getPushType() {
        return pushType;
    }

    public void setPushType(String pushType) {
        this.pushType = pushType;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getPushCount() {
        return pushCount;
    }

    public void setPushCount(Integer pushCount) {
        this.pushCount = pushCount;
    }

    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    public Date getPushTime() {
        return pushTime;
    }

    public void setPushTime(Date pushTime) {
        this.pushTime = pushTime;
    }

    public Integer getTakeTime() {
        return takeTime;
    }

    public void setTakeTime(Integer takeTime) {
        this.takeTime = takeTime;
    }

    public Integer getOpType() {
        return opType;
    }

    public void setOpType(Integer opType) {
        this.opType = opType;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}