package com.junjie.xman.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wenjunjie
 * @version 1.0
 */
public class Response implements Serializable {
    private final Map<String, String> parameters;

    public Response() {
        this.parameters = new HashMap();
    }

    public Response(Map<String, String> parameters) {
        this.parameters = parameters;
    }

    public void addParameter(String key, String value) {
        this.parameters.put(key, value);
    }

    public void removeParameter(String key) {
        this.parameters.remove(key);
    }

    public String getParameterByKey(String key) {
        return (String)this.parameters.get(key);
    }

    public void deleteParameterByKey(String key) {
        this.parameters.remove(key);
    }

    public Request toRequest() {
        return new Request(this.parameters);
    }

    public String toString() {
        return "Response{parameters=" + this.parameters + '}';
    }
}
