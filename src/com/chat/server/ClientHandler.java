package com.chat.server;

//import com.chat.auth.BasicAuthenticationService;
import com.chat.db.DBService;
import com.chat.log.Log;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

public class ClientHandler {
    private Server server;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private String name;
//    private String [] included;

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

                    if (credentialValues.length < 3) {
                        sendMessage("Enter right name & password please!");
                    }
                    else if (server.isLoggedIn(credentialValues[1])){
                        sendMessage("You are already here!");
                    }
                    else if (DBService.checkAccount(connection, credentialValues)) {
                            sendMessage("cmd auth: Status OK.");
                            sendMessage(Log.readLog());
                        name = credentialValues[1];
                            server.broadcastMessage(name + " is logged in.");
//                            System.out.println("------------this name = " + name + "\tin= "+ this.in + "\t out = " + this.out);
//                            System.out.println("--------------hashCode = " + hashCode());
//                            System.out.println("--------------equals = "+equals(hashCode())); // I try
//                            System.out.println("--------------hashCode = " + hashCode());
                            server.subscribe(this);
//                            System.out.println("--------------hashCode = " + hashCode());
                            return;
                            }
                    else {sendMessage("I don't know you! Or password is wrong. \n\tCheck please! \n\tAnd register please, if you have not already done so!");}
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
        try {
            while (true) {
                sendMessage("\nPlease enter broadcast massage, \n\tor \"-s login ...\' for message specially for login accaunt:");
                String message = in.readUTF();
                Log.writeLog (name, message);
                if (message.equals("-exit")) {
                    out.writeUTF("Good buuuuuuuy Sir!");
                    server.broadcastMessage(name + " loged out");
                    System.out.println("out name = " + name);
                    return;
                }
                else if (message.startsWith("-s")) {
                    System.out.println("I go to privateMessage");
                    server.privateMessage(this, message);
                }
                else {
                    server.broadcastMessage(message);
                }
            }
        } catch (IOException e) {
//            return;
            System.out.println("____________ERROR receiveMessage");
            throw new RuntimeException("SWW", e);
        }
    }

    public void sendMessage(String message) {
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
        System.out.println("\tserver = " + server + "\tsocket = " + socket + "\tin = "+ in + "\tout = " + out + "\tname = "+ name);
        return Objects.hash(server, socket, in, out, name);
    }
}
