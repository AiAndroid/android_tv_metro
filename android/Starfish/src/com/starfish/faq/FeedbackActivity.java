package com.starfish.faq;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

/**
 * Created by liuhuadong on 10/20/14.
 */
public class FeedbackActivity extends FragmentActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.feedback_contain);

        Fragment ff = FeedbackFragment.newInstance();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.feed_back_contain, ff, "feedback")
                .commitAllowingStateLoss();
    }
}
