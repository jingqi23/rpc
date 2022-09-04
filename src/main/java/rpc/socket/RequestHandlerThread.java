package rpc.socket;

import lombok.extern.slf4j.Slf4j;
import rpc.RequestHandler;
import rpc.RpcRequest;
import rpc.RpcResponse;
import rpc.registry.ServiceProvider;
import rpc.registry.ServiceRegistry;
import rpc.serializer.CommonSerializer;

import java.io.*;
import java.net.Socket;

/**
 * 处理RpcRequest的工作线程
 */
@Slf4j
public class RequestHandlerThread implements Runnable {
    private Socket socket;
    private RequestHandler requestHandler;
    private ServiceRegistry serviceRegistry;
    private CommonSerializer serializer;

    public RequestHandlerThread(Socket socket, RequestHandler requestHandler, ServiceRegistry serviceRegistry, CommonSerializer serializer) {
        this.socket = socket;
        this.requestHandler = requestHandler;
        this.serviceRegistry = serviceRegistry;
        this.serializer = serializer;
    }

    @Override
    public void run() {
        try (InputStream inputStream = socket.getInputStream();
             OutputStream outputStream = socket.getOutputStream()) {
            RpcRequest rpcRequest = (RpcRequest) ObjectReader.readObject(inputStream);
            String interfaceName = rpcRequest.getInterfaceName();
            Object result = requestHandler.handle(rpcRequest);
            RpcResponse<Object> response = RpcResponse.success(result, rpcRequest.getRequestId());
            ObjectWriter.writeObject(outputStream, response, serializer);
        } catch (IOException e) {
            log.error("调用或发送时有错误发生：", e);
        }
    }

}

