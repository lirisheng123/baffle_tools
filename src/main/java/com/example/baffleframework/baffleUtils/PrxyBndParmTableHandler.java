package com.example.baffleframework.baffleUtils;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.util.JSONPObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: lirisheng
 * @Date: 2022/3/22 15:41
 * @Version 1.0
 */
public class PrxyBndParmTableHandler implements  PrxyBndParmInterface {

    private  ProxyBandParam bandParam = null;
    private String  busiUniqueId;
    private Class<?> bandClass;

    public ProxyBandParam getBandParam() {
        return bandParam;
    }

    public String getBusiUniqueId() {
        return busiUniqueId;
    }

    public Class<?> getBandClass() {
        return bandClass;
    }

    public PrxyBndParmTableHandler(ProxyBandParam bandParam, Class<?> bandClass){
        this.bandParam = bandParam;
        this.busiUniqueId =null;
        this.bandClass = bandClass;
    }

    public PrxyBndParmTableHandler(ProxyBandParam bandParam,String busiUniqueId){
        this.bandParam = bandParam;
        this.busiUniqueId = busiUniqueId;
        this.bandClass = null;
    }

    public PrxyBndParmTableHandler(ProxyBandParam bandParam){
        this.bandParam = bandParam;
        this.busiUniqueId = null;
        this.bandClass = null;
    }

    public PrxyBndParmTableHandler(ProxyBandParam bandParam,String busiUniqueId, Class<?> bandClass){
        this.bandParam = bandParam;
        this.busiUniqueId = busiUniqueId;
    }

    @Override
    public Map<String, Object> getBusiParamValue() {
        return null;
    }

    @Override
    public boolean isBand() {
        return  "ON".equals(bandParam.getIsBand());
    }


    @Override
    public Map<String, List<Object>> getFilterValue() {
        return ProxyBandUtils.changeJsonToMapList(bandParam.getFilterValue());
    }

    @Override
    public Map<String, Object> getBandValue() {
        return ProxyBandUtils.changeJsonToMap(bandParam.getBandValue());
    }

    @Override
    public Class<?> getBandClazz() {
        return bandClass;
    }

}
