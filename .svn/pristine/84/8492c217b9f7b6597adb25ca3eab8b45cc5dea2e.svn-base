package com.invoice.system.service.impl;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.invoice.system.domain.szmodel.SysCommodityCode;
import com.invoice.system.mapper.SysCommodityCodeMapper;
import com.invoice.system.service.SysCommodityCodeService;
import com.invoice.system.shuzuservice.CommodityService;
import com.invoice.system.shuzuservice.WebServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Desoription:商品列表业务实现类
 * @Auther:panyong
 * @create:2020/6/16 16:52
 */
@Service
public class SysCommodityCodeServiceImpl implements SysCommodityCodeService {
    @Autowired
    private SysCommodityCodeMapper sysCommodityCodeMapper;

    @Autowired
    private CommodityService commodityService;
    /***
     *@Description //批量插入
     *@Params [listsc]
     *@Return void
     *@Author panyong
     *@Date 2020/6/18 9:26
    */
    @Override
    @Transactional
    public void insetList(List<SysCommodityCode> listsc) {
        //分批插入
        List<List<SysCommodityCode>> list= Lists.partition(listsc, 100);
        for (List<SysCommodityCode> ls :list) {
            sysCommodityCodeMapper.insertList(ls);
        }

    }

    /***
     *@Description //查询商品
     *@Params [szCommodityCode]
     *@Return java.util.List<com.invoice.system.domain.szmodel.SysCommodityCode>
     *@Author panyong
     *@Date 2020/6/18 10:20
    */
    @Override
    public List<SysCommodityCode> selectCommoditList(SysCommodityCode szCommodityCode) {
        return sysCommodityCodeMapper.selectCommoditCodeList(szCommodityCode);
    }

    @Override
    public SysCommodityCode selectCommoditById(Integer id) {
        return sysCommodityCodeMapper.selectCommoditCodeById(id);
    }

    @Override
    public String checkDictTypeUnique(SysCommodityCode sysCommodityCode) {
        return "1";
    }

    @Override
    public int insertCommodityCode(SysCommodityCode sysCommodityCode) {
        return 0;
    }

    @Override
    public boolean addCommodityCode(String cxtj, Integer cxlx,StringBuilder message) {
        try {
            String result=commodityService.getCommodityCode(cxtj,cxlx);
            Map map = Maps.newHashMap();
            map=WebServiceUtil.parseXml(result);
            String returncode =WebServiceUtil.getValToMap(map, "returncode");
            String returnmsg =WebServiceUtil.getValToMap(map, "returnmsg");
            if (!returncode.equals("0")){
                message.append(returnmsg);
                return false;
            }
            List spbmlist = WebServiceUtil.getValToMap(map, "spbm");
            Map msap = (Map)spbmlist.get(0);
            List list =(List) msap.get("group");
            List<SysCommodityCode> listsc=new ArrayList<>();
            for (int i=0;i<list.size();i++){
                Map bmxxmap=(Map)list.get(i);
                //将map的key转换成小写和将值转成String
                Map mapc=WebServiceUtil.transformUpperCase(bmxxmap);
                SysCommodityCode sysCommodityCode = JSON.parseObject(JSON.toJSONString(mapc), SysCommodityCode.class);
                listsc.add(sysCommodityCode);
            }
            if (listsc.size()>0){
                insetList(listsc,cxtj,cxlx);
            }
        } catch (Exception e) {
            message.append("程序异常");
            return false;
        }
        return true;
    }
    /***
     *@Description //对商户的开票商品编码信息进行添加，修改等操作。
     *@Params [sysCommodityCode]
     *@Return boolean
     *@Author panyong
     *@Date 2020/6/19 18:20
    */
    @Override
    public boolean addSZCommodityCode(SysCommodityCode sysCommodityCode) {

        return false;
    }
    /***
     *@Description //对商户的开票商品编码信息进行修改等操作。
     *@Params [sysCommodityCode]
     *@Return boolean
     *@Author panyong
     *@Date 2020/6/19 18:20
     */
    @Override
    public Boolean editCommodityCode(SysCommodityCode sysCommodityCode, StringBuilder message) {
        return null;
    }

    /***
     *@Description //先删后插入
     *@Params [listsc, cxtj, cxlx]
     *@Return void
     *@Author panyong
     *@Date 2020/6/19 16:26
    */
    @Transactional
    public void insetList(List<SysCommodityCode> listsc, String cxtj, Integer cxlx) {
        if(cxlx==1){
            sysCommodityCodeMapper.deleteByNsrsbh(cxtj);
        }else if (cxlx==2){
            sysCommodityCodeMapper.deleteByJsbh(cxtj);
        }
        insetList(listsc);
    }
}
