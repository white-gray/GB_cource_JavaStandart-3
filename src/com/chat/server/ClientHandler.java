package com.chat.server;

import com.chat.auth.BasicAuthenticationService;
import com.chat.db.DBService;
import com.chat.entity.User;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.spi.AbstractResourceBundleProvider;

public class ClientHandler {
    private Server server;
    private Socket socket;
    private DataInputStream in;
    private static DataOutputStream out;
    private String name;
    private String [] included;

    public ClientHandler(Server server, Socket socket) {
        try {
            this.server = server;
            this.socket = socket;
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            doListen();
        } catch (IOException e) {
            throw new RuntimeException("SWW", e);
        }
    }

    public String getName() {
        return name;
    }

    private void doListen() {
            System.out.println("enter doListen");
            new Thread(() -> {
                try {
                    doAuth();
                    System.out.println("Exit doAuth");
                    receiveMessage();
                    System.out.println("Exit receiveMessage");
                } catch (Exception e) {
                    throw new RuntimeException("SWW", e);
                } finally {
                    server.unsubscribe(this);
                    System.out.println("Client " + socket + " is out");
                    Connection connection = DBService.getConnection();
                    try {
                        DBService.logOut(connection, name);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }).start();
            System.out.println("exit doListen");

    }

    private void doAuth() throws SocketException {
        System.out.println("enter doAuth");

        try {
            while (true) {
                sendMessage("Please enter:\"-auth Nick Name\"");
                String credentials = in.readUTF();
                System.out.println("credentials = " + credentials);
                /**
                  * Input credentials sample
                  * "-auth n1@mail.com 1"
                  */
                if (credentials.startsWith("-auth")) {
                    /**
                     * After splitting sample
                     * array of ["-auth", "n1@mail.com", "1"]
                     */
                    String[] credentialValues = credentials.split("\\s");


                    Connection connection = DBService.getConnection();
                    if (DBService.testValue(connection, credentialValues)) {
                        sendMessage("cmd auth: Status OK.");
                        name = credentialValues[1];
                        server.broadcastMessage(name + " is logged in.");
                        server.subscribe(this);
                        return;
                        }
                }
                }
            }
         catch (IOException | SQLException e) {
            System.out.println("\n\tGood buuuuuuuy!");
  //          System.exit(0);
            throw new RuntimeException("SWW", e);
        }

    }

    /**
     * Receives input data from {@link ClientHandler#in} and then broadcast via {@link Server#broadcastMessage(String)}
     */
    private void receiveMessage() {
        System.out.println("enter receiveMessage");
        sendMessage("Please enter broadcast massage:");
        try {
            while (true) {
                String message = in.readUTF();
                if (message.equals("-exit")) {
                    out.writeUTF("Good buuuuuuuy Sir!");
                    server.broadcastMessage(name + " loged out");
                    System.out.println("out name = " + name);
                    return;
                }
//                if (message.startsWith("/w")) {
//                    List users = BasicAuthenticationService.getUsers();
//                    System.out.println(server.isLoggedIn("n1"));
//                    System.out.println("getName() = " + getName());
//                   String[] messagePart = message.split("\\s");
//                    System.out.println("!!! " + messagePart);
//                    return;
//                }
                server.broadcastMessage(message);
            }
        } catch (IOException e) {
            return;
//            throw new RuntimeException("SWW", e);
        }
    }

    public static void sendMessage(String message) {
        System.out.println("enter sendMessage");

        try {
            out.writeUTF(message);
        } catch (IOException e) {
            throw new RuntimeException("SWW", e);
        }
    }

    @Override
    public boolean equals(Object o) {
        System.out.println("enter equals");

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientHandler that = (ClientHandler) o;
        return Objects.equals(server, that.server) &&
                Objects.equals(socket, that.socket) &&
                Objects.equals(in, that.in) &&
                Objects.equals(out, that.out) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        System.out.println("enter hashCode");
        return Objects.hash(server, socket, in, out, name);
    }
}
