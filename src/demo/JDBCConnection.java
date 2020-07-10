package demo;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCConnection {
    public Connection getConnection() throws Exception {
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection("" +
                "jdbc:postgresql://127.0.0.1:5432/dvdrental", "postgres", "123");
        return connection;
    }
}
