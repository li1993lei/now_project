package com.lilei.play.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;

import com.lilei.play.R;
import com.lilei.play.ui.fragment.BaseFragment;
import com.lilei.play.ui.fragment.FragmentFactory;
import com.lilei.play.ui.view.PagerTab;
import com.lilei.play.utils.UiUtils;


public class MainActivity extends BaseActivity {

    private PagerTab mPagerTab;
	private ViewPager mViewPager;
	private MyViewPagerAdapter adapter;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //界面指示器
        mPagerTab = (PagerTab) findViewById(R.id.pagertab);
        //ViewPager 
		mViewPager = (ViewPager) findViewById(R.id.viewpager);
		//给ViewPager指定一个FragmentPagerAdapter 是PageAdapter的子类  
		adapter = new MyViewPagerAdapter(getSupportFragmentManager());
		//设置适配器
		mViewPager.setAdapter(adapter);
		//绑定ViewPager
		mPagerTab.setViewPager(mViewPager);
		//设置滑动监听
		mPagerTab.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				//加载数据 当对应的页面被选中时 
				BaseFragment fragment = FragmentFactory.createFragment(arg0);//创建碎片
				//加载数据
				fragment.LoadData();
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}
		});
    }

	private class MyViewPagerAdapter extends FragmentPagerAdapter{

		private String[] mTabNames;

		public MyViewPagerAdapter(FragmentManager fm) {
			super(fm);
			//从字符数据中将定义的字符数据取出
			mTabNames = UiUtils.getStringArray(R.array.tab_names);
		}
		//返回指示器的文本
		@Override
		public CharSequence getPageTitle(int position) {
			// TODO Auto-generated method stub
			return mTabNames[position];
		}
		//利用工厂设计模式  返回对应位置上的Fragment
		@Override
		public Fragment getItem(int arg0) {
			//返回对应位置上的Fragment
			BaseFragment fragment = FragmentFactory.createFragment(arg0);
			return fragment;
		}

		@Override
		public int getCount() {
			//个数就是字符数组的长度
			return mTabNames.length;
		}
		
	}
   
}
