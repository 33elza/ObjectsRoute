package com.example.el.objectsroute.presentation.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.el.objectsroute.dataclass.ObjectVisitation;
import com.example.el.objectsroute.router.ObjectListRouter;

import java.util.List;

/**
 * Created by el on 15.02.2018.
 */

@StateStrategyType(SingleStateStrategy.class)
public interface ObjectListView extends MvpView, BaseView, ObjectListRouter {
    void setObjects(List<ObjectVisitation> objects);

    void reloadObject(int index);
}
