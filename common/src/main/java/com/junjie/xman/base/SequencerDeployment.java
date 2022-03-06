package com.junjie.xman.base;

import com.junjie.xman.entity.Request;
import com.junjie.xman.entity.Response;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author wenjunjie
 * @version 1.0
 */
public class SequencerDeployment implements SequencerDeploymentInterface{
    public Response run(Request request) throws IOException {
        return request.toResponse();
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
                    System.out.println("The " + this.getClass().getName() + " start successfully in port " + server.getLocalPort());
                }

                socket = server.accept();
                ObjectInputStream oi = new ObjectInputStream(socket.getInputStream());
                Request request = (Request)oi.readObject();
                response = run(request);
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

    public Response connectDestinations(Request request, int[] dest_ports) throws IOException {
        Socket socket = null;
        ObjectOutputStream objectOutputStream = null;
        ObjectInputStream objectInputStream = null;
        Response response = null;
        for (int dest_port : dest_ports) {
            try {
                socket = new Socket("0.0.0.0", dest_port);
                objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                objectOutputStream.writeObject(request);
                objectOutputStream.flush();
                objectInputStream = new ObjectInputStream(socket.getInputStream());
                response = (Response)objectInputStream.readObject();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                objectOutputStream.close();
                objectInputStream.close();
                socket.close();
            }
            request = response.toRequest();
        }


        return response;
    }
}
