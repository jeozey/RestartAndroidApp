package com.jeo.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

public class MainActivity extends Activity {
    private static final String TAG = MainActivity.class.getName();
    private WindowManager mWindowManager;
    private WindowManager.LayoutParams param;
    private FloatView mLayout;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.e(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        showView();
        findViewById(R.id.txt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.startActivity(new Intent(MainActivity.this,SecondActivity.class));
            }
        });

        ScreenManager.getScreenManager().pushActivity(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume: " );
    }

    private void showView(){
        mLayout=new FloatView(getApplicationContext());
        mLayout.setBackgroundResource(android.R.drawable.ic_menu_delete);
        //获取WindowManager
        mWindowManager=(WindowManager)getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        //设置LayoutParams(全局变量）相关参数
        param = ((MyApplication)getApplication()).getMywmParams();

        param.type=WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;     // 系统提示类型,重要
        param.format=1;
        param.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE; // 不能抢占聚焦点
        param.flags = param.flags | WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH;
        param.flags = param.flags | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS; // 排版不受限制

        param.alpha = 1.0f;

        param.gravity= Gravity.LEFT|Gravity.TOP;   //调整悬浮窗口至左上角
        //以屏幕左上角为原点，设置x、y初始值
        param.x=0;
        param.y=0;

        //设置悬浮窗口长宽数据
        param.width=140;
        param.height=140;

        //显示myFloatView图像
        mWindowManager.addView(mLayout, param);

    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        ScreenManager.getScreenManager().popActivity(this);
        //在程序退出(Activity销毁）时销毁悬浮窗口
        mWindowManager.removeView(mLayout);
    }
}
