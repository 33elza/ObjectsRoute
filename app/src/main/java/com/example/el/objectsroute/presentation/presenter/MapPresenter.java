package com.example.el.objectsroute.presentation.presenter;

import android.content.DialogInterface;
import android.os.Bundle;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.el.objectsroute.R;
import com.example.el.objectsroute.dataclass.ObjectVisitation;
import com.example.el.objectsroute.dataclass.Response;
import com.example.el.objectsroute.interactor.GetObjectsInteractor;
import com.example.el.objectsroute.interactor.RequestType;
import com.example.el.objectsroute.interactor.VisitObjectInteractor;
import com.example.el.objectsroute.presentation.view.MapView;

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
    private Disposable visitObjectDisposable;

    private List<ObjectVisitation> objects;

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
        if (visitObjectDisposable != null && !visitObjectDisposable.isDisposed()) {
            visitObjectDisposable.dispose();
            visitObjectDisposable = null;
        }
    }

    private void getObjects() {
        if (objectListDisposable != null && !objectListDisposable.isDisposed()) {
            objectListDisposable.dispose();
        }
        objectListDisposable = new GetObjectsInteractor()
                .getObjectList(RequestType.CASH_ONLY)
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

    public void OnMarkerClicked(ObjectVisitation object) {
        getViewState().showObjectInfo(object);
    }

    public void onVisitTextViewClicked(final ObjectVisitation object) {
        getViewState().showDialog(R.string.visit_dialog_title,
                R.string.dialog_button_yes,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        visitObject(object);
                        dialogInterface.dismiss();
                    }
                },
                R.string.dialog_button_no,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
    }

    private void visitObject(final ObjectVisitation object) {
        // TODO: 12.03.2018 Показать loader

        visitObjectDisposable = new VisitObjectInteractor()
                .visitObject(object)
                .subscribe(new Consumer<Response>() {
                    @Override
                    public void accept(Response response) throws Exception {
                        if (response.hasError()) {
                            // TODO: 19.02.2018 Обработать ошибку
                        } else {
                            object.setVisited(true);
                            getViewState().showObjectInfo(object);
                        }
                    }
                });
    }
}
