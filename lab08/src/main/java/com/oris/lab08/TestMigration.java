package com.oris.lab08;


import org.flywaydb.core.Flyway;

public class TestMigration {
    final static String url = "jdbc:postgresql://localhost:5432/agona_db";
    final static String username = "elise";
    final static String password = "superuser";

    public static void main(String[] args) {
        // Create the Flyway instance and point it to the database
        Flyway flyway = Flyway.configure().baselineOnMigrate(true)
                .dataSource(url, username, password).load();

        // Start the migration
        flyway.migrate();

    }
}

