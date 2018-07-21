package com.example.ayush.traffgo;

import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.test.mock.MockPackageManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class live_track extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference myRef;
    Button btnShowLocation;
    TextView longitude_show;
    TextView latitude_show;
    private static  final int REQUEST_CODE_PERMISSION=2;
    String mPermission = android.Manifest.permission.ACCESS_FINE_LOCATION;
    TextView mess;

    GPSTracker gps;
    TextView location;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_track);

        try {
            if (ActivityCompat.checkSelfPermission(this, mPermission) != MockPackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{mPermission}, REQUEST_CODE_PERMISSION);
            }
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            btnShowLocation=(Button) findViewById(R.id.button);
        btnShowLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gps =new GPSTracker(live_track.this);
                location =(TextView) findViewById(R.id.locationText);
                latitude_show =(TextView) findViewById(R.id.latitude_show);
                longitude_show=(TextView) findViewById(R.id.longitude_show);
                mess=(TextView)findViewById(R.id.mess);
                location =(TextView) findViewById(R.id.locationText);
                if(gps.canGetLocation()){
                    double latitude =gps.getLatitude();
                    double longitude=gps.getLongitude();
                    database = FirebaseDatabase.getInstance();
                    myRef =database.getReference("location");
                    myRef.setValue(latitude+","+longitude);
                    mess.setText("serious mode");
                    latitude_show.setText(latitude+"");
                    longitude_show.setText(longitude+"");

                }
                else{
                    gps.showSettingAlert();
                }

            }
        });
        btnShowLocation=(Button) findViewById(R.id.serious);
        btnShowLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    mess.setText("serious mode");

                }
                else{
                    gps.showSettingAlert();
                }

            }
        });

        btnShowLocation=(Button) findViewById(R.id.normal);
        btnShowLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    mess.setText("serious mode");


                }
                else{
                    gps.showSettingAlert();
                }

            }
        });

    }
}
