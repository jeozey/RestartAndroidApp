package com.jeo.myapplication;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

/**
 * Created by Administrator on 2016/6/24.
 */
public class ThridActivity extends Activity {
    private static final String TAG = ThridActivity.class.getName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thrid);

        findViewById(R.id.exit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exit(ThridActivity.this,MainActivity.class);
            }
        });
        ScreenManager.getScreenManager().pushActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ScreenManager.getScreenManager().popActivity(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume: " );
    }
    private void exit(Activity currentActitity, Class<? extends Activity> clz){


        Intent mStartActivity = new Intent(currentActitity, clz);
        int mPendingIntentId = 123456;
        PendingIntent mPendingIntent = PendingIntent.getActivity(currentActitity, mPendingIntentId,
                mStartActivity, PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager mgr = (AlarmManager)currentActitity.getSystemService(Context.ALARM_SERVICE);
        mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 5100, mPendingIntent);

        ScreenManager.getScreenManager().finishAllActivity();

        System.exit(0);
    }
}
