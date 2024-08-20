package com.example.wakemaze20;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent ringIntent = new Intent(context, RingActivity.class);
        ringIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(ringIntent);
    }
}
