package com.example.serviceImpl;

import com.example.grpc.HelloWorldGrpc;
import com.example.grpc.HelloWorldService;
import io.grpc.stub.StreamObserver;
import org.springframework.stereotype.Component;

@Component
public class HelloServiceImpl extends HelloWorldGrpc.HelloWorldImplBase {
    @Override
    public void sayHello(HelloWorldService.HelloRequest request,
                         StreamObserver<HelloWorldService.HelloResponse> responseObserver) {
        // 根据请求对象建立响应对象，返回响应信息
        HelloWorldService.HelloResponse response = HelloWorldService.HelloResponse
                .newBuilder()
                .setMessage(String.format("Hello, %s. This message comes from gRPC.", request.getName()))
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
