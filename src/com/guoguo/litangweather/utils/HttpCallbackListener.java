package com.guoguo.litangweather.utils;

public interface HttpCallbackListener {
	void onFinish(String response);
	void onError(Exception e);
}
