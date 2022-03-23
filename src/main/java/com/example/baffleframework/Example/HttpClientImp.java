package com.example.baffleframework.Example;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: lirisheng
 * @Date: 2022/3/22 19:05
 * @Version 1.0
 */
public class HttpClientImp implements HttpClient{

    @Override
    public ResponseObject requestOtherSystem(Map<String,Object> input) {
        ResponseObject object = new ResponseObject();
        object.setStatus("success");
        object.setMessage("success");
        return object;
    }

    @Override
    public void requestOtherSystemByParamOutput(Map<String,Object> input,ResponseObject bandObject) {
        bandObject.setStatus("success");
        bandObject.setMessage("success");
    }

}
