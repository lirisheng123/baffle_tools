package com.example.baffleframework.baffleUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.FileHandler;

/**
 * @Author: lirisheng
 * @Date: 2022/3/22 15:39
 * @Version 1.0
 */
public class ProxyBandUtils {

    public static Object proxyBandJDKObject(Object ob, PrxyBndParmInterface proxyMethod){
        Class<?> obClass = ob.getClass();
        Object instance = Proxy.newProxyInstance(obClass.getClassLoader(), obClass.getInterfaces(), new JDKBandProxy(ob, proxyMethod));
        return instance;
    }

    public static void proxyBandParamObject(Object input, PrxyBndParmInterface proxyMethod){
        Object input1 = input;
        try{
           if (isBan(proxyMethod)){
               setMethodValue(input1, proxyMethod.getBandValue());
           }
           input = input1;
           System.out.println("参数代理结束");
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("参数代理结失败");
        }
    }

    public static boolean isBan(PrxyBndParmInterface proxyMethod){

        boolean isBan = false;
        if(proxyMethod.isBand() && isFilter( proxyMethod.getBusiParamValue(),proxyMethod.getFilterValue() ) ){
            isBan = true;
        }
        return  isBan;
    }

    public static boolean isFilter(Map<String, Object> curMap, Map<String, List<Object>> filterMap){
        if (filterMap == null){
            return true;
        }

        Set<String> keys = filterMap.keySet();
        for(String key : keys){
            if(!filterMap.get(key).contains(curMap.get(key))){
                return false;
            }
        }
        return true;
    }


    public  static void setMethodValue(Object object, Map<String, Object> retnParam) throws Exception{
        if(object instanceof Map){
            populateMap(object, retnParam);
        }else{
            populateObject(object, retnParam);
        }
    }

    public static void populateObject(Object object, Map<String, Object> retnParam) throws Exception{

        Class<?> retnClass = object.getClass();
        Set<String> keys = retnParam.keySet();
        for (String key : keys){
            Field filed = retnClass.getDeclaredField(key);
            filed.setAccessible(true);
            Class<?> type = filed.getType();
            if(type.isEnum()){
                filed.set(object, getEnumFmValue(type, retnParam.get(key)));
            }else{
                filed.set(object, retnParam.get(key));
            }
        }
    }

    public static void populateMap(Object object, Map<String, Object> retnParam) throws Exception{

        if(retnParam == null || retnParam.size() == 0){
            return;
        }
        Map<String, Object> map = (Map<String, Object>)object;
        for(Map.Entry<String,Object> item : retnParam.entrySet()){
            if(map.containsKey(item.getKey())){
                Object value = map.get(item.getKey());
                if (value!=null){
                    Class<?> clazz = object.getClass();
                    if (clazz.isEnum()){
                        map.put(item.getKey(),getEnumFmValue(clazz, item.getValue()));
                        continue;
                    }
                }
            }
            map.put(item.getKey(), item.getValue());
        }

    }

    public static Enum<?> getEnumFmValue(Class<?> enumClass, Object value){

        Enum[] enums = (Enum[]) enumClass.getEnumConstants();

        for(Enum<?> item : enums){
            String name1 = item.name();
            if(item.toString().equals(value)){
                return  item;
            }
        }
        throw  new RuntimeException("枚举值转换失败");
    }

    public static String upperFirstCase(String str){
        char [] chars = str.toCharArray();
        if(chars != null && chars.length != 0){
            chars[0] -= 32;
        }
        return String.valueOf(chars);
    }

    public static Map<String, Object> changeJsonToMap(String jsonStr){
        if (jsonStr.isEmpty()){
            return null;
        }

        Map<String, Object> param = new HashMap<>();
        try{
            param = JSONObject.parseObject(jsonStr);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

        return param;
    }

    public static Map<String, List<Object>> changeJsonToMapList(String jsonStr){

        if (jsonStr == null || jsonStr.isEmpty()){
            return null;
        }

        Map<String, List<Object>> param = new HashMap<>();
        try{
            Map<String, Object> filterMap = JSONObject.parseObject(jsonStr);
            Set<String> keys = filterMap.keySet();
            for (String key : keys){
                List<Object> list = JSONArray.parseArray(filterMap.get(key).toString(), Object.class);
                param.put(key, list);
            }
        }catch (Exception e){
            return null;
        }


        return param;
    }
}
