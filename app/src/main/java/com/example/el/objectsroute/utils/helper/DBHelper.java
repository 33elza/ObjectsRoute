package com.example.el.objectsroute.utils.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by el on 17.03.2018.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String objectVisitationTable = "OBJECT_VISITATION";

    public DBHelper(Context context) {
        super(context, "orDB", null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + objectVisitationTable + "("
                + "_id integer primary key, "
                + "name text, "
                + "address text, "
                + "lat real, "
                + "lng real, "
                + "priority text, "
                + "work text, "
                + "time integer, "
                + "instruments text, "
                + "isVisited integer" + ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}
