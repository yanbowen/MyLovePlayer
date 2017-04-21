package cn.studyjams.s2.sj0132.bowenyan.myloveplayer.service;

/**
 * Created by yanbowen on 4/21/2017.
 */
import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.swust.myLovePlayerMod.IMyLovePlayerService;

import cn.studyjams.s2.sj0132.bowenyan.myloveplayer.helpers.utils.MusicUtils;


public class ServiceBinder implements ServiceConnection {
    private final ServiceConnection mCallback;

    public ServiceBinder(ServiceConnection callback) {
        mCallback = callback;
    }

    @Override
    public void onServiceConnected(ComponentName className, IBinder service) {
        MusicUtils.mService = IMyLovePlayerService.Stub.asInterface(service);
        if (mCallback != null)
            mCallback.onServiceConnected(className, service);
    }

    @Override
    public void onServiceDisconnected(ComponentName className) {
        if (mCallback != null)
            mCallback.onServiceDisconnected(className);
        MusicUtils.mService = null;
    }
}

