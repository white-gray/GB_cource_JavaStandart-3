package com.chat.server;

//import com.chat.auth.AuthenticationService;

public interface Server {
    void broadcastMessage(String message);

    void privateMessage(ClientHandler client, String message);

    boolean isLoggedIn(String nickname);
    void subscribe(ClientHandler client);
    void unsubscribe(ClientHandler client);
//    AuthenticationService getAuthenticationService();
}
