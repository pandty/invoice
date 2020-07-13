package com.invoice.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @Desoription: 地区对象 sys_area
 * @Auther:panyong
 * @create:2020/5/30 16:12
 */
public class SysArea {
    private static final long serialVersionUID = 1L;

    private Integer areaId;//区域ID
    private String area;//区域名称
    private Integer parentId;//上级区域
    private Integer level;//区域等级
    private Integer isLeaf;
    private String simple;

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getIsLeaf() {
        return isLeaf;
    }

    public void setIsLeaf(Integer isLeaf) {
        this.isLeaf = isLeaf;
    }

    public String getSimple() {
        return simple;
    }

    public void setSimple(String simple) {
        this.simple = simple;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("areaId", getAreaId())
                .append("area", getArea())
                .append("parentId", getParentId())
                .append("level", getLevel())
                .append("isLeaf", getIsLeaf())
                .append("simple", getSimple())
                .toString();
    }
}
