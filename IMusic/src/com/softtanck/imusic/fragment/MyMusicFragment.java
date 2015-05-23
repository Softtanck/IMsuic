package com.softtanck.imusic.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

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

	/**
	 * 本地音乐
	 */
	private LinearLayout localMusic;

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

		localMusic = (LinearLayout) view.findViewById(R.id.home_ll_local_music);

		localMusic.setOnClickListener(this);

	}

	@Override
	protected void getservciesData() {
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);

		switch (v.getId()) {
		case R.id.home_ll_local_music:// 本地音乐
			holder.middleFragment = new LocalMusicFragment();
			holder.changeFragment(R.id.home_content, holder.middleFragment, null);
			break;

		}
	}

}
