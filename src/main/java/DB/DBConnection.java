package DB;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;


    static{
        config.setJdbcUrl("jdbc:sqlserver://192.168.21.128:1433;databaseName=Corona;");
        config.setUsername("filipenko");
        config.setPassword("pass123");
        config.addDataSourceProperty( "cachePrepStmts" , "true" );
        config.addDataSourceProperty( "prepStmtCacheSize" , "250" );
        config.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" );
        ds = new HikariDataSource( config );
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
