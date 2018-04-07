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
import tties.cn.energy.model.IModel.Energy_TransformerModel;
import tties.cn.energy.model.IModel.IDataModel;
import tties.cn.energy.model.IModel.IEnergy_TransformerModel;
import tties.cn.energy.model.result.AllElectricitybean;
import tties.cn.energy.model.result.Databean;
import tties.cn.energy.model.result.Energy_TransformerDamgebean;
import tties.cn.energy.model.result.Energy_TransformerListbean;
import tties.cn.energy.model.result.Energy_TransformerTemperaturebean;
import tties.cn.energy.model.result.Energy_TransformerVolumebean;
import tties.cn.energy.view.iview.IDataView;
import tties.cn.energy.view.iview.IEnergy_TransformerView;

/**
 * Created by li on 2018/4/3
 * description：
 * author：guojlli
 */

public class Energy_TransformerPresenter extends BasePresenter<IEnergy_TransformerView> {
    private static final String TAG = "Energy_TransformerPrese";
    IEnergy_TransformerView view;
    IEnergy_TransformerModel model;
    public Energy_TransformerPresenter(IEnergy_TransformerView view){
        this.view=view;
        model=new Energy_TransformerModel();
    }
    //变压器列表
    public void getEnergy_TransformerList(){
        Map<String,Object> map=new HashMap<>();
        map.put("eleAccountId","54");
        model.getEnergy_TransformerListData().getEnergy_TransformerList(map).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Energy_TransformerListbean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Energy_TransformerListbean value) {
                        if(value!=null&&value.getErrorCode()==0){
                            view.setEnergy_TransformerListbeanData(value);
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
    //变压器变损
    public void getEnergy_TransformerDamge(int transformerId){
        Map<String,Object> map=new HashMap<>();
        map.put("transformerId",transformerId);
        map.put("eleAccountId",54);
        map.put("baseDate","2018");
        model.getEnergy_TransformerDamgeData().getEnergy_TransformerDamge(map).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Energy_TransformerDamgebean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Energy_TransformerDamgebean value) {
                        if(value!=null&&value.getErrorCode()==0){
                            view.setEnergy_TransformerDamgebeanData(value);
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
    //变压器温度
    public void getEnergy_TransformerTemperature(int transformerId){
        Map<String,Object> map=new HashMap<>();
        map.put("transformerId",transformerId);
        map.put("baseDate","2018");
        model.getEnergy_TransformerTemperatureData().getEnergy_TransformerTemperature(map).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Energy_TransformerTemperaturebean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Energy_TransformerTemperaturebean value) {
                        if(value!=null&&value.getErrorCode()==0){
                            view.setEnergy_TransformerTemperaturebeanData(value);
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
    //变压器容量
    public void getEnergy_TransformerVolume(int transformerId){
        Map<String,Object> map=new HashMap<>();
        map.put("userName","test");
        map.put("password","E10ADC3949BA59ABBE56E057F20F883E");
        map.put("transformerId",transformerId);
        map.put("eleAccountId","54");
        map.put("baseDate","2018");
        model.getEnergy_TransformerVolumeData().getEnergy_TransformerVolume(map).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Energy_TransformerVolumebean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Energy_TransformerVolumebean value) {
                        if(value!=null&&value.getErrorCode()==0){
                            view.setEnergy_TransformerVolumebeanData(value);
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
