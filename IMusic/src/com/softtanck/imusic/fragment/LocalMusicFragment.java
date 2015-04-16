package com.softtanck.imusic.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.softtanck.imusic.BaseFragment;
import com.softtanck.imusic.R;
import com.softtanck.imusic.utils.LogUtils;

/**
 * 
 * @Description TODO 本地歌曲
 * 
 * @author Tanck
 * 
 * @date Apr 16, 2015 3:55:15 PM
 * 
 */
public class LocalMusicFragment extends BaseFragment {

	@Override
	public void onAttached() {

	}

	@Override
	public void onDeatch() {

	}

	@Override
	public int getLayoutView() {
		return R.layout.fragment_local_music;
	}

	@Override
	public void onViewCreate(View view, Bundle savedInstanceState) {
		LogUtils.d("onViewCreate");
	}

	@Override
	protected void getservciesData() {
		LogUtils.d("getservciesData");
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
	}

}
