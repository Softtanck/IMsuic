package com.softtanck.imusic.fragment;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;

import com.softtanck.imusic.BaseFragment;
import com.softtanck.imusic.ConstantValue;
import com.softtanck.imusic.R;
import com.softtanck.imusic.adapter.LocalMusicAdapter;
import com.softtanck.imusic.anim.PlayMusicAnim;
import com.softtanck.imusic.bean.Music;
import com.softtanck.imusic.bean.PlayMsg;
import com.softtanck.imusic.service.meessage.HandlerMessageListener;
import com.softtanck.imusic.service.meessage.MyHandler;
import com.softtanck.imusic.thirdpart.ActionSlideExpandableListView;
import com.softtanck.imusic.thirdpart.ActionSlideExpandableListView.OnActionClickListener;
import com.softtanck.imusic.ui.HomeActivity;
import com.softtanck.imusic.utils.LogUtils;
import com.softtanck.imusic.utils.MusicUtil;

/**
 * 
 * @Description TODO 本地歌曲
 * 
 * @author Tanck
 * 
 * @date Apr 16, 2015 3:55:15 PM
 * 
 */
public class LocalMusicFragment extends BaseFragment implements OnActionClickListener, OnItemClickListener, HandlerMessageListener {

	/**
	 * 本地歌曲
	 */
	private ActionSlideExpandableListView listView;

	/**
	 * 本地音乐适配器
	 */
	private LocalMusicAdapter adapter;

	/**
	 * 音乐集合
	 */
	private List<Music> mMusiclist;

	/**
	 * 四个展开按钮
	 */
	private int[] buttonIds = { R.id.tv_local_music_ls, R.id.tv_local_music_add, R.id.tv_local_music_delete, R.id.tv_local_music_info };

	/**
	 * 播放消息
	 */
	protected PlayMsg msg;

	/**
	 * 绑定消息
	 */
	private Bundle mbBundle;

	@Override
	public void handlerMessage(Message msg) {
		if (ConstantValue.TYPE_MSG_MUSIC == msg.arg1) {// 为音乐类型
			if (ConstantValue.CURRENT_TAG == msg.what) {// 特定handler
				// 开始更新UI
				if (ConstantValue.MUSIC_CURRENT_STATE == ConstantValue.MUSIC_STATE_PLAYING) {
					HomeActivity.mplay_pause.setImageResource(R.drawable.music_pause_selector);
				}

				mbBundle = msg.getData();

				// 更新歌曲信息
				if (null != mbBundle) {
					Music music = (Music) mbBundle.get(ConstantValue.MUSIC_CURRENT_OBJECT);
					HomeActivity.msongName.setText(music.getTitle());
					HomeActivity.msongSinger.setText(music.getSinger());
				}

			}
		}
	}

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

		initView(view);

		mhandler.setListener(this);

	}

	/**
	 * 初始化布局
	 */
	private void initView(View view) {
		listView = (ActionSlideExpandableListView) view.findViewById(R.id.lv_local_music);
		// 耗时操作.
		mMusiclist = MusicUtil.getAllMusic(context);
		if (null == mMusiclist) {
			holder.showToast("当前没有本地音乐");
			return;
		}
		ConstantValue.mlocalMusics = mMusiclist;
		adapter = new LocalMusicAdapter(context, mMusiclist);
		listView.setAdapter(adapter);
		listView.setItemActionListener(this, buttonIds);
		listView.setOnItemClickListener(this);
	}

	@Override
	protected void getservciesData() {
		LogUtils.d("getservciesData");
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
	}

	@Override
	public void onClick(View itemView, View clickedView, int position) {
		switch (clickedView.getId()) {
		case R.id.tv_local_music_add:// 添加

			LogUtils.d("add");
			break;
		case R.id.tv_local_music_delete:// 删除
			LogUtils.d("delete");
			break;
		case R.id.tv_local_music_info:// 详情
			LogUtils.d("info");
			break;
		case R.id.tv_local_music_ls:// 铃声
			LogUtils.d("ls");
			break;

		default:
			// Other
			break;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		LogUtils.d("onItemClick-->" + position);
		// 如果为当前播放的,让当前的item回去
		if (ConstantValue.MUSIC_CURRENT_STATE == ConstantValue.MUSIC_STATE_PLAYING && ConstantValue.currentMusicPostion == position)
			return;
		int[] startLocation = new int[2];
		view.getLocationInWindow(startLocation);
		// 先设置监听
		PlayMusicAnim.setListener(new OnPlayAnimListener() {

			@Override
			public void OnAnimStarted(Music music) {
			}

			@Override
			public void OnAnimEnded(Music music) {
				// 设置标志
				ConstantValue.CURRENT_TAG = music.hashCode();
				msg = new PlayMsg(music, ConstantValue.MSG_PLAY, ConstantValue.TYPE_MSG_MUSIC);
				HomeActivity.mService.MusicCoreService(msg);
			}
		});
		PlayMusicAnim.setAnim(holder, mMusiclist.get(position), HomeActivity.songHead, startLocation);
	}

}
