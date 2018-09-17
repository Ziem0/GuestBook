package com.webServer.questbook.view;

import com.webServer.questbook.model.Entry;
import com.webServer.questbook.model.EntryList;

import java.util.Iterator;

public class EntryView {

    public String getEntriesView(EntryList entryList) {
        StringBuilder entries = new StringBuilder();
        Iterator<Entry> iterator = entryList.getIterator();
        while (iterator.hasNext()) {
            Entry entry = iterator.next();
            entries.append("<tr>\n<td>");
            entries.append(entry.getMessage());
            entries.append("<br>name: "+entry.getName());
            entries.append("<br>date: "+entry.getDate());
            entries.append("<br><br></td>\n</tr>\n");
        }
        return entries.toString();
    }
}
