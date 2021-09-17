package com.suncountry.sqldemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    public static void main(String args[]) {
        Pg pg = new Pg();
        MsSql msSql = new MsSql();

        pg.insert();
        pg.selectAll();

        msSql.insert();
        msSql.selectAll();
    }
}
