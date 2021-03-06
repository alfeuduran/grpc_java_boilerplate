package com.github.alfeuduran.grpc.greeting.client;

import com.proto.dummy.DummyServiceGrpc;
import com.proto.greet.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class GreetingClient {

    public static void main(String[] args) throws InterruptedException {

        GreetingClient main = new GreetingClient();
        main.run();

    }


    public void run () throws InterruptedException {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext()
                .build();

        //doUnaryCall(channel);
        //doServerStreamingCall(channel);
        doClientStreamingCall(channel);

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

    private void doClientStreamingCall(ManagedChannel channel) throws InterruptedException {
        //create a client (stub)
        //GreetServiceGrpc.GreetServiceBlockingStub greetClient = GreetServiceGrpc.newBlockingStub(channel);

        //create an Async cliet
        GreetServiceGrpc.GreetServiceStub asyncClient = GreetServiceGrpc.newStub(channel);

        CountDownLatch latch = new CountDownLatch(1);

        StreamObserver<LongGreetRequest> requestObserver =  asyncClient.longGreet(new StreamObserver<LongGreetResponse>() {
            @Override
            public void onNext(LongGreetResponse value) {
                // quando temos uma resposta do server
                System.out.println("Received response from the server");
                System.out.println(value.getResult());
                //Ira ser chamado apenas uma vez
            }

            @Override
            public void onError(Throwable t) {
                // quando temos um erro do server

            }

            @Override
            public void onCompleted() {
                // O server terminou de enviar os dados.
                System.out.println("Server has completed sending us something");
                // Ira ser chamado depois do onNext()
                latch.countDown();
            }
        });


        // apenas um lembrete que como isso ira ser async temos que fazer a implementacao de um latch.


        // Streaming Message 1
        System.out.println("Sending Message 1");

        requestObserver.onNext(LongGreetRequest.newBuilder()
        .setGreeting(Greeting.newBuilder()
        .setFirstName("Alfeu")
        .build())
                .build());

        // Streaming Message 2
        System.out.println("Sending Message 2");
        requestObserver.onNext(LongGreetRequest.newBuilder()
                .setGreeting(Greeting.newBuilder()
                        .setFirstName("Duran")
                        .build())
                .build());

        // Streaming Message 3
        System.out.println("Sending Message 3");
        requestObserver.onNext(LongGreetRequest.newBuilder()
                .setGreeting(Greeting.newBuilder()
                        .setFirstName("Santos")
                        .build())
                .build());

        //Falamos para o server que o client acabou de enviar dados
        requestObserver.onCompleted();

        latch.await(3L, TimeUnit.SECONDS);
    }

}
