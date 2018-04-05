package tties.cn.energy.presenter;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import tties.cn.energy.base.BasePresenter;
import tties.cn.energy.model.IModel.DataFragmentModel;
import tties.cn.energy.model.IModel.Energy_BaseenergyModel;
import tties.cn.energy.model.IModel.IDataFragmentModel;
import tties.cn.energy.model.IModel.IEnergy_BaseenergyModel;
import tties.cn.energy.model.result.DataFragmentbean;
import tties.cn.energy.model.result.Databean;
import tties.cn.energy.model.result.Energy_BasePlanbean;
import tties.cn.energy.view.iview.IDataFragmentView;
import tties.cn.energy.view.iview.IEnergy_BaseenergyView;

/**
 * Created by li on 2018/4/3
 * description：
 * author：guojlli
 */

public class Energy_BaseenergyPresenter extends BasePresenter<IEnergy_BaseenergyView> {
    private static final String TAG = "Energy_BaseenergyPresen";
    IEnergy_BaseenergyView view;
    IEnergy_BaseenergyModel model;
    public Energy_BaseenergyPresenter(IEnergy_BaseenergyView view){
        this.view=view;
        model=new Energy_BaseenergyModel();
    }
    public void getEnergy_Baseenergy(){
        Map<String,Object> map=new HashMap<>();
        map.put("userName","test");
        map.put("password","E10ADC3949BA59ABBE56E057F20F883E");
        map.put("objId","1486535776800");
        map.put("objType","1");
        map.put("baseDate","2017-03");
        map.put("eleAccountId","54");
        map.put("dateType","1");

        model.getEnergy_BaseenergyData().getData(map).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Databean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Databean value) {
                        if(value!=null){
                            view.setEnergy_BaseenergyData(value);
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
    public void getEnergy_BaseenergyYear(){
        Map<String,Object> map=new HashMap<>();
        map.put("userName","test");
        map.put("password","E10ADC3949BA59ABBE56E057F20F883E");
        map.put("objId","1486535776800");
        map.put("objType","1");
        map.put("baseDate","2017-03");
        map.put("eleAccountId","54");
        map.put("dateType","2");

        model.getEnergy_BaseenergyData().getData(map).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Databean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Databean value) {
                        if(value!=null){
                            view.setEnergy_BaseenergyYearData(value);
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
    public void getEnergy_BasePlan(){
        Map<String,Object> map=new HashMap<>();
        map.put("userName","test");
        map.put("password","E10ADC3949BA59ABBE56E057F20F883E");
        map.put("objId","1486535776800");
        map.put("baseDate","2017-03");
        map.put("eleAccountId","54");

        model.getEnergy_BasePlanData().getEnergy_BasePlan(map).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Energy_BasePlanbean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Energy_BasePlanbean value) {
                        if(value!=null){
                            view.setEnergy_BasePlanData(value);
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
