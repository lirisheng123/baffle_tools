

# 挡板工具

## 背景

项目中涉及到较多的第三方系统的外调，由于在测试环境中,往往外调接口只支持返回一个默认的值，所以单元测试时,需要在代码中设置挡板才能测试到各种场景。

## 存在问题

普通的挡板设置的不足之处：

1. 在设置挡板时,会在业务代码中插入较多的if-else语句,导致业务逻辑代码变得混乱,不简洁。
2. 在更改默认的挡板值的时候,往往更改代码, 这导致不方便测试。
3. 在只需针对某些业务数据为了挡板条件时,往往需要在业务代码中进行更为复杂处理,导致逻辑慌乱。
4. 挡板代码分散到项目中,后期很难进行管理和维护。

## 挡板工具支持功能

1.以非侵入的方式支持对外调方法，对象进行挡板设置。

2.支持简单挡板逻辑，支持通过配置的方式来设置挡板数据。

3.支持针对业务数据条件挡板逻辑,  即在在运行时,获取业务数据,然后与提前配置的匹配值进行匹配,如果匹配,则进行挡板处理。

4.挡板数据支持配置方式,根据业务数据动态生成

## 使用到的技术

   动态代理,反射

## 常用场景案例

仓库中有常用场景的案例，下面针对常用场景案例做下简单的解释

- 简单挡板设置

  配置挡板值

  ```java
  public static ProxyBandParam getProxyBandParam(){
          ProxyBandParam param = new ProxyBandParam();
          //开启挡板，ON：开启挡板， OFF：关掉挡板
          param.setIsBand("ON");
          //设置返回的挡板值，JSON字符串格式
          param.setBandValue("{\"status\":\"band\",\"message\":\"band\"}");
          return param;
      }
  ```

  挡板处理代码

  ```java
   public static void simpleBand(){
         //将配置好的挡板值作为入参，注：可以将挡板值配置在数据库中，通过DAO层读取，可实现挡板值动态调整
          PrxyBndParmInterface proxyMethod =new  PrxyBndParmTableHandler(getProxyBandParam());
  
          HttpClient client = new HttpClientImp();
          //挡板代理
          client = (HttpClient) ProxyBandUtils.proxyBandJDKObject(client, proxyMethod);
          ResponseObject response = client.requestOtherSystem(new HashMap<>());
          System.out.println("外调方法挡板值:"+response);
  
  
          ResponseObject proxyObject = new ResponseObject();
          proxyObject.setStatus("success");
          proxyObject.setMessage("success");
          //挡板代理
          ProxyBandUtils.proxyBandParamObject(proxyObject,proxyMethod);
          System.out.println("对象挡板值:"+response);
  
      }
  ```

  

- 外调方法的响应值作为入参

  挡板处理

  ```
  HttpClient client = new HttpClientImp();
  //将配置好的挡板值作为入参
  PrxyBndParmInterface proxyMethod =new  PrxyBndParmTableHandler(getProxyBandParam(),ResponseObject.class);
  //外调方法-output作为入参 设置挡板
  client = (HttpClient) ProxyBandUtils.proxyBandJDKObject(client, proxyMethod);
  ResponseObject response = new ResponseObject();
  client.requestOtherSystemByParamOutput(new HashMap<>(),response);
  System.out.println("外调方法-output作为入参挡板值:"+response);
  ```

  

- 业务数据作为挡板过滤条件

  继承`PrxyBndParmTableHandler`类，重写`getBusiParamValue()`方法，根据业务参数获取作为挡板过滤条件的值

  ```java
   @Override
      public Map<String, Object> getBusiParamValue() {
  
          //通过busiUniqueId获取到业务参数
          Map<String,Object> map = new HashMap<>();
          map.put("cretno",getBusiUniqueId());
          return  map;
      }
  ```

  配置挡板值

  ```
    public static ProxyBandParam getProxyBandParamForFilter(){
          ProxyBandParam param = new ProxyBandParam();
          //开启挡板
          param.setIsBand("ON");
          //设置过滤条件，只有业务参数匹配过滤条件，才会开启挡板， JSON字符串格式
          param.setFilterValue("{\"cretno\":[ \"4402\", \"4403\"]}");
          //设置返回的挡板值，JSON字符串格式
          param.setBandValue("{\"status\":\"band\",\"message\":\"band\"}");
          return param;
      }
  ```

  挡板处理

  ```java
  public static void busiFilterBand(){
          HttpClient client = new HttpClientImp();
          //设置挡板值
          //设置busiUniqueId = "4402",根据busiUniqueId可以在getBusiParamValue()方法动态获取挡板过滤值
          PrxyBndParmInterface proxyMethod =new BusiFilterPrxyBndParmTableHandler(getProxyBandParamForFilter(),"4402");
          //挡板处理
          client = (HttpClient) ProxyBandUtils.proxyBandJDKObject(client, proxyMethod);
          ResponseObject response = client.requestOtherSystem(new HashMap<>());
          System.out.println("外调方法-业务过滤挡板值:"+response);
  
      }
  ```

  

  

- 挡板的数据根据业务数据动态生成

  继承`getBandValue()`类，重写`getBandValue()`方法，根据业务参数生成挡板值

  ```java
  @Override
      public Map<String, Object> getBandValue() {
          //通过busiUniqueId获取到业务参数
          Map<String,Object> map = new HashMap<>();
          map.put("value1","band1");
          map.put("value2","band2");
          map.put("value3","band3");
          return  map;
      }
  ```

  挡板处理

  ```java
   public static void complexBandVauleBand(){
          Map<String, Object>  value = new HashMap<>();
          value.put("value1","value1");
          //挡板处理，
          PrxyBndParmInterface proxyMethod = new ComplexBandValueParmHandler(getProxyBandParam());
          ProxyBandUtils.proxyBandParamObject(value, proxyMethod);
          System.out.println("对象--复杂的挡板值 设置挡板:"+value);
      }
  ```

  

  

