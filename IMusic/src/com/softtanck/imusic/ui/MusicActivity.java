package com.softtanck.imusic.ui;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.widget.LinearLayout;

import com.softtanck.imusic.BaseActivity;
import com.softtanck.imusic.R;
import com.softtanck.imusic.adapter.HomeContentAdapter;
import com.softtanck.imusic.background.GetBackground;
import com.softtanck.imusic.fragment.LrcFragment;
import com.softtanck.imusic.fragment.MyMusicFragment;
import com.softtanck.imusic.fragment.RecommendFragment;
import com.softtanck.imusic.fragment.SearchSongFragment;
import com.softtanck.imusic.fragment.SongFragment;
import com.softtanck.imusic.fragment.SongInfoFragment;

/**
 * 
 * @Description TODO 音乐播放器主界面,用来显示歌词等信息
 * 
 * @author Tanck
 * 
 * @date Apr 16, 2015 4:24:17 PM
 * 
 */
public class MusicActivity extends BaseActivity implements OnPageChangeListener {

	/**
	 * 播放界面背景
	 */
	private LinearLayout background;

	/**
	 * 播放界面布局:暂定三个
	 */
	private ViewPager musicPager;

	/**
	 * 歌曲的页面集合
	 */
	private List<Fragment> mList;

	private HomeContentAdapter hadapter;

	@Override
	protected int getViewId() {
		return R.layout.activity_music;
	}

	@Override
	public void onDestroyed() {

	}

	@Override
	protected void onActivityCreate() {

		background = (LinearLayout) findViewById(R.id.music_bg);
		musicPager = (ViewPager) findViewById(R.id.music_content);
		initContentView();
		// 加载毛玻璃
		new GetBackground(background, context).execute(R.drawable.tmp_head, 25);

	}

	/**
	 * 初始化主页面内容布局
	 */
	private void initContentView() {
		mList = new ArrayList<Fragment>();
		middleFragment = new LrcFragment();
		mList.add(middleFragment);
		middleFragment = new SongInfoFragment();
		mList.add(middleFragment);
		// 适配布局
		hadapter = new HomeContentAdapter(fragmentManager, mList);
		musicPager.setAdapter(hadapter);
		musicPager.setOffscreenPageLimit(4);
		musicPager.setOnPageChangeListener(this);
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageSelected(int arg0) {
		// TODO Auto-generated method stub

	}

}
