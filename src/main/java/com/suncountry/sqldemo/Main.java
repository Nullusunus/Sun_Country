package com.suncountry.sqldemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
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
                    + "VALUES (99999, 'SCX3006', 'B738', 210, 41000, 530);";
            statement.executeUpdate(sql);

            sql = "INSERT INTO airplane (airplaneid, callsign, aircraft_type, passenger_capacity, max_altitude, max_speed) "
                    + "VALUES (10100, 'SCX1177', 'B711', 211, 41001, 531);";
            statement.executeUpdate(sql);

            ResultSet rs = statement.executeQuery( "SELECT * FROM airplane;" );
            while ( rs.next() ) {
                int airplaneid = rs.getInt("airplaneid");
                String  callsign = rs.getString("callsign");
                String aircraft_type  = rs.getString("aircraft_type");
                int passenger_capacity = rs.getInt("passenger_capacity");
                int max_altitude = rs.getInt("max_altitude");
                int max_speed = rs.getInt("max_speed");
                System.out.println( "airplaneid = " + airplaneid );
                System.out.println( "callsign = " + callsign );
                System.out.println( "aircraft_type = " + aircraft_type );
                System.out.println( "passenger_capacity = " + passenger_capacity );
                System.out.println( "max_altitude = " + max_altitude );
                System.out.println( "max_speed = " + max_speed );
                System.out.println();
            }
            rs.close();

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
