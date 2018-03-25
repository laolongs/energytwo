package tties.cn.energy.application;

import android.app.Activity;
import android.app.Application;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import tties.cn.energy.R;
import tties.cn.energy.common.Constants;
import tties.cn.energy.model.result.Loginbean;
import tties.cn.energy.utils.ACache;
import tties.cn.energy.utils.NetWorkUtils;
import tties.cn.energy.view.dialog.CriProgressDialog;


public class MyApplication extends Application {

    private static MyApplication instance;
    private List<Activity> activitys;
    public static Boolean mNetWorkState;
    public static Activity curActivity;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        mNetWorkState = NetWorkUtils.isNetworkConnected(MyApplication.getInstance());
        init();
    }

    public static Loginbean getUserInfo() {
        Loginbean userInfo = ACache.getInstance().getAsObject(Constants .CACHE_USERINFO);
        return userInfo;
    }
    private void init() {
        activitys = new ArrayList<>();
    }

    public static synchronized MyApplication getInstance() {
        return instance;
    }

    /**
     * 添加activity
     */
    public void addActivity(Activity act) {
        if (activitys == null) {
            activitys.add(act);
        }
       
    }

    /**
     * 移除activity
     */
    public void removeActivity(Activity act) {
        if (activitys != null) {
            activitys.remove(act);
        }
    }

    /**
     * 退出app
     */
    public void exitApp() {
        if (activitys != null) {
            synchronized (activitys) {
                for (Activity act : activitys) {
                    act.finish();
                }
            }
        }
        System.exit(0);
    }


}
