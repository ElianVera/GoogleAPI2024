package com.example.googleapi;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements GoogleMap.InfoWindowAdapter, GoogleMap.OnMarkerClickListener, OnMapReadyCallback {

    private GoogleMap map;
    private List<Marker> markers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        markers = new ArrayList<>();

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        map.getUiSettings().setZoomControlsEnabled(true);
        map.setOnMarkerClickListener(this);
        map.setInfoWindowAdapter(this);

        // Agrega tus lugares aquí
        LatLng lugar1 = new LatLng(-1.0128027500791659, -79.47109610842227);
        LatLng lugar2 = new LatLng(-1.012581481988231, -79.470586512314);
        LatLng lugar3 = new LatLng(-1.0131882456694135, -79.47037748044593);
        LatLng lugar4 = new LatLng(-1.0127142931324833, -79.46766761165662);
        LatLng lugar5 = new LatLng(-1.0129609756810887, -79.4694411855239);
        // Agrega marcadores personalizados para cada lugar
        Marker marker1 = map.addMarker(new MarkerOptions().position(lugar1).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
        Marker marker2 = map.addMarker(new MarkerOptions().position(lugar2).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
        Marker marker3 = map.addMarker(new MarkerOptions().position(lugar3).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        Marker marker4 = map.addMarker(new MarkerOptions().position(lugar4).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        Marker marker5 = map.addMarker(new MarkerOptions().position(lugar4).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));




        markers.add(marker1);
        markers.add(marker2);
        markers.add(marker3);
        markers.add(marker4);
        markers.add(marker5);

        // Centra el mapa en el primer lugar
        CameraPosition camPos = new CameraPosition.Builder()
                .target(lugar1)
                .zoom(15)
                .build();

        CameraUpdate camUpd3 = CameraUpdateFactory.newCameraPosition(camPos);
        map.animateCamera(camUpd3);
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;  // Utilizamos getInfoContents para personalizar la ventana de información
    }

    @Override
    public View getInfoContents(Marker marker) {
        View v = LayoutInflater.from(MainActivity.this).inflate(R.layout.ventana_informacion, null);

        TextView placeName = v.findViewById(R.id.placeName);
        TextView location = v.findViewById(R.id.location);
        ImageView logo = v.findViewById(R.id.logo);
        TextView hours = v.findViewById(R.id.hours);

        // Personaliza los valores según el marcador seleccionado
        if (markers.indexOf(marker) == 0) {
            placeName.setText("Facultad de Ciencias de la Educación");
            location.setText("Formar profesionales y académicos competitivos y de excelencia, generando conocimientos, tecnología, servicios de calidad y soluciones a los problemas de la sociedad, sustentada en principios.");
            logo.setImageResource(R.drawable.edu);
        } else if (markers.indexOf(marker) == 1) {
            placeName.setText("Facultad de Ciencias de la Ingeniería");
            location.setText("La Facultad de Ciencias de la Ingeniería de la UTEQ es una unidad académica multidisciplinaria con un alto grado de aceptación y reconocimiento nacional e internacional, por su calidad educativa, servicios e interacción con la sociedad, comprometida con los objetivos estratégicos institucionales.");
            logo.setImageResource(R.drawable.ingenie);
        }
         else if (markers.indexOf(marker) == 2) {
            placeName.setText("Facultad de Ciencias Empresariales");
            location.setText("La Facultad de Ciencias Empresariales de la UTEQ será líder en el campo académico, de investigación, extensión y gestión de los niveles Técnicos, Tecnológicos, Pregrado y Postgrado, que permite incorporar Recursos Humanos al medio productivo científico, técnico y social de manera eficaz y de acuerdo a las exigencias de una sociedad competitiva.");
            logo.setImageResource(R.drawable.empresarial);
        }
         else if (markers.indexOf(marker) == 3) {
            placeName.setText("Auditorio Universitario");
            location.setText("Auditorio de la Universidad ");
            logo.setImageResource(R.drawable.auditorio);
        }
        else if (markers.indexOf(marker) == 4) {
            placeName.setText("Facultad de Enfermería");
            location.setText("La Facultad de Ciencias de la Salud de la UTEQ se constituye en un referente de sostenibilidad académica, reconocida regionalmente por formar profesionales altamente cualificados, comprometidos en brindar atención sanitaria de calidad que propenda a la construcción de comunidades y entornos saludables.");
            logo.setImageResource(R.drawable.enfer);
        }

        return v;
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        marker.showInfoWindow();
        return true;
    }
}
