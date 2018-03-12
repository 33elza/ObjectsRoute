package com.example.el.objectsroute.repository;

import com.example.el.objectsroute.dataclass.ObjectVisitation;
import com.example.el.objectsroute.dataclass.Response;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by el on 01.03.2018.
 */

public interface INetworkRepository {

    Single<Response<List<ObjectVisitation>>> loadObjects();

    Single<Response> visitObject(ObjectVisitation object);
}
