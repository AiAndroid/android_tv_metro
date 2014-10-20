package com.xiaomi.mitv.idata.client.app;

import android.content.Context;
import com.xiaomi.mitv.idata.client.base.iDataAgent;
import com.xiaomi.mitv.idata.client.base.iDataAgentFactory;

/**
 * Created by liuhuadong on 7/7/14.
 */
public class iDataAgentFactoryImpl extends iDataAgentFactory {
    public static iDataAgentFactory instance(){
        return new iDataAgentFactoryImpl();
    }

    public iDataAgent createDataAgent(Context context){
        return new iDataAgentImpl(context);
    }
}
