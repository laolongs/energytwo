package tties.cn.energy.view.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AndroidException;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import tties.cn.energy.R;
import tties.cn.energy.api.Api;
import tties.cn.energy.api.ObserverApi;
import tties.cn.energy.api.RetrofitApi;
import tties.cn.energy.application.MyApplication;
import tties.cn.energy.common.Constants;
import tties.cn.energy.common.EventKind;
import tties.cn.energy.model.bean.EventBusBean;
import tties.cn.energy.model.httputils.params.LoginParams;
import tties.cn.energy.model.httputils.send.VersionSend;
import tties.cn.energy.model.result.Versionbean;
import tties.cn.energy.utils.ACache;
import tties.cn.energy.view.MainActivity;

public class SplashActivity extends AppCompatActivity {
    private static final int FAILURE = 0; // 失败
    private static final int SUCCESS = 1; // 成功
    private static final int SHOW_TIME_MIN = 1800; //最短显示时间
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new AsyncTask<Void, Void, Integer>() {

            @Override
            protected Integer doInBackground(Void... params) {
                int result=loadingCache();
                long startTime = System.currentTimeMillis();
                long loadingTime = System.currentTimeMillis() - startTime;
                if (loadingTime < SHOW_TIME_MIN) {
                    try {
                        Thread.sleep(SHOW_TIME_MIN - loadingTime);


                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                return result;
            }

            @Override
            protected void onPostExecute(Integer result) {
                //进入登录画面
                if (result == FAILURE) {
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                }
            }
        }.execute();
    }
    private int loadingCache() {
        Boolean loginStatus = ACache.getInstance().getAsObject(Constants.CACHE_LOGIN_STATUS);
        initVersion();
        if (!MyApplication.mNetWorkState || loginStatus == null || !loginStatus) {
            Log.d("logStatus", "用户非登录状态");
            return FAILURE;
        } else {
//            LoginSend send = new LoginSend();
//            send.send(new LoginParams());
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        }
        return SUCCESS;
    }

    private void initVersion() {
        VersionSend.getVersionData(SplashActivity.this);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(EventBusBean bean) {
        if (bean != null && bean.getKind().equals(EventKind.EVENT_LOGIN)) {
            Boolean loginStatus = bean.getObj();
            if (!loginStatus) {
                Intent intent = new Intent(SplashActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }
        if (bean != null && bean.getKind().equals(EventKind.EVENT_METER_SYCN)) {
            Intent intent = new Intent(SplashActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        }
    }
}
