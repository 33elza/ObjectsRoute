package com.example.el.objectsroute.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.el.objectsroute.R;
import com.example.el.objectsroute.dataclass.ObjectVisitation;
import com.example.el.objectsroute.presentation.presenter.MapPresenter;
import com.example.el.objectsroute.presentation.view.MapView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;


/**
 * Created by el on 12.02.2018.
 */

public class MapFragment extends BaseFragment implements MapView {

    @InjectPresenter
    MapPresenter presenter;

    private GoogleMap map;
    private com.google.android.gms.maps.MapView mapView;
    private LatLngBounds.Builder boundsBuilder;
    private final int BOUNDS_PADDING = 50;

    private List<ObjectVisitation> objects;

    public static MapFragment getInstance() {
        return new MapFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.onCreate(getArguments());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_map, null);

        mapView = rootView.findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);

        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;

                drawMarkers();
                map.moveCamera(CameraUpdateFactory.newLatLngBounds(boundsBuilder.build(), BOUNDS_PADDING));
            }
        });
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void setObjectMarkers(List<ObjectVisitation> objects) {
        this.objects = objects;
        if (map != null) drawMarkers();
    }

    private void drawMarkers() {
        map.clear();

        if (objects == null) return;

        boundsBuilder = new LatLngBounds.Builder();

        for (ObjectVisitation object : objects) {
            map.addMarker(new MarkerOptions()
                    .position(new LatLng(object.getLat(), object.getLng())).title(object.getName()));
            boundsBuilder.include(new LatLng(object.getLat(), object.getLng()));
        }
    }
}
