package com.agricluture.utils;

import android.util.Log;

import com.blankj.utilcode.util.ObjectUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * gosn工具类
 * Created by zem
 * on 2018/12/6 .
 */

public class GsonUtil {
	private static String TAG = "GsonUtil";
	private static Gson gson = new Gson();

	public static Gson getGson() {
		if (gson == null) {
			gson = new Gson();
		}
		return gson;
	}

	/**
	 * @Description: TODO(json 转对象)
	 */
	public static Object json2Object(String jsonStr, Class<?> cl) {
		Object obj = null;
		try {
			obj = gson.fromJson(jsonStr, cl);
		} catch (JsonSyntaxException e) {
			Log.e(TAG, "------json  is   wrong----------");
		}
		return obj;
	}

	/**
	 * @Description: TODO(json to list)
	 */
	public static List<?> json2List(String jsonStr, java.lang.reflect.Type type) {
		List<?> objList = null;
		try {
			objList = gson.fromJson(jsonStr, type);
		} catch (JsonSyntaxException e) {
			Log.e(TAG, "------json  is   wrong----------" + e + "===" + e.getMessage());
		}
		return objList;

	}

	/**
	 * 
	 * @Description: 将json格式转换成map对象
	 */
	public static Map<?, ?> jsonToMap(String jsonStr, java.lang.reflect.Type type) {
		Map<?, ?> objMap = null;
		try {
			objMap = gson.fromJson(jsonStr, type);
		} catch (Exception e) {
			Log.e(TAG, "------json  is   wrong----------");
		}
		return objMap;
	}

	/**
	 * 将json格式转换成map对象
	 * 
	 * @param jsonStr
	 * @return
	 */
	public static Map<?, ?> jsonToMap(String jsonStr) {
		Map<?, ?> objMap = null;
		try {
			java.lang.reflect.Type type = new com.google.gson.reflect.TypeToken<Map<?, ?>>() {
			}.getType();
			objMap = gson.fromJson(jsonStr, type);
		} catch (Exception e) {
			Log.e(TAG, "------json  is   wrong----------");
		}
		return objMap;
	}

	/**
	 * 将对象转化为Json
	 * 
	 * @return String
	 */
	public static <T> String objectToJson(Object object) {
		String jsonStr = gson.toJson(object);
		return jsonStr;
	}
	public static <T> List<T> getRealListFromT(Object ob, Class<T[]> type) {
		if(ObjectUtils.isEmpty(ob)){
			return new ArrayList(Arrays.asList());
		}
		Gson gson = new Gson();
		String json= gson.toJson(ob);
		T[] list = new Gson().fromJson(json, type);
		return new ArrayList(Arrays.asList(list));
	}


	public static <T> T getRealBeanFromT(Object ob, Class<T> type) {
		Gson gson = new Gson();
		String json= gson.toJson(ob);
		T bean = new Gson().fromJson(json, type);
		return bean;
	}
}
