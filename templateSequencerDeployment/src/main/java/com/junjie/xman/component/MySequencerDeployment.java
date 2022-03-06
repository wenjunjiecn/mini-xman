package com.junjie.xman.component;

import com.junjie.xman.base.SequencerDeployment;
import com.junjie.xman.entity.Request;
import com.junjie.xman.entity.Response;
//import-slot
/**
 * @author wenjunjie
 * @version 1.0
 */
public class MySequencerDeployment extends SequencerDeployment {
    @Override
    public Response run(Request request) {
        //code-slot
        connectDestinations(request,dest_ports);
        return response;
    }

    public static void main(String[] args) throws Exception {
        MySequencerDeployment comp = new MySequencerDeployment();
        String input = args[0];
        int port = Integer.parseInt(input);
        comp.start_by_net(port);
    }
}
