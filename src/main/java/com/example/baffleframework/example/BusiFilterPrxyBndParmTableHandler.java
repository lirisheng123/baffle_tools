package com.example.baffleframework.example;

import com.example.baffleframework.baffleUtils.ProxyBandParam;
import com.example.baffleframework.baffleUtils.PrxyBndParmTableHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: lirisheng
 * @Date: 2022/3/22 20:25
 * @Version 1.0
 */
public class BusiFilterPrxyBndParmTableHandler extends PrxyBndParmTableHandler {

    public BusiFilterPrxyBndParmTableHandler(ProxyBandParam bandParam, Class<?> bandClass) {
        super(bandParam, bandClass);
    }

    public BusiFilterPrxyBndParmTableHandler(ProxyBandParam bandParam, String busiUniqueId) {
        super(bandParam, busiUniqueId);
    }

    public BusiFilterPrxyBndParmTableHandler(ProxyBandParam bandParam) {
        super(bandParam);
    }

    public BusiFilterPrxyBndParmTableHandler(ProxyBandParam bandParam, String busiUniqueId, Class<?> bandClass) {
        super(bandParam, busiUniqueId, bandClass);
    }

    @Override
    public Map<String, Object> getBusiParamValue() {

        //通过busiUniqueId获取到业务参数
        Map<String,Object> map = new HashMap<>();
        map.put("cretno",getBusiUniqueId());
        return  map;
    }
}
