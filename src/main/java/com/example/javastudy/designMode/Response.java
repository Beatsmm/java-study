package com.example.javastudy.designMode;

import com.example.javastudy.safe.BusinessCode;

public class Response<T> {

    int code = 0;
    String msg;
    private T data = null;

    public Response() {
    }

    public static <T> Response<T> ok(T data){
        Response response = new Response();
        response.code = 0;
        response.msg = "SUCCESS";
        response.data = data;
        return response;

    }

    public static Response error(int code, String msg) {
        Response response = new Response();
        response.code = code;
        response.msg = msg;
        return response;
    }

    public static Response error(BusinessCode errorCode) {
        return of(errorCode);
    }

    private static Response of(BusinessCode errorCode) {
        Response response = new Response();
        response.code = Integer.parseInt(errorCode.getCode());
        response.msg = errorCode.getMsg();
        return response;
    }

    public static <T> Response<T> error(){
        Response response = new Response();
        response.code = -1;
        response.msg = "ERROR";
        return response;
    }
}
