package com.chat.server;

import com.chat.auth.AuthenticationService;
import com.chat.auth.BasicAuthenticationService;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class ChatServer implements Server {
    private Set<ClientHandler> clients;
    private AuthenticationService authenticationService;

    public ChatServer() {
        System.out.println("enter ChatServer");
        try {
            System.out.println("Server is starting up...");
            ServerSocket serverSocket = new ServerSocket(8888);
            clients = new HashSet<>();
            authenticationService =  new BasicAuthenticationService();
            System.out.println("Server is started up...");

            while (true) {
                System.out.println("Server is listening for clients...");
                Socket socket = serverSocket.accept();
                System.out.println("Client accepted: " + socket);
                new ClientHandler(this, socket);
            }
        } catch (IOException e) {
            throw new RuntimeException("SWW", e);
        }
    }

    @Override
    public synchronized void broadcastMessage(String message) {
        System.out.println("enter broadcastMessage");
        for (ClientHandler z :clients) {
            System.out.println("broadcastMessage_clients : "+z);
        }
        clients.forEach(client -> System.out.println("broadcastMessage_clients-2 : "));
        clients.forEach(client -> client.sendMessage(message));
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
    }

    @Override
    public synchronized void unsubscribe(ClientHandler client) {
        System.out.println("enter client");
        clients.remove(client);
    }

      @Override
      public AuthenticationService getAuthenticationService() {
          System.out.println("enter getAuthenticationService");

          return authenticationService;
      }
}
