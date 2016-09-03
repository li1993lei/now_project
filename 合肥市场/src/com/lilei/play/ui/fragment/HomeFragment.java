package com.lilei.play.ui.fragment;

import java.util.ArrayList;

import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.lilei.play.domain.AppInfo;
import com.lilei.play.http.protocal.HomeProtocol;
import com.lilei.play.ui.adapter.MyBaseAdapter;
import com.lilei.play.ui.adapter.MyBaseAdapter1;
import com.lilei.play.ui.holder.BaseHolder;
import com.lilei.play.ui.holder.HomeHolder;
import com.lilei.play.ui.view.LoadingPage.ResultState;
import com.lilei.play.utils.UiUtils;

public class HomeFragment extends BaseFragment {
	ArrayList<AppInfo> data;
	ArrayList<String> moreData;
	ArrayList<String> datas;
	@Override
	public View onCreateSuccessView() {
		ListView listView = new ListView(UiUtils.getContext());
		listView.setAdapter(new MyAdapter(data));
		return listView;
	}

	@Override
	public ResultState onLoad() {
//		datas  = new ArrayList<String>();
//		for (int i = 0; i <40; i++) {
//			datas.add("测试数据" + i );
//		}
//		return ResultState.STATE_SUCCESS;
		HomeProtocol protocal = new HomeProtocol();
		 data = protocal.getData(0);
		return check(data);//需要对集合进行判断来决定返回的状态
	}

	class MyAdapter extends MyBaseAdapter1<AppInfo>{

		public MyAdapter(ArrayList<AppInfo> data) {
			super(data);
		}

		@Override
		public BaseHolder<AppInfo> getHolder(int position) {
			// TODO Auto-generated method stub
			return new HomeHolder();
		}

		@Override
		public ArrayList<AppInfo> onLoadMore() {
//			moreData  = new ArrayList<String>();
//			for (int i = 0; i < 40; i++) {
//				moreData.add("测试更多的数据" + i );
//			}
			HomeProtocol protocal = new HomeProtocol();
			ArrayList<AppInfo> moreData = protocal.getData(getListSize());
			return moreData;
		}
		
		
	}
	class ViewHolder{
		TextView tvContent;
	}
}
