package com.example.el.objectsroute.dataclass;

/**
 * Created by el on 19.02.2018.
 */

public class Error {
    private Throwable throwable;
    public Error(Throwable throwable) {
        this.throwable = throwable;
    }

    public Throwable getThrowable() {
        return throwable;
    }
}
