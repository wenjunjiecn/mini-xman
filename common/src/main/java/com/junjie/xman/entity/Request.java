package com.junjie.xman.entity;

import java.io.Serializable;
import java.util.Hashtable;
import java.util.Map;

/**
 * @author wenjunjie
 * @version 1.0
 */
public class Request implements Serializable {
    private final Map<String, String> parameters;

    public Request() {
        this.parameters = new Hashtable();
    }

    public Request(Map<String, String> params) {
        this.parameters = params;
    }

    public String getParameterByKey(String key) {
        return (String)this.parameters.get(key);
    }

    public void deleteParameterByKey(String key) {
        this.parameters.remove(key);
    }

    public void addParameter(String key, String value) {
        this.parameters.put(key, value);
    }

    public void removeParameter(String key) {
        this.parameters.remove(key);
    }

    public Response toResponse() {
        return new Response(this.parameters);
    }
}

