package com.example.baffleframework.Example;

import java.util.Map;

/**
 * @Author: lirisheng
 * @Date: 2022/3/22 19:01
 * @Version 1.0
 */
public interface HttpClient {

    ResponseObject requestOtherSystem(Map<String,Object> input);

    void requestOtherSystemByParamOutput(Map<String,Object> input,ResponseObject output);

}
