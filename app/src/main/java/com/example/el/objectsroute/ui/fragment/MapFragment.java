package com.example.el.objectsroute.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

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
import com.google.android.gms.maps.model.Marker;
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

    private BottomSheetBehavior infoBottomSheetBehavior;

    private List<ObjectVisitation> objects;
    private ObjectVisitation object;

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

        final View bottomSheet = rootView.findViewById(R.id.bottom_sheet_info);
        infoBottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        infoBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);

        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;
                drawMarkers();

                final int width = getResources().getDisplayMetrics().widthPixels;
                final int height = getResources().getDisplayMetrics().heightPixels;

                map.moveCamera(CameraUpdateFactory.newLatLngBounds(boundsBuilder.build(), width, height, BOUNDS_PADDING));

                map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {

                        presenter.OnMarkerClicked(object);

                        object = (ObjectVisitation) marker.getTag();
                        setInfoBottomSheetBehavior(object, new ObjectInfoViewHolder(rootView));

                        if (infoBottomSheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                            infoBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                        }
                        return false;
                    }
                });
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

    @Override
    public void showObjectInfo(ObjectVisitation object) {

    }

    private void drawMarkers() {
        map.clear();

        if (objects == null) return;

        boundsBuilder = new LatLngBounds.Builder();
        Marker marker;

        for (ObjectVisitation object : objects) {
            marker = map.addMarker(new MarkerOptions()
                    .position(new LatLng(object.getLat(), object.getLng())).title(object.getName()));
            marker.setTag(object);
            boundsBuilder.include(new LatLng(object.getLat(), object.getLng()));
        }
    }

    private void setInfoBottomSheetBehavior(ObjectVisitation object, ObjectInfoViewHolder holder) {
        if (object == null) return;

        holder.nameTextView.setText(object.getName());
        holder.addressTextView.setText(object.getAddress());
        holder.priorityTextView.setText(object.getPriority());
        holder.workTextView.setText(object.getWork());
        holder.instrumentsTextView.setText(object.getInstruments());
    }

    private class ObjectInfoViewHolder {
        private final TextView addressTextView;
        private final TextView nameTextView;
        private final TextView workTextView;
        private final TextView instrumentsTextView;
        private final TextView priorityTextView;

        private ObjectInfoViewHolder(View rootView) {
            addressTextView = rootView.findViewById(R.id.addressTextView);
            nameTextView = rootView.findViewById(R.id.objectNameTextView);
            workTextView = rootView.findViewById(R.id.workTextView);
            instrumentsTextView = rootView.findViewById(R.id.instrumentsTextView);
            priorityTextView = rootView.findViewById(R.id.priorityTextView);

            rootView.findViewById(R.id.visitTextView).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                }
            });
        }
    }
}
