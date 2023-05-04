package com.example.grpc;

import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.0.0)",
    comments = "Source: pygrpc.proto")
public class PythonServiceGrpc {

  private PythonServiceGrpc() {}

  public static final String SERVICE_NAME = "pygrpc.PythonService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.example.grpc.Request,
      com.example.grpc.Response> METHOD_CALL_PYTHON_METHOD =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "pygrpc.PythonService", "callPythonMethod"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.example.grpc.Request.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.example.grpc.Response.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.example.grpc.Request1,
      com.example.grpc.Response1> METHOD_CALL_PYTHON_METHOD2 =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "pygrpc.PythonService", "callPythonMethod2"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.example.grpc.Request1.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.example.grpc.Response1.getDefaultInstance()));

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static PythonServiceStub newStub(io.grpc.Channel channel) {
    return new PythonServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static PythonServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new PythonServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary and streaming output calls on the service
   */
  public static PythonServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new PythonServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class PythonServiceImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * 第一次java调用python
     * </pre>
     */
    public void callPythonMethod(com.example.grpc.Request request,
        io.grpc.stub.StreamObserver<com.example.grpc.Response> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_CALL_PYTHON_METHOD, responseObserver);
    }

    /**
     * <pre>
     * 第二次python调用java
     * </pre>
     */
    public void callPythonMethod2(com.example.grpc.Request1 request,
        io.grpc.stub.StreamObserver<com.example.grpc.Response1> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_CALL_PYTHON_METHOD2, responseObserver);
    }

    @java.lang.Override public io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_CALL_PYTHON_METHOD,
            asyncUnaryCall(
              new MethodHandlers<
                com.example.grpc.Request,
                com.example.grpc.Response>(
                  this, METHODID_CALL_PYTHON_METHOD)))
          .addMethod(
            METHOD_CALL_PYTHON_METHOD2,
            asyncUnaryCall(
              new MethodHandlers<
                com.example.grpc.Request1,
                com.example.grpc.Response1>(
                  this, METHODID_CALL_PYTHON_METHOD2)))
          .build();
    }
  }

  /**
   */
  public static final class PythonServiceStub extends io.grpc.stub.AbstractStub<PythonServiceStub> {
    private PythonServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private PythonServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PythonServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new PythonServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     * 第一次java调用python
     * </pre>
     */
    public void callPythonMethod(com.example.grpc.Request request,
        io.grpc.stub.StreamObserver<com.example.grpc.Response> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_CALL_PYTHON_METHOD, getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * 第二次python调用java
     * </pre>
     */
    public void callPythonMethod2(com.example.grpc.Request1 request,
        io.grpc.stub.StreamObserver<com.example.grpc.Response1> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_CALL_PYTHON_METHOD2, getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class PythonServiceBlockingStub extends io.grpc.stub.AbstractStub<PythonServiceBlockingStub> {
    private PythonServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private PythonServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PythonServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new PythonServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * 第一次java调用python
     * </pre>
     */
    public com.example.grpc.Response callPythonMethod(com.example.grpc.Request request) {
      return blockingUnaryCall(
          getChannel(), METHOD_CALL_PYTHON_METHOD, getCallOptions(), request);
    }

    /**
     * <pre>
     * 第二次python调用java
     * </pre>
     */
    public com.example.grpc.Response1 callPythonMethod2(com.example.grpc.Request1 request) {
      return blockingUnaryCall(
          getChannel(), METHOD_CALL_PYTHON_METHOD2, getCallOptions(), request);
    }
  }

  /**
   */
  public static final class PythonServiceFutureStub extends io.grpc.stub.AbstractStub<PythonServiceFutureStub> {
    private PythonServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private PythonServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PythonServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new PythonServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * 第一次java调用python
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.grpc.Response> callPythonMethod(
        com.example.grpc.Request request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_CALL_PYTHON_METHOD, getCallOptions()), request);
    }

    /**
     * <pre>
     * 第二次python调用java
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.grpc.Response1> callPythonMethod2(
        com.example.grpc.Request1 request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_CALL_PYTHON_METHOD2, getCallOptions()), request);
    }
  }

  private static final int METHODID_CALL_PYTHON_METHOD = 0;
  private static final int METHODID_CALL_PYTHON_METHOD2 = 1;

  private static class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final PythonServiceImplBase serviceImpl;
    private final int methodId;

    public MethodHandlers(PythonServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CALL_PYTHON_METHOD:
          serviceImpl.callPythonMethod((com.example.grpc.Request) request,
              (io.grpc.stub.StreamObserver<com.example.grpc.Response>) responseObserver);
          break;
        case METHODID_CALL_PYTHON_METHOD2:
          serviceImpl.callPythonMethod2((com.example.grpc.Request1) request,
              (io.grpc.stub.StreamObserver<com.example.grpc.Response1>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    return new io.grpc.ServiceDescriptor(SERVICE_NAME,
        METHOD_CALL_PYTHON_METHOD,
        METHOD_CALL_PYTHON_METHOD2);
  }

}
