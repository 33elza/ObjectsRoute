package com.example.el.objectsroute.repository;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.el.objectsroute.App;
import com.example.el.objectsroute.dataclass.ObjectVisitation;
import com.example.el.objectsroute.utils.helper.DBHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by el on 11.03.2018.
 */

public class DbRepository implements IDbRepository {
    private static final DbRepository ourInstance = new DbRepository();

    private SQLiteDatabase db;
    private DBHelper dbHelper;

    private static final String ID_COLUMN = "_id";
    private static final String NAME_COLUMN = "name";
    private static final String ADDRESS_COLUMN = "address";
    private static final String LAT_COLUMN = "lat";
    private static final String LNG_COLUMN = "lng";
    private static final String PRIORITY_COLUMN = "priority";
    private static final String WORK_COLUMN = "work";
    private static final String TIME_COLUMN = "time";
    private static final String INSTRUMENTS_COLUMN = "instruments";
    private static final String IS_VISITED_COLUMN = "isVisited";

    private DbRepository() {
        dbHelper = new DBHelper(App.getAppContext());
        db = dbHelper.getWritableDatabase();
    }

    public static DbRepository getInstance() {
        return ourInstance;
    }

    @Override
    public void saveObjects(List<ObjectVisitation> objects) {
        db.delete(dbHelper.OBJECT_VISITATION_TABLE, null, null);

        ContentValues cv;
        for (ObjectVisitation object : objects) {
            cv = new ContentValues();

            cv.put(NAME_COLUMN, object.getName());
            cv.put(ADDRESS_COLUMN, object.getAddress());
            cv.put(LAT_COLUMN, object.getLat());
            cv.put(LNG_COLUMN, object.getLng());
            cv.put(PRIORITY_COLUMN, object.getPriority());
            cv.put(WORK_COLUMN, object.getWork());
            cv.put(TIME_COLUMN, object.getTime());
            cv.put(INSTRUMENTS_COLUMN, object.getInstruments());
            cv.put(IS_VISITED_COLUMN, (object.isVisited() ? 1 : 0));

            db.insert(dbHelper.OBJECT_VISITATION_TABLE, null, cv);
        }
    }

    @Override
    public List<ObjectVisitation> getObjects() {
        ArrayList<ObjectVisitation> objects = new ArrayList<>();
        Cursor cursor = db.query(dbHelper.OBJECT_VISITATION_TABLE, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            int idColIndex = cursor.getColumnIndex(ID_COLUMN);
            int nameColIndex = cursor.getColumnIndex(NAME_COLUMN);
            int addressColIndex = cursor.getColumnIndex(ADDRESS_COLUMN);
            int latColIndex = cursor.getColumnIndex(LAT_COLUMN);
            int lngColIndex = cursor.getColumnIndex(LNG_COLUMN);
            int priorityColIndex = cursor.getColumnIndex(PRIORITY_COLUMN);
            int workColIndex = cursor.getColumnIndex(WORK_COLUMN);
            int timeColIndex = cursor.getColumnIndex(TIME_COLUMN);
            int instrumentsColIndex = cursor.getColumnIndex(INSTRUMENTS_COLUMN);
            int isVisitedColIndex = cursor.getColumnIndex(IS_VISITED_COLUMN);

            do {
                objects.add(new ObjectVisitation(cursor.getInt(idColIndex),
                        (cursor.getInt(isVisitedColIndex) == 1),
                        cursor.getString(nameColIndex),
                        cursor.getString(addressColIndex),
                        cursor.getFloat(latColIndex),
                        cursor.getFloat(lngColIndex),
                        cursor.getString(priorityColIndex),
                        cursor.getString(workColIndex),
                        cursor.getInt(timeColIndex),
                        cursor.getString(instrumentsColIndex)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return objects;
    }

    @Override
    public void updateObject(ObjectVisitation object) {
        final ContentValues cv = new ContentValues();
        cv.put(IS_VISITED_COLUMN, 1);
        db.update(dbHelper.OBJECT_VISITATION_TABLE, cv, ID_COLUMN + " = ?", new String[]{Long.toString(object.getId())});
    }
}
