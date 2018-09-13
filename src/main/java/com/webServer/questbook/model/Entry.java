package com.webServer.questbook.model;

import lombok.Getter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
public class Entry {
    private int id;
    private String name;
    private String message;
    private LocalDate date;

    public Entry(int id, String name, String message) {
        this.id = id;
        this.name = name;
        this.message = message;
        this.date = LocalDate.now();
    }

    public Entry(int id, String name, String message, LocalDate date) {
        this.id = id;
        this.name = name;
        this.message = message;
        this.date = date;
    }

    public String getDate() {
        return date.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    @Override
    public String toString() {
        return String.format("name:%s\ndate:%s\nmessage:%s", name, getDate(), message);
    }

//    public String[] toStringArray() {
//        return new String[]{""+id, name, message, getDate()};
//    }
}
