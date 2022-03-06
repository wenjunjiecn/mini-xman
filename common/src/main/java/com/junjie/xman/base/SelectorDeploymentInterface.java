package com.junjie.xman.base;

import com.junjie.xman.entity.Request;
import com.junjie.xman.entity.Response;
import java.io.IOException;

public interface SelectorDeploymentInterface {
    Response run(Request request);

    void start_by_net(int port) throws IOException, ClassNotFoundException;
}
