package com.starfish.faq;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.aimashi.store.app.view.UserView;
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
                views.add(new UserView(context, getResources().getString(R.string.feedback), R.drawable.feedback, ""));
                views.add(new UserView(context, getResources().getString(R.string.disgnosis),R.drawable.diagnosis,""));
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
