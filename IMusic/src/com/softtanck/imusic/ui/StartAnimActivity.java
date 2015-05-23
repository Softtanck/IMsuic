package com.softtanck.imusic.ui;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.softtanck.imusic.R;
import com.softtanck.imusic.adapter.GuidePageAdapter;

/**
 * 
 * @Description TODO 引导界面
 * 
 * @author Tanck
 * 
 * @date May 23, 2015 3:52:03 PM
 * 
 */
public class StartAnimActivity extends Activity implements OnClickListener {
	private ViewPager viewPager;
	private List<View> pageViews;
	private ImageView dians;
	private View go;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		LayoutInflater inflater = getLayoutInflater();
		pageViews = new ArrayList<View>();

		pageViews.add(inflater.inflate(R.layout.guide_item_01, null));
		pageViews.add(inflater.inflate(R.layout.guide_item_02, null));
		pageViews.add(inflater.inflate(R.layout.guide_item_03, null));

		setContentView(R.layout.activity_start_anim);

		go = pageViews.get(pageViews.size() - 1).findViewById(R.id.starthome);
		dians = (ImageView) findViewById(R.id.imgDian);

		go.setOnClickListener(this);
		drawPoints(0);
		viewPager = (ViewPager) findViewById(R.id.awesomepager);
		viewPager.setAdapter(new GuidePageAdapter(pageViews));
		viewPager.setOnPageChangeListener(new GuidePageChangeListener());

	}

	// 指引页面更改事件监听器
	class GuidePageChangeListener implements OnPageChangeListener {

		@Override
		public void onPageScrollStateChanged(int arg0) {

		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		@Override
		public void onPageSelected(int arg0) {
			drawPoints(arg0);
		}
	}

	public void drawPoints(int currentIndex) {
		int radius = 6; // 半径
		int spacing = 40; // 点之间间隔
		Bitmap points = Bitmap.createBitmap(radius * 2 + spacing * (pageViews.size() - 1), radius * 2, Config.ARGB_8888);
		Canvas canvas = new Canvas();
		canvas.setBitmap(points);
		Paint paint = new Paint();
		paint.setAntiAlias(true); // 设置画笔为无锯齿
		paint.setStyle(Style.FILL); // 实心
		for (int i = 0; i < pageViews.size(); i++) {
			paint.setColor(Color.GRAY);
			if (currentIndex == i) // 设置选中项为红色
				paint.setColor(Color.RED);
			canvas.drawCircle(radius + spacing * i, radius, radius, paint);
		}
		dians.setImageBitmap(points);
	}

	@Override
	public void onClick(View view) {

		switch (view.getId()) {
		case R.id.starthome:
			Intent homeActivity = new Intent(StartAnimActivity.this, HomeActivity.class);
			startActivity(homeActivity);
			finish();
			break;
		default:
			break;
		}
	}

}