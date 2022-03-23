package com.example.baffleframework.example;

import com.example.baffleframework.baffleUtils.ProxyBandParam;
import com.example.baffleframework.baffleUtils.ProxyBandUtils;
import com.example.baffleframework.baffleUtils.PrxyBndParmInterface;
import com.example.baffleframework.baffleUtils.PrxyBndParmTableHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: lirisheng
 * @Date: 2022/3/22 18:57
 * @Version 1.0
 */
public class BandTestUtils {

    public static void  main(String arg[]){
        simpleBand();
        simpleBandWithParmOutput();
        busiFilterBand();
        complexBandVauleBand();
    }

    public static void simpleBand(){
        PrxyBndParmInterface proxyMethod =new  PrxyBndParmTableHandler(getProxyBandParam());

        //外调方法设置挡板
        HttpClient client = new HttpClientImp();
        client = (HttpClient) ProxyBandUtils.proxyBandJDKObject(client, proxyMethod);
        ResponseObject response = client.requestOtherSystem(new HashMap<>());
        System.out.println("外调方法挡板值:"+response);


        //对对象设置挡板
        ResponseObject proxyObject = new ResponseObject();
        proxyObject.setStatus("success");
        proxyObject.setMessage("success");
        ProxyBandUtils.proxyBandParamObject(proxyObject,proxyMethod);
        System.out.println("对象挡板值:"+response);

    }

    public static void simpleBandWithParmOutput(){
        HttpClient client = new HttpClientImp();

        //外调方法-output作为入参 设置挡板
        PrxyBndParmInterface proxyMethod =new  PrxyBndParmTableHandler(getProxyBandParam(),ResponseObject.class);
        client = (HttpClient) ProxyBandUtils.proxyBandJDKObject(client, proxyMethod);
        ResponseObject response = new ResponseObject();
        client.requestOtherSystemByParamOutput(new HashMap<>(),response);
        System.out.println("外调方法-output作为入参挡板值:"+response);

    }


    public static void busiFilterBand(){

        //外调方法-业务过滤设置挡板
        HttpClient client = new HttpClientImp();
        PrxyBndParmInterface proxyMethod =new BusiFilterPrxyBndParmTableHandler(getProxyBandParamForFilter(),"4402");
        client = (HttpClient) ProxyBandUtils.proxyBandJDKObject(client, proxyMethod);
        ResponseObject response = client.requestOtherSystem(new HashMap<>());
        System.out.println("外调方法-业务过滤挡板值:"+response);

    }

    public static void complexBandVauleBand(){
        //对象--复杂的挡板值 设置挡板
        Map<String, Object>  value = new HashMap<>();
        value.put("value1","value1");
        PrxyBndParmInterface proxyMethod = new ComplexBandValueParmHandler(getProxyBandParam());
        ProxyBandUtils.proxyBandParamObject(value, proxyMethod);
        System.out.println("对象--复杂的挡板值 设置挡板:"+value);
    }

    public static ProxyBandParam getProxyBandParam(){
        ProxyBandParam param = new ProxyBandParam();
        //开启挡板
        param.setIsBand("ON");
        param.setBandValue("{\"status\":\"band\",\"message\":\"band\"}");
        return param;
    }

    public static ProxyBandParam getProxyBandParamForFilter(){
        ProxyBandParam param = new ProxyBandParam();
        //开启挡板
        param.setIsBand("ON");
        param.setFilterValue("{\"cretno\":[ \"4402\", \"4403\"]}");
        param.setBandValue("{\"status\":\"band\",\"message\":\"band\"}");
        return param;
    }


}
