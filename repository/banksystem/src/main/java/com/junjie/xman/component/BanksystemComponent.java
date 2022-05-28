package com.junjie.xman.component;

import com.junjie.xman.base.SequencerDeployment;
import com.junjie.xman.entity.Request;
import com.junjie.xman.entity.Response;
import java.io.IOException;
/**
 * @author wenjunjie
 * @version 1.0
 */
public class BanksystemComponent extends SequencerDeployment {
    @Override
    public Response run(Request request) throws IOException{
        int[] dest_ports={17111 , 17555};
        Response response = connectDestinations(request, dest_ports);
        return response;
    }

    public static void main(String[] args) throws Exception {
        BanksystemComponent comp = new BanksystemComponent();
        String input = args[0];
        int port = Integer.parseInt(input);
        comp.start_by_net(port);
    }
}
