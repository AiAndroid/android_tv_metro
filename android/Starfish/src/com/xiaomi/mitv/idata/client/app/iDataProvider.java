package com.xiaomi.mitv.idata.client.app;

import com.xiaomi.mitv.idata.client.base.iDataProviderBase;

/**
 * Created by liuhuadong on 7/7/14.
 *
 */
public class iDataProvider extends iDataProviderBase {
    @Override
    public void setAuthority() {
        AUTHORITY = "com.xiaomi.mitv.idata.client.app.faq";
    }

    @Override
    public void createAgent() {
        agent = iDataAgentImpl.createInstance(getContext());
    }

    @Override
    public void setDataBaseName() {
        DATABASE_NAME = "idata_sb.db";
    }

}