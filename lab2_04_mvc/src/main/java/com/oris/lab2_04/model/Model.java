package com.oris.lab2_04.model;

import java.util.HashMap;
import java.util.Map;

public class Model {

    private String viewName;
    private Map<String, Object> params;

    public Model(String viewName) {
        params = new HashMap<>();
        this.viewName = viewName;
    }

    public void add(String name, Object value) {
        params.put(name, value);
    }

    public String getViewName() {
        return viewName;
    }

    public Map<String, Object> getParams() {
        return params;
    }
}