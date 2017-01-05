Using gRPC in both C# and Java should be just as easy as using them individually. 
All you need to do is use the same .proto file to generate code for each. 
The .proto defines the methods and messages that both C# and Java would be compatible with.

If you look at each set of examples, they each use the same .proto (i.e., helloworld.proto or route_guide.proto). 
The examples are compatible between languages (i.e., C# helloworld server can communicate with Java helloworld client).

Specifying gRPC services in proto files is pretty straight-forward; 
I'd assume just looking at the examples would make that clear enough. 
For more information about defining proto messages, you may take a look at the protobuf documentation.


source: http://stackoverflow.com/questions/40443556/communication-between-c-sharp-and-java