package tties.cn.energy.model.httputils.send;

import android.content.Context;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import tties.cn.energy.api.ObserverApi;
import tties.cn.energy.api.RetrofitApi;
import tties.cn.energy.common.Constants;
import tties.cn.energy.common.EventKind;
import tties.cn.energy.model.bean.EventBusBean;
import tties.cn.energy.model.result.Versionbean;
import tties.cn.energy.utils.ACache;
import tties.cn.energy.view.activity.SplashActivity;

/**
 * Created by li on 2018/3/22
 * description：
 * author：guojlli
 */

public class VersionSend {
  public static void getVersionData(Context context){
      final String TAG = "VersionSend";
      RetrofitApi.getServer()
              .getVersion()
              .observeOn(AndroidSchedulers.mainThread())
              .subscribeOn(Schedulers.io())
              .subscribe(new ObserverApi<Versionbean>(context) {
                  @Override
                  public void onSuccess(Versionbean versionbean) {

                  }

                  @Override
                  public void onNext(Versionbean versionbean) {
                      try {
                          Log.d(TAG, "检查新版本: " + versionbean);
                          ACache.getInstance().put(Constants.CACHEE_VERSION, versionbean);
                          EventBusBean bean = new EventBusBean();
                          bean.setKind(EventKind.EVENT_VERSION_SYCN);
                          EventBus.getDefault().post(bean);
                      } catch (Exception e) {
                          e.printStackTrace();
                      } finally {
                      }
                  }

                  @Override
                  public void onError(Throwable e) {
                      Log.d(TAG, "异常错误");
                      EventBusBean bean = new EventBusBean();
                      bean.setKind(EventKind.EVENT_VERSION_SYCN);
                      EventBus.getDefault().post(bean);
                  }
              });
  }
}
