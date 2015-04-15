package com.softtanck.imusic;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.softtanck.imusic.view.TitleView;

/**
 * @author Tanck
 * 
 * @Description TODO 所有Activity的基类
 * 
 * @date Jan 16, 2015 5:20:57 PM
 */
public abstract class BaseActivity extends FragmentActivity implements
		OnClickListener {

	/**
	 * 中间视图要展示的View
	 */
	protected Fragment middleFragment;
	/**
	 * 布局加载器
	 */
	public LayoutInflater inflater;
	/**
	 * 碎片管器器
	 */
	public FragmentManager fragmentManager;
	/**
	 * 公共标题
	 */
	public TitleView titleView;
	/**
	 * 上下文
	 */
	public Context context;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(getViewId());

		inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		fragmentManager = getSupportFragmentManager();
		context = getApplicationContext();

		titleView = (TitleView) findViewById(R.id.comm_titleView);

		onActivityCreate();

		ActivityContainer.addActivity(this);
	}

	/**
	 * 设置布局展示的View
	 */
	protected abstract int getViewId();

	/**
	 * 初始化布局控件
	 */
	protected abstract void onActivityCreate();

	/**
	 * 子类需要重写该点击事件,如果有则重写,没有则忽略
	 */
	@Override
	public void onClick(View v) {

	}

	/**
	 * 打印一个Toast
	 * 
	 * @param textId
	 */
	public void showToast(int textId) {
		showToast(getString(textId));
	}

	/**
	 * 打印一个Toast
	 * 
	 * @param text
	 */
	public void showToast(String text) {
		View view = inflater.inflate(R.layout.toast_message, null);
		((TextView) view.findViewById(R.id.tx_message)).setText(text);
		Toast mToast = new Toast(getApplicationContext());
		mToast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM, 0, 100);
		mToast.setDuration(Toast.LENGTH_SHORT);
		mToast.setView(view);
		mToast.show();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		onDestroyed();
		ActivityContainer.finishActivity(this);
	}

	@Override
	protected void onStop() {
		super.onStop();
	}

	/**
	 * 当Activity被销毁时调用
	 */
	public abstract void onDestroyed();

	@Override
	public void startActivity(Intent intent) {
		super.startActivity(intent);
		// TODO 一些切换的动画
	}

	@Override
	public void finish() {
		super.finish();
		// TODO 一些切换的动画
	}
}
