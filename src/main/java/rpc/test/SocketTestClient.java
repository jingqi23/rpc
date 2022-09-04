package rpc.test;

import rpc.RpcClientProxy;
import rpc.common.HelloObject;
import rpc.common.HelloService;
import rpc.serializer.KryoSerializer;
import rpc.socket.SocketClient;

public class SocketTestClient {
    public static void main(String[] args) {
        SocketClient client = new SocketClient();
        client.setSerializer(new KryoSerializer());
        RpcClientProxy proxy = new RpcClientProxy(client);
        HelloService helloService = proxy.getProxy(HelloService.class);
        HelloObject object = new HelloObject(12, "This is a message");
        String res = helloService.hello(object);
        System.out.println(res);
    }
}
