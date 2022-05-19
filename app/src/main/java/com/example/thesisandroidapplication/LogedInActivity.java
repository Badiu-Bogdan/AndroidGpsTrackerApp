package com.example.thesisandroidapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.thesisandroidapplication.service.MapService;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;

public class LogedInActivity extends AppCompatActivity implements OnMapReadyCallback{//,View.OnClickListener {

    private Button btnSetupFragment;
    private Button btnAddListener;
    private TextView textViewImei;
    private FrameLayout setupFrameLayout;

    private GoogleMap mMap;
    private Button btnLogOut;
    private GoogleSignInOptions gso;
    private GoogleSignInClient gsc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loged_in);

        //Init buttons
        initButtons();

        //Init Map
        initMap();

        //Init Google Auth
        initGoogleAuth();

        //Init Button Listeners
        setUpButtonOnClickListeners();
    }

    private void initGoogleAuth()
    {
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this, gso);

        //Check if auth was scces. Print mail and name if yes or close activity if not.
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {
            System.out.println(acct.getDisplayName());
            System.out.println(acct.getEmail());
        }
        else{
            finish();
        }
    }

    private void initMap()
    {
        //Init Map into fragment;
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.maps);
        mapFragment.getMapAsync(this);
    }

    private void initButtons()
    {
        btnSetupFragment = findViewById(R.id.btnSetup);
        btnAddListener = findViewById(R.id.btnAdd);
        textViewImei = findViewById(R.id.imeiTextView);
        setupFrameLayout = findViewById(R.id.mainFragmentToSetup);
        btnLogOut = findViewById(R.id.btnLogOut);

    }

    void signOut() {
        gsc.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                finish();
                startActivity(new Intent(LogedInActivity.this, MainActivity.class));
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        // Vin si fac buttonu de setup vizibil doar aici.
        // Si tot aici fac si incarcarea fragmentului.
        // Fac la click pe item in listView sa se stearga clasa asta noua de MapService si sa imi
        // creeze un noua, La stergere mi se va sterge markeru actual si pune unul nou.
        // Si tot asa.
        mMap = googleMap;
        MapService mapService = new MapService(mMap, "123456789");
        mapService.startWorking();
    }

    void setUpButtonOnClickListeners()
    {
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signOut();
            }
        });

        btnSetupFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(setupFrameLayout.getVisibility() == View.GONE)
                {
                    setupFrameLayout.setVisibility(View.VISIBLE);
                }
                else
                {
                    setupFrameLayout.setVisibility(View.GONE);
                }
            }
        });
    }
}


  /*
    *//*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this, gso);
        btnSignIn = findViewById(R.id.btnSignIn);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.maps);
        mapFragment.getMapAsync(this);

        btnSetupFragment = findViewById(R.id.btnSetup);
        btnSetupFragment.setOnClickListener(this);
        setupFragment = new SetupFragment();
    }

    void signIn()
    {
        Intent signInIntent = gsc.getSignInIntent();
        start
    }


    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {

    }

    @Override
    public void onClick(View v)
    {
        fTran = getFragmentManager().beginTransaction();
        if(setupStatus)
        {
            if(getSupportFragmentManager().getBackStackEntryCount() != 0) {
                this.onBackPressed();
            }

        }
        else
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.mainFragmentToSetup, setupFragment).addToBackStack("setupFragment").commit();
        }
        setupStatus = !setupStatus;


*//**//*        if(v.getId() == R.id.btnSetup && !setupStatus)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.mainFragmentToSetup, new SetupFragment(), "setupFragment").addToBackStack(null).commit();
            btnSetupFragment.setText("Back");
            setupStatus = true;
            return;
        }
        if(v.getId() == R.id.btnSetup && setupStatus)
        {
            MainActivity.this.getFragmentManager().popBackStack();
            setupStatus = false;
            btnSetupFragment.setText("Setup");
            return;
        }*//**//*

    }*//*
    }

    @Override
    public void onClick(View view) {

    }
}*/