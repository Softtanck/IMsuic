package com.softtanck.imusic.ui;

import android.graphics.Color;
import android.widget.ListView;

import com.softtanck.imusic.BaseActivity;
import com.softtanck.imusic.ConstantValue;
import com.softtanck.imusic.R;
import com.softtanck.imusic.adapter.PlayQueueAdapter;

/**
 * 
 * @Description TODO 播放队列
 * 
 * @author Tanck
 * 
 * @date May 27, 2015 5:25:54 PM
 * 
 */
public class PlayQueueActivity extends BaseActivity {

	/**
	 * 播放队列
	 * 
	 */
	private ListView mlListView;

	/**
	 * 播放队列适配器
	 */
	private PlayQueueAdapter adapter;

	@Override
	protected int getViewId() {
		return R.layout.activity_play_queue;
	}

	@Override
	protected void onActivityCreate() {

		initTitleView();

		initView();
	}

	/**
	 * 设置标题
	 */
	private void initTitleView() {
		titleView.removeAllMenu();
		titleView.addLeftDrawableMenu(context, R.drawable.btn_back, 15, 25, null);
		titleView.setTitle("当前播放队列:");
		titleView.setTileTextColor(Color.WHITE);
		titleView.addRightDrawableMenu(context, R.drawable.btn_right_more, 20, 5, null);
	}

	/**
	 * 初始化View
	 */
	private void initView() {
		mlListView = (ListView) findViewById(R.id.music_lrc_lv_paly_queue);
		adapter = new PlayQueueAdapter(context, ConstantValue.mlocalMusics);
		mlListView.setAdapter(adapter);
	}

	@Override
	public void onDestroyed() {
	}

}
