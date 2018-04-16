package com.example.el.objectsroute.repository.directions;

import com.example.el.objectsroute.dataclass.Routes;

import io.reactivex.Single;
import retrofit2.Call;

/**
 * Created by el on 11.04.2018.
 */

public interface IDirectionsRepository {
    Call<Routes> getRoute();
}
