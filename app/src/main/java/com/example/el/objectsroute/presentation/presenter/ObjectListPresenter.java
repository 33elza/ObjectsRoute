package com.example.el.objectsroute.presentation.presenter;

import android.content.DialogInterface;
import android.os.Bundle;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.el.objectsroute.R;
import com.example.el.objectsroute.dataclass.ObjectVisitation;
import com.example.el.objectsroute.dataclass.Response;
import com.example.el.objectsroute.interactor.VisitObjectInteractor;
import com.example.el.objectsroute.presentation.view.ObjectListView;
import com.example.el.objectsroute.ui.adapter.ObjectListAdapter;
import com.example.el.objectsroute.utils.handler.HttpErrorHandler;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

/**
 * Created by el on 15.02.2018.
 */

@InjectViewState
public class ObjectListPresenter extends MvpPresenter<ObjectListView> {

    private List<ObjectVisitation> objects;
    private int selectedObjectIndex;


    public void onCreate(Bundle arguments) {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        if (objects == null) {
            getViewState().showProgressBar();
        } else {
            getViewState().hideProgressBar();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onObjectsEvent(Response.ObjectListResponse response) {
        getViewState().hideProgressBar();
        if (response.hasError()) {
            // TODO: 19.02.2018 Обработать ошибку
        } else {
            objects = response.getData();
            getViewState().setObjects(objects);
            getViewState().hideProgressBar();
        }
    }

    public ObjectListAdapter.Listener getObjectAdapterListener() {
        return new ObjectListAdapter.Listener() {
            @Override
            public void onVisitObjectClick(final ObjectVisitation object, final int index) {

                getViewState().showDialog(R.string.visit_dialog_title,
                        R.string.dialog_button_yes,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                visitObject(object, index);
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
        };
    }

    private void visitObject(final ObjectVisitation object, final int index) {
        getViewState().showProgressBar();
        selectedObjectIndex = index;
        new VisitObjectInteractor().visitObject(object);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onObjectVisitedEvent(Response.VisitObjectResponse response) {
        getViewState().hideProgressBar();
        if (response.hasError()) {
            new HttpErrorHandler(getViewState()).handleError(response.getError());
        } else {
            response.getData().setVisited(true);
            getViewState().reloadObject(selectedObjectIndex);
        }
    }
}
