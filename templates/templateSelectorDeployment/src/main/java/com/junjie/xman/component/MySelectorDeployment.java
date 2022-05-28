package com.junjie.xman.component;

import com.junjie.xman.base.SelectorDeployment;
import com.junjie.xman.entity.Request;
import com.junjie.xman.entity.Response;
//import-slot
/**
 * @author wenjunjie
 * @version 1.0
 */
public class MySelectorDeployment extends SelectorDeployment {
    @Override
    public Response run(Request request) {
        Response response = super.run(request);
        request = response.toRequest();
        //condition-slot
        response = request.toResponse();
        return response;
    }

    public static void main(String[] args) throws Exception {
        MySelectorDeployment comp = new MySelectorDeployment();
        String input = args[0];
        int port = Integer.parseInt(input);
        comp.start_by_net(port);
    }
}
