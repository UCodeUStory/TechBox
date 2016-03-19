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
import java.util.List;


public class JsonUtils {


	public static void main(String[] args){
		//testToJson();
//		testFromJsonStr();
		String jsonStr = "{\"name\":\"mayubao\",\"age\":\"10\",\"height\":170}";
		String jsonStr2 = "[{\"name\":\"mayubao\",\"age\":\"10\",\"height\":170},{\"name\":\"mayubao\",\"age\":\"20\",\"height\":160}]";
		/**
		 * 初次看到第一行代码的时候感觉非常怪异，很奇怪为什么需要设计这样的API。
		 * 仔细思索之后，发现设计者真实的目的其实非常简单，只是为了提取泛型的类型，
		 * 原因是Java的泛型对象的类型不能直接通过类型本身获取到，
		 * 比如类似List<String>.class的代码是无法通过编译，原因和Java泛型的实现机制有关系。
		 */
		//普通类型可以直接通过.class 获取类型
		TypeToken<UserInfo> type = new TypeToken<UserInfo>(){};
		UserInfo userInfo = JsonUtils.jsonToObject(jsonStr, type);
		System.out.println(userInfo.name);
		System.out.println(userInfo.age);
		System.out.println(userInfo.height);

        //带泛型的用下面方法获得
		TypeToken<List<UserInfo>> listTypeToken = new TypeToken<List<UserInfo>>(){};
		List<UserInfo> userInfoList = JsonUtils.jsonToObject(jsonStr2,listTypeToken);
		System.out.println("userInfoList-size= "+userInfoList.size());
		for(int i=0;i<userInfoList.size();i++){
			System.out.println("" + userInfoList.get(i).name);
			System.out.println("" + userInfoList.get(i).age);
			System.out.println("" + userInfoList.get(i).height);
		}
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
