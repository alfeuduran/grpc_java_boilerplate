package com.github.alfeuduran.grpc.greeting.client;

import com.proto.dummy.DummyServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GreetingClients {

    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext()
                .build();


        System.out.println("Creating stub");
        DummyServiceGrpc.DummyServiceBlockingStub syncClient = DummyServiceGrpc.newBlockingStub(channel);

    //DummyServiceGrpc.DummyServiceFutureStub asyncClient = DummyServiceGrpc.newFutureStub(channel);

    // do something


        System.out.println("Shutting down channel");
        channel.shutdown();


    }
}
