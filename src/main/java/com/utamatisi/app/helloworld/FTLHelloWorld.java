package com.utamatisi.app.helloworld;

import com.gs.collections.impl.list.mutable.FastList;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FTLHelloWorld {

    public static void main(String[] args) {

        //Freemarker configuration object
        Configuration cfg = new Configuration();
        try {
            //Load template from source folder
            Template template = cfg.getTemplate("src/main/java/com/utamatisi/app/helloworld/helloworld.ftl");

            // Build the data-model
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("message", "Hello World!");

            //List parsing
            List<String> methods = FastList.newListWith("done", "task","inProgress");

            data.put("class", "todo");
            data.put("className", "Todo");
            data.put("fields", methods);


            // Console output
            Writer out = new OutputStreamWriter(System.out);
            template.process(data, out);
            out.flush();

            // File output
            Writer file = new FileWriter (new File("test.java"));
            template.process(data, file);
            file.flush();
            file.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }
}
