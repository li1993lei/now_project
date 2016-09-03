package com.lilei.play.ui.fragment;

import java.util.ArrayList;

import com.lilei.play.ui.view.LoadingPage;
import com.lilei.play.ui.view.LoadingPage.ResultState;
import com.lilei.play.utils.UiUtils;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public abstract class BaseFragment extends Fragment {

	private LoadingPage loadingPage;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		loadingPage = new LoadingPage(UiUtils.getContext()){

			@Override
			public View onCreateSuccessView() {
//				TextView textView = new TextView(UiUtils.getContext());
//				textView.setText("hahahah");
//				return textView;
				View view = BaseFragment.this.onCreateSuccessView();//basefragment也不知道要实例化的布局 交给子类去做
				return view;
			}

			@Override
			public ResultState onLoad() {  //BaseFragment也不知道去下载什么数据 交给子类去实现  

				return BaseFragment.this.onLoad();
			}
			
		};
		return loadingPage;
	}
	//子类覆盖此方法来创建自己的布局
	public abstract View onCreateSuccessView();
	//子类覆盖自方法来加载数据
	public abstract ResultState onLoad();
	//调用的还是loadingpage中的LoadData方法
	public void LoadData() {
		if (loadingPage != null ) {
			loadingPage.loadData();
		}
		
	}
	// 对网络返回数据的合法性进行校验
		public ResultState check(Object obj) {
			if (obj != null) {
				if (obj instanceof ArrayList) {// 判断是否是集合
					ArrayList list = (ArrayList) obj;

					if (list.isEmpty()) {
						return ResultState.STATE_EMPTY;
					} else {
						return ResultState.STATE_SUCCESS;
					}
				}
			}

			return ResultState.STATE_ERROR;
		}
}
