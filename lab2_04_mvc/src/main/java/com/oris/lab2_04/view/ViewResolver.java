package com.oris.lab2_04.view;

import com.oris.lab2_04.model.Model;

import java.io.File;
import java.net.URL;

public class ViewResolver {

    private Model model;

    public ViewResolver(Model model) {
        this.model = model;
    }

    public View resolve() {
        URL url = ViewResolver.class.getClassLoader().getResource(model.getViewName() + ".html");
        File viewFile = new File(url.getPath());
        return new View(model, viewFile);
    }
}