package com.softtanck.imusic;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.softtanck.imusic.service.meessage.MyHandler;

/**
 * @author Tanck
 * 
 * @Description 所有碎片的基类
 * 
 * @date Jan 16, 2015 8:52:21 PM
 */
public abstract class BaseFragment extends Fragment implements OnClickListener {
	/**
	 * 碎片持有者
	 */
	public BaseActivity holder;
	/**
	 * 自身当前是否可見
	 */
	public boolean selfVisible;
	/**
	 * 上下文
	 */
	public Context context;

	/**
	 * 初始化
	 */
	private boolean init = true;

	/**
	 * 消息处理者
	 */
	public MyHandler mhandler;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		mhandler = new MyHandler();

		HandlerMessageContainer.addHandler(mhandler);

		holder = (BaseActivity) activity;

		context = holder.getApplicationContext();

		onAttached();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(getLayoutView(), container, false);
	}

	/**
	 * 当Fragment被附加的时候
	 */
	public abstract void onAttached();

	/**
	 * 当Fragment被分离的时候
	 */
	public abstract void onDeatch();

	/**
	 * 获取Fragment填充布局
	 */
	public abstract int getLayoutView();

	@Override
	public void onDetach() {
		super.onDetach();
		HandlerMessageContainer.removeHandler(mhandler);
		onDeatch();
		holder = null;
	}

	@Override
	public void onHiddenChanged(boolean hidden) {
		super.onHiddenChanged(hidden);
		selfVisible = !hidden;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO 初始化一些数据,如findViewById
		super.onViewCreated(view, savedInstanceState);
		onViewCreate(view, savedInstanceState);
	}

	/**
	 * 当View被创建的时候
	 * 
	 * @param savedInstanceState
	 */
	public abstract void onViewCreate(View view, Bundle savedInstanceState);

	@Override
	public void onResume() {
		super.onResume();
		if (getUserVisibleHint()) {
			getdata();
		}
	}

	/**
	 * 获取数据
	 */
	private void getdata() {
		if (init) {
			init = false;
			getservciesData();
		}
	}

	/**
	 * 第一次获取数据
	 */
	protected abstract void getservciesData();

	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);
		if (isVisibleToUser) {
			getdata();
		}
	}

	/**
	 * 点击事件处理,如果有直接重写,没有就忽略
	 */
	@Override
	public void onClick(View v) {

	}

}
