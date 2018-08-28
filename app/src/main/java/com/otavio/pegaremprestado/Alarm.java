package com.otavio.pegaremprestado;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class Alarm extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("Alarmou","tocou ");
        Toast.makeText(context,"Alarm set in seconds",Toast.LENGTH_LONG).show();

    }
}
