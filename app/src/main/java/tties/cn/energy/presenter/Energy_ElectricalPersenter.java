package tties.cn.energy.presenter;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import tties.cn.energy.base.BasePresenter;
import tties.cn.energy.model.IModel.DataModel;
import tties.cn.energy.model.IModel.IDataModel;
import tties.cn.energy.model.result.DataAllbean;
import tties.cn.energy.model.result.Databean;
import tties.cn.energy.utils.DateUtil;
import tties.cn.energy.view.iview.IDataView;
import tties.cn.energy.view.iview.IEnergy_BaseenergyView;

/**
 * Created by li on 2018/4/11
 * description：
 * author：guojlli
 */

public class Energy_ElectricalPersenter extends BasePresenter<IDataView> {
    private static final String TAG = "Energy_ElectricalPersen";
    IDataView view;
    IDataModel model;
    DataAllbean dataAllbean=new DataAllbean();
    public Energy_ElectricalPersenter(IDataView view){
        this.view=view;
        model=new DataModel();
    }
    public void getEnergy_Electrical(){
        String baseDate= DateUtil.getCurrentYear()+"-"+(DateUtil.getCurrentMonth()-1);
        Map<String,Object> map=new HashMap<>();
        map.put("userName",dataAllbean.getUserName());
        map.put("password",dataAllbean.getPassword());
        map.put("objId",dataAllbean.getEnergyledgerId());
        map.put("objType",1);//对象类型（1、分户；2、计量点）
        map.put("baseDate",baseDate);
        map.put("eleAccountId",dataAllbean.getEleAccountId());
        map.put("dateType",1);//日期类型（1、月   2年；）
        Log.i(TAG, "onErrordata: "+dataAllbean.getUserName());
        Log.i(TAG, "onErrordata: "+dataAllbean.getPassword());
        Log.i(TAG, "onErrordata: "+dataAllbean.getEnergyledgerId());
        Log.i(TAG, "onErrordata: "+baseDate);
        Log.i(TAG, "onErrordata: "+dataAllbean.getEleAccountId());
        model.getDataData().getData(map).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Databean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Databean value) {
                        if(value!=null){
                            view.setDataData(value);
                        }else{
                            Log.i(TAG, "onError: "+"数据有误");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "onErrordata: "+e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public void getEnergy_ElectricalChart(){
        Map<String,Object> map=new HashMap<>();
        map.put("userName",dataAllbean.getUserName());
        map.put("password",dataAllbean.getPassword());
        map.put("objId",dataAllbean.getEnergyledgerId());
        map.put("objType",1);
        map.put("baseDate",dataAllbean.getBaseData());
        map.put("eleAccountId",dataAllbean.getEleAccountId());
        map.put("dateType",2);
        Log.i(TAG, "onErrordata: "+dataAllbean.getUserName());
        Log.i(TAG, "onErrordata: "+dataAllbean.getPassword());
        Log.i(TAG, "onErrordata: "+dataAllbean.getEnergyledgerId());
        Log.i(TAG, "onErrordata: "+dataAllbean.getBaseData());
        Log.i(TAG, "onErrordata: "+dataAllbean.getEleAccountId());
        model.getDataData().getData(map).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Databean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Databean value) {
                        if(value!=null){
                            view.setDataChartData(value);
                        }else{
                            Log.i(TAG, "onError: "+"数据有误");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "onErrorchart: "+e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
