package com.example.el.objectsroute.dataclass;

import java.util.List;

/**
 * Created by el on 11.04.2018.
 */

public class Routes {
    public List<Route> routes;
    public String Status;

    public String getPoints() {
        return this.routes.get(0).overview_polyline.points;
    }

    class Route {
        OverviewPolyline overview_polyline;
    }

    class OverviewPolyline {
        String points;
    }
}
