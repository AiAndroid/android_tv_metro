package com.starfish.faq;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;
import com.tv.ui.metro.MainActivity;
import com.tv.ui.metro.view.MetroFragment;
import com.tv.ui.metro.view.UserViewFactory;

import java.util.ArrayList;

public class StarfishActivity extends MainActivity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //please call this
        UserViewFactory.getInstance().setFactory(new UserViewFactory.ViewCreatorFactory() {
            @Override
            public ArrayList<View> create(Context context) {
                ArrayList<View> views = new ArrayList<View>();
                FeedBackUserView feed = new FeedBackUserView(context, getResources().getString(R.string.feedback), R.drawable.feedback, "");
                views.add(feed);

                feed.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getBaseContext(), FeedbackActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        getBaseContext().startActivity(intent);
                    }
                });

                if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    FeedBackUserView diagnoisis = new FeedBackUserView(context, getResources().getString(R.string.disgnosis), R.drawable.diagnosis, "");
                    views.add(diagnoisis);
                }

                ImageView iv = new ImageView(getBaseContext());
                if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    iv.setBackground(new ColorDrawable(0Xff99cc00));
                }else {
                    iv.setBackgroundResource(R.drawable.userview_item_bg2);
                }
                iv.setScaleType(ImageView.ScaleType.FIT_CENTER);
                Picasso.with(getBaseContext()).load("https://github.com/AiAndroid/android_tv_metro/raw/master/android/out/production/Starfish/applink.png").into(iv);
                //iv.setImageResource(R.drawable.diagnosis);
                views.add(iv);

                return views;
            }

            @Override
            public int getPadding(Context context) {
                return getResources().getDimensionPixelSize(R.dimen.user_view_padding);
            }
        });
    }

    protected void createTabsLoader() {
        mLoader = new FAQLoader(this, null);
    }

    @Override
    protected void setUserFragmentClass() {
        isNeedUserTab = true;
        mUserTabName = getResources().getString(R.string.feedback_tab);
        mUserFragmentClass = MetroFragment.class;
    }
}
