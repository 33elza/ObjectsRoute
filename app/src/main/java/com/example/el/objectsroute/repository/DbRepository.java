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
    private static final String objectVisitationTable = "OBJECT_VISITATION";

    private DbRepository() {
        DBHelper dbHelper = new DBHelper(App.getAppContext());
        db = dbHelper.getWritableDatabase();
    }

    public static DbRepository getInstance() {
        return ourInstance;
    }

    @Override
    public void saveObjects(List<ObjectVisitation> objects) {
        ContentValues cv;
        for (ObjectVisitation object : objects) {
            cv = new ContentValues();

            cv.put("name", object.getName());
            cv.put("address", object.getAddress());
            cv.put("lat", object.getLat());
            cv.put("lng", object.getLng());
            cv.put("priority", object.getPriority());
            cv.put("work", object.getWork());
            cv.put("time", object.getTime());
            cv.put("instruments", object.getInstruments());
            cv.put("isVisited", (object.isVisited() ? 1 : 0));

            db.insert(objectVisitationTable, null, cv);
        }
    }

    @Override
    public List<ObjectVisitation> getObjects() {
        ArrayList<ObjectVisitation> objects = new ArrayList<>();
        Cursor cursor = db.query(objectVisitationTable, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            int idColIndex = cursor.getColumnIndex("_id");
            int nameColIndex = cursor.getColumnIndex("name");
            int addressColIndex = cursor.getColumnIndex("address");
            int latColIndex = cursor.getColumnIndex("lat");
            int lngColIndex = cursor.getColumnIndex("lng");
            int priorityColIndex = cursor.getColumnIndex("priority");
            int workColIndex = cursor.getColumnIndex("work");
            int timeColIndex = cursor.getColumnIndex("time");
            int instrumentsColIndex = cursor.getColumnIndex("instruments");
            int isVisitedColIndex = cursor.getColumnIndex("isVisited");

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
        ContentValues cv = new ContentValues();
        cv.put("isVisited", 1);
        db.update(objectVisitationTable, cv, "_id = ?", new String[]{Long.toString(object.getId())});
    }
}
