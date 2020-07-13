package com.invoice.system.service;

import com.invoice.system.domain.SysClientInfoPush;

import java.util.List;
import java.util.Map;

/**
 * @author lijialin
 * @description 商户信息推推送业务处理接口
 * @date 2020/6/11
 */
public interface SysClientInfoPushService {
    /**
     * 添加商户信息
     * @author lijialin
     * @date 2020/6/11 10:02
     * @param sysClientInfoPush
     */
    boolean insertClientInfo(SysClientInfoPush sysClientInfoPush,StringBuilder message);

    /**
     * 修改商户信息
     * @author lijialin
     * @date 2020/6/11 10:02
     * @param sysClientInfoPush
     */
    public boolean updateClientInfo(SysClientInfoPush sysClientInfoPush,StringBuilder message);

    /**
     * 查询商户信息
     * @author lijialin
     * @date 2020/6/12 14:58
     */
    public List<SysClientInfoPush> selectClientInfoList(SysClientInfoPush szCommodity);

    /**
     * 根据主键Id 查询商户信息
     * @author lijialin
     * @date 2020/6/16 10:09
     * @param id
     * @return
     */
    public SysClientInfoPush selClientInfoById(Integer id);

    /**
     * 回写商户信息签章状态、开通状态、返回信息、返回错误、托管的服务标识
     * @author lijialin
     * @date 2020/6/18 11:41
     * @param sysClientInfoPush
     */
    void updateClientInfoKtztParams(SysClientInfoPush sysClientInfoPush);

    /**
     * 校验设备状态
     * @author lijialin
     * @date 2020/6/23 15:39
     * @param jsbh
     */
    Map checkOnlineUrl(String jsbh);

}
