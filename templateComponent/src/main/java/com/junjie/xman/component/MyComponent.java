package com.junjie.xman.component;

import com.junjie.xman.base.Component;
import com.junjie.xman.entity.Request;
import com.junjie.xman.entity.Response;

/**
 * @author wenjunjie
 * @version 1.0
 */
public class MyComponent extends Component {
    @Override
    public Response run(Request request) {
        Response response = super.run(request);
        //here you write your own code
        System.out.println("the comp1 is running...");
        return response;
    }

    public static void main(String[] args) throws Exception {
        MyComponent comp = new MyComponent();
        String input = args[0];
        int port = Integer.parseInt(input);
        comp.start_by_net(port);
    }
}
