package com.invoice.system.service;

import com.invoice.system.domain.szmodel.SysCommodityCode;

import java.util.List;
import java.util.Map;

/**
 * @Desoription://商品编码业务接口
 * @Auther:panyong
 * @create:2020/6/16 16:51
 */
public interface SysCommodityCodeService {
    //批量插入商品编码信息
    public void insetList(List<SysCommodityCode> listsc);
    //查询商品编码信息
    public List<SysCommodityCode> selectCommoditList(SysCommodityCode szCommodityCode);
    //根据id进行查询
    public SysCommodityCode selectCommoditById(Integer id);
    //判断
    String checkDictTypeUnique(SysCommodityCode sysCommodityCode);
    //添加设备商品编码到本地
    int insertCommodityCode(SysCommodityCode sysCommodityCode);
    //添加设备商品编码到本地
    boolean addCommodityCode(String cxtj, Integer cxlx,StringBuilder message);
    //添加数族商品编码
    boolean addSZCommodityCode(SysCommodityCode sysCommodityCode);
    //编辑数族商品编码
    Boolean editCommodityCode(SysCommodityCode sysCommodityCode, StringBuilder message);
}
