package Connect;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

public class Hikari {
    private static final HikariConfig config = new HikariConfig();
    private static final HikariDataSource dataSource;

    static {
        config.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); 
        config.setJdbcUrl("jdbc:sqlserver://localhost:1433;databaseName=MySpace");
        config.setUsername("sa");
        config.setPassword("sa");

        dataSource = new HikariDataSource(config);
    }

    public static DataSource getDataSource() {
        return dataSource;
    }
}
