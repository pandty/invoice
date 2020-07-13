package com.invoice.system.service;

import com.invoice.system.domain.SysArea;

import java.util.List;

/**
 * @Desoription:地区业务层
 * @Auther:panyong
 * @create:2020/6/1 10:07
 */
public interface ISysAreaService {
    /***
     *@Description //根据条件分页查询地区列表
     *@Params [area]
     *@Return java.util.List<com.invoice.system.domain.SysArea>
     *@Author panyong
     *@Date 2020/6/1 10:14
    */
    public List<SysArea> selectAreaList(SysArea area);
}
