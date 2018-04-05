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
import tties.cn.energy.model.IModel.DataModel;
import tties.cn.energy.model.IModel.Energy_ForceModel;
import tties.cn.energy.model.IModel.IDataModel;
import tties.cn.energy.model.IModel.IEnergy_ForceModel;
import tties.cn.energy.model.result.AllElectricitybean;
import tties.cn.energy.model.result.DataFragmentbean;
import tties.cn.energy.model.result.Databean;
import tties.cn.energy.view.iview.IDataView;
import tties.cn.energy.view.iview.IEnergy_ForceView;

/**
 * Created by li on 2018/4/3
 * description：
 * author：guojlli
 */

public class Energy_ForcePresenter extends BasePresenter<IEnergy_ForceView> {
    private static final String TAG = "DataPresenter";
    IEnergy_ForceView view;
    IEnergy_ForceModel model;
    public Energy_ForcePresenter(IEnergy_ForceView view){
        this.view=view;
        model=new Energy_ForceModel();
    }
    public void getEnergy_Force(){
        Map<String,Object> map=new HashMap<>();
        map.put("userName","test");
        map.put("password","E10ADC3949BA59ABBE56E057F20F883E");
        map.put("objId","1486535776800");
        map.put("objType","1");
        map.put("baseDate","2017-03");
        map.put("eleAccountId","54");
        map.put("dateType","1");
        model.getEnergy_ForceData().getData(map).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Databean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Databean value) {
                        if(value!=null){
                            view.setEnergy_ForceData(value);
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
    public void getEnergy_ForcechartData(){
        Map<String,Object> map=new HashMap<>();
        map.put("userName","test");
        map.put("password","E10ADC3949BA59ABBE56E057F20F883E");
        map.put("objId","1486535776800");
        map.put("objType","1");
        map.put("baseDate","2017-03");
        map.put("eleAccountId","54");
        map.put("dateType","2");
        model.getEnergy_ForcechargeData().getData(map).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Databean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Databean value) {
                        if(value!=null){
                            view.setEnergy_ForceChartData(value);
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
//    public void getEnergy_Forcecharge(String year,int count){
    public void getEnergy_Forcecharge(){
        Map<String,Object> map=new HashMap<>();
        map.put("userName","test");
        map.put("password","E10ADC3949BA59ABBE56E057F20F883E");
        map.put("objId","1486535776800");
        map.put("objType","1");
        map.put("baseDate","2017-12");
        map.put("count","13");
        map.put("eleAccountId","54");

        model.getEnergy_ForcechargeData().getDataFragemet(map).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DataFragmentbean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DataFragmentbean value) {
                        if(value!=null){
                            view.setEnergy_ForceCharge(value);
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

}
