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
import tties.cn.energy.base.BaseView;
import tties.cn.energy.model.IModel.Data_ElectricModel;
import tties.cn.energy.model.IModel.IData_ElectricModel;
import tties.cn.energy.model.result.AllElectricitybean;
import tties.cn.energy.model.result.Data_Electricbean;
import tties.cn.energy.model.result.Data_Nobean;
import tties.cn.energy.view.dialog.BottomStyleDialog;
import tties.cn.energy.view.iview.IData_ElectricView;

/**
 * Created by li on 2018/4/3
 * description：
 * author：guojlli
 */

public class Data_ElectricPresenter extends BasePresenter<IData_ElectricView> {
    private static final String TAG = "Data_ElectricPresenter";
    IData_ElectricView view;
    IData_ElectricModel model;
    public Data_ElectricPresenter(IData_ElectricView view){
        this.view=view;
        model=new Data_ElectricModel();
    }
    public void getData_Electric(int dataType){
        Map<String,Object> map=new HashMap<>();
        map.put("userName","test");
        map.put("password","E10ADC3949BA59ABBE56E057F20F883E");
        map.put("objId","1486536312217");
        map.put("objType","2");
        map.put("dataType",dataType);
        map.put("baseDate","2017-03-27");
        model.getData_ElectricData().getData_Electric(map).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Data_Electricbean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Data_Electricbean value) {
                        if(value!=null){
                            view.setData_ElectricData(value);
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
