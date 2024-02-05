package Connect;

import java.sql.Connection;
import java.sql.SQLException;

public class Connecting {
    public static Connection getConnection() throws SQLException {
        return Hikari.getDataSource().getConnection();
    }
}
