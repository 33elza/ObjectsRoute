package com.example.el.objectsroute.repository.directions;

import com.example.el.objectsroute.dataclass.Routes;

import io.reactivex.Single;

/**
 * Created by el on 11.04.2018.
 */

public interface IDirectionsRepository {
    Single<Routes> getRoute();
}
