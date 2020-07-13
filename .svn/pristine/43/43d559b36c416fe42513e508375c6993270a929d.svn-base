package com.invoice.system.service.impl;

import com.google.common.collect.Lists;
import com.invoice.system.domain.szmodel.SysCommodity;
import com.invoice.system.mapper.SysCommoditMapper;
import com.invoice.system.service.SysCommoditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Desoription:商品编码业务处理实现类
 * @Auther:panyong
 * @create:2020/6/4 15:52
 */
@Service
public class SysCommoditServiceImpl implements SysCommoditService {
    @Autowired
    private SysCommoditMapper szCommoditMapper;
    /***
     *@Description //批量插入商品编码信息
     *@Params [listsc]
     *@Return void
     *@Author panyong
     *@Date 2020/6/5 9:09
    */
    @Override
    @Transactional
    public void insetList(List<SysCommodity> listsc) {
        List<List<SysCommodity>> list= Lists.partition(listsc, 100);
        for (List<SysCommodity> ls :list){
            szCommoditMapper.insetList(ls);
        }

    }
    /***
     *@Description /查询商品编码信息
     *@Params [area]
     *@Return java.util.List<com.invoice.system.domain.szmodel.SzCommodity>
     *@Author panyong
     *@Date 2020/6/5 16:02
    */
    @Override
    public List<SysCommodity> selectCommoditList(SysCommodity szCommodity) {
        return szCommoditMapper.selectCommoditList(szCommodity);
    }

    @Override
    public SysCommodity selectCommoditById(Integer id) {
        return szCommoditMapper.selectCommoditById(id);
    }
}

