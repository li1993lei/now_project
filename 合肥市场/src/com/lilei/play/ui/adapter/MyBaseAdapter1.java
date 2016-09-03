package com.lilei.play.ui.adapter;

import java.util.ArrayList;

import com.lilei.play.ui.holder.BaseHolder;
import com.lilei.play.ui.holder.MoreHolder;
import com.lilei.play.utils.UiUtils;

import android.R.integer;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class MyBaseAdapter1<T> extends BaseAdapter {
	ArrayList<T> data;
	private static final int ITEM_LOAD_MORE = 0;
	private static final int ITEM_LIST_VIEW = 1;
	public MyBaseAdapter1(ArrayList<T> data) {
		this.data = data;
	}
	@Override
	public int getViewTypeCount() {
		// TODO Auto-generated method stub
		return 2;
	}
	@Override
	public int getItemViewType(int position) {
		if (position == getCount() -1) {
			return ITEM_LOAD_MORE;
		}else {
			return getInnerType(); //不能写死  
		}
	}
	private int getInnerType() {
		return ITEM_LIST_VIEW; //默认返回listview 子类可通过复写该方法来返回自己的类型
 	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size() + 1;
	}

	@Override
	public T getItem(int position) {
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		BaseHolder holder = null ;
		if (convertView == null ) { 
			if (getItemViewType(position) == ITEM_LOAD_MORE) {
				holder = new MoreHolder(hasMore());
			}else {
				holder  = getHolder(position);
			}
		}else {
			holder = (BaseHolder<T>) convertView.getTag();
		}
		//刷新数据
		if (getItemViewType(position) != ITEM_LOAD_MORE) {  //才刷新数据
			
			holder.setData(getItem(position));
		}else {
			MoreHolder moreHolder = (MoreHolder) holder;
			if (moreHolder.getData() == MoreHolder.TYPE_HAS_MORE) {
				loadMore(moreHolder);
			}
			
		}
		return holder.getRootView();
	}
	private boolean isLoadMore = false;
	
	public void loadMore(final MoreHolder holder){
		if (!isLoadMore) {
			isLoadMore = true;
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					 final ArrayList<T> moreData = onLoadMore();
					 UiUtils.runOnUiThread(new Runnable() {
						
						@Override
						public void run() {
							//现在想通过判断moreData的值来对加载更多布局的条目做一个判断
							if (moreData == null) {
								holder.setData(MoreHolder.TYPE_ERROR);
							}else {
								if (moreData.size() < 20) {
									holder.setData(MoreHolder.TYPE_NO_MORE);
								}else {
									holder.setData(MoreHolder.TYPE_HAS_MORE);
								}
							}
							
						 	data.addAll(moreData);
					 		MyBaseAdapter1.this.notifyDataSetChanged();
							
						}
					});
					 isLoadMore = false;
				}
			}).start();
		}
	}
	
	public boolean hasMore(){
		return true;
	}
	public int getListSize(){
		return data.size();
	}
	public abstract BaseHolder<T> getHolder(int position);
	public abstract ArrayList<T> onLoadMore();
}
