package com.example.el.objectsroute.utils.handler;
import com.example.el.objectsroute.dataclass.Error;
import com.example.el.objectsroute.router.BaseRouter;

/**
 * Created by el on 14.03.2018.
 */

public abstract class BaseHandler {
    public abstract void handleError(Error error);

    public abstract void handleError(Error error, BaseRouter baseRouter);
}


