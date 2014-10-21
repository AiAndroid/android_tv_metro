package com.starfish.faq;

import android.content.Context;
import android.content.res.Configuration;
import com.aimashi.store.app.view.UserView;

/**
 * Created by liuhuadong on 10/20/14.
 */
public class FeedBackUserView extends UserView {
    static boolean colorswith = true;
    public FeedBackUserView(Context context, String title, int logoRes, String summary) {
        super(context, title, logoRes, summary);

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            if(colorswith)
                this.setBackgroundResource(R.drawable.userview_item_bg);
            else
                this.setBackgroundResource(R.drawable.userview_item_bg2);

            if (!colorswith) colorswith = true;
            else colorswith = false;
        }
    }
}
