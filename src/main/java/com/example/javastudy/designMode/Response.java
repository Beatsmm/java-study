package com.example.javastudy.designMode;

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

    public static <T> Response<T> error(){
        Response response = new Response();
        response.code = -1;
        response.msg = "ERROR";
        return response;
    }
}
