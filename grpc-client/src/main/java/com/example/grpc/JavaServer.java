package com.example.grpc;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;


public class JavaServer {

    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder.forPort(50051).addService(new MyService()).build();
        server.start();
        System.out.println("Server started");
        server.awaitTermination();
    }

    static class MyService extends PythonServiceGrpc.PythonServiceImplBase {
        @Override
        public void callPythonMethod(Request request, StreamObserver<Response> responseObserver) {
            Response response = Response.newBuilder().setMessage("Hello " + request.getMessage()).build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
    }
}
