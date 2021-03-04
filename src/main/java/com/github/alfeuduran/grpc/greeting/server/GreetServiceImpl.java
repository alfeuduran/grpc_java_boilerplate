package com.github.alfeuduran.grpc.greeting.server;

import com.proto.greet.GreetRequest;
import com.proto.greet.GreetResponse;
import com.proto.greet.GreetServiceGrpc;
import com.proto.greet.Greeting;
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


}
