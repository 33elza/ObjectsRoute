package com.example.el.objectsroute.presentation.presenter;

import android.os.Bundle;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.el.objectsroute.dataclass.ObjectVisitation;
import com.example.el.objectsroute.dataclass.Response;
import com.example.el.objectsroute.interactor.GetObjectVisitationListInteractor;
import com.example.el.objectsroute.presentation.view.MapView;
import com.example.el.objectsroute.repository.DbRepository;
import com.example.el.objectsroute.repository.IDbRepository;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by el on 15.02.2018.
 */

@InjectViewState
public class MapPresenter extends MvpPresenter<MapView> {

    private Disposable objectListDisposable;

    private List<ObjectVisitation> objects;

    private List<ObjectVisitation> visitedObjects;


    public void onCreate(Bundle arguments) {
        if (objects == null) getObjects();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (objectListDisposable != null && !objectListDisposable.isDisposed()) {
            objectListDisposable.dispose();
            objectListDisposable = null;
        }
    }

    private void getObjects() {
        if (objectListDisposable != null && !objectListDisposable.isDisposed()) {
            objectListDisposable.dispose();
        }
        objectListDisposable = new GetObjectVisitationListInteractor()
                .getObjectList()
                .subscribe(new Consumer<Response<List<ObjectVisitation>>>() {
                    @Override
                    public void accept(Response<List<ObjectVisitation>> response) throws Exception {
                        if (response.hasError()) {
                            // TODO: 19.02.2018 Обработать ошибку
                        } else {
                            objects = response.getData();
                            getViewState().setObjectMarkers(objects);
                        }
                    }
                });
    }

    public void getVisitedObjects() {
        visitedObjects = new GetObjectVisitationListInteractor().getVisitedObjects();
    }

    public void OnMarkerClicked (ObjectVisitation object) {
        getViewState().showObjectInfo(object);
    }

    public void OnVisitTextViewClicked(ObjectVisitation object) {
        List<ObjectVisitation> visitedObjects = new ArrayList<>();
        visitedObjects.add(object);
        getViewState().saveObjects(visitedObjects);
    }
}
