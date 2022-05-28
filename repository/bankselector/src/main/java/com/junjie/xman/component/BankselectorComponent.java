package com.junjie.xman.component;

import com.junjie.xman.base.SelectorDeployment;
import com.junjie.xman.entity.Request;
import com.junjie.xman.entity.Response;
//import-slot
/**
 * @author wenjunjie
 * @version 1.0
 */
public class BankselectorComponent extends SelectorDeployment {
    @Override
    public Response run(Request request) {
        Response response = super.run(request);
        request = response.toRequest();
        if("1".equals(request.getParameterByKey("bankid"))){request.addParameter("dest_port","17222");}
else if("2".equals(request.getParameterByKey("bankid"))){request.addParameter("dest_port","17333");}
else if("3".equals(request.getParameterByKey("bankid"))){request.addParameter("dest_port","17444");}
        response = request.toResponse();
        return response;
    }

    public static void main(String[] args) throws Exception {
        BankselectorComponent comp = new BankselectorComponent();
        String input = args[0];
        int port = Integer.parseInt(input);
        comp.start_by_net(port);
    }
}
