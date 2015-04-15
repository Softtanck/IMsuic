package com.softtanck.imusic.ui;

import java.util.ArrayList;
import java.util.List;

import android.R.integer;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.support.v4.app.Fragment;
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
public class HomeActivity extends BaseActivity implements OnPageChangeListener {

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

		mPager = (ViewPager) findViewById(R.id.home_content_pager);

		titlecolor = getResources().getColor(R.color.common_color);

		initTitle();

		initContentView();

	}

	/**
	 * 初始化主页标题
	 */
	private void initTitle() {
		tiltleStrip = (ImageView) findViewById(R.id.home_iv_category_selector);
		myTtile = (TextView) findViewById(R.id.home_tv_my_tilte);
		recommedTtile = (TextView) findViewById(R.id.home_tv_recommed_title);
		songTtile = (TextView) findViewById(R.id.home_tv_song_title);
		serachTtile = (TextView) findViewById(R.id.home_tv_serach_title);
		myTtile.setTextColor(titlecolor);
		// 首先计算出图片的宽度
		Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
				R.drawable.home_category_selector);
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
		hadapter = new HomeContentAdapter(fragmentManager, mList);
		mPager.setAdapter(hadapter);
		mPager.setOffscreenPageLimit(4);
		mPager.setOnPageChangeListener(this);
	}

	@Override
	public void onDestroyed() {

	}

	@Override
	public void onPageScrollStateChanged(int position) {
	}

	@Override
	public void onPageScrolled(int position, float currentOffset,
			int currentpxOffset) {

		// 位置偏移结束
		if (0 == currentpxOffset) {
			return;
		}
		total = position
				* (ConstantValue.WINDOW_WIDTH - BaseUtils.dip(context, 10)) / 4;
		// 位置
		currentPosition = (int) (-currentOffset
				* ((ConstantValue.WINDOW_WIDTH - BaseUtils.dip(context, 10)) / 4) - total);
		tiltleStrip.scrollTo(currentPosition, 0);
	}

	@Override
	public void onPageSelected(int position) {
	}

	@Override
	public void onBackPressed() {
		if ((System.currentTimeMillis() - currTime) > 2000) {
			showToast("再按一次,退出应用");
			currTime = System.currentTimeMillis();
			return;
		} else {
			// 退出程序前判断是否登录 若登录则：保存用户ID和TOKEN
			ActivityContainer.finishAll();
		}
	}
}
