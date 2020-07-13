package com.invoice.web.controller.api;

import com.invoice.common.core.controller.BaseController;
import com.invoice.common.core.domain.AjaxResult;
import com.invoice.system.domain.szmodel.SignatureToken;
import com.invoice.system.domain.szmodel.SysInvoice;
import com.invoice.system.domain.szmodel.SysIssueInvoicePush;
import com.invoice.system.service.SysInvoiceService;
import com.invoice.system.service.SysIssueInvoicePushService;
import com.invoice.system.shuzuservice.WebServiceUtil;
import jdk.nashorn.internal.parser.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.Map;

/**
 * @Desoription:发票api接口
 * @Auther:panyong
 * @create:2020/6/12 10:46
 */
@Controller
@RequestMapping("/api/invoice")
public class InvoiceAPIController extends BaseController {
    @Autowired
    private SysIssueInvoicePushService szIssueInvoicePushService;
    @Autowired
    private SysInvoiceService sysInvoiceService;
    /***
     *@Description //增值税普通电子发票
     *@Params [szIssueInvoicePush]
     *@Return com.invoice.common.core.domain.AjaxResult
     *@Author panyong
     *@Date 2020/6/15 15:38
    */
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave1(@Valid @RequestBody SysIssueInvoicePush szIssueInvoicePush,
                                BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return  AjaxResult.error(bindingResult);
        }
        StringBuilder message = new StringBuilder();
        SignatureToken signatureToken=new SignatureToken();
        signatureToken.setTime(szIssueInvoicePush.getTime());
        signatureToken.setToken(szIssueInvoicePush.getToken());
        //验证秘钥
        boolean bool=WebServiceUtil.verifyToken(signatureToken,message);
        if (!bool){
            return AjaxResult.error(message.toString());
        }
        //开票操作
        Map map=szIssueInvoicePushService.insertIssueInvoicePush(szIssueInvoicePush,message);
        if (map!=null){
            return AjaxResult.success(map);
        }else{
            return AjaxResult.error(message.toString());
        }
    }

    /***
     *@Description //发票查询
     *@Params [jsbh机身编号, fplxdm发票类型代码, cxfs查询方式, cxtj查询条件]
     *@Return com.invoice.common.core.domain.AjaxResult
     *@Author panyong
     *@Date 2020/6/23 14:46
    */
    @PostMapping("/getInvoice")
    @ResponseBody
    public AjaxResult getInvoice(SignatureToken signatureToken,String jsbh,String fplxdm,String cxfs,String cxtj){
        if (jsbh.isEmpty()){
            return  AjaxResult.error("机身编号不能为空");
        }
        if (fplxdm.isEmpty()){
            return  AjaxResult.error("发票类型代码不能为空");
        }
        if (cxtj.isEmpty()){
            return  AjaxResult.error("查询条件不能为空");
        }
        if (cxfs.isEmpty()){
            return  AjaxResult.error("查询方式不能为空");
        }
        StringBuilder message = new StringBuilder();
        //验证秘钥
        boolean bool=WebServiceUtil.verifyToken(signatureToken,message);
        if (!bool){
            return AjaxResult.error(message.toString());
        }
        //查询发票操作
        SysInvoice sysInvoice=sysInvoiceService.getInvoice(jsbh,fplxdm,cxfs, cxtj,message);
        if (sysInvoice!=null){
            return AjaxResult.success(sysInvoice);
        }else {
            return AjaxResult.error(message.toString());
        }
    }

}
