package com.example.el.objectsroute.repository;

import com.example.el.objectsroute.dataclass.ObjectVisitation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by el on 11.03.2018.
 */

public class DbRepository implements IDbRepository{
    private static final DbRepository ourInstance = new DbRepository();

    private final List<ObjectVisitation> visitedObjects = new ArrayList<>();

    private DbRepository() {
    }

    public static DbRepository getInstance() { return ourInstance; }


    @Override
    public void saveObjects(List<ObjectVisitation> objects) {
        for (ObjectVisitation object : objects) {
            if (!visitedObjects.contains(object)) {
                visitedObjects.add(object);
            }
        }

//        for (ObjectVisitation object : objects) {
//            for (ObjectVisitation visitedObject : visitedObjects) {
//                if (object == visitedObject){
//                    object.setVisited(true);
//                }
//            }
//        }
    }

    @Override
    public List<ObjectVisitation> getVisitedObjectsObjects() {
        return visitedObjects;
    }

    @Override
    public void updateObject(ObjectVisitation object) {

    }
}
