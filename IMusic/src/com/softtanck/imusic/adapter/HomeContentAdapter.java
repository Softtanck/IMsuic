package com.softtanck.imusic.adapter;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

/**
 * 
 * @Description TODO 主页页面切换适配器
 * 
 * @author Tanck
 * 
 * @date Apr 15, 2015 10:08:10 AM
 * 
 */
public class HomeContentAdapter extends FragmentPagerAdapter {

	/**
	 * 主页页面集合
	 */
	private List<Fragment> mList;

	/**
	 * 碎片管理器
	 */
	private FragmentManager fManager;

	public HomeContentAdapter(FragmentManager fm, List<Fragment> mList) {
		super(fm);
		this.mList = mList;
		this.fManager = fm;
	}

	@Override
	public Fragment getItem(int position) {
		Fragment fragment = null;
		if (position < mList.size()) {
			fragment = mList.get(position);
		} else {
			fragment = mList.get(0);
		}
		return fragment;
	}

	@Override
	public int getCount() {
		return mList.size();
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView(mList.get(position).getView());
	}

}
