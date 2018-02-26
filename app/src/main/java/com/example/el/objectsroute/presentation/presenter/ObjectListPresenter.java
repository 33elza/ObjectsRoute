package com.example.el.objectsroute.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.el.objectsroute.dataclass.ObjectVisitation;
import com.example.el.objectsroute.dataclass.Response;
import com.example.el.objectsroute.interactor.GetObjectVisitationListInteractor;
import com.example.el.objectsroute.presentation.view.ObjectListView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by el on 15.02.2018.
 */

@InjectViewState
public class ObjectListPresenter extends MvpPresenter<ObjectListView> {

}
