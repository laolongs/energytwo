package tties.cn.energy.model.httputils.send;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import tties.cn.energy.api.RetrofitApi;
import tties.cn.energy.model.result.AllElectricitybean;

/**
 * Created by li on 2018/4/3
 * description：
 * author：guojlli
 */

public class AllElectricitySend {
    AllElectricitybean allElectricitybean;
    private static final String TAG = "AllElectricitySend";
    public AllElectricitybean getAllElectricityData() {

        Map<String,Object> map=new HashMap<>();
        map.put("userName","南洋印染");
        map.put("password","96E79218965EB72C92A549DD5A330112");
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
                Log.i(TAG, "onNext: "+value.getMeterList().size());
                if(value!=null){
                    allElectricitybean =value;
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
        return allElectricitybean;
    }
}
