package com.softtanck.imusic.fragment;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.softtanck.imusic.BaseFragment;
import com.softtanck.imusic.ConstantValue;
import com.softtanck.imusic.R;
import com.softtanck.imusic.adapter.HomeContentAdapter;
import com.softtanck.imusic.utils.BaseUtils;

/**
 * 
 * @Description TODO 主界面布局
 * 
 * @author Tanck
 * 
 * @date Apr 16, 2015 10:49:57 AM
 * 
 */
public class HomeFragment extends BaseFragment implements OnPageChangeListener {

	/**
	 * 主页滑动切换
	 */
	private ViewPager mPager;

	/**
	 * 主页页面集合
	 */
	private List<Fragment> mList;

	/**
	 * 中间容器
	 */
	private Fragment middleFragment;

	/**
	 * 主页页面适配器
	 */
	private HomeContentAdapter hadapter;

	/**
	 * 我的标题
	 */
	private TextView myTtile;

	/**
	 * 推荐标题
	 */
	private TextView recommedTtile;

	/**
	 * 曲库标题
	 */
	private TextView songTtile;

	/**
	 * 搜索标题
	 */
	private TextView serachTtile;

	/**
	 * 标题颜色
	 */
	private int titlecolor;

	/**
	 * 标题指示器
	 */
	private ImageView tiltleStrip;

	/**
	 * 指示器偏移
	 */
	private int offset;

	/**
	 * 当前标题位置
	 */
	private int currentPosition;

	/**
	 * 退出时间
	 */
	private long currTime = 0;

	/**
	 * 标题总共
	 */
	private int total;

	@Override
	public void onAttached() {

	}

	@Override
	public void onDeatch() {

	}

	@Override
	public int getLayoutView() {
		return R.layout.fragment_home;
	}

	@Override
	public void onViewCreate(View view, Bundle savedInstanceState) {
		initView(view);
	}

	/**
	 * 初始化布局
	 */
	private void initView(View view) {

		mPager = (ViewPager) view.findViewById(R.id.home_content_pager);

		titlecolor = getResources().getColor(R.color.common_title_background);

		initTitle(view);

		initContentView();

	}

	/**
	 * 初始化主页标题
	 */
	private void initTitle(View view) {
		tiltleStrip = (ImageView) view.findViewById(R.id.home_iv_category_selector);
		myTtile = (TextView) view.findViewById(R.id.home_tv_my_tilte);
		recommedTtile = (TextView) view.findViewById(R.id.home_tv_recommed_title);
		songTtile = (TextView) view.findViewById(R.id.home_tv_song_title);
		serachTtile = (TextView) view.findViewById(R.id.home_tv_serach_title);
		myTtile.setTextColor(titlecolor);
		// 首先计算出图片的宽度
		Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.home_category_selector);
		// 计算偏移
		offset = (ConstantValue.WINDOW_WIDTH / 4 - bitmap.getWidth()) / 2;
		// 设置图片的位置,向右偏移
		Matrix matrix = new Matrix();
		// padding 10dip/2
		matrix.setTranslate(offset + BaseUtils.dip(context, 5), 0);
		tiltleStrip.setImageMatrix(matrix);
	}

	/**
	 * 初始化主页面内容布局
	 */
	private void initContentView() {
		mList = new ArrayList<Fragment>();
		middleFragment = new MyMusicFragment();
		mList.add(middleFragment);
		middleFragment = new RecommendFragment();
		mList.add(middleFragment);
		middleFragment = new SongFragment();
		mList.add(middleFragment);
		middleFragment = new SearchSongFragment();
		mList.add(middleFragment);
		// 适配布局
		hadapter = new HomeContentAdapter(holder.fragmentManager, mList);
		mPager.setAdapter(hadapter);
		mPager.setOffscreenPageLimit(4);
		mPager.setOnPageChangeListener(this);
	}

	@Override
	protected void getservciesData() {

	}

	@Override
	public void onPageScrollStateChanged(int arg0) {

	}

	@Override
	public void onPageScrolled(int position, float currentOffset, int arg2) {
		// 初始位置偏移
		int moffset = 10;
		total = position * (ConstantValue.WINDOW_WIDTH - BaseUtils.dip(context, moffset)) / 4;
		// 位置
		currentPosition = (int) (-currentOffset * ((ConstantValue.WINDOW_WIDTH - BaseUtils.dip(context, moffset)) / 4) - total);
		tiltleStrip.scrollTo(currentPosition, 0);
	}

	@Override
	public void onPageSelected(int position) {
		// 设置标题颜色
		switch (position) {
		case 0:
			myTtile.setTextColor(titlecolor);
			setTextColor(recommedTtile, Color.WHITE);
			setTextColor(songTtile, Color.WHITE);
			setTextColor(serachTtile, Color.WHITE);
			break;
		case 1:
			recommedTtile.setTextColor(titlecolor);
			setTextColor(myTtile, Color.WHITE);
			setTextColor(songTtile, Color.WHITE);
			setTextColor(serachTtile, Color.WHITE);
			break;
		case 2:
			songTtile.setTextColor(titlecolor);
			setTextColor(myTtile, Color.WHITE);
			setTextColor(recommedTtile, Color.WHITE);
			setTextColor(serachTtile, Color.WHITE);
			break;
		case 3:
			serachTtile.setTextColor(titlecolor);
			setTextColor(myTtile, Color.WHITE);
			setTextColor(recommedTtile, Color.WHITE);
			setTextColor(songTtile, Color.WHITE);
			break;
		default:
			myTtile.setTextColor(titlecolor);
			setTextColor(recommedTtile, Color.WHITE);
			setTextColor(songTtile, Color.WHITE);
			setTextColor(serachTtile, Color.WHITE);
			break;
		}
	}

	/**
	 * 设置字体颜色
	 * 
	 * @param view
	 * @param color
	 */
	private void setTextColor(TextView view, int color) {
		view.setTextColor(color);
	}

}
