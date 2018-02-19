package com.example.el.objectsroute.dataclass;

/**
 * Created by el on 19.02.2018.
 */

public class Response<T> {
    private T data;
    private Error error;

    public Response(T data) {
        this.data = data;
    }

    public Response(Error error) {
        this.error = error;
    }

    public Response() {
    }

    public boolean hasError() {
        return error != null;
    }

    public T getData() {
        return data;
    }

    public Error getError() {
        return error;
    }
}
