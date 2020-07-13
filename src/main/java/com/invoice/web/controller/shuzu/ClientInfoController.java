package com.invoice.web.controller.shuzu;

import com.invoice.common.core.controller.BaseController;
import com.invoice.common.core.domain.AjaxResult;
import com.invoice.common.core.page.TableDataInfo;
import com.invoice.system.domain.SysClientInfoPush;
import com.invoice.system.service.SysClientInfoPushService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/*/**
 * 商户信息管理
 * @author lijialin
 * @date 2020/6/19 9:20
 */
@Controller
@RequestMapping("/shuzu/clientInfo")
public class ClientInfoController extends BaseController {
    private String prefix = "shuzu/clientInfo";
    @Autowired
    private SysClientInfoPushService sysClientInfoPushService;

    @RequiresPermissions("shuzu:clientInfo:view")
    @GetMapping()
    public String clientInfo(){
        return prefix + "/clientInfo";
    }

    /**
     * 查询商户信息
     * @author lijialin
     * @date 2020/6/19 9:21
     */
    @RequiresPermissions("shuzu:clientInfo:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysClientInfoPush sysClientInfoPush){
        startPage();
        List<SysClientInfoPush> list = sysClientInfoPushService.selectClientInfoList(sysClientInfoPush);
        return getDataTable(list);
    }

    /**
     * 查询商户详情
     * @author lijialin
     * @date 2020/6/19 9:22
     * @return
     */
    @RequiresPermissions("shuzu:clientInfo:detail")
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Integer id, ModelMap mmap) {
        mmap.put("name", "clientInfo");
        mmap.put("clientInfo", sysClientInfoPushService.selClientInfoById(id));
        return prefix + "/detail";
    }

    /**
     * 查询商户详情
     * @author lijialin
     * @date 2020/6/19 9:22
     * @return
     */
    @RequiresPermissions("shuzu:clientInfo:detail")
    @PostMapping("/checkOnlineUrl")
    @ResponseBody
    public AjaxResult checkOnlineUrl(String jsbh) {
        Map result =sysClientInfoPushService.checkOnlineUrl(jsbh);
        if ("0".equals(result.get("returncode"))){
           return AjaxResult.success((String)result.get("returnmsg"));
        }else {
            return  AjaxResult.error((String) result.get("returnmsg"));
        }
    }
}
