package com.example.el.objectsroute.utils.handler;

import com.example.el.objectsroute.R;
import com.example.el.objectsroute.dataclass.Error;
import com.example.el.objectsroute.presentation.view.BaseView;
import com.example.el.objectsroute.repository.NetworkRepository;
import com.example.el.objectsroute.router.BaseRouter;

import java.net.UnknownHostException;

/**
 * Created by el on 14.03.2018.
 */

public class HttpErrorHandler extends BaseHandler {
    private BaseView view;

    public HttpErrorHandler(BaseView view) {
        this.view = view;
    }

    @Override
    public void handleError(Error error) {
        if (error.getThrowable() instanceof UnknownHostException) {
            view.showError(R.string.no_internet_error);
        } else {
            view.showError(R.string.unknown_error);
        }
    }

    @Override
    public void handleError(Error error, BaseRouter baseRouter) {
        if (error.getThrowable() instanceof UnknownHostException) {
            view.showError(R.string.no_internet_error);
        } else if (error.getThrowable() instanceof NetworkRepository.GoToAuthException && baseRouter != null) {
            baseRouter.goToAuthorization();
        } else {
            view.showError(R.string.unknown_error);
        }
    }
}
