package com.example.el.objectsroute.interactor;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.el.objectsroute.dataclass.Error;
import com.example.el.objectsroute.dataclass.Response;
import com.example.el.objectsroute.dataclass.Routes;
import com.example.el.objectsroute.repository.directions.DirectionsRepository;
import com.example.el.objectsroute.repository.directions.IDirectionsRepository;

import org.greenrobot.eventbus.EventBus;

import io.reactivex.functions.Consumer;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by el on 11.04.2018.
 */

public class GetRouteInteractor {
    private IDirectionsRepository directionsRepository = DirectionsRepository.getInstance();

    @SuppressLint("CheckResult")
    public void getRoute() {
        directionsRepository.getRoute().enqueue(new Callback<Routes>() {
            @Override
            public void onResponse(Call<Routes> call, retrofit2.Response<Routes> response) {
                EventBus.getDefault().post(new Response.RouteResponse(response.body()));
            }

            @Override
            public void onFailure(Call<Routes> call, Throwable t) {
                EventBus.getDefault().post(new Response.RouteResponse(new Error(t)));
            }
        });
    }
}
