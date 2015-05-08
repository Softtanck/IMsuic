package com.softtanck.imusic.fragment;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;

import com.softtanck.imusic.BaseFragment;
import com.softtanck.imusic.R;
import com.softtanck.imusic.adapter.LocalMusicAdapter;
import com.softtanck.imusic.bean.Music;
import com.softtanck.imusic.thirdpart.ActionSlideExpandableListView;
import com.softtanck.imusic.thirdpart.ActionSlideExpandableListView.OnActionClickListener;
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
public class LocalMusicFragment extends BaseFragment implements OnActionClickListener {

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
	private List<Music> list;

	/**
	 * 四个展开按钮
	 */
	private int[] buttonIds = { R.id.tv_local_music_ls, R.id.tv_local_music_add, R.id.tv_local_music_delete, R.id.tv_local_music_info };

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
	}

	/**
	 * 初始化布局
	 */
	private void initView(View view) {
		listView = (ActionSlideExpandableListView) view.findViewById(R.id.lv_local_music);
		list = new ArrayList<Music>();
		for (int i = 0; i < 10; i++) {
			Music music = new Music("尊严");
			list.add(music);
		}
		adapter = new LocalMusicAdapter(context, list);
		listView.setAdapter(adapter);
		listView.setItemActionListener(this, buttonIds);
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

}
