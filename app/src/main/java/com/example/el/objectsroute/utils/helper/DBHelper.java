package com.example.el.objectsroute.utils.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by el on 17.03.2018.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DB_NAME = "orDB";

    public static final String OBJECT_VISITATION_TABLE = "OBJECT_VISITATION";

    public static final String ID_COLUMN = "_id";
    public static final String NAME_COLUMN = "name";
    public static final String ADDRESS_COLUMN = "address";
    public static final String LAT_COLUMN = "lat";
    public static final String LNG_COLUMN = "lng";
    public static final String PRIORITY_COLUMN = "priority";
    public static final String WORK_COLUMN = "work";
    public static final String TIME_COLUMN = "time";
    public static final String INSTRUMENTS_COLUMN = "instruments";
    public static final String IS_VISITED_COLUMN = "isVisited";

    private static final String INTEGER_TYPE = "integer";
    private static final String TEXT_TYPE = "text";
    private static final String REAL_TYPE = "real";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + OBJECT_VISITATION_TABLE + "("
                + ID_COLUMN + " " + INTEGER_TYPE + " primary key, "
                + NAME_COLUMN + " " + TEXT_TYPE + ", "
                + ADDRESS_COLUMN + " " + TEXT_TYPE + ", "
                + LAT_COLUMN + " " + REAL_TYPE + ", "
                + LNG_COLUMN + " " + REAL_TYPE + ", "
                + PRIORITY_COLUMN + " " + TEXT_TYPE + ", "
                + WORK_COLUMN + " " + TEXT_TYPE + ", "
                + TIME_COLUMN + " " + INTEGER_TYPE + ", "
                + INSTRUMENTS_COLUMN + " " + TEXT_TYPE + ", "
                + IS_VISITED_COLUMN + " " + INTEGER_TYPE + ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}
