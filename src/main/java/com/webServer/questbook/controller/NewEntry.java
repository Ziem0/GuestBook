package com.webServer.questbook.controller;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.webServer.questbook.model.Entry;
import com.webServer.questbook.model.EntryList;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

public class NewEntry implements HttpHandler {
    private EntryList entryList;

    public NewEntry(EntryList entryList) {
        this.entryList = entryList;
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        String method = httpExchange.getRequestMethod();
        String response = "";

        if (method.equalsIgnoreCase("GET")) {
            JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/sign.html");
            JtwigModel model = JtwigModel.newModel();
            response = template.render(model);

        } else if (method.equalsIgnoreCase("POST")) {
            InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "UTF-8");
            BufferedReader br = new BufferedReader(isr);

            String formData = br.readLine();
            Map inputs = parseFormData(formData);

            response = "<html><head><meta http-equiv=\"refresh\" content=\"0; url=/\" /></head><html>";

            String name = String.valueOf(inputs.get("name"));
            String message = String.valueOf(inputs.get("message"));

            entryList.addEntry(new Entry(name, message));
        }
        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    private Map<String, String> parseFormData(String formData) {
        Map<String, String> inputs = new HashMap<>();
        String[] pairs = formData.split("&");
        for (String pair : pairs) {
            String[] keyValue = pair.split("=");
            String value = URLDecoder.decode(keyValue[1]);
            inputs.put(keyValue[0], value);
        }
        return inputs;
    }
}
