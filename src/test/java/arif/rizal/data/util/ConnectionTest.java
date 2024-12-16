package arif.rizal.data.util;

import com.zaxxer.hikari.HikariDataSource;
import entity.DatabaseUtil;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionTest {
    @Test
    void testConnection() throws SQLException {
        HikariDataSource dataSource = DatabaseUtil.getDataSource();
        Connection connection = dataSource.getConnection();

        connection.close();
        dataSource.close();

    }

}
