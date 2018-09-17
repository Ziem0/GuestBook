package com.webServer.questbook.dao;

import com.webServer.questbook.model.Entry;
import com.webServer.questbook.model.EntryList;

import java.sql.*;
import java.time.LocalDate;

public class ConnectBook {
    private static ConnectBook connectBook = null;
    private final Connection connection;
    private PreparedStatement preparedStatement = null;
    private ResultSet result = null;

    private ConnectBook() {
        this.connection = ConnectDB.getConnection();
        ConnectDB.migrate();
    }

    public static ConnectBook getConnectBook() {
        if (connectBook == null) {
            synchronized (ConnectBook.class) {
                if (connectBook == null) {
                    connectBook = new ConnectBook();
                }
            }
        }
        return connectBook;
    }

    private void setPreparedStatement(String sql) {
        try {
            preparedStatement = connection.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void closePreparedStatementAndResult() {
        try {
            preparedStatement.close();
            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addNewRecord(Entry entry) {
        String command = "insert into entries(name, message, date) values(?,?,?);";
        setPreparedStatement(command);
        try {
            preparedStatement.setString(1,entry.getName());
            preparedStatement.setString(2,entry.getMessage());
            preparedStatement.setString(3, entry.getDate());
            preparedStatement.executeUpdate();
            closePreparedStatementAndResult();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public EntryList getEntries() {
        EntryList entryList = new EntryList();
        String query = "select * from entries;";
        setPreparedStatement(query);
        try {
            result = preparedStatement.executeQuery();
            while (result.next()) {
                int id = result.getInt(1);
                String name = result.getString(2);
                String message = result.getString(3);
                String date = result.getString(4);
                LocalDate formDate = LocalDate.parse(date);
                entryList.addEntry(new Entry(id, name, message, formDate));
            }
            closePreparedStatementAndResult();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entryList;
    }
}
