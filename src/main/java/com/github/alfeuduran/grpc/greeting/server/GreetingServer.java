package com.github.alfeuduran.grpc.greeting.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class GreetingServer {

    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Hello GRPC!");

        Server server = ServerBuilder.forPort(50051)
                .addService(new GreetServiceImpl())
                .build();

        server.start();

        //Gracefuly shutdown
        Runtime.getRuntime().addShutdownHook(new Thread( () -> {
            System.out.println("Received Shutdown Request");
            server.shutdown();
            System.out.println("Successfully sttoped the server");
        }));

        server.awaitTermination();

    }
}
