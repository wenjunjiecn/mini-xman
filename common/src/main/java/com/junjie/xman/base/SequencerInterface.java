package com.junjie.xman.base;

import com.junjie.xman.entity.Request;
import com.junjie.xman.entity.Response;
import java.io.IOException;

public interface SequencerInterface {
    Response run(Request request);

    void start_by_net(int port) throws IOException, ClassNotFoundException;
}
