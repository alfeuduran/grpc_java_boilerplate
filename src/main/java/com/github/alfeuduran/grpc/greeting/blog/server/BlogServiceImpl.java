package com.github.alfeuduran.grpc.greeting.blog.server;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.proto.blog.Blog;
import com.proto.blog.BlogServiceGrpc;
import com.proto.blog.CreateBlogRequest;
import com.proto.blog.CreateBlogResponse;
import io.grpc.stub.StreamObserver;
import org.bson.Document;

public class BlogServiceImpl extends BlogServiceGrpc.BlogServiceImplBase {

    private MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
    private MongoDatabase database = mongoClient.getDatabase("mydb");
    private MongoCollection<Document> collection = database.getCollection("blog");

    @Override
    public void createBlog(CreateBlogRequest request, StreamObserver<CreateBlogResponse> responseObserver) {


        System.out.println("Received Create Blog Request");

        Blog blog = request.getBlog();

        Document doc = new Document("author_id", blog.getAuthorId())
                .append("title",blog.getTitle())
                .append("content",blog.getContent());


        System.out.println("Inserting Blog..");
        collection.insertOne(doc);
        System.out.println("Blog Inserted");

        String id = doc.getObjectId("_id").toString(); // id que é criado pelo Mongo
        System.out.println("Return String " + id);


        CreateBlogResponse response = CreateBlogResponse.newBuilder()
                .setBlog(blog.toBuilder()
                .setId(id))
                .build();


        responseObserver.onNext(response);

        responseObserver.onCompleted();

    }


}


