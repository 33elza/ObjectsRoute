package com.example.el.objectsroute.interactor;

import android.annotation.SuppressLint;

import com.example.el.objectsroute.dataclass.Error;
import com.example.el.objectsroute.dataclass.ObjectVisitation;
import com.example.el.objectsroute.dataclass.Response;
import com.example.el.objectsroute.repository.db.DbRepository;
import com.example.el.objectsroute.repository.db.IDbRepository;
import com.example.el.objectsroute.repository.network.INetworkRepository;
import com.example.el.objectsroute.repository.network.NetworkRepository;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by el on 20.02.2018.
 */

public class GetObjectsInteractor {

    private INetworkRepository networkRepository = NetworkRepository.getInstance();
    private IDbRepository dbRepository = DbRepository.getInstance();

    @SuppressLint("CheckResult")
    public void getObjectList(final int requestType) {
        if (requestType == RequestType.CASH_ONLY || requestType == RequestType.CASH_AND_LOAD) {
            EventBus.getDefault().post(new Response.ObjectsResponse(dbRepository.getObjects()));
        }
        if (requestType == RequestType.FORCE_LOAD || requestType == RequestType.CASH_AND_LOAD) {
            networkRepository.loadObjects().doOnSuccess(new Consumer<List<ObjectVisitation>>() {
                @Override
                public void accept(List<ObjectVisitation> objectVisitations) throws Exception {
                    dbRepository.saveObjects(objectVisitations);
                }
            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<List<ObjectVisitation>>() {
                        @Override
                        public void accept(List<ObjectVisitation> objects) throws Exception {
                            EventBus.getDefault().post(new Response.ObjectsResponse(dbRepository.getObjects()));
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            EventBus.getDefault().post(new Response.ObjectsResponse(new Error(throwable)));
                        }
                    });
        }
    }
}