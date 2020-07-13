package com.invoice.system.mapper;

import com.invoice.system.domain.szmodel.SysCommodityCode;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Desoription:商品编码数据服务层
 * @Auther:panyong
 * @create:2020/6/16 16:59
 */
@Repository
public interface SysCommodityCodeMapper {
    //批量插入数据
    void insertList(List<SysCommodityCode> ls);
    //根据条件查询商品
    List<SysCommodityCode> selectCommoditCodeList(SysCommodityCode szCommodityCode);
    //根据id单个查询
    SysCommodityCode selectCommoditCodeById(Integer id);
    //根据纳税人识别号删除
    void deleteByNsrsbh(String nsrsbh);
    //根据机身编号查询
    void deleteByJsbh(String jsbh);
}
