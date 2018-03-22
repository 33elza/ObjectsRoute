package com.example.el.objectsroute.presentation.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.el.objectsroute.router.MainRouter;

/**
 * Created by el on 15.02.2018.
 */

@StateStrategyType(SingleStateStrategy.class)
public interface MainView extends MvpView, MainRouter {
    void showMessage(CharSequence message);

    void setupInitialState();
}
