package com.example.el.objectsroute.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.el.objectsroute.R;
import com.example.el.objectsroute.dataclass.ObjectVisitation;
import com.example.el.objectsroute.presentation.presenter.MapPresenter;
import com.example.el.objectsroute.presentation.view.MapView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
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

    private ObjectInfoViewHolder objectInfoViewHolder;

    private BottomSheetDialog bottomSheetDialog;

    private List<ObjectVisitation> objects;

    private CameraPosition previousCameraPosition;
    private static final String KEY_CAMERA_POSITION = "camera_position";

    public static MapFragment getInstance() {
        return new MapFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.onCreate(getArguments());

        if (savedInstanceState != null) {
            previousCameraPosition = savedInstanceState.getParcelable(KEY_CAMERA_POSITION);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (map != null) {
            outState.putParcelable(KEY_CAMERA_POSITION, map.getCameraPosition());
            super.onSaveInstanceState(outState);
        }
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
                map.getUiSettings().setZoomControlsEnabled(true);

                map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        presenter.OnMarkerClicked((ObjectVisitation) marker.getTag());
                        return false;
                    }
                });

                if (objects == null || objects.isEmpty()) return;
                drawMarkers();
            }
        });

        final View bottomInfoView = getActivity().getLayoutInflater().inflate(R.layout.bottom_sheet_object_info, null);
        objectInfoViewHolder = new ObjectInfoViewHolder(bottomInfoView);

        bottomSheetDialog = new BottomSheetDialog(getActivity());
        bottomSheetDialog.setContentView(bottomInfoView);

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
    public void setObjectInfo(final ObjectVisitation object) {

        if (object == null) return;

        objectInfoViewHolder.nameTextView.setText(object.getName());
        objectInfoViewHolder.addressTextView.setText(object.getAddress());
        objectInfoViewHolder.priorityTextView.setText(object.getPriority());
        objectInfoViewHolder.workTextView.setText(object.getWork());
        objectInfoViewHolder.instrumentsTextView.setText(object.getInstruments());
        objectInfoViewHolder.visitTextView.setEnabled(!object.isVisited());
        objectInfoViewHolder.visitTextView.setText(object.isVisited() ? R.string.is_visited_text : R.string.visit_text);
        objectInfoViewHolder.visitTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onVisitTextViewClicked(object);
            }
        });
    }

    @Override
    public void showObjectInfo() {
        bottomSheetDialog.show();
    }

    private void drawMarkers() {
        if (map == null) return;

        map.clear();

        if (objects == null || objects.isEmpty()) return;

        boundsBuilder = new LatLngBounds.Builder();

        Marker marker;
        for (ObjectVisitation object : objects) {
            marker = map.addMarker(new MarkerOptions()
                    .position(new LatLng(object.getLat(), object.getLng())).title(object.getName()));
            marker.setTag(object);
            boundsBuilder.include(new LatLng(object.getLat(), object.getLng()));
        }

        if (previousCameraPosition != null) {
            map.moveCamera(CameraUpdateFactory.newCameraPosition(previousCameraPosition));
        } else {
            map.animateCamera(CameraUpdateFactory.newLatLngBounds(boundsBuilder.build(),
                    getResources().getDisplayMetrics().widthPixels,
                    getResources().getDisplayMetrics().heightPixels,
                    BOUNDS_PADDING));
        }
    }

    private class ObjectInfoViewHolder {
        private final TextView addressTextView;
        private final TextView nameTextView;
        private final TextView workTextView;
        private final TextView instrumentsTextView;
        private final TextView priorityTextView;
        private final TextView visitTextView;

        private ObjectInfoViewHolder(View rootView) {
            addressTextView = rootView.findViewById(R.id.addressTextView);
            nameTextView = rootView.findViewById(R.id.objectNameTextView);
            workTextView = rootView.findViewById(R.id.workTextView);
            instrumentsTextView = rootView.findViewById(R.id.instrumentsTextView);
            priorityTextView = rootView.findViewById(R.id.priorityTextView);
            visitTextView = rootView.findViewById(R.id.visitTextView);
        }
    }
}
