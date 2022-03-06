package com.junjie.xman.starter;

import com.junjie.xman.entity.Request;
import com.junjie.xman.entity.Response;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class MainStarter {

    public static void main(String[] args) throws IOException {
        System.out.println("please enter the main component/composite and make sure each related components have been started:");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        System.out.println("please enter the port:");
        int port = Integer.parseInt(scanner.nextLine());
        Request request = new Request();
        Socket socket = null;
        ObjectOutputStream objectOutputStream = null;
        ObjectInputStream objectInputStream = null;
        Response response = null;

        try {
            socket = new Socket("0.0.0.0", port);
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(request);
            objectOutputStream.flush();
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            response = (Response)objectInputStream.readObject();
            System.out.println("The execution result:");
            System.out.println(response);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            objectOutputStream.close();
            objectInputStream.close();
            socket.close();
        }

    }
}
