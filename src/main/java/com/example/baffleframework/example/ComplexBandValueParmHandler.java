package com.example.baffleframework.example;

import com.example.baffleframework.baffleUtils.ProxyBandParam;
import com.example.baffleframework.baffleUtils.PrxyBndParmTableHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: lirisheng
 * @Date: 2022/3/23 9:02
 * @Version 1.0
 */
public class ComplexBandValueParmHandler extends PrxyBndParmTableHandler {

    public ComplexBandValueParmHandler(ProxyBandParam bandParam, Class<?> bandClass) {
        super(bandParam, bandClass);
    }

    public ComplexBandValueParmHandler(ProxyBandParam bandParam, String busiUniqueId) {
        super(bandParam, busiUniqueId);
    }

    public ComplexBandValueParmHandler(ProxyBandParam bandParam) {
        super(bandParam);
    }

    public ComplexBandValueParmHandler(ProxyBandParam bandParam, String busiUniqueId, Class<?> bandClass) {
        super(bandParam, busiUniqueId, bandClass);
    }

    @Override
    public Map<String, Object> getBandValue() {
        //通过busiUniqueId获取到业务参数
        Map<String,Object> map = new HashMap<>();
        map.put("value1","band1");
        map.put("value2","band2");
        map.put("value3","band3");
        return  map;
    }
}
