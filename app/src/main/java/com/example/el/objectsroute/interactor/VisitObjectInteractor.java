package com.example.el.objectsroute.interactor;

import com.example.el.objectsroute.dataclass.Error;
import com.example.el.objectsroute.dataclass.ObjectVisitation;
import com.example.el.objectsroute.dataclass.Response;
import com.example.el.objectsroute.repository.DbRepository;
import com.example.el.objectsroute.repository.IDbRepository;
import com.example.el.objectsroute.repository.INetworkRepository;
import com.example.el.objectsroute.repository.NetworkRepository;

import org.greenrobot.eventbus.EventBus;

import io.reactivex.functions.Consumer;

/**
 * Created by el on 12.03.2018.
 */

public class VisitObjectInteractor {

    private INetworkRepository networkRepository = NetworkRepository.getInstance();
    private IDbRepository dbRepository = DbRepository.getInstance();

    public void visitObject(final ObjectVisitation object) {

        networkRepository.visitObject(object).doOnSuccess(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                object.setVisited(true);
                dbRepository.updateObject(object);
            }
        }).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                EventBus.getDefault().post(new Response.VisitObjectResponse(object));
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                EventBus.getDefault().post(new Response.VisitObjectResponse(new Error(throwable)));
            }
        });
    }
}
