package com.softtanck.imusic.ui;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.softtanck.imusic.ActivityContainer;
import com.softtanck.imusic.BaseActivity;
import com.softtanck.imusic.R;
import com.softtanck.imusic.fragment.HomeFragment;
import com.softtanck.imusic.view.RoundedCornerImageView;

/**
 * 
 * @Description TODO 播放器主界面
 * 
 * @author Tanck
 * 
 * @date Apr 14, 2015 8:20:31 PM
 * 
 */
public class HomeActivity extends BaseActivity {

	/**
	 * 退出时间
	 */
	private long currTime;

	/**
	 * 音乐头像:主界面
	 */
	private RoundedCornerImageView songHead;
	
	private ImageView imageView;

	@Override
	protected int getViewId() {
		return R.layout.activity_main;
	}

	@Override
	public void onDestroyed() {

	}

	@Override
	protected void onActivityCreate() {
		initView();

		songHead = (RoundedCornerImageView) findViewById(R.id.home_iv_now_play_song);

		songHead.setOnClickListener(this);
		
		imageView = (ImageView) findViewById(R.id.iv_test);
		imageView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				imageView.startAnimation(AnimationUtils.loadAnimation(context, R.anim.icon_translate));
			}
		});
	}

	/**
	 * 初始化布局
	 */
	private void initView() {
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		middleFragment = new HomeFragment();
		transaction.add(R.id.home_content, middleFragment);
		transaction.commit();
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.home_iv_now_play_song:// 主界面音乐头像
			Intent music = new Intent(HomeActivity.this, MusicActivity.class);
			startActivity(music);
			break;
		}
	}

	@Override
	public void onBackPressed() {
		if (fragmentManager.popBackStackImmediate())
			return;
		if ((System.currentTimeMillis() - currTime) > 2000) {
			showToast("再按一次,退出应用");
			currTime = System.currentTimeMillis();
			return;
		} else {
			ActivityContainer.finishAll();
		}
	}

}
