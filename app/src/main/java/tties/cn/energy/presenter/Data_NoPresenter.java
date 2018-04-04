package tties.cn.energy.presenter;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import tties.cn.energy.api.RetrofitApi;
import tties.cn.energy.api.RetrofitApiPub;
import tties.cn.energy.base.BasePresenter;
import tties.cn.energy.model.IModel.Data_NoModel;
import tties.cn.energy.model.IModel.Data_PressModel;
import tties.cn.energy.model.IModel.IData_NoModel;
import tties.cn.energy.model.IModel.IData_PressModel;
import tties.cn.energy.model.result.AllElectricitybean;
import tties.cn.energy.model.result.Data_Nobean;
import tties.cn.energy.model.result.Data_Pressbean;
import tties.cn.energy.view.iview.IData_NoView;
import tties.cn.energy.view.iview.IData_PressView;

/**
 * mainpresenter
 */

public class Data_NoPresenter extends BasePresenter<IData_NoView>  {
    private static final String TAG = "Data_NoPresenter";
    IData_NoView view;
    IData_NoModel model;
    public Data_NoPresenter(IData_NoView view) {
        this.view = view;
        this.model = new Data_NoModel();
    }
    public void getData_NoData(int dataType){
        Map<String,Object> map=new HashMap<>();
        map.put("userName","admin");
        map.put("password","AEC60231D83FE6CF81444BC536596887");
        map.put("objId","1486536481282");
        map.put("baseDate","2017-03");
        map.put("dataType",dataType);
        model.getData_NoData().getData_No(map).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Data_Nobean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Data_Nobean value) {
                        Log.i(TAG, "onNext: "+value.getLimitData().size());
                        if(value!=null){
                            view.setData_NoData(value);
                        }else{
                            Log.i(TAG, "onNext: "+"数据有误");
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
                    view.setAllElectricity(value);
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
