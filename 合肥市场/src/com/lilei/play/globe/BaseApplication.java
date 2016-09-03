package com.lilei.play.globe;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
/**
 * 自定义的application   用于初始化上下文  获取主线程ID 封装handler
 * @author 李小磊
 *
 */
public class BaseApplication extends Application {

	public static Context context;
	private static int mainId;
	private static Handler handler;

	public static Context getContext() {
		return context;
	}

	public static int getMainId() {
		return mainId;
	}

	public static Handler getHandler() {
		return handler;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		context = getApplicationContext();
		mainId = android.os.Process.myTid();
		handler = new Handler();
	}
}
