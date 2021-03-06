package com.invoice.web.controller.shuzu;

import com.invoice.common.annotation.Log;

import com.invoice.common.constant.UserConstants;
import com.invoice.common.core.controller.BaseController;
import com.invoice.common.core.domain.AjaxResult;
import com.invoice.common.core.page.TableDataInfo;
import com.invoice.common.enums.BusinessType;
import com.invoice.common.utils.poi.ExcelUtil;

import com.invoice.system.domain.szmodel.SysCommodityCode;
import com.invoice.system.service.SysCommodityCodeAddService;
import com.invoice.system.service.SysCommodityCodeService;
import com.invoice.system.shuzuservice.CommodityService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Desoription:商品编码web控制层
 * @Auther:panyong
 * @create:2020/6/18 9:59
 */
@Controller
@RequestMapping("/shuzu/commoditycode")
public class CommodityCodeController extends BaseController {
    private String prefix = "shuzu/commoditycode";
    @Autowired
    private SysCommodityCodeService sysCommodityCodeService;
    @RequiresPermissions("shuzu:commoditcode:view")
    @GetMapping()
    public String commoditcode(){
        return prefix + "/commoditycode";
    }
    /***
     *@Description //查询商品编码信息
     *@Params [area]
     *@Return com.invoice.common.core.page.TableDataInfo
     *@Author panyong
     *@Date 2020/6/5 17:51
     */
    @RequiresPermissions("shuzu:commoditycode:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysCommodityCode sysCommodityCode){
        startPage();
        List<SysCommodityCode> list = sysCommodityCodeService.selectCommoditList(sysCommodityCode);
        return getDataTable(list);
    }
    /***
     *@Description //查看详情列表
     *@Params [id, mmap]
     *@Return java.lang.String
     *@Author panyong
     *@Date 2020/6/18 15:04
    */
    @RequiresPermissions("shuzu:commoditycode:detail")
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Integer id, ModelMap mmap)
    {
        mmap.put("name", "sysCommodityCode");
        mmap.put("sysCommodityCode", sysCommodityCodeService.selectCommoditById(id));
        return prefix + "/detail";
    }
    /***
     *@Description //添加纳税人商品
     *@Params []
     *@Return java.lang.String
     *@Author panyong
     *@Date 2020/6/18 17:04
    */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }


    /***
     *@Description //添加保存
     *@Params [dict]
     *@Return com.invoice.common.core.domain.AjaxResult
     *@Author panyong
     *@Date 2020/6/19 9:37
    */
    @Log(title = "字典类型", businessType = BusinessType.INSERT)
    @RequiresPermissions("shuzu:commoditycode:add")
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(String cxtj,Integer cxlx) {

        if (!cxtj.isEmpty()){
            StringBuilder message = new StringBuilder();
            boolean b=sysCommodityCodeService.addCommodityCode(cxtj,cxlx,message);
            if (b){
                return success("添加成功");
            }else {
                return error(message.toString());
            }
        }else {//根据纳税人识别号查询
            return error("查询条件不能为空");
        }
    }

    /**
     * 修改保存字典类型
     */
    @Log(title = "字典类型", businessType = BusinessType.UPDATE)
    @RequiresPermissions("shuzu:commoditycode:edit")
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated SysCommodityCode sysCommodityCode) {

        StringBuilder message = new StringBuilder();
        Boolean bool=sysCommodityCodeService.editCommodityCode(sysCommodityCode,message);
        if (!bool){
            return error("修改字典'" + sysCommodityCode.getSpmc() + "'失败!");
        }
        return success("修改成功");
    }

    @Log(title = "编码管理", businessType = BusinessType.EXPORT)
    @RequiresPermissions("shuzu:commoditycode:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysCommodityCode sysCommodityCode)
    {
        List<SysCommodityCode> list = sysCommodityCodeService.selectCommoditList(sysCommodityCode);
        ExcelUtil<SysCommodityCode> util = new ExcelUtil<SysCommodityCode>(SysCommodityCode.class);
        return util.exportExcel(list, "编码数据");
    }
}
