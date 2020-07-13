package com.invoice.system.service.impl;

import com.google.common.collect.Maps;
import com.invoice.system.domain.szmodel.SysCommodityCodeAdd;
import com.invoice.system.mapper.SysCommodityCodeAddMapper;
import com.invoice.system.service.SysCommodityCodeAddService;
import com.invoice.system.shuzuservice.CommodityService;
import com.invoice.system.shuzuservice.WebServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Desoription:商品编码自定义业务层
 * @Auther:panyong
 * @create:2020/6/20 11:14
 */
@Service
public class SysCommodityCodeAddServiceImpl implements SysCommodityCodeAddService {
    @Autowired
    private SysCommodityCodeAddMapper sysCommodityCodeAddMapper;
    @Autowired
    private CommodityService commodityService;
    /***
     *@Description //添加商品编码业务处理
     *@Params [sysCommodityCodeAdd, message]
     *@Return boolean
     *@Author panyong
     *@Date 2020/6/22 10:26
    */
    @Override
    public boolean addSZCommodityCode(SysCommodityCodeAdd sysCommodityCodeAdd,StringBuilder message) {
        if (sysCommodityCodeAdd!=null&&sysCommodityCodeAdd.getMc()!=null&&sysCommodityCodeAdd.getNsrsbh()!=null) {
            //根据纳税号和商品名称进行查询
            SysCommodityCodeAdd sc = sysCommodityCodeAddMapper.selectByNMc(sysCommodityCodeAdd.getNsrsbh(), sysCommodityCodeAdd.getMc());
            if (sc!=null&&sc.getStatus()==1){
                message.append("该商品已添加成功");
                return false;
            }else{
                //没有则进行记录在表中 status=0为未处理状态
                sysCommodityCodeAdd.setStatus(0);
                if(sc==null){//插入表
                    sysCommodityCodeAddMapper.insert(sysCommodityCodeAdd);
                }else{
                    //根据id进行修改信息
                    sysCommodityCodeAdd.setId(sc.getId());
                    sysCommodityCodeAddMapper.updateById(sysCommodityCodeAdd);
                }

                //调用接口添加商品
                String result=commodityService.addCommodity(sysCommodityCodeAdd);
                Map map = Maps.newHashMap();
                map=WebServiceUtil.parseXml(result);
                String returncode =WebServiceUtil.getValToMap(map, "returncode");
                String returnmsg =WebServiceUtil.getValToMap(map, "returnmsg");
                if (!returncode.equals("0")){
                    //添加失败则返回错误信息
                    message.append(returnmsg);
                    return false;
                }else{
                    //添加成功修改状态为添加成功状态status=1
                    sysCommodityCodeAdd.setStatus(1);
                    String bm=WebServiceUtil.getValToMap(map, "bm");
                    sysCommodityCodeAdd.setBm(bm);
                    sysCommodityCodeAddMapper.updateById(sysCommodityCodeAdd);
                    return true;
                }
            }

        }
        message.append("参数错误");
        return false;
    }

    @Override
    public boolean editSZCommodityCode(SysCommodityCodeAdd sysCommodityCodeAdd, StringBuilder message) {
        if (sysCommodityCodeAdd!=null&&!sysCommodityCodeAdd.getBm().isEmpty()&&!sysCommodityCodeAdd.getNsrsbh().isEmpty()) {
            //根据纳税号和商品名称进行查询
            SysCommodityCodeAdd sc = sysCommodityCodeAddMapper.selectByBM(sysCommodityCodeAdd.getNsrsbh(),sysCommodityCodeAdd.getBm());
            if (sc!=null&&sc.getStatus()==1){

                String result=commodityService.addCommodity(sysCommodityCodeAdd);
                Map map = Maps.newHashMap();
                map=WebServiceUtil.parseXml(result);
                String returncode =WebServiceUtil.getValToMap(map, "returncode");
                String returnmsg =WebServiceUtil.getValToMap(map, "returnmsg");
                if (!returncode.equals("0")){
                    //添加失败则返回错误信息
                    message.append(returnmsg);
                    return false;
                }else{
                    //添加成功修改状态为添加成功状态status=1
                    sysCommodityCodeAdd.setStatus(1);
                    sysCommodityCodeAdd.setId(sc.getId());
                    String bm=WebServiceUtil.getValToMap(map, "bm");
                    sysCommodityCodeAdd.setBm(bm);
                    sysCommodityCodeAddMapper.updateById(sysCommodityCodeAdd);
                    return true;
                }
            }else {
                message.append("未找到该产品");
                return false;
            }
        }
        message.append("参数错误");
        return false;
    }

    @Override
    public boolean removeCommodityCodeAdd(String bm, String jsbh, String nsrsbh ,StringBuilder message) {
        SysCommodityCodeAdd sc = sysCommodityCodeAddMapper.selectByBM(nsrsbh,bm);
        if(sc!=null){
            String result=commodityService.removeCommodity(bm,jsbh,nsrsbh);
            Map map = Maps.newHashMap();
            map=WebServiceUtil.parseXml(result);
            String returncode =WebServiceUtil.getValToMap(map, "returncode");
            String returnmsg =WebServiceUtil.getValToMap(map, "returnmsg");
            if (!returncode.equals("0")){
                //添加失败则返回错误信息
                message.append(returnmsg);
                return false;
            }else{
                //添加成功修改状态为添加成功状态status=1
                sysCommodityCodeAddMapper.deleteById(sc.getId());
                return true;
            }
        }

        return false;
    }

}
