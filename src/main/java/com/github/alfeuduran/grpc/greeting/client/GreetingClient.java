package com.github.alfeuduran.grpc.greeting.client;

import com.proto.dummy.DummyServiceGrpc;
import com.proto.greet.GreetRequest;
import com.proto.greet.GreetResponse;
import com.proto.greet.GreetServiceGrpc;
import com.proto.greet.Greeting;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GreetingClient {

    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext()
                .build();


        System.out.println("Creating stub");
        // old and dummy
        //DummyServiceGrpc.DummyServiceBlockingStub syncClient = DummyServiceGrpc.newBlockingStub(channel);
        //DummyServiceGrpc.DummyServiceFutureStub asyncClient = DummyServiceGrpc.newFutureStub(channel);


        //creating a service client (bloking -> sync)
        GreetServiceGrpc.GreetServiceBlockingStub  greetClient = GreetServiceGrpc.newBlockingStub(channel);

        //creating protocol buffer greeting message
        Greeting greeting = Greeting.newBuilder()
                .setFirstName("Alfeu")
                .setLastName("Duran")
                .build();

        //do the same for a GreetRequest
        GreetRequest greetRequest = GreetRequest.newBuilder()
                .setGreeting(greeting)
                .build();

        //call the RPC and get back a GreetResponse (protocol buffers)
        GreetResponse greetResponse = greetClient.greet(greetRequest);


        System.out.println(greetResponse.getResult());

        System.out.println("Shutting down channel");
        channel.shutdown();


    }
}