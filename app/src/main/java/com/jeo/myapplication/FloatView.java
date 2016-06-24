package com.jeo.myapplication;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

public class FloatView extends View {
    private float mTouchStartX;
    private float mTouchStartY;
    private float x;
    private float y;

    private WindowManager wm=(WindowManager)getContext().getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
    private WindowManager.LayoutParams wmParams = ((MyApplication)getContext().getApplicationContext()).getMywmParams();

    public FloatView(Context context) {
        super(context);
    }
    private int downX,downY;
    @Override
    public boolean onTouchEvent(MotionEvent event) {


        //获取相对屏幕的坐标，即以屏幕左上角为原点
        x = event.getRawX();
        y = event.getRawY()-25;   //25是系统状态栏的高度
        Log.i("currP", "currX"+x+"====currY"+y);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
//                setBackgroundResource(android.R.drawable.ic_menu_delete);
                //获取相对View的坐标，即以此View左上角为原点
                mTouchStartX =  event.getX();
                mTouchStartY =  event.getY();
                downX=(int)( x-mTouchStartX);
                downY=(int) (y-mTouchStartY);

                Log.i("startP", "startX"+mTouchStartX+"====startY"+mTouchStartY);

                break;
            case MotionEvent.ACTION_MOVE:
                Log.e("a", "onTouchEvent: 3");
                updateViewPosition();
                break;

            case MotionEvent.ACTION_UP:
//                setBackgroundResource(android.R.drawable.ic_menu_add);
                Log.e("a", "onTouchEvent: downX"+downX+"---"+(x - mTouchStartX) );
                Log.e("a", "onTouchEvent: downY"+downY+"---"+(y - mTouchStartY) );
                if (Math.abs(downX-(x - mTouchStartX)) < 10 && Math.abs(downY-(y - mTouchStartY)) < 10) {
                    Log.e("a", "onTouchEvent: 1" );
                } else {
                    Log.e("a", "onTouchEvent: 2" );
                    updateViewPosition();
                }
                mTouchStartX=mTouchStartY=0;
                break;
        }
        return true;
    }

    private void updateViewPosition(){
        //更新浮动窗口位置参数
        wmParams.x=(int)( x-mTouchStartX);
        wmParams.y=(int) (y-mTouchStartY);
        wm.updateViewLayout(this, wmParams);

    }

}