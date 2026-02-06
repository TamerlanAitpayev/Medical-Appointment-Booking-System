package Factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionSingleton {

    private static Connection connection;

    // private constructor
    private DatabaseConnectionSingleton() {}

    public static Connection getInstance() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(
                        "YOUR_DB_URL",
                        "YOUR_DB_USER",
                        "YOUR_DB_PASSWORD"
                );
                System.out.println("Database connected (Singleton)");
            } catch (SQLException e) {
                throw new RuntimeException("Database connection failed", e);
            }
        }
        return connection;
    }
}
