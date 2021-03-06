package com.github.alfeuduran.grpc.greeting.server;

import com.proto.greet.*;
import io.grpc.stub.ServerCallStreamObserver;
import io.grpc.stub.StreamObserver;

public class GreetServiceImpl extends GreetServiceGrpc.GreetServiceImplBase {


    @Override
    public void greet(GreetRequest request, StreamObserver<GreetResponse> responseObserver) {
        Greeting greeting = request.getGreeting();
        String firstName = greeting.getFirstName();

        String result = "Hello" + firstName;
        GreetResponse response = GreetResponse.newBuilder()
                .setResult(result)
                .build();


        // always using StreamObserver we have async response;
        //set response
        responseObserver.onNext(response);

        // complete the RPC call
        responseObserver.onCompleted();

    }


    @Override
    public void greetManyTimes(GreetManyTimesRequest request, StreamObserver<GreetManyTimesResponse> responseObserver) {

        String fisrtName = request.getGreeting().getFirstName();

        try{
            for(int i = 0; i < 10; i++){
                String result = "Hello " + fisrtName + ", response number: " + i;
                GreetManyTimesResponse response = GreetManyTimesResponse.newBuilder()
                        .setResult(result)
                        .build();

                responseObserver.onNext(response);
                Thread.sleep(1000L);
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            responseObserver.onCompleted();
        }
    }

    @Override
    public StreamObserver<LongGreetRequest> longGreet(StreamObserver<LongGreetResponse> responseObserver) {
        StreamObserver<LongGreetRequest> streamObserverOfReqeust = new StreamObserver<LongGreetRequest>() {

            String result = "";
            @Override
            public void onNext(LongGreetRequest value) {
                result += "Hello" + value.getGreeting().getFirstName() + "! ";
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onCompleted() {
                responseObserver.onNext(
                        LongGreetResponse.newBuilder()
                        .setResult(result)
                        .build()
                );
                // nessa caso mandamos como response o final result string.
                responseObserver.onCompleted();

            }
        };
        return streamObserverOfReqeust;
    }

    @Override
    public StreamObserver<GreetEveryOneRequest> greetEveryone(StreamObserver<GreetEveryOneResponse> responseObserver) {
        StreamObserver<GreetEveryOneRequest> requestObserver = new StreamObserver<GreetEveryOneRequest>() {
            @Override
            public void onNext(GreetEveryOneRequest value) {
                String result = "Hello" + value.getGreeting().getFirstName();
                GreetEveryOneResponse greetEveryOneResponse = GreetEveryOneResponse.newBuilder()
                        .setResult(result)
                        .build();

                responseObserver.onNext(greetEveryOneResponse);

        }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onCompleted() {

                responseObserver.onCompleted();
            }
        };

        return requestObserver;

    }
}
