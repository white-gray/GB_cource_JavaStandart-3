package com.chat.db;

import com.chat.server.ClientHandler;

import java.sql.*;
import java.sql.ResultSet;

public class DBService {
    public DBService() {}

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/GN_JavaSt_Less3_accounts?createDatabaseIfNotExist=true&allowPublicKeyRetrieval=true&useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC",
                    "root",
                    "rooTRoot7"
            );
        } catch (SQLException throwables) {
            throw new RuntimeException("SWW during establishing DB connection", throwables);
        }
    }

    public static void close(Connection connection) {
        try {
            connection.close();
        } catch (SQLException throwables) {
            throw new RuntimeException("SWW during connection close", throwables);
        }
    }

    public static void logOut (Connection connection, String name) throws SQLException {
        System.out.println("DBService_logOut");
        Statement stmt = connection.createStatement();
        int rss = stmt.executeUpdate("UPDATE `gn_javast_less3_accounts`.`users`\n" +
                "SET\n" +
                "here = 0\n" +
                "WHERE name=\""+name+"\";");
        return;
    }



    public static void rollback(Connection connection) {
        try {
            connection.rollback();
        } catch (SQLException e) {
            throw new RuntimeException("SWW during rollback", e);
        }
    }


    public static void insertUser(Connection connection) throws SQLException {
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("INSERT INTO `gn_javast_less3_accounts`.`users`\n" +
                        "(`name`,\n" +
                        "`e-mail`,\n" +
                        "`password`)\n" +
                        "VALUES\n" +
                        "(\"nickName\", \"nickMail\", \"nickPasword\");");
    }



    public static Connection fillDB (Connection connection) throws SQLException {
// создание таблицы и админа; да и ещё кучки пользователей
        Statement stmt = connection.createStatement();
        int rs = stmt.executeUpdate("CREATE TABLE `gn_javast_less3_accounts`.`users` (\n" +
                "  `idUsers` INT NOT NULL AUTO_INCREMENT,\n" +
                "  `name` VARCHAR(45) NOT NULL,\n" +
                "  `e-mail` VARCHAR(45) NOT NULL,\n" +
                "  `password` VARCHAR(45) NOT NULL,\n" +
                "  `here` TINYINT NOT NULL,\n" +
                "  PRIMARY KEY (`idUsers`));");
        rs = stmt.executeUpdate("INSERT INTO `gn_javast_less3_accounts`.`users`\n" +
                "(`name`,\n" +
                "`e-mail`,\n" +
                "`password`,\n"+
                "`here`)\n" +
                "VALUES (\"admin\", \"adm@mail.com\", \"0\", 0);");
        for (int j=1; j<21; j++){
        rs = stmt.executeUpdate("INSERT INTO `gn_javast_less3_accounts`.`users`\n" +
                "(`name`,\n" +
                "`e-mail`,\n" +
                "`password`,\n"+
                "`here`)\n" +
                "VALUES (\""+j+"\", \""+j+"@mail.com\", \""+j+"\", 0);");
        }
        stmt.close();
        return connection;
    }

    public static boolean checkDB (Connection connection) throws SQLException {
 // проверка прописана ли в базе таблица
        Statement stmt = connection.createStatement();
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM `gn_javast_less3_accounts`.`users`;");
        } catch (SQLException throwables) {
            return false;
        }
        finally {
            stmt.close();
        }
        return true;
    }




    public static boolean testValue (Connection connection, String [] test) throws SQLException {
// проверка nick and password
        Statement stmt = connection.createStatement();

        for (String q : test) {
            System.out.println("test = " + q);
        }
        System.out.println("test[1] = " +test[1] + "\ttest[2] = " +test[2]);

        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM `gn_javast_less3_accounts`.`users` WHERE name=\""+test[1]+"\" AND password=\""+test[2]+"\";");

            if(rs.absolute(1)) {
                ResultSet sr = stmt.executeQuery("SELECT * FROM `gn_javast_less3_accounts`.`users` WHERE name=\""+test[1]+"\" AND password=\""+test[2]+"\" AND here=1;");
                if(sr.absolute(1)) {
                    ClientHandler.sendMessage("You are already here!");
                    return false;
                     }
                int rss = stmt.executeUpdate("UPDATE `gn_javast_less3_accounts`.`users`\n" +
                    "SET\n" +
                    "here = 1\n" +
                    "WHERE name=\""+test[1]+"\" AND password=\""+test[2]+"\";");
                return true;
            }
            ClientHandler.sendMessage("No a such user by nick and password.");
            return false;
        } catch (SQLException throwables) {
            System.out.println("false");
            throwables.printStackTrace();
            return false;
        }
        finally {
            System.out.println("stmt.close()");
            stmt.close();
        }


    }



}
