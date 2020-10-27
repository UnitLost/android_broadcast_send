package com.example.android_broadcast_send;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.app.NotificationManager;
import androidx.core.app.NotificationCompat;


import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyBroadcastReceiver MyBroadcastReceiver = new MyBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("abc3");
        registerReceiver(MyBroadcastReceiver, intentFilter);
        Button btn = findViewById(R.id.button1);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction("abc3");
                sendBroadcast(intent);
            }
        });

    }

    class MyBroadcastReceiver extends BroadcastReceiver {

        private NotificationManager manager;

        @SuppressLint("WrongConstant")
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("abc1")) {
                Toast.makeText(context, "广播来了1：", 1).show();
            }
            if (intent.getAction().equals("abc2")) {
                Toast.makeText(context, "广播来了2：", 1).show();
            }
            if (intent.getAction().equals("abc3")) {
                //要用到通知功能必不可少就是获取一个NotificationManager对象（系统服务）
                manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                //高版本需要渠道
                if (Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    //创建自己所需要的渠道
                    NotificationChannel notificationChannel = new NotificationChannel("channelid1", "channelname", NotificationManager.IMPORTANCE_HIGH);
                    //在Android O之后，增加了NotificationChannel的新特性，相同channel的通知拥有同样的特性，例如优先级，声音，振动，LED灯等等。
                    notificationChannel.setDescription("这是channel1");
                    notificationChannel.enableLights(true);
                    notificationChannel.enableVibration(true);
                    notificationChannel.setLightColor(Color.rgb(99,99,99));
                    manager.createNotificationChannel(notificationChannel);
                }
                //使用build模式构建一个通知
                NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this, "channelid1");
                builder.setSmallIcon(R.drawable.ic_launcher_background)
                        .setContentTitle("没有网络")
                        .setContentText("网络掉线了")
                        .setAutoCancel(true);
                manager.notify(0x12, builder.build());
            }

        }
    }

}