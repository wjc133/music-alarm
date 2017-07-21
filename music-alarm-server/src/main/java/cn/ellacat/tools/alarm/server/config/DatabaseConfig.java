package cn.ellacat.tools.alarm.server.config;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;

/**
 * @author wjc133
 */
@Configuration
public class DatabaseConfig {
    @Bean
    public JdbcConnectionSource getConnection() throws SQLException {
        String dbUrl = "jdbc:sqlite:./music.db";
        JdbcConnectionSource source = new JdbcConnectionSource(dbUrl);
        source.initialize();
        return source;
    }
}
