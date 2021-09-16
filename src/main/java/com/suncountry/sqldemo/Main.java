package com.suncountry.sqldemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Main {
    public static void main(String args[]) {
        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/pg_testdb",
                            "postgres", "pw");
            connection.setAutoCommit(false);
            System.out.println("Opened database successfully");

            statement = connection.createStatement();
            String sql = "INSERT INTO airplane (airplaneid, callsign, aircraft_type, passenger_capacity, max_altitude, max_speed) "
                    + "VALUES (33333, 'SCX3006', 'B738', 210, 41000, 530);";
            statement.executeUpdate(sql);

            sql = "INSERT INTO airplane (airplaneid, callsign, aircraft_type, passenger_capacity, max_altitude, max_speed) "
                    + "VALUES (44444, 'SCX1177', 'B711', 211, 41001, 531);";
            statement.executeUpdate(sql);

            statement.close();
            connection.commit();
            connection.close();
        } catch (Exception e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        System.out.println("Records created successfully");
    }
}
