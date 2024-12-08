package com.oris.lab06;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Version;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@WebServlet("*.ftl")
public class TemplateHandlerServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    //    ApplicationContext context = new ClassPathXmlApplicationContext("D:\\IdeaProjects\\ORIS_2.0\\lab06_jdbc\\src\\main\\webapp\\WEB_INF\\web.xml");
        var cfg = new Configuration(new Version("2.3.31"));
        cfg.setClassForTemplateLoading(TemplateHandlerServlet.class, "/templates");
        cfg.setDefaultEncoding("UTF-8");
//        FreeMarkerConfiguration configurationWrapper = context.getBean("freeMarkerConf", FreeMarkerConfiguration.class);
//        Configuration cfg = configurationWrapper.getConfiguration();

        Template template = cfg.getTemplate("index.ftl");
        Map<String, Object> data = new HashMap<String, Object>();
        InputStream inputStream = TemplateHandlerServlet.class.getClassLoader().getResourceAsStream(request.getServletPath());
        byte[] content = inputStream.readAllBytes();
        String contentString = new String(content);
        System.out.println(contentString);
        Iterator<String> iter = request.getAttributeNames().asIterator();

        while (iter.hasNext()) {
            String name = iter.next();
            System.out.println(name + "     " + request.getAttribute(name));
            if (contentString.contains(name.strip())) {
                data.put(name, request.getAttribute(name));
                System.out.println(name + "     " + request.getAttribute(name));
            }
        }
        try (StringWriter out = new StringWriter()) {
            template.process(data, out);
            Writer fileWriter = response.getWriter();
            try {
                template.process(data, fileWriter);
            } finally {
                fileWriter.close();
            }
            System.out.println(out.getBuffer().toString());
            out.flush();
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }
    }

}
