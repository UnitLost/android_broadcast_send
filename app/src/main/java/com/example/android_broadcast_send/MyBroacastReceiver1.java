package com.example.android_broadcast_send;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyBroacastReceiver1 extends BroadcastReceiver {
    public MyBroacastReceiver1(){

    }

    @Override
    public void onReceive(Context context, Intent intent){
        Toast.makeText(context,"收到了自定义的广播1",Toast.LENGTH_LONG).show();
    }

}
