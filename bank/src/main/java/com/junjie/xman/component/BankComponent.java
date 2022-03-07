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
public class BankComponent extends Component {
    @Override
    public Response run(Request request) {
        Response response = super.run(request);
        //here you write your own code
        String bankid = response.getParameterByKey("bankid");
        Map<String,String> balances = connectDataBase(bankid);//mock-database
        String account = response.getParameterByKey("account");
        String balance = balances.get(account);
        response.addParameter("balance",balance);
        System.out.println("The user "+account+" is checking the balance:"+balance);

        return response;
    }

    public static void main(String[] args) throws Exception {
        BankComponent comp = new BankComponent();
        String input = args[0];
        int port = Integer.parseInt(input);
        comp.start_by_net(port);
    }

    private Map<String, String> connectDataBase(String bankid) {
        Map<String,String> balances = new HashMap<>();
        if("1".equals(bankid)){
            balances.put("11111","10000");
            balances.put("22222","20000");
        }else if("2".equals(bankid)){
            balances.put("11111","30000");
            balances.put("22222","40000");
        }else if("3".equals(bankid)){
            balances.put("11111","50000");
            balances.put("22222","60000");
        }
        return balances;
    }

}
