package com.example.el.objectsroute.dataclass;

import java.util.List;

/**
 * Created by el on 19.02.2018.
 */

public abstract class Response<T> {
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

    public static class ObjectsResponse extends Response<List<ObjectVisitation>> {

        public ObjectsResponse(List<ObjectVisitation> data) {
            super(data);
        }

        public ObjectsResponse(Error error) {
            super(error);
        }
    }

    public static class VisitObjectResponse extends Response<ObjectVisitation> {

        public VisitObjectResponse(Error error) {
            super(error);
        }

        public VisitObjectResponse(ObjectVisitation data) {
            super(data);
        }
    }

    public static class LoginResponse extends Response<Void> {

        public LoginResponse(Error error) {
            super(error);
        }

        public LoginResponse() {
        }
    }

    public static class ObjectListResponse extends Response<List<ObjectVisitation>> {

        public ObjectListResponse(List<ObjectVisitation> data) {
            super(data);
        }

        public ObjectListResponse(Error error) {
            super(error);
        }
    }

    public static class AuthStatusResponse {
        private boolean isAuthorized;

        public AuthStatusResponse(boolean isAuthorized) {
            this.isAuthorized = isAuthorized;
        }

        public boolean isAuthorized() {
            return isAuthorized;
        }
    }
}


