package com.example.vani.music;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import static android.content.ContentValues.TAG;

public class My_Broadcast_Receiver extends BroadcastReceiver {



    @Override
    public void onReceive(Context context, Intent intent) {

        StringBuilder sb = new StringBuilder();
        sb.append("Broadcast Receiver Action : " + intent.getAction() + " \n");

        String message = sb.toString();

        Log.d(TAG, message);
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();

    }
}
