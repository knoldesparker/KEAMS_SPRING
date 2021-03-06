package keams.keams;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionCreator {
    private Connection connection = null;

    public ConnectionCreator() {
        try {
            Class.forName("com.mysql.jdbc.Driver");

        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
    public Connection getConnection() {
        try {
            // connection to webhost
        /*
        connection = DriverManager.
                getConnection("jdbc:mysql://den1.mysql6.gear.host/stratekdb?user=stratekdb&password=stratekDB123!&useUnicode=true&characterEncoding=UTF-8");
        */

            // connection to localhost

            connection = DriverManager.
                    getConnection("jdbc:mysql://localhost/kea?user=root&password=1234&useUnicode=true&characterEncoding=UTF-8");


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return connection;
    }
}
