package com.suncountry.sqldemo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main {
    public static void main(String args[]) {
        Pg pg = new Pg();
        MsSql msSql = new MsSql();
        msSql.initialize();
        String sql = "";

        // Selecting and transforming tables from microsoft sql database and inserting them into postgres database.
        List<String> sqlList = new ArrayList<String>();
        int randNum = 0;
        randNum = (int) (Math.random() * (1000000 - 100) + 100);
        sqlList = msSql.transform(randNum);
        Iterator<String> itrSql = sqlList.iterator();
        while (itrSql.hasNext()){
            sql = itrSql.next();
            pg.insert(sql);
        }
        System.out.println();
        System.out.println("Output from transformed data in the postgres database table.");
        System.out.println();
        pg.selectAll();
    }
}
