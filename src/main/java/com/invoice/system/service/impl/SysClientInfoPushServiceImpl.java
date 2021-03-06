package com.invoice.system.service.impl;

import com.google.common.collect.Maps;
import com.invoice.common.utils.RedisUtils;
import com.invoice.system.domain.SysClientInfoPush;
import com.invoice.system.mapper.SysClientInfoPushMapper;
import com.invoice.system.service.SysClientInfoPushService;
import com.invoice.system.shuzuservice.ClienterService;
import com.invoice.system.shuzuservice.WebServiceUtil;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author lijialin
 * @description 商户信息推送业务处理实现类
 * @date 2020/6/11
 */
@Service
public class SysClientInfoPushServiceImpl implements SysClientInfoPushService {

    private static Logger logger = LoggerFactory.getLogger(SysClientInfoPushServiceImpl.class);

    @Autowired
    private SysClientInfoPushMapper clientInfoPushMapper;
    @Autowired
    private ClienterService clienterService;

    /**
     * 添加商户信息
     * @author lijialin
     * @date 2020/6/11 10:02
     * @param sysClientInfoPush
     */
    @Override
    public boolean insertClientInfo(SysClientInfoPush sysClientInfoPush,StringBuilder message) {
        //1.地区转化 中文转化为代码
        if (!comverArea(sysClientInfoPush)){
            message.append("根据中文找不到地区代码");
            return false;
        }
        //2.根据纳税人识别查询数据判断是否推送过
        SysClientInfoPush selClientInfo =clientInfoPushMapper.selClientInfo(sysClientInfoPush.getNsrsbh());
        if (selClientInfo == null) { //还没推送过商户信息
            String returncode,returnmsg = Strings.EMPTY;
            //3.调用数族商户信息推送接口
            String responResult =clienterService.pushClientInfo(sysClientInfoPush);
            Map<String, Object> responMap = WebServiceUtil.parseXml(responResult);
            returncode =WebServiceUtil.getValToMap(responMap, "returncode");
            returnmsg =WebServiceUtil.getValToMap(responMap, "returnmsg");
            message.append(returnmsg);
            if ("0".equals(returncode)){
                //成功情况 保存商户信息
                sysClientInfoPush.setMerchantid(WebServiceUtil.getValToMap(responMap, "merchantid"));
                sysClientInfoPush.setCreateTime(new Date().getTime());
                clientInfoPushMapper.insertInfo(sysClientInfoPush);
                //获取数族的商户信息 回写 商户信息参数
                getSzClientInfo(sysClientInfoPush);
                return true;
            }else {
                //保存失败记录到 log 表
                return false;
            }
        }else {
            message.append("该商户已存在不允许重复添加！");
            return false;
        }
    }

    //1.获取数族的商户信息 回写 商户信息参数
    private void getSzClientInfo(SysClientInfoPush sysClientInfoPush){
        new Thread(){
            public void run() {
                //调用数族 验证
                updateClientInfoKtztParams(sysClientInfoPush);
            }
        }.start();
    }


    /**
     * 修改商户信息
     * @author lijialin
     * @date 2020/6/11 10:02
     * @param sysClientInfoPush
     */
    @Override
    public boolean updateClientInfo(SysClientInfoPush sysClientInfoPush,StringBuilder message) {
        //1.地区转化 中文转化为代码
        if (!comverArea(sysClientInfoPush)){
            message.append("根据中文地区名称找不到地区代码");
            return false;
        }
        //1.根据纳税人识别查询数据判断是否推送过
        SysClientInfoPush selClientInfo =clientInfoPushMapper.selClientInfo(sysClientInfoPush.getNsrsbh());
        if (selClientInfo==null) { //还没推送过商户信息
            message.append("该商户不存在，无法做变更操作");
            return false;
        }else {
            String returncode,returnmsg = Strings.EMPTY;
            //调用数族商户信息推送接口
            String responResult =clienterService.pushClientInfo(sysClientInfoPush);
            Map<String, Object> responMap = WebServiceUtil.parseXml(responResult);
            returncode =WebServiceUtil.getValToMap(responMap, "returncode");
            returnmsg =WebServiceUtil.getValToMap(responMap, "returnmsg");
            message.append(returnmsg);
            if ("0".equals(returncode)){
                //更新数据商户信息
                clientInfoPushMapper.updateClientInfo(sysClientInfoPush);
            }else {
                //保存失败记录到 log 表
                return false;
            }
            return true;
        }
    }


