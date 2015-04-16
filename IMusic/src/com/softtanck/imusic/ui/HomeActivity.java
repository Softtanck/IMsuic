package com.softtanck.imusic.ui;

import java.util.ArrayList;
import java.util.List;

import android.R.integer;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.StaticLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.softtanck.imusic.ActivityContainer;
import com.softtanck.imusic.BaseActivity;
import com.softtanck.imusic.ConstantValue;
import com.softtanck.imusic.R;
import com.softtanck.imusic.adapter.HomeContentAdapter;
import com.softtanck.imusic.fragment.HomeFragment;
import com.softtanck.imusic.fragment.MyMusicFragment;
import com.softtanck.imusic.fragment.RecommendFragment;
import com.softtanck.imusic.fragment.SearchSongFragment;
import com.softtanck.imusic.fragment.SongFragment;
import com.softtanck.imusic.utils.BaseUtils;
import com.softtanck.imusic.utils.LogUtils;

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

	@Override
	protected int getViewId() {
		return R.layout.activity_main;
	}

	@Override
	protected void onActivityCreate() {
		initView();
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
	public void onDestroyed() {

	}

	@Override
	public void onBackPressed() {
		if ((System.currentTimeMillis() - currTime) > 2000) {
			showToast("再按一次,退出应用");
			currTime = System.currentTimeMillis();
			return;
		} else {
			ActivityContainer.finishAll();
		}
	}

}
