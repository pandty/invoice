package com.invoice.system.service;

import com.invoice.system.domain.szmodel.SysCommodity;

import java.util.List;

/**
 * @Desoription:商品编码业务处理接口
 * @Auther:panyong
 * @create:2020/6/4 15:45
 */
public interface SysCommoditService {
    //批量插入商品编码信息
    public void insetList(List<SysCommodity> listsc);
    //查询商品编码信息
    public List<SysCommodity> selectCommoditList(SysCommodity szCommodity);
    //根据id进行查询
    SysCommodity selectCommoditById(Integer id);
}
