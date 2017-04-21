package cn.studyjams.s2.sj0132.bowenyan.myloveplayer.service;

import android.content.ContextWrapper;

/**
 * Created by yanbowen on 4/21/2017.
 */

public class ServiceToken {
    public ContextWrapper mWrappedContext;

    public ServiceToken(ContextWrapper context) {
        mWrappedContext = context;
    }
}
