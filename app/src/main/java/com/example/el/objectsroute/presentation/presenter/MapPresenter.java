package com.example.el.objectsroute.presentation.presenter;

import android.content.DialogInterface;
import android.os.Bundle;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.el.objectsroute.R;
import com.example.el.objectsroute.dataclass.ObjectVisitation;
import com.example.el.objectsroute.dataclass.Response;
import com.example.el.objectsroute.interactor.GetObjectsInteractor;
import com.example.el.objectsroute.interactor.GetRouteInteractor;
import com.example.el.objectsroute.interactor.RequestType;
import com.example.el.objectsroute.interactor.VisitObjectInteractor;
import com.example.el.objectsroute.presentation.view.MapView;
import com.example.el.objectsroute.router.MapRouter;
import com.example.el.objectsroute.utils.handler.HttpErrorHandler;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

/**
 * Created by el on 15.02.2018.
 */

@InjectViewState
public class MapPresenter extends MvpPresenter<MapView> {

    private List<ObjectVisitation> objects;
    private String points;

    public void onCreate(Bundle arguments) {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        if (objects == null) {
            getViewState().showProgressBar();
            getObjects();
        }
        getRoute();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void getRoute() {
        new GetRouteInteractor().getRoute();
    }

    private void getObjects() {
        new GetObjectsInteractor().getObjectList(RequestType.FORCE_LOAD);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onObjectsEvent(Response.ObjectsResponse response) {
        getViewState().hideProgressBar();
        if (response.hasError()) {
            new HttpErrorHandler(getViewState()).handleError(response.getError());
        } else {
            objects = response.getData();
            getViewState().setObjectMarkers(objects);

            // TODO: 14.03.2018 Отправлять отсортированный список 
            EventBus.getDefault().post(new Response.ObjectListResponse(response.getData()));
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRouteEvent(Response.RouteResponse response) {
        if (response.hasError()) {
            new HttpErrorHandler(getViewState()).handleError(response.getError());
        } else {
            points = response.getData().getPoints();
        }
    }

    public void OnMarkerClicked(ObjectVisitation object) {
        getViewState().setObjectInfo(object);
        getViewState().showObjectInfo();
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
        getViewState().showProgressBar();
        new VisitObjectInteractor().visitObject(object);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onObjectVisitedEvent(Response.VisitObjectResponse response) {
        getViewState().hideProgressBar();
        if (response.hasError()) {
            new HttpErrorHandler(getViewState()).handleError(response.getError());
        } else {
            response.getData().setVisited(true);
            getViewState().setObjectInfo(response.getData());
            getViewState().redrawMarkers();
        }
    }

    public MapRouter getRouter() {
        return getViewState();
    }
}
