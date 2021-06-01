package com.example.config;

import com.example.grpc.HelloWorldGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class GrpcClientConfiguration {
    /** gRPC Server的地址 */
    @Value("${server-host}")
    private String host;

    /** gRPC Server的端口 */
    @Value("${server-port}")
    private int port;

    private ManagedChannel channel;
    private HelloWorldGrpc.HelloWorldBlockingStub stub;

    public void start() {
        // 开启channel
        channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();

        // 通过channel获取到服务端的stub
        stub = HelloWorldGrpc.newBlockingStub(channel);
    }

    public void shutdown() throws InterruptedException {
        // 调用shutdown方法后等待1秒关闭channel
        channel.shutdown().awaitTermination(1, TimeUnit.SECONDS);
    }

    public HelloWorldGrpc.HelloWorldBlockingStub getStub() {
        return this.stub;
    }
}
