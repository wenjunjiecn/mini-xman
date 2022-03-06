package com.junjie.xman.base;

import com.junjie.xman.entity.Request;
import com.junjie.xman.entity.Response;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Component implements ComponentInterface {

    public Response run(Request request) {
        Response response = request.toResponse();
        return response;
    }

    public void start_by_net(int port) throws IOException, ClassNotFoundException {
        ServerSocket server = null;
        Socket socket = null;
        ObjectOutputStream objectOutputStream = null;
        Response response = null;
        boolean flag = true;

        while(true) {
            try {
                server = new ServerSocket(port);
                if (flag) {
                    flag = false;
                    System.out.println("The " + this.getClass().getName() + " component start successfully in port " + server.getLocalPort());
                }

                socket = server.accept();
                ObjectInputStream oi = new ObjectInputStream(socket.getInputStream());
                Request request = (Request)oi.readObject();
                response = this.run(request);
                objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                objectOutputStream.writeObject(response);
                objectOutputStream.flush();
            } catch (ClassNotFoundException | IOException e) {
                throw e;
            } finally {
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }

                if (socket != null) {
                    socket.close();
                }

                if (server != null) {
                    server.close();
                }

            }
        }
    }
}