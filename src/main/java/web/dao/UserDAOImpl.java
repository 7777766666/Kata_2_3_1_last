package web.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import web.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDAOImpl implements UserDAO {
    private static int USERS_COUNT;

    private static Connection connection;

    private static final String URL = "jdbc:mysql://localhost:3306/mydbtest?verifyServerCertificate=false&useSSL=false&requireSSL=false&useLegacyDatetimeCode=false&amp&serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<User> index() {
        List<User> users = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM User";
            ResultSet resultSet = statement.executeQuery(SQL);

            while(resultSet.next()) {
                User user = new User();

                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setEmail(resultSet.getString("email"));
                user.setAge(resultSet.getInt("age"));

                users.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return users;
    }

    public User show(int id) {
//        return users.stream().filter(user -> user.getId() == id).findAny().orElse(null);
        return null;
    }

    public void save(User user) {
//        user.setId(++USERS_COUNT);
//        users.add(user);

        try {
            Statement statement = connection.createStatement();
            String SQL = "INSERT INTO User VALUES(" + 1 + ",'" + user.getName() +
                    "'," + user.getAge() + ",'" + user.getEmail() + "')";

            statement.executeUpdate(SQL);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    public void update(int id, User updatedUser) {
//        User userToBeUpdated = show(id);
//
//        userToBeUpdated.setName(updatedUser.getName());
//        userToBeUpdated.setAge(updatedUser.getAge());
//        userToBeUpdated.setEmail(updatedUser.getEmail());
    }

    public void delete(int id) {
//        users.removeIf(p -> p.getId() == id);
    }
}
