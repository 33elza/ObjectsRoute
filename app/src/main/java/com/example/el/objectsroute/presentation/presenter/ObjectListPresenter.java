package com.example.el.objectsroute.presentation.presenter;

import android.content.DialogInterface;
import android.view.View;
import android.widget.ProgressBar;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.el.objectsroute.App;
import com.example.el.objectsroute.R;
import com.example.el.objectsroute.dataclass.ObjectVisitation;
import com.example.el.objectsroute.dataclass.Response;
import com.example.el.objectsroute.interactor.GetObjectsInteractor;
import com.example.el.objectsroute.interactor.RequestType;
import com.example.el.objectsroute.interactor.VisitObjectInteractor;
import com.example.el.objectsroute.presentation.view.ObjectListView;
import com.example.el.objectsroute.ui.adapter.ObjectListAdapter;

import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by el on 15.02.2018.
 */

@InjectViewState
public class ObjectListPresenter extends MvpPresenter<ObjectListView> {

    private Disposable objectListDisposable;
    private Disposable visitObjectDisposable;

    private List<ObjectVisitation> objects;

    private ProgressBar progressBar;

    public void onStart() {
        getObjects(objects == null ? RequestType.FORCE_LOAD : RequestType.CASH_ONLY);
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

    private void getObjects(int requestType) {
        if (objectListDisposable != null && !objectListDisposable.isDisposed()) {
            objectListDisposable.dispose();
        }
        objectListDisposable = new GetObjectsInteractor()
                .getObjectList(requestType)
                .subscribe(new Consumer<Response<List<ObjectVisitation>>>() {
                    @Override
                    public void accept(Response<List<ObjectVisitation>> response) throws Exception {
                        if (response.hasError()) {
                            // TODO: 19.02.2018 Обработать ошибку
                        } else {
                            objects = response.getData();
                            getViewState().setObjects(objects);
                        }
                    }
                });
    }

    public View.OnClickListener getMakeRouteButtonClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                App.getRouter().goToMap();
            }
        };
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
                                progressBar.setVisibility(View.VISIBLE);
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
        visitObjectDisposable = new VisitObjectInteractor()
                .visitObject(object)
                .subscribe(new Consumer<Response>() {
                    @Override
                    public void accept(Response response) throws Exception {
                        if (response.hasError()) {
                            // TODO: 19.02.2018 Обработать ошибку
                        } else {
                            object.setVisited(true);
                            getViewState().reloadObject(index);
                        }
                    }
                });
    }

    public void setProgressBar(ProgressBar progressBar) {
        if (progressBar == null) return;
        this.progressBar = progressBar;
    }
}
