package com.junjie.xman.component;

import com.junjie.xman.base.Sequencer;
import com.junjie.xman.entity.Request;
import com.junjie.xman.entity.Response;
//import-slot
/**
 * @author wenjunjie
 * @version 1.0
 */
public class MySequencer extends Sequencer {
    @Override
    public Response run(Request request) {
        Response response = super.run(request);
        //code-slot
        return response;
    }

    public static void main(String[] args) throws Exception {
        MySequencer comp = new MySequencer();
        String input = args[0];
        int port = Integer.parseInt(input);
        comp.start_by_net(port);
    }
}
