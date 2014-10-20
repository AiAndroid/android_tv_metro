package com.xiaomi.mitv.idata.client.app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.HandlerThread;
import com.xiaomi.mitv.idata.util.Utils;

import java.util.Date;

/**
 * Created by liuhuadong on 7/17/14.
 */
public class iDataLocalORM {
    public static final Uri SETTINGS_CONTENT_URI      = Uri.parse(iDataProvider.AUTHORITY + "/settings");
    public static final Uri CONFIGURATION_CONTENT_URI = Uri.parse(iDataProvider.AUTHORITY + "/data");

    private static String TAG = "iDataLocalORM";
    private static String last_time_fetch_configuration = "last_time_fetch_configuration";
    //handler thread
    private HandlerThread ht;
    private Handler backHandler;

    private static iDataLocalORM _instance;

    public static iDataLocalORM getInstance(Context con) {
        if (_instance == null) {
            _instance = new iDataLocalORM(con);
        }

        return _instance;
    }

    public void post(Runnable run) {
        backHandler.post(run);
    }

    private Context mContext;

    private iDataLocalORM(Context con) {
        mContext = con.getApplicationContext();

        ht = new HandlerThread("iDataLocalThread");
        ht.start();
        backHandler = new Handler(ht.getLooper());
    }

    public static String[] settingsProject = new String[]{
            "_id",
            "name",
            "value",
    };

    public long getLastDataCollectionTime() {
        long lastSyncTime = 0;
        String value = this.getSettingValue(last_time_fetch_configuration);
        if (value != null) {
            try {
                lastSyncTime = Long.valueOf(value);
            } catch (Exception ne) {
            }
        }
        return lastSyncTime;
    }

    public void setLastDataCollectionTime() {
        this.addSetting(last_time_fetch_configuration, String.valueOf(System.currentTimeMillis()));
    }

    public int getDataCollectionInterval(int defaultValue) {
        return 120;
    }


    public static int getIntValue(Context con, String name, int defaultValue) {
        String value = getSettingValue(con, SETTINGS_CONTENT_URI, name);
        int valueB = defaultValue;
        try {
            if (value != null) {
                valueB = Integer.valueOf(value);
            }
        } catch (Exception ne) {
            ne.printStackTrace();
        }

        return valueB;
    }

    public static boolean getBooleanValue(Context con, String name, boolean defaultValue) {
        String value = getSettingValue(con, SETTINGS_CONTENT_URI, name);
        boolean valueB = defaultValue;
        try {
            if (value != null) {
                valueB = value.equals("1") ? true : false;
            }
        } catch (Exception ne) {
            ne.printStackTrace();
        }

        return valueB;
    }


    public static String getStringValue(Context con, String name, String defaultValue) {
        String value = getSettingValue(con, SETTINGS_CONTENT_URI, name);
        String valueB = defaultValue;
        try {
            if (value != null && value.length() > 0) {
                valueB = value;
            }
        } catch (Exception ne) {
            ne.printStackTrace();
        }

        return valueB;
    }

    public static class SettingsCol {
        public static final String ID = "_id";
        public static final String Name = "name";
        public static final String Value = "value";
        public static final String ChangeDate = "date_time";
    }

    //settings
    public String getSettingValue(String name) {
        String va = null;
        String where = SettingsCol.Name + "='" + name + "'";
        Cursor cursor = mContext.getContentResolver().query(SETTINGS_CONTENT_URI, settingsProject, where, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                va = cursor.getString(cursor.getColumnIndex(SettingsCol.Value));
            }
            cursor.close();
        }
        return va;
    }

    public Uri addSetting(String name, String value) {
        return addSetting(mContext, name, value);
    }

    public static Uri addSetting(Context context, String name, String value) {
        return addSetting(context, SETTINGS_CONTENT_URI, name, value);
    }

    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static String getSettingValue(Context con, Uri settingUri, String name) {
        String va = null;
        String where = SettingsCol.Name + "='" + name + "'";
        Cursor cursor = con.getContentResolver().query(settingUri, settingsProject, where, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                va = cursor.getString(cursor.getColumnIndex(SettingsCol.Value));
            }
            cursor.close();
            cursor = null;
        }
        return va;
    }

    private static Uri addSetting(Context context, Uri settingUri, String name, String value) {
        Uri ret = null;
        ContentValues ct = new ContentValues();
        ct.put(SettingsCol.Name, name);
        ct.put(SettingsCol.Value, value);
        ct.put(SettingsCol.ChangeDate, Utils.dateToString(new Date()));
        //if exist, update
        if (null != getSettingValue(context, settingUri, name)) {
            updateSetting(context, settingUri, name, value);
        } else {
            ret = context.getContentResolver().insert(settingUri, ct);
        }

        return ret;
    }

    public static boolean updateSetting(Context context, Uri settingUri, String name, String value) {
        boolean ret = false;
        String where = String.format(" name = \"%1$s\" ", name);
        ContentValues ct = new ContentValues();
        ct.put(SettingsCol.Value, value);
        ct.put(SettingsCol.ChangeDate, Utils.dateToString(new Date()));

        if (context.getContentResolver().update(settingUri, ct, where, null) > 0) {
            ret = true;
        }
        return ret;
    }
}

