package com.example.baffleframework.example;

/**
 * @Author: lirisheng
 * @Date: 2022/3/22 19:01
 * @Version 1.0
 */
public class ResponseObject {

     private  String status;
     private String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ResponseObject{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
