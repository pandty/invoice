package com.invoice.system.mapper;


import com.invoice.system.domain.szmodel.SysCommodityCodeAdd;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
/***
 *@Description //商户的开票商品编码信息操作数据接口层
 *@Params
 *@Return 
 *@Author panyong
 *@Date 2020/6/22 17:39
*/
@Repository
public interface SysCommodityCodeAddMapper {


    int insert(SysCommodityCodeAdd record);

    //根据商品名称和纳税号查询
    SysCommodityCodeAdd selectByNMc(@Param("nsrsbh") String nsrsbh,@Param("mc") String mc);
    //修改状态
    void updateStatus(SysCommodityCodeAdd sysCommodityCodeAdd);
    //根据进行修改
    void updateByNMc(SysCommodityCodeAdd sysCommodityCodeAdd);
    //根据id进行修改
    void updateById(SysCommodityCodeAdd sysCommodityCodeAdd);
    //根据编码
    SysCommodityCodeAdd selectByBM(@Param("nsrsbh") String nsrsbh,@Param("bm")String bm);
    //根据id进行删除
    void deleteById(@Param("id") Integer id);
}