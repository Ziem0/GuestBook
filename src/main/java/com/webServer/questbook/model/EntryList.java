package com.webServer.questbook.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class EntryList {

    private List<Entry> entries;

    public EntryList() {
        this.entries = new ArrayList<>();
    }

    public void addEntry(Entry entry) {
        entries.add(entry);
    }
}
