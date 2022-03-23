package com.example.baffleframework.baffleUtils;

import jdk.nashorn.internal.scripts.JD;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Author: lirisheng
 * @Date: 2022/3/22 15:39
 * @Version 1.0
 */
public class JDKBandProxy implements InvocationHandler {

    private Object target;

    private PrxyBndParmInterface proxyMethod = null;

    public JDKBandProxy(Object target, PrxyBndParmInterface proxyMethod){
        this.target = target;
        this.proxyMethod = proxyMethod;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        try{
            if(method.getReturnType() != void.class){
                result  = method.getReturnType().newInstance();
            }
            for(Object object : args){
                if(object.getClass().equals(proxyMethod.getBandClazz())){
                    result = object;
                    break;
                }
            }
            if(result == null){
                throw new RuntimeException("无可以设置值的对象类");
            }

            if( ProxyBandUtils.isBan(proxyMethod) ){
                ProxyBandUtils.setMethodValue(result, proxyMethod.getBandValue());
            }else{
                result = method.invoke(target, args);
            }

            return  result;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("代理对象出现异常:"+e+",不进行代理");
        }
        result = method.invoke(target, args);
        System.out.println("挡板关闭,代理介绍");
        return result;
    }
}
