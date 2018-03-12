package com.example.el.objectsroute.interactor;

import com.example.el.objectsroute.dataclass.ObjectVisitation;
import com.example.el.objectsroute.dataclass.Response;
import com.example.el.objectsroute.repository.DbRepository;
import com.example.el.objectsroute.repository.IDbRepository;
import com.example.el.objectsroute.repository.INetworkRepository;
import com.example.el.objectsroute.repository.NetworkRepository;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by el on 20.02.2018.
 */

public class GetObjectVisitationListInteractor {

    private INetworkRepository networkRepository = NetworkRepository.getInstance();
    private IDbRepository dbRepository = DbRepository.getInstance();

    public Observable<Response<List<ObjectVisitation>>> getObjectList() {
        return networkRepository.getObjectList();
    }

    public  List<ObjectVisitation> getVisitedObjects() {
        return dbRepository.getVisitedObjectsObjects();
    }
}
