package com.example.el.objectsroute.repository;

import com.example.el.objectsroute.dataclass.ObjectVisitation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by el on 11.03.2018.
 */

public class DbRepository implements IDbRepository{
    private static final DbRepository ourInstance = new DbRepository();

    private final ArrayList<ObjectVisitation> objects = new ArrayList<>();

    private DbRepository() {
    }

    public static DbRepository getInstance() { return ourInstance; }


    @Override
    public void saveObjects(List<ObjectVisitation> objects) {
       this.objects.clear();
       this.objects.addAll(objects);
    }

    @Override
    public List<ObjectVisitation> getObjects() {
        return objects;
    }

    @Override
    public void updateObject(ObjectVisitation object) {
        for (ObjectVisitation objectVisitation : objects) {
            if (objectVisitation.equals(object)) {
                break;
            }
        }
    }
}
