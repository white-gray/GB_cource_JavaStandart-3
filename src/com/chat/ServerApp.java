package com.chat;

import com.chat.db.DBService;
import com.chat.server.ChatServer;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ServerApp {
    public static void main(String[] args) {
        new ChatServer();
    }
}
