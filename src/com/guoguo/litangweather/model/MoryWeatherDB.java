package com.guoguo.litangweather.model;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.guoguo.litangweather.db.MoryWeatherOpenHelper;

public class MoryWeatherDB {
	// database name
	public static final String DB_NAME = "mory_weather";

	// version number
	public static final int VERSION = 1;

	private SQLiteDatabase mDb;
	private static MoryWeatherDB mMoryWeatherDB;

	// constructor
	private MoryWeatherDB(Context context) {
		MoryWeatherOpenHelper dbHelper = new MoryWeatherOpenHelper(context,
				DB_NAME, null, VERSION);
		mDb = dbHelper.getWritableDatabase();
	}

	// get database instance
	public synchronized static MoryWeatherDB getInstance(Context context) {
		if (mMoryWeatherDB == null) {
			mMoryWeatherDB = new MoryWeatherDB(context);
		}

		return mMoryWeatherDB;
	}

	// save province data
	public void saveProvinces(Province province) {
		if (province != null) {
			ContentValues values = new ContentValues();
			values.put("province_name", province.getProvinceName());
			values.put("province_code", province.getProvinceCode());
			mDb.insert("Province", null, values);
		}
	}

	// load province data
	public List<Province> loadProvinces() {
		List<Province> lists = new ArrayList<Province>();
		Cursor cursor = mDb.query("Province", null, null, null, null, null,
				null);

		if (cursor.moveToFirst()) {
			do {
				Province province = new Province();
				province.setId(cursor.getInt(cursor.getColumnIndex("id")));
				province.setProvinceName(cursor.getString(cursor
						.getColumnIndex("province_name")));
				province.setProvinceCode(cursor.getString(cursor
						.getColumnIndex("province_code")));
				lists.add(province);
			} while (cursor.moveToNext());
		}
		return lists;
	}

	// save city data
	public void saveCity(City city) {
		if (city != null) {
			ContentValues values = new ContentValues();
			values.put("city_name", city.getCityName());
			values.put("city_code", city.getCityCode());
			values.put("province_id", city.getProvinceId());
			mDb.insert("City", null, values);
		}
	}

	// load city data
	public List<City> loadCities(int provinceId) {
		List<City> lists = new ArrayList<City>();
		Cursor cursor = mDb.query("City", null, "province_id = ?",
				new String[] { String.valueOf(provinceId) }, null, null, null);
		if (cursor.moveToFirst()) {
			do {
				City city = new City();
				city.setId(cursor.getInt(cursor.getColumnIndex("id")));
				city.setCityName(cursor.getString(cursor
						.getColumnIndex("city_name")));
				city.setCityCode(cursor.getString(cursor
						.getColumnIndex("city_code")));
				city.setProvinceId(provinceId);
				lists.add(city);
			} while (cursor.moveToNext());
		}
		return lists;
	}

	// save county data
	public void saveCounty(County county) {
		if (county != null) {
			ContentValues values = new ContentValues();
			values.put("county_name", county.getCountyName());
			values.put("county_code", county.getCountyCode());
			values.put("city_id", county.getCityId());
			mDb.insert("County", null, values);
		}
	}

	// load county data
	public List<County> loadCounties(int cityId) {
		List<County> lists = new ArrayList<County>();
		Cursor cursor = mDb.query("County", null, "city_id = ?",
				new String[] { String.valueOf(cityId) }, null, null, null);
		if (cursor.moveToFirst()) {
			do {
				County county = new County();
				county.setId(cursor.getInt(cursor.getColumnIndex("id")));
				county.setCountyName(cursor.getString(cursor
						.getColumnIndex("county_name")));
				county.setCountyCode(cursor.getString(cursor
						.getColumnIndex("county_code")));
				county.setCityId(cityId);
				lists.add(county);
			} while (cursor.moveToNext());
		}
		return lists;
	}
}
