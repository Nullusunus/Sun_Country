package com.suncountry.sqldemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Pg {
    static Connection connection = null;
    static Statement statement = null;

    public Pg() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/pg_testdb", "postgres", "pw");
            connection.setAutoCommit(false);
            System.out.println("Opened postgres database successfully");

            statement = connection.createStatement();
        } catch (Exception e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
    }

    public static void selectAll() {
        try {
            ResultSet rs = statement.executeQuery("SELECT * FROM airplane;");
            System.out.println("postgres pg_testdb database airplane table");
            System.out.println("airplaneid    callsign    aircraft_type    passenger_capacity    max_altitude    max_speed");
            while (rs.next()) {
                int airplaneid = rs.getInt("airplaneid");
                String callsign = rs.getString("callsign");
                String aircraft_type = rs.getString("aircraft_type");
                int passenger_capacity = rs.getInt("passenger_capacity");
                int max_altitude = rs.getInt("max_altitude");
                int max_speed = rs.getInt("max_speed");
                System.out.println(airplaneid + "    " + airplaneid + "    " + callsign + "    " + aircraft_type + "    " + passenger_capacity + "    " + max_altitude + "    " + max_speed);
            }
        } catch (Exception e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
    }

    public static void insert() {
        try {
            String sql = "INSERT INTO airplane (airplaneid, callsign, aircraft_type, passenger_capacity, max_altitude, max_speed) "
                    + "VALUES (10020, 'SCX3006', 'B738', 210, 41000, 530);";
            statement.executeUpdate(sql);

            sql = "INSERT INTO airplane (airplaneid, callsign, aircraft_type, passenger_capacity, max_altitude, max_speed) "
                    + "VALUES (20010, 'SCX1177', 'B711', 211, 41001, 531);";
            statement.executeUpdate(sql);
            connection.commit();
        } catch (Exception e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
    }
}
