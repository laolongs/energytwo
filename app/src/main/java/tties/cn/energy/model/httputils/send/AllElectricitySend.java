package tties.cn.energy.model.httputils.send;

import android.content.Context;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import tties.cn.energy.api.RetrofitApi;
import tties.cn.energy.common.Constants;
import tties.cn.energy.model.result.AllElectricitybean;
import tties.cn.energy.model.result.OpsLoginbean;
import tties.cn.energy.utils.ACache;
import tties.cn.energy.view.activity.Data_NoActivity;
import tties.cn.energy.view.dialog.BottomStyleDialog;

/**
 * Created by li on 2018/4/3
 * description：
 * author：guojlli
 */

public class AllElectricitySend {
    Context context;
    public AllElectricitySend(Context context){
        this.context=context;
    }
    AllElectricitybean allElectricitybean;
    private static final String TAG = "AllElectricitySend";
    public void getAllElectricityData() {
        String name = ACache.getInstance().getAsObject(Constants.CACHE_LOGIN_USERNAME);
        String pass = ACache.getInstance().getAsObject(Constants.CACHE_LOGIN_PASSWORDMD5);
        long energyledgerId = ACache.getInstance().getAsObject(Constants.CACHE_OPS_ENERGYLEDGERID);
        Map<String,Object> map=new HashMap<>();
        map.put("userName",name);
        map.put("password",pass);
        map.put("objId",energyledgerId);
        map.put("objType",1);
        RetrofitApi.getServer().getAllElectricity(map)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new Observer<AllElectricitybean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(AllElectricitybean value) {

                if(value!=null){
                    Log.i(TAG, "onNext: "+value.getMeterList().size());
//                    allElectricitybean =value;
                }else{
                    Log.i(TAG, "onError: "+"数据有误");
                }

            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "onError: "+e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
