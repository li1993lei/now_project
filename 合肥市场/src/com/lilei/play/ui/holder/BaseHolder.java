package com.lilei.play.ui.holder;

import android.view.View;

public abstract class BaseHolder<T> {

	private View rootView;
	private T data; //item对应的数据 有余不知道类型 用T来代替
	
	public T getData() {
		return data;
	}
	//设置完数据后 刷新视图
	public void setData(T data) {
		this.data = data;
		refreshView(data);
	}
	public BaseHolder() {
		rootView = initView();//初始化 
		rootView.setTag(this);//打标记
	}
	//初始化布局
	public abstract View initView();
	//获取布局对象
	public View getRootView() {
		return rootView;
	}
	
	public abstract void refreshView(T data);
}
