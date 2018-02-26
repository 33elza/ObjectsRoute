package com.example.el.objectsroute.interactor;

import com.example.el.objectsroute.dataclass.ObjectVisitation;
import com.example.el.objectsroute.dataclass.Response;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

/**
 * Created by el on 20.02.2018.
 */

public class GetObjectVisitationListInteractor {

    public Observable<Response<List<ObjectVisitation>>> getObjectList(List<ObjectVisitation> objectList) {

        final List<ObjectVisitation> visitationList = new ArrayList<>();

        return Observable.just(new Response<List<ObjectVisitation>>(visitationList));
    }
}
