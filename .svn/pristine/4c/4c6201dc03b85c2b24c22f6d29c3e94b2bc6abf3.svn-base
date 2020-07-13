package com.invoice.system.mapper;

import com.invoice.system.domain.szmodel.SysCommodity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Desoription:商品编码数据层
 * @Auther:panyong
 * @create:2020/6/4 15:55
 */
@Repository
public interface SysCommoditMapper {
    /***
     *@Description //批量插入商品编码信息
     *@Params [listsc]
     *@Return void
     *@Author panyong
     *@Date 2020/6/5 9:11
    */
    public void insetList(List<SysCommodity> listsc);

    public List<SysCommodity> selectCommoditList(SysCommodity szCommodity);

    SysCommodity selectCommoditById(Integer id);
}
