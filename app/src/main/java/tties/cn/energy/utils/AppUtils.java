package tties.cn.energy.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import tties.cn.energy.application.MyApplication;
import tties.cn.energy.common.Constants;
import tties.cn.energy.model.result.Loginbean;

/**
 * Created by qizepu on 2017/2/22.
 * 跟App相关的辅助类
 */
public class AppUtils {

    private AppUtils() {
                /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");

    }

    public static Loginbean getUsertInfo() {
        Loginbean ret = ACache.getInstance().getAsObject(Constants.CACHE_USERINFO);
        return ret;
    }
    /**
     * 获取应用程序名称
     */
    public static String getAppName() {
        try {
            Context context = MyApplication.getInstance();
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            int labelRes = packageInfo.applicationInfo.labelRes;
            return context.getResources().getString(labelRes);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * [获取应用程序版本名称信息]
     *
     * @return 当前应用的版本名称
     */
    public static int getVersionCode() {
        try {
            Context context = MyApplication.getInstance();
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            return packageInfo.versionCode;

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static String getVersionName() {
        try {
            Context context = MyApplication.getInstance();
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            return packageInfo.versionName;

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "1.0.0";
    }

    public static long getDownLoad() {
        Long id = ACache.getInstance().getAsObject(Constants.DOWNLOAD_ID);
        return id == null ? 0L : id;
    }

    public static void saveDownLoad(long id) {
        ACache.getInstance().put(Constants.DOWNLOAD_ID, id);
    }

    public static boolean isWifiConnected() {
        Context context = MyApplication.getInstance();
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiNetworkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (wifiNetworkInfo.isConnected()) {
            return true;
        }

        return false;
    }


}