package com.softtanck.imusic.fragment;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;

import com.softtanck.imusic.BaseFragment;
import com.softtanck.imusic.R;
import com.softtanck.imusic.ui.utils.CircleImageView;

/**
 * 
 * @Description TODO 播放歌曲信息
 * 
 * @author Tanck
 * 
 * @date Apr 16, 2015 6:00:48 PM
 * 
 */
public class SongInfoFragment extends BaseFragment {

	private CircleImageView imageView;

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
		return R.layout.fragment_music_song_info;
	}

	@Override
	public void onViewCreate(View view, Bundle savedInstanceState) {
		imageView = (CircleImageView) view.findViewById(R.id.tv_music_song_info);
		imageView.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.song_info_rotate));
	}

	@Override
	protected void getservciesData() {

	}

}
