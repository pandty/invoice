package com.invoice.web.controller.shuzu;

import com.invoice.common.core.controller.BaseController;
import com.invoice.common.core.page.TableDataInfo;
import com.invoice.system.domain.szmodel.SysCommodity;
import com.invoice.system.service.SysCommoditService;
import com.invoice.system.shuzuservice.CommodityService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Desoription:商品编码信息管理
 * @Auther:panyong
 * @create:2020/6/5 15:56
 */
@Controller
@RequestMapping("/shuzu/commodity")
public class CommodityController  extends BaseController {
    private String prefix = "shuzu/commodity";
    @Autowired
    private SysCommoditService szCommoditService;
    @Autowired
    private CommodityService commodityService;
    @RequiresPermissions("shuzu:commodit:view")
    @GetMapping()
    public String commodit(){
        //commodityService.getCommodity("SSBMQQ",33.0+"");
        return prefix + "/commodity";
    }
    /***
     *@Description //查询商品编码信息
     *@Params [area]
     *@Return com.invoice.common.core.page.TableDataInfo
     *@Author panyong
     *@Date 2020/6/5 17:51
    */
    @RequiresPermissions("shuzu:commodity:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysCommodity szCommodity){
        startPage();
        List<SysCommodity> list = szCommoditService.selectCommoditList(szCommodity);
        return getDataTable(list);
    }
    @RequiresPermissions("shuzu:commodity:list")
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Integer id, SysCommodity szCommodity) {
        szCommodity=szCommoditService.selectCommoditById(id);
        return "system/dict/data/data";
    }
}
