package com.guoguo.litangweather.utils;

import android.text.TextUtils;

import com.guoguo.litangweather.model.City;
import com.guoguo.litangweather.model.County;
import com.guoguo.litangweather.model.MoryWeatherDB;
import com.guoguo.litangweather.model.Province;

public class Utility {
	public static synchronized boolean handleProvinceResponse(
			MoryWeatherDB moryWeatherDB, String response) {
		if (!TextUtils.isEmpty(response)) {
			String[] allProvinces = response.split(",");
			if (allProvinces != null && allProvinces.length > 0) {
				for (String str : allProvinces) {
					String[] array = str.split("\\|");
					Province province = new Province();
					province.setProvinceCode(array[0]);
					province.setProvinceName(array[1]);
					moryWeatherDB.saveProvinces(province);
				}
				return true;
			}
		}
		return false;
	}

	public static synchronized boolean handleCitiesResponse(
			MoryWeatherDB moryWeatherDB, String response, int provinceId) {
		if (!TextUtils.isEmpty(response)) {
			String[] allCities = response.split(",");
			if (allCities != null && allCities.length > 0) {
				for (String c : allCities) {
					String[] array = c.split("\\|");
					City city = new City();
					city.setCityCode(array[0]);
					city.setCityName(array[1]);
					city.setProvinceId(provinceId);
					moryWeatherDB.saveCity(city);
				}
				return true;
			}
		}
		return false;
	}

	public static synchronized boolean handleCountiesResponse(
			MoryWeatherDB moryWeatherDB, String response, int cityId) {
		if (!TextUtils.isEmpty(response)) {
			String[] allCounties = response.split(",");
			if (allCounties != null && allCounties.length > 0) {
				for (String c : allCounties) {
					String[] array = c.split("\\|");
					County county = new County();
					county.setCountyCode(array[0]);
					county.setCountyName(array[1]);
					county.setCityId(cityId);
					moryWeatherDB.saveCounty(county);
				}
				return true;
			}
		}
		return false;
	}
}
