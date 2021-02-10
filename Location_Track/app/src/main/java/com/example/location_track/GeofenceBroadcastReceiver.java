package com.example.location_track;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class GeofenceBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        //throw new UnsupportedOperationException("Not yet implemented");
        // Enqueues a JobIntentService passing the context and intent as parameters
        GeofenceTransitionsJobIntentService.enqueueWork(context, intent);
    }
}