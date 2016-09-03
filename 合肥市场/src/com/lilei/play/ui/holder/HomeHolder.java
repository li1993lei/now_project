package com.lilei.play.ui.holder;

import android.view.View;
import android.widget.TextView;

import com.lilei.play.R;
import com.lilei.play.domain.AppInfo;
import com.lilei.play.utils.UiUtils;

public class HomeHolder extends BaseHolder<AppInfo> {

	private TextView tvContentTextView;

	@Override
	public View initView() {
		View view = UiUtils.inflate(R.layout.list_item_home);
		tvContentTextView = (TextView) view.findViewById(R.id.tv_content);
		return view;
	}

	@Override
	public void refreshView(AppInfo data) {
		tvContentTextView.setText(data.name);
	}

}
