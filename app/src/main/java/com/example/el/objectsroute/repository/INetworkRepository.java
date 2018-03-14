package com.example.el.objectsroute.repository;

import com.example.el.objectsroute.dataclass.ObjectVisitation;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by el on 01.03.2018.
 */

public interface INetworkRepository {

    Single<List<ObjectVisitation>> loadObjects();

    Single<String> visitObject(ObjectVisitation object);
}
