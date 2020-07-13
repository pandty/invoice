package com.invoice.quartz.task;

import com.invoice.common.utils.DateUtils;
import com.invoice.system.domain.SysClientInfoPush;
import com.invoice.system.mapper.SysClientInfoPushMapper;
import com.invoice.system.service.SysClientInfoPushService;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author lijialin
 * @description 商户信息定时任务
 * @date 2020/6/18
 */
@Component("clientInfoTask")
public class ClientInfoTask {
    @Autowired
    private SysClientInfoPushMapper sysClientInfoPushMapper;

    @Autowired
    private SysClientInfoPushService sysClientInfoPushService;

    Logger logger = Logger.getLogger(ClientInfoTask.class);

    /**
     * 定时获取商户信息开通状态
     * @author lijialin
     * @date 2020/6/18 9:39
     */
    public void updateKtztParams() {
        logger.info("开始执行变更商户信息的电子签章状态及开通状态");
        //获取当前的非结果的状态 99 未申请  0 等待审核   （2 驳回  1 审核通过） 最终结果不考虑
        List<SysClientInfoPush> sysClientInfoPushs = sysClientInfoPushMapper.selectNotKtClientInfos();
        for (SysClientInfoPush sysClientInfoPush : sysClientInfoPushs){
            try {
                sysClientInfoPushService.updateClientInfoKtztParams(sysClientInfoPush);
            }catch (Exception ex){
                logger.info(ex.getMessage(),ex);
            }
        }
    }
}
