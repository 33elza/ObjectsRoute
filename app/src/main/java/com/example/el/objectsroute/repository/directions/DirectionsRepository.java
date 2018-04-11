package com.example.el.objectsroute.repository.directions;

import com.example.el.objectsroute.dataclass.Routes;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by el on 11.04.2018.
 */

public class DirectionsRepository implements IDirectionsRepository {

    private static final DirectionsRepository ourInstance = new DirectionsRepository();
    private RouteApi service;

    private DirectionsRepository() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        service = new Retrofit
                .Builder()
                .baseUrl("https://maps.googleapis.com/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build()
                .create(RouteApi.class);
    }

    public static DirectionsRepository getInstance() {
        return ourInstance;
    }

    public Single<Routes> getRoute() {
        return service.getRoute("Chicago", "Los+Angeles", true, "AIzaSyATkxNMg0MKlrxubdODdPf3S6fSQwEu-o8");
        //return service.getRoute("Ульяновск, ул. Радищева, 102", "г.Ульяновск, Московское ш., 9", App.getAppContext().getString(R.string.map_key));
    }

    public interface RouteApi {
        @GET("/maps/api/directions/json")
        Single<Routes> getRoute(
                @Query(value = "origin") String origin,
                @Query(value = "destination") String destination,
                @Query(value = "sensor") boolean sensor,
                @Query(value = "key") String key);
    }
}
