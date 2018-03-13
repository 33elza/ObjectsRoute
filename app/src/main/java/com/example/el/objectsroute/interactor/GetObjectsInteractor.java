package com.example.el.objectsroute.interactor;

import com.example.el.objectsroute.dataclass.Error;
import com.example.el.objectsroute.dataclass.ObjectVisitation;
import com.example.el.objectsroute.dataclass.Response;
import com.example.el.objectsroute.repository.DbRepository;
import com.example.el.objectsroute.repository.IDbRepository;
import com.example.el.objectsroute.repository.INetworkRepository;
import com.example.el.objectsroute.repository.NetworkRepository;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by el on 20.02.2018.
 */

public class GetObjectsInteractor {

    private INetworkRepository networkRepository = NetworkRepository.getInstance();
    private IDbRepository dbRepository = DbRepository.getInstance();

    private Disposable loadObjectsDisposable;

    public Observable<Response<List<ObjectVisitation>>> getObjectList(final int requestType) {
        return Observable.create(new ObservableOnSubscribe<Response<List<ObjectVisitation>>>() {
            @Override
            public void subscribe(final ObservableEmitter<Response<List<ObjectVisitation>>> emitter) throws Exception {
                if (requestType == RequestType.CASH_ONLY || requestType == RequestType.CASH_AND_LOAD) {
                    emitter.onNext(new Response<List<ObjectVisitation>>(dbRepository.getObjects()));
                }
                if (requestType == RequestType.FORCE_LOAD || requestType == RequestType.CASH_AND_LOAD) {
                    loadObjectsDisposable = networkRepository.loadObjects()
                            .doOnSuccess(new Consumer<Response<List<ObjectVisitation>>>() {
                                @Override
                                public void accept(Response<List<ObjectVisitation>> listResponse) throws Exception {
                                    dbRepository.saveObjects(listResponse.getData());
                                }
                            })
                            .subscribe(new Consumer<Response<List<ObjectVisitation>>>() {
                                           @Override
                                           public void accept(Response<List<ObjectVisitation>> listResponse) throws Exception {
                                               emitter.onNext(listResponse);
                                           }
                                       },
                                    new Consumer<Throwable>() {
                                        @Override
                                        public void accept(Throwable throwable) throws Exception {
                                            emitter.onNext(new Response<List<ObjectVisitation>>(new Error(throwable)));
                                        }
                                    });
                }
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnDispose(new Action() {
                    @Override
                    public void run() throws Exception {
                        if (loadObjectsDisposable != null && !loadObjectsDisposable.isDisposed()) {
                            loadObjectsDisposable.dispose();
                            loadObjectsDisposable = null;
                        }
                    }
                });
    }
}