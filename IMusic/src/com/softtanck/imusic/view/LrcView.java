package com.softtanck.imusic.view;

import java.util.ArrayList;
import java.util.List;

import com.softtanck.imusic.ConstantValue;
import com.softtanck.imusic.bean.LrcContent;
import com.softtanck.imusic.utils.LogUtils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.Shader.TileMode;
import android.graphics.Typeface;
import android.util.AttributeSet;

/**
 * 自定义绘画歌词，产生滚动效果,歌词渐变,切换动画
 * 
 * @author Tanck
 * 
 */
public class LrcView extends android.widget.TextView {
	private float width; // 歌词视图宽度
	private float height; // 歌词视图高度
	private Paint currentPaint; // 当前画笔对象
	private Paint notCurrentPaint; // 非当前画笔对象
	private float textSize = 50; // 文本大小
	private float textHeight = textSize + 10; // 文本间距高度
	private int textColorGradient = 30;// 歌词文本上下颜色渐变
	private int textColorDefault = 255;// 歌词上下颜色默认
	private int index = 0; // list集合下标

	private List<LrcContent> mLrcList = new ArrayList<LrcContent>();

	public void setmLrcList(List<LrcContent> mLrcList) {
		this.mLrcList = mLrcList;
	}

	public LrcView(Context context) {
		super(context);
		init();
	}

	public LrcView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public LrcView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	private void init() {
		setFocusable(true); // 设置可对焦

		// 高亮部分
		currentPaint = new Paint();
		currentPaint.setAntiAlias(true); // 设置抗锯齿，让文字美观饱满
		currentPaint.setTextAlign(Paint.Align.CENTER);// 设置文本对齐方式

		// 非高亮部分
		notCurrentPaint = new Paint();
		notCurrentPaint.setAntiAlias(true);
		notCurrentPaint.setTextAlign(Paint.Align.CENTER);

		currentPaint.setColor(Color.argb(210, 251, 248, 29));

		currentPaint.setTextSize(textSize);
		currentPaint.setTypeface(Typeface.SANS_SERIF);

		notCurrentPaint.setTextSize(textSize - 10);
		notCurrentPaint.setTypeface(Typeface.SANS_SERIF);
	}

	/**
	 * 绘画歌词
	 */
	@SuppressLint("DrawAllocation")
	@Override
	protected void onDraw(Canvas canvas) {
		if (canvas == null) {
			return;
		}
		try {
			canvas.drawText(mLrcList.get(index).getLrcStr(), width / 2,
					height / 2, currentPaint);
			float tempY = height / 2;
			// 画出本句之前的句子
			for (int i = index - 1; i >= 0; i--) {
				// 向上推移
				tempY = tempY - textHeight;
				CalcLrcAlpha(index - i);// 底部渐变
				canvas.drawText(mLrcList.get(i).getLrcStr(), width / 2, tempY,
						notCurrentPaint);
			}
			tempY = height / 2;
			// 画出本句之后的句子
			for (int i = index + 1; i < mLrcList.size(); i++) {
				// 往下推移
				tempY = tempY + textHeight;
				CalcLrcAlpha(i - index);// 底部渐变
				canvas.drawText(mLrcList.get(i).getLrcStr(), width / 2, tempY,
						notCurrentPaint);
			}
		} catch (Exception e) {
			canvas.drawText("没有找到歌词,赶紧去下载", width / 2, height / 2, currentPaint);
		}
	}

	/**
	 * 当view大小改变的时候调用的方法
	 */
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		this.width = w;
		this.height = h;
	}

	/**
	 * 设置歌词索引
	 * 
	 * @param index
	 */
	public void setIndex(int index) {
		this.index = index;
	}

	/**
	 * 计算颜色渐变
	 * 
	 * @param i
	 *            特征
	 */
	private void CalcLrcAlpha(int i) {
		int tempAlpha = 0;
		// 歌词渐变
		tempAlpha = textColorDefault - i * textColorGradient;
		if (0 > tempAlpha)
			tempAlpha = 0;
		notCurrentPaint.setColor(Color.argb(tempAlpha, 255, 255, 255));
	}
}
