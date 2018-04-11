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

/**
 * Created by el on 11.04.2018.
 */

public class GetRouteInteractor {
    private IDirectionsRepository directionsRepository = DirectionsRepository.getInstance();

    @SuppressLint("CheckResult")
    public void getRoute() {
        Log.d("ROUTE ", "Interactor");

        directionsRepository.getRoute().doOnSuccess(new Consumer<Routes>() {
            @Override
            public void accept(Routes routeResponse) throws Exception {
                Log.d("ROUTE ", "Accept");
            }
        })
                .subscribe(new Consumer<Routes>() {
                    @Override
                    public void accept(Routes routeResponse) throws Exception {
                        EventBus.getDefault().post(new Response.RouteResponse(routeResponse));
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        EventBus.getDefault().post(new Response.RouteResponse(new Error(throwable)));
                    }
                });
    }
}
