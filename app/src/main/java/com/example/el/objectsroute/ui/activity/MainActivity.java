package com.example.el.objectsroute.ui.activity;

import android.os.Bundle;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.el.objectsroute.App;
import com.example.el.objectsroute.R;
import com.example.el.objectsroute.presentation.presenter.MainPresenter;
import com.example.el.objectsroute.presentation.view.MainView;
import com.example.el.objectsroute.router.Router;
import com.example.el.objectsroute.ui.fragment.AuthorizationFragment;
import com.example.el.objectsroute.ui.fragment.MapFragment;
import com.example.el.objectsroute.ui.fragment.ObjectTabsFragment;
import com.example.el.objectsroute.ui.fragment.RegistrationFragment;

public class MainActivity extends MvpAppCompatActivity implements Router, MainView {

    @InjectPresenter
    MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        App.setRouter(this);

        presenter.onCreate();

        //временная
        //App.getRouter().goToObjectList();
        App.getRouter().goToAuthorization();
        // App.getRouter().goToMap();
    }

    @Override
    public void goToMap() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, MapFragment.getInstance())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void goBack() {
        onBackPressed();

    }

    @Override
    public void goToRegister() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, RegistrationFragment.getInstance())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void goToAuthorization() {
        setTitle(R.string.authorization_title);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, AuthorizationFragment.getInstance())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void goToObjectList() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, ObjectTabsFragment.getInstance())
                .addToBackStack(null)
                .commit();

    }


    @Override
    public void showMessage(CharSequence message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
