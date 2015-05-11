package com.softtanck.imusic.fragment;

import android.os.Bundle;
import android.os.Message;
import android.view.View;

import com.softtanck.imusic.BaseFragment;
import com.softtanck.imusic.ConstantValue;
import com.softtanck.imusic.R;
import com.softtanck.imusic.service.meessage.HandlerMessageListener;
import com.softtanck.imusic.utils.LogUtils;
import com.softtanck.imusic.view.LrcView;

/**
 * 
 * @Description TODO 歌词界面
 * 
 * @author Tanck
 * 
 * @date Apr 16, 2015 5:59:12 PM
 * 
 */
public class LrcFragment extends BaseFragment implements HandlerMessageListener {

	/**
	 * 歌词布局
	 */
	public static LrcView lrcView;

	@Override
	public void onAttached() {
	}

	@Override
	public void onDeatch() {

	}

	@Override
	public int getLayoutView() {
		return R.layout.fragment_music_lrc;
	}

	@Override
	public void onViewCreate(View view, Bundle savedInstanceState) {

		initView(view);

		mhandler.setListener(this);

	}

	/**
	 * 初始化布局
	 */
	private void initView(View view) {
		lrcView = (LrcView) view.findViewById(R.id.music_lrc);
	}

	@Override
	protected void getservciesData() {

	}

	@Override
	public void handlerMessage(Message msg) {

		if (ConstantValue.TYPE_MSG_MUSIC == msg.arg1) {// 消息类型

		}

	}

}
