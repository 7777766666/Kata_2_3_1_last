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
        User user = null;

        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM User WHERE id=?");

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();

            user = new User();

            user.setId(resultSet.getInt("id"));
            user.setName(resultSet.getString("name"));
            user.setEmail(resultSet.getString("email"));
            user.setAge(resultSet.getInt("age"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return user;
    }

    public void save(User user) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO User VALUES(6, ?, ?, ?)");

            preparedStatement.setString(1, user.getName());
            preparedStatement.setInt(2, user.getAge());
            preparedStatement.setString(3, user.getEmail());

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void update(int id, User updatedUser) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("UPDATE User SET name=?, age=?, email=? WHERE id=?");

            preparedStatement.setString(1, updatedUser.getName());
            preparedStatement.setInt(2, updatedUser.getAge());
            preparedStatement.setString(3, updatedUser.getEmail());
            preparedStatement.setInt(4, id);

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void delete(int id) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("DELETE FROM User WHERE id=?");

            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
