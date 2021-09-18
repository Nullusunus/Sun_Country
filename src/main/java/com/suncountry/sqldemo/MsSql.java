package com.suncountry.sqldemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/*
 *  MsSql class implements methods to initialize, select from, insert into, and transform data for the microsoft sql database.
 */
public class MsSql {
    static Connection connection = null;
    static Statement statement = null;

    public MsSql() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager
                    .getConnection("jdbc:sqlserver://localhost;database=sql_demo;user=sa;password=Pw123456*");
            connection.setAutoCommit(false);
            System.out.println("Opened microsoft sql database successfully");

            statement = connection.createStatement();
        } catch (Exception e) {
                System.err.println( e.getClass().getName()+": "+ e.getMessage() );
                System.exit(0);
        }
    }

    /*
     *  The initialize() method populates the rows of the airplane table for microsoft sql database.
     */
    public static void initialize() {
        String sql = "";

        sql = "INSERT INTO airplane (airplaneid, callsign, aircraft_type, passenger_capacity, max_altitude, max_speed) "
                + "VALUES (00000, 'SCX1111', 'B738', 210, 41000, 530);";
        insert(sql);

        sql = "INSERT INTO airplane (airplaneid, callsign, aircraft_type, passenger_capacity, max_altitude, max_speed) "
                + "VALUES (11111, 'SCX2222', 'B737', 208, 41000, 530);";
        insert(sql);

        sql = "INSERT INTO airplane (airplaneid, callsign, aircraft_type, passenger_capacity, max_altitude, max_speed) "
                + "VALUES (22222, 'SCX3333', 'B736', 207, 41000, 530);";
        insert(sql);

        sql = "INSERT INTO airplane (airplaneid, callsign, aircraft_type, passenger_capacity, max_altitude, max_speed) "
                + "VALUES (33333, 'SCX4444', 'B735', 206, 41000, 530);";
        insert(sql);

        sql = "INSERT INTO airplane (airplaneid, callsign, aircraft_type, passenger_capacity, max_altitude, max_speed) "
                + "VALUES (44444, 'SCX5555', 'B734', 205, 41000, 530);";
        insert(sql);
    }

    /*
     *  The selectAll() method outputs all rows of the airplane table.
     */
    public static void selectAll() {
        try {
            ResultSet rs = statement.executeQuery( "SELECT * FROM airplane;" );
            System.out.println("mssql sql_demo database airplane table");
            System.out.println("airplaneid    callsign    aircraft_type    passenger_capacity    max_altitude    max_speed");
            while ( rs.next() ) {
                int airplaneid = rs.getInt("airplaneid");
                String  callsign = rs.getString("callsign");
                String aircraft_type  = rs.getString("aircraft_type");
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

    /*
     *  The insert() method executes the insert command in the sql parameter.
     */
    public static void insert(String sql) {
        try {
            statement.executeUpdate(sql);
            connection.commit();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    /*
     * The transform() method selects all from the airplane table and returns an ArayList of strings for each row.
     * The retruned list will be inserted into the postgres database.
     */
    public static List<String> transform(int idOffset) {
        List<String> sqlList = new ArrayList<String>();
        try {
            ResultSet rs = statement.executeQuery( "SELECT * FROM airplane;" );
            while ( rs.next() ) {
                int airplaneid = rs.getInt("airplaneid");
                String  callsign = rs.getString("callsign");
                String aircraft_type  = rs.getString("aircraft_type");
                int passenger_capacity = rs.getInt("passenger_capacity");
                int max_altitude = rs.getInt("max_altitude");
                int max_speed = rs.getInt("max_speed");

                String sql = "INSERT INTO airplane (airplaneid, callsign, aircraft_type, passenger_capacity, max_altitude, max_speed) "
                        + "VALUES (" + (airplaneid + idOffset) + ", '" + callsign + "', '" + aircraft_type + "', " + passenger_capacity + ", " + max_altitude + ", " + max_speed + ");";
                sqlList.add(sql);
            }
        } catch (Exception e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        return sqlList;
    }
}
