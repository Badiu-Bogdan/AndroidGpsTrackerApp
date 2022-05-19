package com.example.thesisandroidapplication.repository;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AccountDataBase {

    private DatabaseReference mDatabase;
    private String imeiList;

    public AccountDataBase()
    {
        mDatabase = FirebaseDatabase.getInstance().getReference("account");
    }

    public void readElement()
    {
        ArrayList<String> imei =  new ArrayList<String>();
        mDatabase.child("Bogdan Badiu").child("imei").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                    String result =  String.valueOf(task.getResult().getValue());
                    Log.d("firebase", result);
                    imeiList = result;

                }
            }
        });
        System.out.println(imeiList);
    }



}
