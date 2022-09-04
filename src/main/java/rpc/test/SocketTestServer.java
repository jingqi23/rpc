package rpc.test;

import rpc.common.HelloService;
import rpc.common.HelloServiceImpl;
import rpc.serializer.HessianSerializer;
import rpc.socket.SocketServer;

public class SocketTestServer {
    public static void main(String[] args) {
        HelloService helloService = new HelloServiceImpl();
        SocketServer socketServer = new SocketServer("127.0.0.1", 9998);
        socketServer.setSerializer(new HessianSerializer());
        socketServer.publishService(helloService, HelloService.class);
    }
}

