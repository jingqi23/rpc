package rpc.test;

import rpc.RpcClient;
import rpc.RpcClientProxy;
import rpc.common.HelloObject;
import rpc.common.HelloService;
import rpc.netty.NettyClient;
import rpc.serializer.ProtobufSerializer;

public class NettyTestClient {

    public static void main(String[] args) {
        RpcClient client = new NettyClient();
        client.setSerializer(new ProtobufSerializer());
        RpcClientProxy rpcClientProxy = new RpcClientProxy(client);
        HelloService helloService = rpcClientProxy.getProxy(HelloService.class);
        HelloObject object = new HelloObject(12, "This is a message");
        String res = helloService.hello(object);
        System.out.println(res);
    }

}

