package com.softtanck.imusic.fragment;

import android.os.Bundle;
import android.view.View;

import com.softtanck.imusic.BaseFragment;
import com.softtanck.imusic.R;
import com.softtanck.imusic.utils.LogUtils;

/**
 * 
 * @Description TODO 推荐
 * 
 * @author Tanck
 * 
 * @date Apr 15, 2015 9:52:35 AM
 * 
 */
public class RecommendFragment extends BaseFragment {

	@Override
	public void onAttached() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onDeatch() {
		// TODO Auto-generated method stub

	}

	@Override
	public int getLayoutView() {
		// TODO Auto-generated method stub
		return R.layout.fragment_recommed;
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
