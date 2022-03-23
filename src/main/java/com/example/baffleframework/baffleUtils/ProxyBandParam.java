package com.example.baffleframework.baffleUtils;

import java.util.List;
import java.util.Map;

/**
 * @Author: lirisheng
 * @Date: 2022/3/22 15:29
 * @Version 1.0
 */

public class ProxyBandParam {

    private String isBand;
    private String isFilterBand;
    private String filterValue;
    private String bandValue;
    private Class<?> bandClazz;

    public String getIsBand() {
        return isBand;
    }

    public void setIsBand(String isBand) {
        this.isBand = isBand;
    }

    public String getIsFilterBand() {
        return isFilterBand;
    }

    public void setIsFilterBand(String isFilterBand) {
        this.isFilterBand = isFilterBand;
    }

    public String getFilterValue() {
        return filterValue;
    }

    public void setFilterValue(String filterValue) {
        this.filterValue = filterValue;
    }

    public String getBandValue() {
        return bandValue;
    }

    public void setBandValue(String bandValue) {
        this.bandValue = bandValue;
    }

    public Class<?> getBandClazz() {
        return bandClazz;
    }

    public void setBandClazz(Class<?> bandClazz) {
        this.bandClazz = bandClazz;
    }

    @Override
    public String toString() {
        return "ProxyBandParam{" +
                "isBand='" + isBand + '\'' +
                ", isFilterBand='" + isFilterBand + '\'' +
                ", filterValue='" + filterValue + '\'' +
                ", bandValue='" + bandValue + '\'' +
                ", bandClazz=" + bandClazz +
                '}';
    }
}
