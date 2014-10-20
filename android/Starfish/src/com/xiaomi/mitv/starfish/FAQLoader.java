package com.xiaomi.mitv.starfish;

import android.content.Context;
import com.tv.ui.metro.loader.TabsGsonLoader;
import com.tv.ui.metro.model.DisplayItem;

/**
 * Created by liuhuadong on 10/20/14.
 */
public class FAQLoader extends TabsGsonLoader {
    public FAQLoader(Context context, DisplayItem item) {
        super(context, item);
    }

    @Override
    public void setLoaderURL(DisplayItem item) {
        calledURL = "https://raw.githubusercontent.com/AiAndroid/stream/master/tv/game/home_faq.json";
    }
}
