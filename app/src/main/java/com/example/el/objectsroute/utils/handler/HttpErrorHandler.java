package com.example.el.objectsroute.utils.handler;

import android.view.View;

import com.example.el.objectsroute.presentation.view.BaseView;
import com.example.el.objectsroute.dataclass.Error;

import java.net.UnknownHostException;

/**
 * Created by el on 14.03.2018.
 */

public class HttpErrorHandler extends BaseHandler{
    private BaseView view;

    public HttpErrorHandler(BaseView view) {
        this.view = view;
    }

    @Override
    public void handleError(Error error) {
        if (error.getThrowable() instanceof UnknownHostException) {
            //view.showError();
        }
    }

}
