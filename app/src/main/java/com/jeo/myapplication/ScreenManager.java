package com.jeo.myapplication;

import android.app.Activity;
import android.util.Log;

import java.util.ArrayList;

public class ScreenManager {
	private static final String TAG = ScreenManager.class.getName();
	private static ArrayList<Activity> activityStack;
	private static ScreenManager instance;

	private ScreenManager() {
	}

	public static ScreenManager getScreenManager() {
		if (instance == null) {
			instance = new ScreenManager();
		}
		return instance;
	}

	// 退出栈顶Activity
	public void popActivity(Activity activity) {
		if (activity != null) {
			activity.finish();
			if (activityStack != null) {
				activityStack.remove(activity);
			}
			activity = null;
		}
	}

	// 获得当前栈顶Activity
	public Activity currentActivity() {
//		Activity activity = activityStack.lastElement();
//		return activity;
		return null;
	}

	// 将当前Activity推入栈中
	public void pushActivity(Activity activity) {
		if (activityStack == null) {
			activityStack = new ArrayList<Activity>();
		}
		activityStack.add(activity);
	}

	// 退出栈中所有Activity
	public void popAllActivityExceptOne(Class cls) {
		while (true) {
			Activity activity = currentActivity();
			if (activity == null) {
				break;
			}
			if (activity.getClass().equals(cls)) {
				break;
			}
			popActivity(activity);
		}
	}
	
	//退出列表中所有Activity
	
	public void finishAllActivity(){
		if(activityStack!=null) {
			for (Activity act : activityStack) {
				if (act != null) {
					Log.e(TAG, "finishAllActivity: "+act.getLocalClassName());
					act.finish();
					act = null;
				}
			}
			activityStack.clear();
		}
	}
	
}
