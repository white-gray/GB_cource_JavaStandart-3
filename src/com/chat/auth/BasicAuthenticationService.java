package com.chat.auth;

import com.chat.db.DBService;
import com.chat.entity.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public class BasicAuthenticationService implements AuthenticationService {
    /**
     * Fake database with stubbed entities
     */

//    private static final List<User> users;
//
//    static {
//        users = List.of(
//                new User("n1", "n1@mail", "1"),
//                new User("n2", "n2@mail", "2"),
//                new User("n3", "n3@mail", "3")
//        );
//    }




    public BasicAuthenticationService ()  {
//  проверка есть ли такая таблица, и если нет, - создать её, и прописать в ней пользователя admin
        Connection connection = DBService.getConnection();
        try {
            if (DBService.checkDB(connection)) {
            }
            else {
                Connection sd= DBService.fillDB (connection);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }



//
//    public static List<User> getUsers() {
//        return users;
//    }
//
//    @Override
//    public Optional<User> doAuth(String email, String password) {
//        for (User user : users) {
//            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
//                return Optional.of(user);
//            }
//        }
//        return Optional.empty();
//    }
}
