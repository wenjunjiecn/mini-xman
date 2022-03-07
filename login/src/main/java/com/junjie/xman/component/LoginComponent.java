package com.junjie.xman.component;

import com.junjie.xman.base.Sequencer;
import com.junjie.xman.entity.Request;
import com.junjie.xman.entity.Response;
import com.junjie.xman.component.InputComponent;
import com.junjie.xman.component.VerifyComponent;
/**
 * @author wenjunjie
 * @version 1.0
 */
public class LoginComponent extends Sequencer {
    @Override
    public Response run(Request request) {
        Response response = super.run(request);
        request = response.toRequest();
		InputComponent input = new InputComponent();
		response = input.run(request);
		request = response.toRequest();
		VerifyComponent verify = new VerifyComponent();
		response = verify.run(request);
        return response;
    }

    public static void main(String[] args) throws Exception {
        LoginComponent comp = new LoginComponent();
        String input = args[0];
        int port = Integer.parseInt(input);
        comp.start_by_net(port);
    }
}
