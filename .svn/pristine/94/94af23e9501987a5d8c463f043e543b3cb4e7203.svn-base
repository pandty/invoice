package com.invoice.system.service.impl;

import com.invoice.system.domain.SysArea;
import com.invoice.system.mapper.SysAreaMapper;
import com.invoice.system.service.ISysAreaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Desoription:
 * @Auther:panyong
 * @create:2020/6/1 10:16
 */
@Service
public class SysAreaServiceImpl implements ISysAreaService {
    private static final Logger log = LoggerFactory.getLogger(SysAreaServiceImpl.class);
    @Autowired
    private SysAreaMapper areaMapper;
    @Override
    public List<SysArea> selectAreaList(SysArea area) {
        return areaMapper.selectAreaList(area);
    }
}
