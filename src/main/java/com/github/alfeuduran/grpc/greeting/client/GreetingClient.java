package com.github.alfeuduran.grpc.greeting.client;

import com.proto.dummy.DummyServiceGrpc;
import com.proto.greet.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GreetingClient {

    public static void main(String[] args) {

        GreetingClient main = new GreetingClient();
        main.run();

    }


    public void run (){
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext()
                .build();

        //doUnaryCall(channel);
        //doServerStreamingCall(channel);

        System.out.println("Shutting down channel");
        channel.shutdown();


    }

    private void doUnaryCall(ManagedChannel channel){

        //DummyServiceGrpc.DummyServiceBlockingStub syncClient = DummyServiceGrpc.newBlockingStub(channel);
        //DummyServiceGrpc.DummyServiceFutureStub asyncClient = DummyServiceGrpc.newFutureStub(channel);


        //Unary
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


    }

    private void doServerStreamingCall(ManagedChannel channel){

        GreetServiceGrpc.GreetServiceBlockingStub  greetClient = GreetServiceGrpc.newBlockingStub(channel);

        //Server Streaming

        GreetManyTimesRequest greetManyTimesRequest = GreetManyTimesRequest.newBuilder()
                .setGreeting(Greeting.newBuilder().setFirstName("Alfeu"))
                .build();

        greetClient.greetManyTimes(greetManyTimesRequest)
                .forEachRemaining(greetManyTimesResponse -> {
                    System.out.println(greetManyTimesResponse.getResult());
                });

    }


}
