package com.softtanck.imusic.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import com.softtanck.imusic.ConstantValue;
import com.softtanck.imusic.bean.Music;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.TypedValue;

public class BaseUtils {

	public static Map<String, String> initMap(Object... params) {
		if (params == null || params.length < 1 || params.length % 2 != 0)
			return null;
		Map<String, String> map = new HashMap<String, String>();
		for (int i = 0; i < params.length; i = i + 2)
			map.put(String.valueOf(params[i]), String.valueOf(params[i + 1]));
		return map;
	}

	public static Bundle initBundle(Object... params) {
		if (params == null || params.length < 1 || params.length % 2 != 0)
			return null;
		Bundle bundle = new Bundle();
		for (int i = 0; i < params.length; i = i + 2) {
			if (params[i + 1] instanceof String) {
				bundle.putString(String.valueOf(params[i]), String.valueOf(params[i + 1]));
			} else if (params[i + 1] instanceof Integer) {
				bundle.putInt(String.valueOf(params[i]), Integer.valueOf(params[i + 1].toString()));
			} else if (params[i + 1] instanceof Float) {
				bundle.putFloat(String.valueOf(params[i]), Float.valueOf(params[i + 1].toString()));
			} else if (params[i + 1] instanceof Double) {
				bundle.putDouble(String.valueOf(params[i]), Double.valueOf(params[i + 1].toString()));
			} else if (params[i + 1] instanceof Long) {
				bundle.putFloat(String.valueOf(params[i]), Long.valueOf(params[i + 1].toString()));
			} else if (params[i + 1] instanceof Short) {
				bundle.putShort(String.valueOf(params[i]), Short.valueOf(params[i + 1].toString()));
			} else if (params[i + 1] instanceof Byte) {
				bundle.putByte(String.valueOf(params[i]), Byte.valueOf(params[i + 1].toString()));
			} else if (params[i + 1] instanceof Boolean) {
				bundle.putBoolean(String.valueOf(params[i]), Boolean.valueOf(params[i + 1].toString()));
			} else {
				throw new RuntimeException("param should be base type");
			}
		}
		return bundle;
	}

	public static boolean hasSDCard() {
		return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
	}

	public static String inputStreamToString(InputStream in) {
		InputStreamReader ir = null;
		BufferedReader br = null;
		StringBuffer sb = new StringBuffer();
		String result = null;
		try {
			ir = new InputStreamReader(in);
			br = new BufferedReader(ir);
			String line;
			while ((line = br.readLine()) != null) {
				sb.append("\n").append(line);
			}
			if (sb.length() > 0)
				result = sb.substring("\n".length());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ir != null)
					ir.close();
				if (br != null)
					br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public static boolean inputStreamToOutputStream(InputStream input, OutputStream output) {
		BufferedOutputStream out = null;
		BufferedInputStream in = null;
		in = new BufferedInputStream(input, 16 * 1024);
		out = new BufferedOutputStream(output, 16 * 1024);
		int b;
		try {
			while ((b = in.read()) != -1) {
				out.write(b);
			}
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null)
					out.close();
				if (in != null)
					in.close();
			} catch (final IOException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public static InputStream bitmapToInputStream(Bitmap bitmap) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
		InputStream sbs = new ByteArrayInputStream(baos.toByteArray());
		return sbs;
	}

	public static InputStream byteToInputStream(byte[] bytes) {
		ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
		return bais;
	}

	public static Drawable bitmapToDrawable(Resources res, Bitmap bitmap) {
		return new BitmapDrawable(res, bitmap);
	}

	public static Bitmap drawableToBitmap(Drawable drawable) {
		Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(),
				drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
		Canvas canvas = new Canvas(bitmap);
		drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
		drawable.draw(canvas);
		return bitmap;
	}

	public static int dip(Context context, int value) {
		DisplayMetrics dm = context.getResources().getDisplayMetrics();
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, dm);
	}

	public static int sp(Context context, int value) {
		DisplayMetrics dm = context.getResources().getDisplayMetrics();
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, value, dm);
	}

	public static int getAppVersion(Context context) {
		try {
			PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
			return info.versionCode;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return 1;
	}

	public static boolean bitmapToFile(Bitmap bitmap, String filePath) {
		boolean isSuccess = false;
		if (bitmap == null) {
			return isSuccess;
		}
		OutputStream out = null;
		try {
			out = new BufferedOutputStream(new FileOutputStream(filePath), 8 * 1024);
			isSuccess = bitmap.compress(CompressFormat.PNG, 70, out);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (out != null)
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return isSuccess;
	}

	public static InputStream getStringStream(String sInputString) {
		if (sInputString != null && !sInputString.trim().equals("")) {
			try {
				ByteArrayInputStream tInputStringStream = new ByteArrayInputStream(sInputString.getBytes());
				return tInputStringStream;
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}

	public static boolean isExists(String path) {
		File file = new File(path);
		if (file.exists())
			return true;
		return false;
	}

	/**
	 * 计算对象在音乐中的位置
	 * 
	 * @param music
	 * @return
	 */
	public static int calcInMusicPosition(Music music) {
		int positon = ConstantValue.mlocalMusics.indexOf(music);
		if (0 > positon) {
			return 0;
		}
		return positon;
	}

	/**
	 * 根据当前的music计算下一个位置
	 * 
	 * @param music
	 *            当前的对象
	 * @return
	 */
	public static Music calcInMusicByMusicNextMusic(Music music) {
		if (null == music)
			return ConstantValue.mlocalMusics.get(0);
		int positon = calcInMusicPosition(music);
		if (positon < ConstantValue.mlocalMusics.size() - 1) {
			++positon;
		} else {
			positon = 0;
		}
		return ConstantValue.mlocalMusics.get(positon);
	}

	/**
	 * 根据当前的位置计算下一个位置
	 * 
	 * @return
	 */
	public static Music calcInMusicByMusicNextMusic() {
		int positon = 0;
		if (ConstantValue.currentMusicPostion <= ConstantValue.mlocalMusics.size() - 1) {
			positon = ConstantValue.currentMusicPostion;
		}
		return ConstantValue.mlocalMusics.get(positon);
	}

	/**
	 * 根据当前位置计算上一个位置
	 * 
	 * @param music
	 * @return
	 */
	public static Music calcInMusicByPre(Music music) {
		if (null == music)
			return ConstantValue.mlocalMusics.get(0);
		int positon = calcInMusicPosition(music);
		if (positon > 0) {
			--positon;
		} else {
			positon = ConstantValue.mlocalMusics.size() - 1;
		}
		return ConstantValue.mlocalMusics.get(positon);
	}

}
