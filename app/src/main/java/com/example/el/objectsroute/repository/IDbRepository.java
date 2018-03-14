package com.example.el.objectsroute.repository;

import com.example.el.objectsroute.dataclass.ObjectVisitation;

import java.util.List;

/**
 * Created by el on 11.03.2018.
 */

public interface IDbRepository {
    void saveObjects(List<ObjectVisitation> objects);

    List<ObjectVisitation> getObjects();

    void updateObject(ObjectVisitation object);

}
