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
 * Created by el on 12.03.2018.
 */

public class VisitObjectInteractor {

    private INetworkRepository networkRepository = NetworkRepository.getInstance();
    private IDbRepository dbRepository = DbRepository.getInstance();

    private Disposable visitObjectDisposable;

    public Observable<Response> visitObject(final ObjectVisitation object) {
        return Observable.create(new ObservableOnSubscribe<Response>() {
            @Override
            public void subscribe(final ObservableEmitter<Response> emitter) throws Exception {
                visitObjectDisposable = networkRepository.visitObject(object)
                        .doOnSuccess(new Consumer<Response>() {
                            @Override
                            public void accept(Response response) throws Exception {
                                dbRepository.updateObject(object);
                            }
                        })
                        .subscribe(new Consumer<Response>() {
                            @Override
                            public void accept(Response response) throws Exception {
                                emitter.onNext(new Response<>(object));
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                emitter.onNext(new Response(new Error(throwable)));
                            }
                        });
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnDispose(new Action() {
                    @Override
                    public void run() throws Exception {
                        if (visitObjectDisposable != null && !visitObjectDisposable.isDisposed()) {
                            visitObjectDisposable.dispose();
                            visitObjectDisposable = null;
                        }
                    }
                });
    }
}
