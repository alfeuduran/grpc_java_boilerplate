// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: dummy/greet.proto

package com.proto.greet;

public final class Greet {
  private Greet() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_greet_Greeting_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_greet_Greeting_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_greet_GreetRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_greet_GreetRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_greet_GreetResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_greet_GreetResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_greet_GreetManyTimesRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_greet_GreetManyTimesRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_greet_GreetManyTimesResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_greet_GreetManyTimesResponse_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\021dummy/greet.proto\022\005greet\"1\n\010Greeting\022\022" +
      "\n\nfirst_name\030\001 \001(\t\022\021\n\tlast_name\030\002 \001(\t\"1\n" +
      "\014GreetRequest\022!\n\010greeting\030\001 \001(\0132\017.greet." +
      "Greeting\"\037\n\rGreetResponse\022\016\n\006result\030\001 \001(" +
      "\t\":\n\025GreetManyTimesRequest\022!\n\010greeting\030\001" +
      " \001(\0132\017.greet.Greeting\"(\n\026GreetManyTimesR" +
      "esponse\022\016\n\006result\030\001 \001(\t2\225\001\n\014GreetService" +
      "\0224\n\005Greet\022\023.greet.GreetRequest\032\024.greet.G" +
      "reetResponse\"\000\022O\n\016GreetManyTimes\022\034.greet" +
      ".GreetManyTimesRequest\032\035.greet.GreetMany" +
      "TimesResponse0\001B\023\n\017com.proto.greetP\001b\006pr" +
      "oto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_greet_Greeting_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_greet_Greeting_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_greet_Greeting_descriptor,
        new java.lang.String[] { "FirstName", "LastName", });
    internal_static_greet_GreetRequest_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_greet_GreetRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_greet_GreetRequest_descriptor,
        new java.lang.String[] { "Greeting", });
    internal_static_greet_GreetResponse_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_greet_GreetResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_greet_GreetResponse_descriptor,
        new java.lang.String[] { "Result", });
    internal_static_greet_GreetManyTimesRequest_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_greet_GreetManyTimesRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_greet_GreetManyTimesRequest_descriptor,
        new java.lang.String[] { "Greeting", });
    internal_static_greet_GreetManyTimesResponse_descriptor =
      getDescriptor().getMessageTypes().get(4);
    internal_static_greet_GreetManyTimesResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_greet_GreetManyTimesResponse_descriptor,
        new java.lang.String[] { "Result", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
