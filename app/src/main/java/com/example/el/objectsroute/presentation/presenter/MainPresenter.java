package com.example.el.objectsroute.presentation.presenter;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.el.objectsroute.App;
import com.example.el.objectsroute.R;
import com.example.el.objectsroute.dataclass.Response;
import com.example.el.objectsroute.interactor.GetAuthStatusInteractor;
import com.example.el.objectsroute.presentation.view.MainView;
import com.example.el.objectsroute.router.Router;
import com.example.el.objectsroute.ui.adapter.MainViewPagerAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by el on 15.02.2018.
 */

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> implements Router {

    public void onCreate() {
        App.setRouter(this);

        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        new GetAuthStatusInteractor().getAuthStatus();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetAuthEvent(Response.AuthStatusResponse response) {
        if (response.isAuthorized()) {
            getViewState().setupInitialState();
        } else {
            App.getRouter().goToAuthorization();
        }
    }

    @Override
    public void goToMap() {
        getViewState().goToMap();
    }

    @Override
    public void goBack() {
        getViewState().goBack();
    }

    @Override
    public void goToAuthorization() {
        getViewState().goToAuthorization();
    }

    @Override
    public void goToObjectList() {
        getViewState().goToObjectList();
    }

    public BottomNavigationView.OnNavigationItemSelectedListener getOnNavigationItemSelectedListener() {
        return new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.mapNavigationItem:
                        App.getRouter().goToMap();
                        break;
                    default:
                        App.getRouter().goToObjectList();
                        break;
                }
                return true;
            }
        };
    }

    public ViewPager.OnPageChangeListener getOnPageChangeListener() {
        return new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case MainViewPagerAdapter.MAP_INDEX:
                        App.getRouter().goToMap();
                        break;
                    default:
                        App.getRouter().goToObjectList();
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        };
    }
}
