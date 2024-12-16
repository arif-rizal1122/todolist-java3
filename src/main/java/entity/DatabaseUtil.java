package entity;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DatabaseUtil {

    private static HikariDataSource hikariDataSource;
    static {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName("com.mysql.cj.jdbc.Driver");
        hikariConfig.setUsername("root");
        hikariConfig.setPassword("");
        hikariConfig.setJdbcUrl("jdbc:mysql://localhost:3306/java-todolist");

        // pool
        hikariConfig.setMinimumIdle(5);
        hikariConfig.setMaximumPoolSize(10);
        hikariConfig.setIdleTimeout(60_000);
        hikariConfig.setMaxLifetime(60 * 60 * 100);

        hikariDataSource = new HikariDataSource(hikariConfig);
    }

    public static HikariDataSource getDataSource(){
        return hikariDataSource;
    };


}
