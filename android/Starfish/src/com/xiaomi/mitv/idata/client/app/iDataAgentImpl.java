package com.xiaomi.mitv.idata.client.app;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;
import com.xiaomi.mitv.idata.client.base.iDataAgent;
import com.xiaomi.mitv.idata.util.DeviceHelper;
import org.json.JSONObject;

/**
 * Created by liuhuadong on 7/7/14.
 */
public class iDataAgentImpl extends iDataAgent {
    private String TAG = "iDataAgent-Impl";

    private static iDataAgent _instance;
    public static iDataAgent createInstance(Context context) {
        if (_instance == null) {
            _instance = iDataAgentFactoryImpl.instance().createDataAgent(context);
        }
        return _instance;
    }

    public iDataAgentImpl(Context context){
        mContext = context.getApplicationContext();
    }

    @Override
    public void onDataCollect(StringBuilder data) {
        Log.d(TAG, "data collect action coming");

        JSONObject jo = new JSONObject();      


        Log.d(TAG, "do data collection = " + jo.toString());
        sendDataToDataCenter("soundbarapp", jo.toString());
    }

    @Override
    public void onConfiguration(ContentValues jsonData) {
        Log.d(TAG, "onConfiguration ="+jsonData);
    }

    @Override
    public void onDiagnosis() {
        //StringBuilder sb = new StringBuilder();
        //this.sendDiagnosisToDataCenter(sb);

        Log.d(TAG, "TODO: need impl diagnosis");
    }

    @Override
    public String getClientAppID() {
        return DeviceHelper.getDeviceID(mContext);
    }
}
