package com.softtanck.imusic.ui;

import java.util.ArrayList;
import java.util.List;

import com.softtanck.imusic.BaseActivity;
import com.softtanck.imusic.ConstantValue;
import com.softtanck.imusic.R;
import com.softtanck.imusic.R.id;
import com.softtanck.imusic.R.layout;
import com.softtanck.imusic.adapter.HomeContentAdapter;
import com.softtanck.imusic.fragment.MyMusicFragment;
import com.softtanck.imusic.fragment.RecommendFragment;
import com.softtanck.imusic.fragment.SearchSongFragment;
import com.softtanck.imusic.fragment.SongFragment;
import com.softtanck.imusic.view.TitleView;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Menu;

/**
 * 
 * @Description TODO 播放器主界面
 * 
 * @author Tanck
 * 
 * @date Apr 14, 2015 8:20:31 PM
 * 
 */
public class HomeActivity extends BaseActivity implements OnPageChangeListener {

	/**
	 * 主页滑动切换
	 */
	private ViewPager mPager;

	/**
	 * 主页页面集合
	 */
	private List<Fragment> mList;

	/**
	 * 中间容器
	 */
	private Fragment middleFragment;

	/**
	 * 主页页面适配器
	 */
	private HomeContentAdapter hadapter;

	@Override
	protected int getViewId() {
		return R.layout.activity_main;
	}

	@Override
	protected void onActivityCreate() {
		initView();
	}

	/**
	 * 初始化布局
	 */
	private void initView() {

		mPager = (ViewPager) findViewById(R.id.home_content_pager);

		initContentView();

		initTitle();

	}

	/**
	 * 初始化主页标题
	 */
	private void initTitle() {

	}

	/**
	 * 初始化主页面内容布局
	 */
	private void initContentView() {
		mList = new ArrayList<Fragment>();
		middleFragment = new MyMusicFragment();
		mList.add(middleFragment);
		middleFragment = new RecommendFragment();
		mList.add(middleFragment);
		middleFragment = new SongFragment();
		mList.add(middleFragment);
		middleFragment = new SearchSongFragment();
		mList.add(middleFragment);
		// 适配布局
		hadapter = new HomeContentAdapter(fragmentManager, mList);
		mPager.setAdapter(hadapter);
		mPager.setOffscreenPageLimit(4);
	}

	@Override
	public void onDestroyed() {

	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageSelected(int arg0) {
		// TODO Auto-generated method stub

	}

}
