package com.github.alfeuduran.grpc.greeting.blog.client;

import com.github.alfeuduran.grpc.greeting.client.GreetingClient;
import com.proto.blog.Blog;
import com.proto.blog.BlogServiceGrpc;
import com.proto.blog.CreateBlogRequest;
import com.proto.blog.CreateBlogResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class BlogClient {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("Hello! Im gRPC client for blog");
        BlogClient main = new BlogClient();
        main.run();

    }


    public void run () throws InterruptedException {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext()
                .build();


        BlogServiceGrpc.BlogServiceBlockingStub blogClient = BlogServiceGrpc.newBlockingStub(channel);

        Blog blog = Blog.newBuilder()
                .setAuthorId("Alfeu")
                .setTitle("New Blog")
                .setContent("Hello world this is my first blog!")
                .build();

        CreateBlogResponse createResponse =  blogClient.createBlog(
                CreateBlogRequest.newBuilder()
                        .setBlog(blog)
                        .build()
        );

        System.out.println("Received create blog response from the client");
        System.out.println(createResponse.toString());







    }



}
