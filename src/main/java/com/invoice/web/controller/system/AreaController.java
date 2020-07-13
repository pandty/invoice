package com.invoice.web.controller.system;

import com.invoice.common.core.controller.BaseController;
import com.invoice.common.core.page.TableDataInfo;
import com.invoice.system.domain.SysArea;
import com.invoice.system.service.ISysAreaService;
import com.invoice.system.shuzuservice.CommodityService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Desoription:地区信息
 * @Auther:panyong
 * @create:2020/5/30 15:59
 */
@Controller
@RequestMapping("/system/area")
public class AreaController  extends BaseController {

    private String prefix = "system/area";
    @Autowired
    private ISysAreaService AreaService;
    @Autowired
    private CommodityService commodityService;
    @RequiresPermissions("system:area:view")
    @GetMapping()
    public String area(){
        //commodityService.getCommodity("SSBMQQ",33.0+"");
        return prefix + "/area";
    }

    @RequiresPermissions("system:area:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysArea area){
        startPage();
        List<SysArea> list = AreaService.selectAreaList(area);
        return getDataTable(list);
    }

}
