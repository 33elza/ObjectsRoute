package com.example.el.objectsroute;

import android.app.Application;
import android.content.Context;

/**
 * Created by el on 11.02.2018.
 */

public class App extends Application {

    private static App instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static Context getAppContext() {
        return instance.getApplicationContext();
    }
}
