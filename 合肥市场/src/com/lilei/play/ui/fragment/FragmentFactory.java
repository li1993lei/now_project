package com.lilei.play.ui.fragment;

import android.annotation.SuppressLint;
import java.util.HashMap;


public class FragmentFactory {
	@SuppressLint("UseSparseArrays") 
	//使用HashMap来保存键和值  键就是位置  值就是BaseFragment
	public static HashMap<Integer, BaseFragment> mFragmentMap = new HashMap<Integer, BaseFragment>();
	public static BaseFragment createFragment(int pos) {
		//先从集合中取  
		BaseFragment fragment = mFragmentMap.get(pos);
		//为空才new
		if (fragment == null) {
			
			switch (pos) {
			case 0:
				fragment = new HomeFragment();
				break;
			case 1:
				fragment = new AppFragment();
				break;
			case 2:
				fragment = new GameFragment();
				break;
			case 3:
				fragment = new SubjectFragment();
				break;
			case 4:
				fragment = new RecommendFragment();
				break;
			case 5:
				fragment = new CategoryFragment();
				break;
			case 6:
				fragment = new HotFragment();
				break;
				
			default:
				break;
			}
			//将fragment用集合维护起来
			mFragmentMap.put(pos, fragment);
		}
		
		return fragment;
	}

}
