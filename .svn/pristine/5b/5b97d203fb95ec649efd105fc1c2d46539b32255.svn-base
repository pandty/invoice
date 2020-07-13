package com.invoice.web.controller.api;

/**
 * @Desoription:商品编码控制层
 * @Auther:panyong
 * @create:2020/6/19 17:50
 */

import com.invoice.common.core.domain.AjaxResult;
import com.invoice.system.domain.szmodel.SignatureToken;
import com.invoice.system.domain.szmodel.SysCommodityCode;
import com.invoice.system.domain.szmodel.SysCommodityCodeAdd;
import com.invoice.system.service.SysCommodityCodeAddService;
import com.invoice.system.service.SysCommodityCodeService;
import com.invoice.system.shuzuservice.WebServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;

/**
 * @Desoription:商品相关接口接口
 * @Auther:panyong
 * @create:2020/6/12 10:46
 */
@Controller
@RequestMapping("/api/commodity")
public class CommodityAPIController {
    @Autowired
    private SysCommodityCodeAddService sysCommodityCodeAddService;
    @Autowired
    private SysCommodityCodeService sysCommodityCodeService;
    /***
     *@Description //自定义商品编码设置
     * 对商户的开票商品编码信息进行添加
     *@Params [szIssueInvoicePush]
     *@Return com.invoice.common.core.domain.AjaxResult
     *@Author panyong
     *@Date 2020/6/15 15:38
     */
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Valid SysCommodityCodeAdd sysCommodityCodeAdd,
                     BindingResult bindingResult, SignatureToken signatureToken) {
        if(bindingResult.hasErrors()){
            return  AjaxResult.error(bindingResult);
        }
        StringBuilder message = new StringBuilder();
        //验证秘钥
        boolean bool=WebServiceUtil.verifyToken(signatureToken,message);
        if (!bool){
            return AjaxResult.error(message.toString());
        }
        bool=sysCommodityCodeAddService.addSZCommodityCode(sysCommodityCodeAdd,message);
        if (bool){
            return AjaxResult.success();
        }else{
            return AjaxResult.error(message.toString());
        }
    }
    /***
     *@Description //对商户的开票商品编码信息进行修改
     *@Params [sysCommodityCodeAdd, bindingResult]
     *@Return com.invoice.common.core.domain.AjaxResult
     *@Author panyong
     *@Date 2020/6/22 16:20
    */
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Valid SysCommodityCodeAdd sysCommodityCodeAdd,
                               BindingResult bindingResult, SignatureToken signatureToken) {
        if(bindingResult.hasErrors()){
            return  AjaxResult.error(bindingResult);
        }
        StringBuilder message = new StringBuilder();
        //验证秘钥
        boolean bool=WebServiceUtil.verifyToken(signatureToken,message);
        if (!bool){
            return AjaxResult.error(message.toString());
        }
        bool=sysCommodityCodeAddService.editSZCommodityCode(sysCommodityCodeAdd,message);
        if (bool){
            return AjaxResult.success();
        }else{
            return AjaxResult.error(message.toString());
        }
    }
    /***
     *@Description //编码删除
     *@Params [bm, jsbh, nsrsbh]
     *@Return com.invoice.common.core.domain.AjaxResult
     *@Author panyong
     *@Date 2020/6/23 11:11
    */
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult removeSave(String bm,String jsbh,String nsrsbh, SignatureToken signatureToken) {
        if(bm.isEmpty()){
            return  AjaxResult.error("编码不能为空");
        }
        if(jsbh.isEmpty()){
            return  AjaxResult.error("机身编号不能为空");
        }
        if(nsrsbh.isEmpty()){
            return  AjaxResult.error("纳税人识别号不能为空");
        }
        StringBuilder message = new StringBuilder();
        //验证秘钥
        boolean bool=WebServiceUtil.verifyToken(signatureToken,message);
        if (!bool){
            return AjaxResult.error(message.toString());
        }
        bool=sysCommodityCodeAddService.removeCommodityCodeAdd(bm,jsbh,nsrsbh,message);
        if (bool){
            return AjaxResult.success();
        }else{
            return AjaxResult.error(message.toString());
        }
    }

    /**
     * 查询编码信息
     * @param sysCommodityCode
     * @Author panyong
     * @return AjaxResult
     */
    @PostMapping("/list")
    @ResponseBody
    public AjaxResult getList(SysCommodityCode sysCommodityCode, SignatureToken signatureToken){
        if(sysCommodityCode.getJsbh()==null&&sysCommodityCode.getNsrsbh().isEmpty()){
            return  AjaxResult.error("纳税人识别号不能为空");
        }
        StringBuilder message = new StringBuilder();
        //验证秘钥
        boolean bool=WebServiceUtil.verifyToken(signatureToken,message);
        if (!bool){
            return AjaxResult.error(message.toString());
        }
        List<SysCommodityCode> list = sysCommodityCodeService.selectCommoditList(sysCommodityCode);
        return AjaxResult.success(list);
    }
}
