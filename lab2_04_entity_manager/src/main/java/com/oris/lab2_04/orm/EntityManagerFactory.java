package com.oris.lab2_04.orm;

import javax.sql.DataSource;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.Closeable;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class EntityManagerFactory implements Closeable {

    final static Logger logger = LogManager.getLogger(EntityManagerFactory.class);

    private DataSource dataSource;
    private List<Class<?>> entities;

    public EntityManagerFactory() {
        try {
            Class.forName("org.postgresql.Driver");
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl("jdbc:postgresql://localhost:5432/music");
            config.setUsername("elise");
            config.setPassword("superuser");
            config.setConnectionTimeout(50000);
            config.setMaximumPoolSize(10);
            dataSource = new HikariDataSource(config);
            entities = Context.getInstance().getEntities();
            createTables(dataSource.getConnection());
            logger.info("data source created");
        } catch (ClassNotFoundException  | SQLException e) {
            logger.error("", e);
        }
    }

    public void close() {
        ((HikariDataSource) dataSource).close();
    }

    public EntityManager createEntityManager() throws SQLException {
        return new EntityManagerImpl(dataSource.getConnection());
    }

    private void createTables(Connection connection) throws SQLException {
        List<String> sqlStatements = DDLGenerator.generateAllTables(entities);
        for (String sql : sqlStatements) {
            System.out.println(sql);
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        }
    }

}