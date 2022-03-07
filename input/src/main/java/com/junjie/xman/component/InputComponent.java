package com.junjie.xman.component;

import com.junjie.xman.base.Component;
import com.junjie.xman.entity.Request;
import com.junjie.xman.entity.Response;
import java.util.Scanner;


/**
 * @author wenjunjie
 * @version 1.0
 */
public class InputComponent extends Component {
    @Override
    public Response run(Request request) {
        Response response = super.run(request);
        //here you write your own code
        System.out.println("=========ATM==========");
        System.out.println("please enter your account:");
        Scanner scanner = new Scanner(System.in);
        String account = scanner.nextLine();
        System.out.println("please enter your password:");
        String password = scanner.nextLine();
        System.out.println("Please enter the serial number of the bank where you want to check your balance:");
        System.out.println("1.Bank1");
        System.out.println("2.Bank2");
        System.out.println("3.Bank3");
        String bankid = scanner.nextLine();
        
        response.addParameter("account",account);
        response.addParameter("password",password);
        response.addParameter("bankid",bankid);
        
        return response;
    }

    public static void main(String[] args) throws Exception {
        InputComponent comp = new InputComponent();
        String input = args[0];
        int port = Integer.parseInt(input);
        comp.start_by_net(port);
    }
}
