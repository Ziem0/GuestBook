package com.webServer.questbook.controller;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.webServer.questbook.model.EntryList;
import com.webServer.questbook.view.EntryView;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.io.IOException;
import java.io.OutputStream;

public class Guestbook implements HttpHandler {
    private EntryList entryList;

    public Guestbook(EntryList entryList) {
        this.entryList = entryList;
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        EntryView entryView = new EntryView();
        String entries = entryView.getEntriesView(entryList);

        JtwigTemplate jtwigTemplate = JtwigTemplate.classpathTemplate("templates/index.html");

        JtwigModel model = JtwigModel.newModel();

        model.with("entries", entries);

        String response = jtwigTemplate.render(model);

        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
