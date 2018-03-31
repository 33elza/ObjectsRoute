package com.example.el.objectsroute.repository.network;

import com.example.el.objectsroute.App;
import com.example.el.objectsroute.R;
import com.example.el.objectsroute.dataclass.PriorityType;
import com.example.el.objectsroute.dataclass.http.ObjectVisitationResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by el on 31.03.2018.
 */

public class ObjectsMapper {

    public List<com.example.el.objectsroute.dataclass.ObjectVisitation> map(List<ObjectVisitationResponse> objectVisitationResponseList) {
        List<com.example.el.objectsroute.dataclass.ObjectVisitation> objects = new ArrayList<>();

        for (ObjectVisitationResponse objectVisitation : objectVisitationResponseList) {
            objects.add(new com.example.el.objectsroute.dataclass.ObjectVisitation(objectVisitation.getId(),
                    objectVisitation.isVisited(),
                    objectVisitation.getName(),
                    objectVisitation.getAddress(),
                    objectVisitation.getLat(),
                    objectVisitation.getLng(),
                    objectVisitation.getPriority().equals(App.getAppContext().getString(R.string.priority_high)) ? PriorityType.HIGH : PriorityType.LOW,
                    objectVisitation.getWork(),
                    objectVisitation.getTime(),
                    objectVisitation.getInstruments()));
        }

        return objects;
    }
}

