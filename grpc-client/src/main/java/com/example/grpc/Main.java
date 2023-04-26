package com.example.grpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class Main {

    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext()
                .build();
        PythonServiceGrpc.PythonServiceBlockingStub stub = PythonServiceGrpc.newBlockingStub(channel);

        Request request = Request.newBuilder().setMessage("Python").build();
        Response response = stub.callPythonMethod(request);

        System.out.println(response.getMessage());

        channel.shutdown();
    }
}