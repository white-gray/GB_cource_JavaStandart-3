package com.chat.server;

//import com.chat.auth.AuthenticationService;
//import com.chat.auth.BasicAuthenticationService;
import com.chat.db.DBService;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


public class ChatServer implements Server {
    private Set<ClientHandler> clients;
//    private AuthenticationService authenticationService;

    public ChatServer() {
        System.out.println("enter ChatServer");
        try {
            System.out.println("Server is starting up...");
            ServerSocket serverSocket = new ServerSocket(8888);
            clients = new HashSet<>();
//            authenticationService =  new BasicAuthenticationService();
  /**
  *  проверка есть ли такая таблица, и если нет, - создать её, и прописать в ней пользователя admin
   *  ...да и кучку ещё пользователей...
  */
            Connection connection = DBService.getConnection();
                if (!DBService.checkDB(connection)) {
                    Connection sd= DBService.fillDB (connection);}
            System.out.println("Base of logins is OK");
//__________________________________________________________
            System.out.println("Server is started up...");

            while (true) {
                System.out.println("Server is listening for clients...");
                Socket socket = serverSocket.accept();
                System.out.println("Client accepted: " + socket);
                new ClientHandler(this, socket);
            }
        } catch (IOException | SQLException e) {
            throw new RuntimeException("SWW", e);
        }
    }

    @Override
    public synchronized void broadcastMessage(String message) {
        System.out.println("enter broadcastMessage");
        for (ClientHandler client :clients) {
            System.out.println("_________________broadcastMessage_clients : "+client);
            client.sendMessage(message);
        }
//        clients.forEach(client -> System.out.println("broadcastMessage_clients-2 : "));
//        clients.forEach(client -> client.sendMessage(message));
    }

    @Override
    public void privateMessage(ClientHandler client, String message) {
        System.out.println("Enter privateMessage");
        String[] messagePart = message.split("\\s");
        String itName = messagePart[1];
        String senderName = client.getName();
        System.out.println("itName = " + itName + "\tsenderName = " + senderName);
        messagePart [0] = messagePart [1] = null;
        for (int q=0; q< messagePart.length; q++){
            System.out.println("messagePart["+q+"] = "+messagePart[q]);
        }
        for (ClientHandler cl :clients) {
            System.out.println("----itName = "+itName+"\tcl.getName = "+cl.getName());
            if (cl.getName().equals(itName)){
                System.out.println("send");
                 cl.sendMessage("From user "+ senderName + ":\n\t"+String.join(" ", messagePart).replace("null ", "").replace("null", ""));
            }
        }
        return;
    }

    @Override
    public synchronized boolean isLoggedIn(String nickname) {
        System.out.println("enter isLoggedIn");
        return clients.stream()
                .filter(clientHandler -> clientHandler.getName().equals(nickname))
                .findFirst()
                .isPresent();
    }

    @Override
    public synchronized void subscribe(ClientHandler client) {
        System.out.println("enter subscribe");
        clients.add(client);
        for (ClientHandler q : clients) {
            System.out.println("----------- client "+q);
        }
    }

    @Override
    public synchronized void unsubscribe(ClientHandler client) {
        System.out.println("enter client");
        clients.remove(client);
    }

//      @Override
//      public AuthenticationService getAuthenticationService() {
//          System.out.println("enter getAuthenticationService");
//
//          return authenticationService;
//      }
}
