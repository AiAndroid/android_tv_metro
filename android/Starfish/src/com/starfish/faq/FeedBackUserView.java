package com.starfish.faq;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import com.aimashi.store.app.view.UserView;

/**
 * Created by liuhuadong on 10/20/14.
 */
public class FeedBackUserView extends UserView {
    public FeedBackUserView(Context context, String title, int logoRes, String summary) {
        super(context, title, logoRes, summary);

        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), FeedbackActivity.class);
                getContext().startActivity(intent);
            }
        });
    }
}
