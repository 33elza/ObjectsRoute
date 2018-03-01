package com.example.el.objectsroute.presentation.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.el.objectsroute.dataclass.ObjectVisitation;

import java.util.List;

/**
 * Created by el on 15.02.2018.
 */

@StateStrategyType(SingleStateStrategy.class)
public interface ObjectListView extends MvpView {
    void setObjects(List<ObjectVisitation> objects);
}
