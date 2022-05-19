package com.example.thesisandroidapplication.service;

import androidx.annotation.NonNull;

import com.example.thesisandroidapplication.model.Gps;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MapService {
    GoogleMap map;
    String imei;
    DatabaseReference databaseReference;
    ValueEventListener activeListener;
    Marker marker;

    public MapService(GoogleMap entityMap, String newImei)
    {
        //myRef = FirebaseDatabase.getInstance().getReference("imei");
        map = entityMap;
        imei = newImei;
        startWorking();
    }

    //Iau o referinta la locatiea din baza de date
    // Si creed un listener pe ea.In momentul in care datele se schimba markerul existent este
    // sters de pe harta si creed unul nou cu noua pozitie.
    // Si tot asa si camera v-a lua focus pe noul marker.
    public void startWorking()
    {
        databaseReference = FirebaseDatabase.getInstance().getReference("imei/"+imei);
        activeListener =  databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // Add a marker in Sydney and move the camera
                Gps gps = snapshot.getValue(Gps.class);
                LatLng point = new LatLng(gps.getLat(), gps.getLng());
                if(marker != null)
                {
                    marker.remove();
                }
                marker = map.addMarker(new MarkerOptions().position(point).title("Marker in Sydney"));
                //map.addMarker(new MarkerOptions().position(point).title("Marker in Sydney"));
                map.moveCamera(CameraUpdateFactory.newLatLng(point));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    // Aici inchid listenerul pe imeiul dat si sterg markerul de pe harta
    public void close()
    {
        databaseReference.removeEventListener(activeListener);
        if(marker != null)
            marker.remove();
    }

}
