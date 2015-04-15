package com.softtanck.imusic.fragment;

import android.os.Bundle;
import android.view.View;

import com.softtanck.imusic.BaseFragment;
import com.softtanck.imusic.R;
import com.softtanck.imusic.utils.LogUtils;

/**
 * 
 * @Description TODO 主页面我的碎片布局
 * 
 * @author Tanck
 * 
 * @date Apr 15, 2015 9:51:38 AM
 * 
 */
public class MyMusicFragment extends BaseFragment {

	@Override
	public void onAttached() {

	}

	@Override
	public void onDeatch() {

	}

	@Override
	public int getLayoutView() {
		return R.layout.fragment_mymusic;
	}

	@Override
	public void onViewCreate(View view, Bundle savedInstanceState) {
		LogUtils.d("onViewCreate");
	}

	@Override
	protected void getservciesData() {
		LogUtils.d("getservciesData");
	}

}
