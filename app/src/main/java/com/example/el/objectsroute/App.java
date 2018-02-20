package com.example.el.objectsroute;

import android.app.Application;
import android.content.Context;

import com.example.el.objectsroute.router.Router;

/**
 * Created by el on 11.02.2018.
 */

public class App extends Application {

    private static App instance;
    private static Router router;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static Router getRouter() {
        return router;
    }

    public static void setRouter(Router router) {
        instance.router = router;
    }

    public static Context getAppContext() {
        return instance.getApplicationContext();
    }
}
