package tties.cn.energy.presenter;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import tties.cn.energy.api.RetrofitApi;
import tties.cn.energy.base.BasePresenter;
import tties.cn.energy.model.IModel.Data_NoModel;
import tties.cn.energy.model.IModel.Data_RateModel;
import tties.cn.energy.model.IModel.IData_NoModel;
import tties.cn.energy.model.IModel.IData_RateModel;
import tties.cn.energy.model.result.AllElectricitybean;
import tties.cn.energy.model.result.Data_HaveKwbean;
import tties.cn.energy.model.result.Data_NoKvarbean;
import tties.cn.energy.model.result.Data_Nobean;
import tties.cn.energy.view.iview.IData_NoView;
import tties.cn.energy.view.iview.IData_RateView;

/**
 * mainpresenter
 */

public class Data_RatePresenter extends BasePresenter<IData_RateView>  {
    private static final String TAG = "Data_RatePresenter";
    IData_RateView view;
    IData_RateModel model;
    public Data_RatePresenter(IData_RateView view) {
        this.view = view;
        this.model = new Data_RateModel();
    }
    public void getData_HaveKwData(int dataType){
        Map<String,Object> map=new HashMap<>();
        map.put("userName","test");
        map.put("password","E10ADC3949BA59ABBE56E057F20F883E");
        map.put("objId","1486536312217");
        map.put("objType","2");
        map.put("dataType",dataType);
        map.put("baseDate","2017-03-27");
        model.getData_HaveKWData().getData_HaveKw(map).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Data_HaveKwbean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Data_HaveKwbean value) {
                        if(value!=null){
                            Log.i(TAG, "onNext: ");
                            view.setData_HaveKWData(value);
                        }else{
                            Log.i(TAG, "onError: "+"数据有误");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "onError: "+"数据有误");
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
    public void getData_NoKvarKwData(int dataType){
        Map<String,Object> map=new HashMap<>();
        map.put("userName","test");
        map.put("password","E10ADC3949BA59ABBE56E057F20F883E");
        map.put("objId","1486536312217");
        map.put("objType","2");
        map.put("dataType",dataType);
        map.put("baseDate","2017-03-27");
        model.getData_NoKvarData().getData_NoKvar(map).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Data_NoKvarbean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Data_NoKvarbean value) {
                        if(value!=null){
                            Log.i(TAG, "onNext: ");
                            view.setData_NoKvarData(value);
                        }else{
                            Log.i(TAG, "onError: "+"数据有误");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "onError: "+"数据有误");
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
