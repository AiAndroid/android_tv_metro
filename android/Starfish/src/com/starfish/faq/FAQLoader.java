package com.starfish.faq;

import android.content.Context;
import android.content.res.Configuration;
import com.tv.ui.metro.loader.TabsGsonLoader;
import com.tv.ui.metro.model.DisplayItem;

import java.util.Locale;

/**
 * Created by liuhuadong on 10/20/14.
 */
public class FAQLoader extends TabsGsonLoader {
    public FAQLoader(Context context, DisplayItem item) {
        super(context, item);
    }

    @Override
    public void setLoaderURL(DisplayItem item) {
        if(getContext().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            calledURL = "https://raw.githubusercontent.com/AiAndroid/stream/master/tv/game/home_faq_mobile.json";
        }else {
            Locale locale = getContext().getResources().getConfiguration().locale;
            if (locale.getLanguage().equalsIgnoreCase("zh")) {
                calledURL = "https://raw.githubusercontent.com/AiAndroid/stream/master/tv/game/home_faq.json";
            } else {
                calledURL = "https://raw.githubusercontent.com/AiAndroid/stream/master/tv/game/home_faq_en.json";
            }
        }
    }
}
