package com.lilei.play.http.protocal;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.lilei.play.domain.AppInfo;

/**
 * 首页网络数据解析
 * 
 * @author Kevin
 * @date 2015-10-28
 */
public class HomeProtocol extends BaseProtocol<ArrayList<AppInfo>> {

	private ArrayList<String> pictures;

	@Override
	public String getKey() {
		return "home";
	}

	@Override
	public String getParams() {
		return "";// 如果没有参数,就传空串,不要传null
	}

	@Override
	public ArrayList<AppInfo> parseData(String result) {
		// Gson, JsonObject
		// 使用JsonObject解析方式: 如果遇到{},就是JsonObject;如果遇到[], 就是JsonArray

       
		try {
			JSONObject jo = new JSONObject(result);
			JSONArray ja = jo.getJSONArray("list");
			ArrayList<AppInfo> appInfos = new ArrayList<AppInfo>();
			for (int i = 0; i < ja.length(); i++) {
				AppInfo info = new AppInfo();
				JSONObject jo1 = (JSONObject) ja.get(i);
				info.des = jo1.getString("des");
				info.downloadUrl = jo1.getString("downloadUrl");
				info.iconUrl = jo1.getString("iconUrl");
				info.name = jo1.getString("name");
				info.packageName = jo1.getString("packageName");
				info.id = jo1.getInt("id");
				info.size = jo1.getLong("size");
				info.stars = (float) jo1.getDouble("stars");
				appInfos.add(info);
			}
			
			JSONArray ja1 = jo.getJSONArray("picture");
			ArrayList<String> pictures = new ArrayList<String>();
			for (int j = 0; j < ja1.length(); j++) {
				pictures.add(ja1.getString(j));
			}
			return appInfos;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<String> getPictureList() {
		return pictures;
	}

}
