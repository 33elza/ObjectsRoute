package com.example.el.objectsroute.ui.activity;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.el.objectsroute.R;
import com.example.el.objectsroute.presentation.presenter.MainPresenter;
import com.example.el.objectsroute.presentation.view.MainView;
import com.example.el.objectsroute.ui.adapter.MainViewPagerAdapter;

public class MainActivity extends MvpAppCompatActivity implements MainView {

    @InjectPresenter
    MainPresenter presenter;

    private ViewPager viewPager;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(new MainViewPagerAdapter(getSupportFragmentManager()));
        viewPager.addOnPageChangeListener(presenter.getOnPageChangeListener());

        bottomNavigationView = findViewById(R.id.bottomNavView);
        bottomNavigationView.setOnNavigationItemSelectedListener(presenter.getOnNavigationItemSelectedListener());
        presenter.onCreate();
    }

    @Override
    public void goToMap() {
        if (viewPager.getCurrentItem() != MainViewPagerAdapter.MAP_INDEX) {
            viewPager.setCurrentItem(MainViewPagerAdapter.MAP_INDEX, true);
        }
        bottomNavigationView.getMenu().getItem(MainViewPagerAdapter.MAP_INDEX).setChecked(true);
    }

    @Override
    public void goBack() {
        onBackPressed();
    }

    @Override
    public void goToAuthorization() {

    }

    @Override
    public void goToObjectList() {
        if (viewPager.getCurrentItem() != MainViewPagerAdapter.LIST_INDEX) {
            viewPager.setCurrentItem(MainViewPagerAdapter.LIST_INDEX, true);
        }
        bottomNavigationView.getMenu().getItem(MainViewPagerAdapter.LIST_INDEX).setChecked(true);
    }

    @Override
    public void showMessage(CharSequence message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
