package com.invoice.web.controller.api;

import com.invoice.common.annotation.Log;
import com.invoice.common.constant.UserConstants;
import com.invoice.common.core.controller.BaseController;
import com.invoice.common.core.domain.AjaxResult;
import com.invoice.common.enums.BusinessType;
import com.invoice.framework.util.ShiroUtils;
import com.invoice.system.domain.SysClientInfoPush;
import com.invoice.system.domain.SysMenu;
import com.invoice.system.domain.szmodel.SignatureToken;
import com.invoice.system.service.SysClientInfoPushService;
import com.invoice.system.shuzuservice.WebServiceUtil;
import org.apache.logging.log4j.util.Strings;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

/**
 * @author lijialin
 * @description 商户信息管理
 * @date 2020/6/11
 */

@Controller
@RequestMapping("/api/clientInfo")
public class SysClientInfoPushController extends BaseController {

    private String prefix = "system/clientInfo";

    @Autowired
    private SysClientInfoPushService sysClientInfoPushService;

    /**
     * 新增保存菜单
     */
    @ResponseBody
    @PostMapping(value="/add")
    public AjaxResult addSave(@Valid SysClientInfoPush sysClientInfoPush, BindingResult bindingResult,SignatureToken signatureToken) {
        StringBuilder message = new StringBuilder();
        //验证秘钥
        boolean bool=WebServiceUtil.verifyToken(signatureToken,message);
        if (!bool){
            return AjaxResult.error(message.toString());
        }
        //校验请求参数
        if (Strings.isBlank(sysClientInfoPush.getChannelId())&&Strings.isBlank(sysClientInfoPush.getSalerMobile())){
            return AjaxResult.error(bindingResult,"channelId和salerMobile必须至少填一个");
        }
        if(bindingResult.hasErrors()){
            return AjaxResult.error(bindingResult);
        }

        if (sysClientInfoPushService.insertClientInfo(sysClientInfoPush,message)){
            return AjaxResult.success(message.toString());
        }else{
            return AjaxResult.error(message.toString());
        }
    }

    /**
     * 新增保存菜单
     */
    @ResponseBody
    @PostMapping(value="/update")
    public AjaxResult updateSave(@Valid SysClientInfoPush sysClientInfoPush, BindingResult bindingResult,SignatureToken signatureToken) {
        StringBuilder message = new StringBuilder();
        //验证秘钥
        boolean bool=WebServiceUtil.verifyToken(signatureToken,message);
        if (!bool){
            return AjaxResult.error(message.toString());
        }
        //校验请求参数
        if (Strings.isBlank(sysClientInfoPush.getChannelId())&&Strings.isBlank(sysClientInfoPush.getSalerMobile())){
            return AjaxResult.error(bindingResult,"channelId和salerMobile必须至少填一个");
        }
        if(bindingResult.hasErrors()){
            return AjaxResult.error(bindingResult);
        }
        if (sysClientInfoPushService.updateClientInfo(sysClientInfoPush,message)){
            return AjaxResult.success(message.toString());
        }else{
            return AjaxResult.error(message.toString());
        }
    }

}
