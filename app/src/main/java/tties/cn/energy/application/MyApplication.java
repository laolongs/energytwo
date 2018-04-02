package tties.cn.energy.application;

import android.app.Activity;
import android.app.Application;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.loader.ImageLoader;
import com.lzy.imagepicker.view.CropImageView;

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
        initImageLoad();
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
    private void initImageLoad() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Activity activity, String path, ImageView imageView, int width, int height) {
                Glide.with(activity).load(path).into(imageView);
            }

            @Override
            public void displayImagePreview(Activity activity, String path, ImageView imageView, int width, int height) {

            }

            @Override
            public void clearMemoryCache() {

            }
        });   //设置图片加载器
        imagePicker.setShowCamera(true);                      //显示拍照按钮
        imagePicker.setCrop(true);                           //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true);                   //是否按矩形区域保存
        imagePicker.setSelectLimit(1);              //选中数量限制
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(800);                       //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(800);                      //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);                         //保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);
    }

}
