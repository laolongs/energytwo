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
import tties.cn.energy.model.result.DataAllbean;
import tties.cn.energy.model.result.DataFragmentbean;
import tties.cn.energy.model.result.Databean;
import tties.cn.energy.utils.DateUtil;
import tties.cn.energy.view.iview.IDataView;
import tties.cn.energy.view.iview.IEnergy_ForceView;

/**
 * Created by li on 2018/4/3
 * description：
 * author：guojlli
 */

public class Energy_ForcePresenter extends BasePresenter<IEnergy_ForceView> {
    private static final String TAG = "Energy_ForcePresenter";
    IEnergy_ForceView view;
    IEnergy_ForceModel model;
    DataAllbean dataAllbean=new DataAllbean();
    public Energy_ForcePresenter(IEnergy_ForceView view){
        this.view=view;
        model=new Energy_ForceModel();
    }
    public void getEnergy_Force(){
        String baseDate= DateUtil.getCurrentYear()+"-"+(DateUtil.getCurrentMonth()-1);
        Map<String,Object> map=new HashMap<>();
        map.put("userName",dataAllbean.getUserName());
        map.put("password",dataAllbean.getPassword());
        map.put("objId",dataAllbean.getEnergyledgerId());
        map.put("objType",1);
        map.put("baseDate", baseDate);
        map.put("eleAccountId",dataAllbean.getEleAccountId());
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
        map.put("userName",dataAllbean.getUserName());
        map.put("password",dataAllbean.getPassword());
        map.put("objId",dataAllbean.getEnergyledgerId());
        map.put("objType",1);
        map.put("baseDate", dataAllbean.getBaseData());
        map.put("eleAccountId",dataAllbean.getEleAccountId());
        map.put("dateType",2);
        Log.i(TAG, "onErrordata: "+dataAllbean.getBaseData());
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
    public void getEnergy_Forcecharge(){
        String baseDate= DateUtil.getCurrentYear()+"-"+(DateUtil.getCurrentMonth()-1);
        Map<String,Object> map=new HashMap<>();
        map.put("userName",dataAllbean.getUserName());
        map.put("password",dataAllbean.getPassword());
        map.put("objId",dataAllbean.getEnergyledgerId());
        map.put("objType",1);
        map.put("baseDate",baseDate);
        map.put("eleAccountId",dataAllbean.getEleAccountId());
        map.put("count",12);
        Log.i(TAG, "onErrordata: "+dataAllbean.getUserName());
        Log.i(TAG, "onErrordata: "+dataAllbean.getPassword());
        Log.i(TAG, "onErrordata: "+dataAllbean.getEnergyledgerId());
        Log.i(TAG, "onErrordata: "+baseDate);
        Log.i(TAG, "onErrordata: "+dataAllbean.getEleAccountId());
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
