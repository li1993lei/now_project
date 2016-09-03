package com.lilei.play.ui.holder;

import com.lilei.play.R;
import com.lilei.play.utils.UiUtils;

import android.R.integer;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MoreHolder extends BaseHolder<Integer> {
	
	public static final int TYPE_HAS_MORE = 0;
	public static final int TYPE_NO_MORE = 1;
	public static final int TYPE_ERROR = 2;
	private LinearLayout llLoadMore;
	private TextView tvLoadError;
	public MoreHolder(boolean hasMore) {
		setData(hasMore?TYPE_HAS_MORE:TYPE_NO_MORE);
	}

	@Override
	public View initView() {
		View view = UiUtils.inflate(R.layout.list_item_more);

		llLoadMore = (LinearLayout) view.findViewById(R.id.ll_load_more);
		tvLoadError = (TextView) view.findViewById(R.id.tv_load_error);
		return view;
	}

	@Override
	public void refreshView(Integer data) {
		switch (data) {
		case TYPE_HAS_MORE:
			llLoadMore.setVisibility(View.VISIBLE);
			tvLoadError.setVisibility(View.GONE);
			break;
		case TYPE_NO_MORE:
			llLoadMore.setVisibility(View.GONE);
			tvLoadError.setVisibility(View.GONE);
			break;
		case TYPE_ERROR:
			llLoadMore.setVisibility(View.GONE);
			tvLoadError.setVisibility(View.VISIBLE);
			break;

		default:
			break;
		}
		
	}

}
