package rpc.test;

import rpc.common.HelloService;
import rpc.common.HelloServiceImpl;
import rpc.netty.NettyServer;
import rpc.serializer.ProtobufSerializer;

public class NettyTestServer {

    public static void main(String[] args) {
        HelloService helloService = new HelloServiceImpl();
        NettyServer server = new NettyServer("127.0.0.1", 9999);
        server.setSerializer(new ProtobufSerializer());
        server.publishService(helloService, HelloService.class);
    }

}

