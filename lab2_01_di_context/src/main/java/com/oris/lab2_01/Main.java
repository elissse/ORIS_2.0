package com.oris.lab2_01;

public class Main {
    public static void main(String[] args) {
        Context context = new Context();
        Application application = (Application) context.getObjectByName(Application.class.getName());
        application.run();
    }

}