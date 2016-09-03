package com.lilei.play.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.lilei.play.R;
import com.lilei.play.utils.UiUtils;
/**
 * 抽取的界面 
 * 共同的状态有 未加载  加载中  加载失败  加载为空  加载成功
 * 
 * 思路：初始化布局时  将父类可以实现的布局 实例化出来 在定义五种状态 利用当前的状态值来显示不同的界面是显示还是隐藏
 * 对于加载成功的布局 其父类并不知道怎么做 可以定义一个接口 让实现其的子类去完成 
 * 
 * @author 李小磊
 *
 */
public abstract class LoadingPage extends FrameLayout {

	private static final int STATE_LOAD_UNDO = 0;// 未加载
	private static final int STATE_LOAD_LOADING = 1;// 正在加载
	private static final int STATE_LOAD_EMPTY = 2;// 加载为空
	private static final int STATE_LOAD_ERROR = 3;// 加载错误
	private static final int STATE_LOAD_SUCCESS = 4;// 加载成功

	private int mCurrentState = STATE_LOAD_UNDO;

	private View mLoadingPage;
	private View mErrorPage;
	private View mEmptyPage;
	private View mSuccessPage;

	public LoadingPage(Context context) {
		super(context);
		initView();
	}

	public LoadingPage(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public LoadingPage(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView();
	}

	private void initView() {
		// 加载中的布局
		if (mLoadingPage == null) {
			mLoadingPage = UiUtils.inflate(R.layout.page_loading);
			addView(mLoadingPage);
		}
		// 加载失败的布局
		if (mErrorPage == null) {
			mErrorPage = UiUtils.inflate(R.layout.page_error);
			addView(mErrorPage);
		}
		// 加载为空的布局
		if (mEmptyPage == null) {
			mEmptyPage = UiUtils.inflate(R.layout.page_empty);
			addView(mEmptyPage);
		}
		showRightView();
	}

	private void showRightView() {
		// if (mCurrentState == STATE_LOAD_UNDO || mCurrentState ==
		// STATE_LOAD_LOADING) {
		// mLoadingPage.setVisibility(View.VISIBLE);
		// }else {
		// mLoadingPage.setVisibility(View.INVISIBLE);
		// }

		mLoadingPage
		.setVisibility((mCurrentState == STATE_LOAD_UNDO || mCurrentState == STATE_LOAD_LOADING) ? View.VISIBLE
						: View.GONE);
		mEmptyPage
		.setVisibility((mCurrentState == STATE_LOAD_EMPTY) ? View.VISIBLE
				: View.GONE);
		
		mErrorPage
		.setVisibility((mCurrentState == STATE_LOAD_ERROR) ? View.VISIBLE
				: View.GONE);
		
		//加载成功的布局  由子类去完成 在父类完成不了
		
		if (mSuccessPage == null && mCurrentState == STATE_LOAD_SUCCESS) {
			mSuccessPage = onCreateSuccessView();  //由子类去实现
			if (mSuccessPage != null) {
				//判断是否为空 不为空的话才添加   因为上面是实例化布局文件对象 一般不用判空  而成功界面由子类来实现的  所以需要判空 来决定是否添加
				addView(mSuccessPage);
			}
		}
		
		if (mSuccessPage != null) {
			mSuccessPage
			.setVisibility((mCurrentState == STATE_LOAD_SUCCESS) ? View.VISIBLE
					: View.GONE);
		}
	}
	//起子线程获取数据  利用获取数据的返回值 来判断加载哪个状态的界面  利用枚举
	public void loadData(){
		//判断是否数据 下载状态
		if (mCurrentState != STATE_LOAD_LOADING) {
			mCurrentState = STATE_LOAD_LOADING;  //将状态置为下载状态
			
			//起一个子线程来下载
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					//父类并不知道下载的动作 交给实现它的完成  但是它需要知道下载的状态 是成功 还是失败 还是为空
					final ResultState state = onLoad(); //需要返回一个状态
					UiUtils.runOnUiThread(new Runnable() { //工具类 用于在主线程运行
						
						@Override
						public void run() {
							mCurrentState = state.getState();  //拿到当前的状态值
							showRightView();  //更新布局
						}
					});
				}
			}).start();
		}
	}
	public abstract ResultState onLoad();
	public abstract View onCreateSuccessView();
	public enum ResultState {
		STATE_SUCCESS(STATE_LOAD_SUCCESS), STATE_EMPTY(STATE_LOAD_EMPTY), STATE_ERROR(
				STATE_LOAD_ERROR);

		private int state;

		private ResultState(int state) {
			this.state = state;
		}

		public int getState() {
			return state;
		}
	}
	
	public static class Person {

		public static Person P1 = new Person(10);
		public static Person P2 = new Person(12);
		public static Person P3 = new Person(19);

		public Person(int age) {

		}
	}

	// public enum Person {
	// P1,P2,P3;
	// }
}
