package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by joakim on 2015-12-08.
 */
public class DatabaseConfiguration {

    public DatabaseConfiguration() {

    }

    public static Connection getConnection() {
        Connection connection = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            String url = "jdbc:mysql://localhost:3306/championwarfare";
            connection = DriverManager.getConnection(url, "root", "hejjocke");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }


}
