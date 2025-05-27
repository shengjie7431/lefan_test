package com.lefancrm.backend.dto.hzReport;

/**
 * 案件区域分布报表
 */
public class CaseAreaDto {
    /**
     * 地址
     */
    private Integer areaType;
    /**
     * 区域
     */
    private Integer regionType;
    /**
     * 总数
     */
    private Integer count=0;

    /**
     * 地区名称
     */
    private String regionName;

    /**
     * 占比
     */
    private Double proportion=0D;

    public Integer getAreaType() {
        return areaType;
    }

    public void setAreaType(Integer areaType) {
        this.areaType = areaType;
    }

    public Integer getRegionType() {
        return regionType;
    }

    public void setRegionType(Integer regionType) {
        this.regionType = regionType;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public Double getProportion() {
        return proportion;
    }

    public void setProportion(Double proportion) {
        this.proportion = proportion;
    }

    public CaseAreaDto(Integer areaType, Integer regionType, Integer count, String regionName, Double proportion) {
        this.areaType = areaType;
        this.regionType = regionType;
        this.count = count;
        this.regionName = regionName;
        this.proportion = proportion;
    }

    public CaseAreaDto() {
    }
}
