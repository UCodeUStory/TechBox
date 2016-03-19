package com.ustory.techbox.utils;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.ustory.techbox.bean.UserInfo;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;


public class JsonUtils {


	public static void main(String[] args){
		//testToJson();
//		testFromJsonStr();
		String jsonStr = "{\"name\":\"mayubao\",\"age\":\"10\",\"height\":170}";
		
		TypeToken<UserInfo> type = new TypeToken<UserInfo>(){};

		UserInfo userInfo = JsonUtils.jsonToObject(jsonStr, type);
		System.out.println(userInfo.name);
		System.out.println(userInfo.age);
		System.out.println(userInfo.height); 
	}



	private static void testFromJsonStr() {
		Gson gson = new Gson();
		UserInfo userInfo = gson.fromJson("{\"name\":\"mayubao\",\"age\":\"10\",\"height\":170}", UserInfo.class);
		System.out.println(userInfo.name);
		System.out.println(userInfo.age);
		System.out.println(userInfo.height);
	}

	private static void testToJson() {
		Gson gson = new Gson();
		UserInfo userInfo = new UserInfo();
		userInfo.name = "mayubao";
		userInfo.age = "10";
		userInfo.height = 170;
		String userJsonStr = gson.toJson(userInfo);
		System.out.println(userJsonStr);
	}
	
	private static Gson gson = new Gson();
	
	/**
	 * 转换对象成json字符串格?
	 * @param  srcObj 被转换的对象
	 * @return json 字符?
	 */
	public static String objectToJson(Object srcObj){
		String jsonStr = "";
		try {
			jsonStr = gson.toJson(srcObj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonStr;
	}
	/**
	 * 把json串转化成对象
	 * @param jsonStr json
	 * @param typeToken 对象类型
	 * @return 目标对象
	 */
	public static <T> T jsonToObject(String jsonStr,TypeToken<T> typeToken) throws JsonSyntaxException {
		Type type = typeToken.getType();
		T result = null;
		try {
			result = gson.fromJson(jsonStr, type);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 利用gson包方法将网络返回的inputstream流转成对象（objClass类
	 * 
	 * @param inStream
	 * @param classType
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	public static <T> T parseObjectFromInputStreamByGson(InputStream inStream,
			Type classType) throws IllegalStateException, IOException {
		Reader reader = new InputStreamReader(inStream);
		JsonReader jReader = new JsonReader(reader);
		return (T)gson.fromJson(jReader, classType);
	}
}
