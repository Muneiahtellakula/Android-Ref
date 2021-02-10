package com.example.location_track;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import im.delight.android.location.SimpleLocation;

public class MainActivity2 extends Activity {
    private SimpleLocation mLocation;
TextView tv;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        tv=findViewById(R.id.textView);
        // construct a new instance
        mLocation = new SimpleLocation(this);

        // reduce the precision to 5,000m for privacy reasons
        mLocation.setBlurRadius(5000);

        // if we can't access the location yet
        if (!mLocation.hasLocationEnabled()) {
            // ask the user to enable location access
            SimpleLocation.openSettings(this);
        }
        final double latitude = mLocation.getLatitude();
        final double longitude = mLocation.getLongitude();
        final long timestampInMilliseconds = mLocation.getTimestampInMilliseconds();
        final long elapsedTimeInNanoseconds = mLocation.getElapsedTimeInNanoseconds();

        Toast.makeText(MainActivity2.this, "Latitude: "+latitude, Toast.LENGTH_SHORT).show();
        Toast.makeText(MainActivity2.this, "Longitude: "+longitude, Toast.LENGTH_SHORT).show();
        Toast.makeText(MainActivity2.this, "Timestamp in milliseconds: "+timestampInMilliseconds, Toast.LENGTH_SHORT).show();
        Toast.makeText(MainActivity2.this, "Elapsed time in nanoseconds: "+elapsedTimeInNanoseconds, Toast.LENGTH_SHORT).show();
        tv.setText("Latitude: "+latitude+"\n"+"Longitude: "+longitude+"\n"+"Timestamp in milliseconds: "+timestampInMilliseconds+
                "\n"+"Elapsed time in nanoseconds: "+elapsedTimeInNanoseconds);

        /*findViewById(R.id.textView).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                *//*final double latitude = mLocation.getLatitude();
                final double longitude = mLocation.getLongitude();
                final long timestampInMilliseconds = mLocation.getTimestampInMilliseconds();
                final long elapsedTimeInNanoseconds = mLocation.getElapsedTimeInNanoseconds();

                Toast.makeText(MainActivity2.this, "Latitude: "+latitude, Toast.LENGTH_SHORT).show();
                Toast.makeText(MainActivity2.this, "Longitude: "+longitude, Toast.LENGTH_SHORT).show();
                Toast.makeText(MainActivity2.this, "Timestamp in milliseconds: "+timestampInMilliseconds, Toast.LENGTH_SHORT).show();
                Toast.makeText(MainActivity2.this, "Elapsed time in nanoseconds: "+elapsedTimeInNanoseconds, Toast.LENGTH_SHORT).show();*//*
            }

        });*/
    }

    @Override
    protected void onResume() {
        super.onResume();

        // make the device update its location
        mLocation.beginUpdates();
    }

    @Override
    protected void onPause() {
        // stop location updates (saves battery)
        mLocation.endUpdates();

        super.onPause();
    }
}