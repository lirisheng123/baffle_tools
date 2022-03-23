package com.example.baffleframework.baffleUtils;

import java.util.List;
import java.util.Map;

/**
 * @Author: lirisheng
 * @Date: 2022/3/22 15:40
 * @Version 1.0
 */
public interface PrxyBndParmInterface {

    public Map<String, Object> getBusiParamValue();

    public boolean isBand();

    public boolean isFilterBand();
    public  Map<String, List<Object>> getFilterValue();
    public Map<String, Object> getBandValue();
    public Class<?>  getBandClazz();


}
