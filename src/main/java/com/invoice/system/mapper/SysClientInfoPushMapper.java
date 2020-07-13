package com.invoice.system.mapper;

import com.invoice.system.domain.SysArea;
import com.invoice.system.domain.SysClientInfoPush;
import io.swagger.models.auth.In;

import java.util.List;

/*/**
 * 商户信息推送
 * @author lijialin
 * @date 2020/6/10 17:26
 */
public interface SysClientInfoPushMapper {

    /**
     * 添加商户信息
     * @author lijialin
     * @date 2020/6/11 10:08
     * @param sysClientInfoPush
     */
    Integer insertInfo(SysClientInfoPush sysClientInfoPush);

    /**
     * 根据纳税人识别号查询商户信息
     * @author lijialin
     * @date 2020/6/11 14:52
     * @param nsrsbh
     */
    SysClientInfoPush selClientInfo(String nsrsbh);

    /*/**
     * 修改商户信息
     * @author lijialin
     * @date 2020/6/11 16:50
     * @param [sysClientInfoPush]
     * @return java.lang.Integer
     */
   Integer updateClientInfo(SysClientInfoPush sysClientInfoPush);

    /**
     * 查询商户信息
     * @author lijialin
     * @date 2020/6/12 14:53
     * @param sysClientInfoPush
     */
   List<SysClientInfoPush> selectClientInfoList(SysClientInfoPush sysClientInfoPush);

    /**
     * 根据Id查询商户信息
     * @author lijialin
     * @date 2020/6/11 14:52
     * @param id
     */
    SysClientInfoPush selClientInfoById(Integer id);

    /**
     * 查询未开通 或关闭商户信息
     * @author lijialin
     * @date 2020/6/11 14:52
     */
    List<SysClientInfoPush> selectNotKtClientInfos();

        //电子签章状态为  99 未申请  或  0 等待审核
}