    /**
     * 地区由中文名转化Id
     * @author lijialin
     * @date 2020/6/12 11:24
     * @param sysClientInfoPush
     */
    private boolean comverArea(SysClientInfoPush sysClientInfoPush){
        String address =RedisUtils.getAreaIdByAreaName(sysClientInfoPush.getProvincename(),sysClientInfoPush.getCityname());
        if (address ==null){
            logger.info("找不到地区代码");
            return false;
        }
        String[] addressArr = address.split("#");
        if (addressArr.length ==1){
            sysClientInfoPush.setProvinceno(addressArr[0]);
        }else if (addressArr.length ==2){
            sysClientInfoPush.setProvinceno(addressArr[0]);
            sysClientInfoPush.setCityno(addressArr[1]);
        }
        return true;
    }

    public List<SysClientInfoPush> selectClientInfoList(SysClientInfoPush sysClientInfoPush) {
        return clientInfoPushMapper.selectClientInfoList(sysClientInfoPush);
    }

    /**
     * 根据Id 获取商户详情
     * @author lijialin
     * @date 2020/6/18 10:06
     * @param
     * @return com.invoice.system.domain.SysClientInfoPush
     */
    public SysClientInfoPush selClientInfoById(Integer id) {

        return clientInfoPushMapper.selClientInfoById(id);
    }

    /**
     * 回写商户信息开通状态、返回信息、返回错误、托管的服务标识
     * @author lijialin
     * @date 2020/6/18 11:41
     * @param sysClientInfoPush
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateClientInfoKtztParams(SysClientInfoPush sysClientInfoPush) {
        //调用数族 验证
        String responXml = clienterService.selClientInfo(sysClientInfoPush.getNsrsbh());
        Map map = WebServiceUtil.parseXml(responXml);
        String rtnCode = WebServiceUtil.getValToMap(map, "returncode");
        String rtnMsg = WebServiceUtil.getValToMap(map, "returnmsg");
        if ("0".equals(rtnCode)){
            String ktzt = WebServiceUtil.getValToMap(map, "ktzt");
            sysClientInfoPush.setRtnCode(rtnCode);
            sysClientInfoPush.setRtnMsg(rtnMsg);
            sysClientInfoPush.setKtzt(ktzt);
            //sysClientInfoPush.setZdytgwfbs(WebServiceUtil.getValToMap(map, "zdytgwfbs"));
            sysClientInfoPush.seteSignet(WebServiceUtil.getValToMap(map, "eSignet"));
            clientInfoPushMapper.updateClientInfo(sysClientInfoPush);
        }else {
            sysClientInfoPush.setRtnCode(rtnCode);
            sysClientInfoPush.setRtnMsg(rtnMsg);
            clientInfoPushMapper.updateClientInfo(sysClientInfoPush);
        }

    }

    @Override
    public Map checkOnlineUrl(String jsbh) {
        String responXml =clienterService.selTerminalStatus(jsbh);
        Map map = WebServiceUtil.parseXml(responXml);
        String rtnCode = WebServiceUtil.getValToMap(map, "returncode");
        String rtnMsg = WebServiceUtil.getValToMap(map, "returnmsg");
        Map resultMap = Maps.newHashMap();
        resultMap.put("returncode", rtnCode);
        resultMap.put("returnmsg", rtnMsg);
        return resultMap;
    }

}
