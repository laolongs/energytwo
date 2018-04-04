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
import tties.cn.energy.model.result.AllElectricitybean;
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

        Map<String,Object> map=new HashMap<>();
        map.put("userName","test");
        map.put("password","E10ADC3949BA59ABBE56E057F20F883E");
        map.put("objId","1486535776800");
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
