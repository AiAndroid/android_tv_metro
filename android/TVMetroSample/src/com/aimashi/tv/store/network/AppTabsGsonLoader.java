package com.aimashi.tv.store.network;

import com.tv.ui.metro.loader.TabsGsonLoader;
import com.tv.ui.metro.model.DisplayItem;

import android.content.Context;

public class AppTabsGsonLoader extends TabsGsonLoader {

	public AppTabsGsonLoader(Context context, DisplayItem item) {
		super(context, item);
	}

	@Override
	public void setLoaderURL(DisplayItem item) {
		String url = "http://appstore.duokanbox.com/v2/app/home";
		calledURL = new CommonUrl(getContext()).addCommonParams(url);
	}
}
