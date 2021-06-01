package com.example.config;

import com.example.serviceImpl.HelloServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GrpcServerConfiguration {
    @Autowired
    HelloServiceImpl service;

    /** 注入配置文件中的端口信息 */
    @Value("${grpc.server-port}")
    private int port;
    private Server server;

    public void start() throws IOException {
        // 构建服务端
        server = ServerBuilder.forPort(port).addService(service).build().start();

        // 添加服务端关闭的逻辑
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            GrpcServerConfiguration.this.stop();
        }));
    }

    private void stop() {
        if (server != null) {
            // 关闭服务端
            server.shutdown();
        }
    }

    public void block() throws InterruptedException {
        if (server != null) {
            // 服务端启动后直到应用关闭都处于阻塞状态，方便接收请求
            server.awaitTermination();
        }
    }
}
