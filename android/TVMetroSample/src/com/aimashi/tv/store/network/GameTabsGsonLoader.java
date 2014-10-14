package com.aimashi.tv.store.network;

import android.content.Context;

import android.content.res.Configuration;
import com.tv.ui.metro.loader.TabsGsonLoader;
import com.tv.ui.metro.model.DisplayItem;

import java.util.Locale;

public class GameTabsGsonLoader  extends TabsGsonLoader {

    public GameTabsGsonLoader(Context context, DisplayItem item) {
        super(context, item);
    }

    @Override
    public void setLoaderURL(DisplayItem item) {
        String url = "";
        Locale locale = getContext().getResources().getConfiguration().locale;
        if(locale.getLanguage().equalsIgnoreCase("zh")){
            url = "https://raw.githubusercontent.com/AiAndroid/stream/master/tv/game/home.json";
            if(getContext().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
                url = "https://raw.githubusercontent.com/AiAndroid/stream/master/tv/game/home_port.json";
            }
        }else{
            url = "https://raw.githubusercontent.com/AiAndroid/stream/master/tv/game/home_en.json";
        }
        calledURL = new CommonUrl(getContext()).addCommonParams(url);
    }
}
