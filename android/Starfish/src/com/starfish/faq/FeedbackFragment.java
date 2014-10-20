package com.starfish.faq;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.xiaomi.mitv.idata.client.app.AppData;
import com.xiaomi.mitv.idata.util.iDataCenterORM;

public class FeedbackFragment extends Fragment implements RadioGroup.OnCheckedChangeListener{
	
	View mSubmitBtn;
	Toast mToast;
	EditText mSubject;
	EditText mMsg;
	EditText mPhone;
	EditText mMail;
	RadioGroup mType;
	View mLogCheck;
    public static Fragment newInstance() {
        return new FeedbackFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_feedback, container, false);
    }
    
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mSubmitBtn = (View)getActivity().findViewById(R.id.submit);
        mSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	sendFeedback();
            }
        });
        
    	mSubject = (EditText)getActivity().findViewById(R.id.subject);
    	mMsg = (EditText)getActivity().findViewById(R.id.msg);
    	mPhone = (EditText)getActivity().findViewById(R.id.phone);
    	mMail = (EditText)getActivity().findViewById(R.id.mail);
    	mType = (RadioGroup)getActivity().findViewById(R.id.type);
    	mType.setOnCheckedChangeListener(this);
    	mLogCheck = getActivity().findViewById(R.id.checkBoxlog);
    	mLogCheck.setVisibility(View.INVISIBLE);
    }
    
    private void sendFeedback(){
        String str = getActivity().getText(R.string.fb_tost).toString();
    	if(mSubject.getText().length()==0){
            // Tell the user about what we did.
            if (mToast != null) {
                mToast.cancel();
            }
            String newstr = str.replace("xxx", ""+getActivity().getText(R.string.fb_subject));
            mToast = Toast.makeText(getActivity(), newstr,
                    Toast.LENGTH_LONG);
            mToast.show();
            return;
    	}
    	if(mMsg.getText().length()==0){
            // Tell the user about what we did.
            if (mToast != null) {
                mToast.cancel();
            }
            String newstr = str.replace("xxx", getActivity().getText(R.string.fb_msg));
            mToast = Toast.makeText(getActivity(), newstr,
                    Toast.LENGTH_LONG);
            mToast.show();
            return;
    	}
    	if(mPhone.getText().length()==0 && mMail.getText().length()==0){
            // Tell the user about what we did.
            if (mToast != null) {
                mToast.cancel();
            }

            mToast = Toast.makeText(getActivity(), getActivity().getText(R.string.fb_contact),
                    Toast.LENGTH_LONG);
            mToast.show();
            return;
    	}

    	
    	String tag = "";
    	switch(mType.getCheckedRadioButtonId()){
    	case 0:
    		tag = getActivity().getText(R.string.fb_suggestion).toString();
    		break;
    	case 1:
    		tag = getActivity().getText(R.string.fb_bug).toString();
    		break;
    		
    	}

        String body = mMsg.getText().toString();

    	iDataCenterORM.getInstance(getActivity()).sendFeedback(this.getActivity().getPackageName(), AppData.appKey,
                tag, mSubject.getText().toString(), body,
    			mPhone.getText().toString(), mMail.getText().toString(), "");
        mToast = Toast.makeText(getActivity(), R.string.fb_submit_finish,
                Toast.LENGTH_LONG);
        mToast.show();
        getActivity().onBackPressed();
    }
    
    public void onCheckedChanged(RadioGroup group, int checkedId){
    	switch(checkedId){
			case R.id.radio0:
				mLogCheck.setVisibility(View.INVISIBLE);
				mMsg.setHint(R.string.fb_suggestion_msg);
				break;
			case R.id.radio1:
				mLogCheck.setVisibility(View.VISIBLE);
				mMsg.setHint(R.string.fb_error_msg);
				break;
    	}
    }
}
