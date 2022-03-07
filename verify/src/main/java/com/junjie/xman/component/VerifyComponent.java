package com.junjie.xman.component;

import com.junjie.xman.base.Component;
import com.junjie.xman.entity.Request;
import com.junjie.xman.entity.Response;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wenjunjie
 * @version 1.0
 */
public class VerifyComponent extends Component {
    @Override
    public Response run(Request request) {
        Response response = super.run(request);
        //here you write your own code
        Map<String,String> accounts = new HashMap<>();
        accounts.put("11111","12345");
        accounts.put("22222","23456");
        String account = response.getParameterByKey("account");
        String password = response.getParameterByKey("password");
        if(password.equals(accounts.get(account))){
            response.addParameter("verify","true");
        }else{
            response.addParameter("verify","false");
        }

        return response;
    }

    public static void main(String[] args) throws Exception {
        VerifyComponent comp = new VerifyComponent();
        String input = args[0];
        int port = Integer.parseInt(input);
        comp.start_by_net(port);
    }
}
