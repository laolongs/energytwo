package tties.cn.energy.presenter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import tties.cn.energy.api.RetrofitApiPub;
import tties.cn.energy.base.BasePresenter;
import tties.cn.energy.common.Constants;
import tties.cn.energy.common.EventKind;
import tties.cn.energy.model.IModel.Data_PressModel;
import tties.cn.energy.model.IModel.IData_PressModel;
import tties.cn.energy.model.IModel.ILoginModel;
import tties.cn.energy.model.IModel.LoginModel;
import tties.cn.energy.model.bean.EventBusBean;
import tties.cn.energy.model.result.AllElectricitybean;
import tties.cn.energy.model.result.Data_Pressbean;
import tties.cn.energy.model.result.Loginbean;
import tties.cn.energy.utils.ACache;
import tties.cn.energy.utils.EncryptUtil;
import tties.cn.energy.utils.StringUtil;
import tties.cn.energy.utils.ToastUtil;
import tties.cn.energy.view.MainActivity;
import tties.cn.energy.view.dialog.CriProgressDialog;
import tties.cn.energy.view.iview.IData_PressView;
import tties.cn.energy.view.iview.ILoginView;

/**
 * mainpresenter
 */

public class Data_PressPresenter extends BasePresenter<IData_PressView>  {
    private static final String TAG = "Data_PressPresenter";
    IData_PressView view;
    IData_PressModel model;
    public Data_PressPresenter(IData_PressView view) {
        this.view = view;
        this.model = new Data_PressModel();
    }
    public void getData_PressData(int dataType){
        Map<String,Object> map=new HashMap<>();
        map.put("userName","admin");
        map.put("password","AEC60231D83FE6CF81444BC536596887");
        map.put("objId","1486536481282");
        map.put("baseDate","2017-02");
        map.put("dataType",dataType);
        model.getData_PressData().getData_Press(map).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Data_Pressbean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Data_Pressbean value) {
                        view.setData_PressData(value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public void getAllElectricityData() {
        Map<String,Object> map=new HashMap<>();
        map.put("userName","南洋印染");
        map.put("password","96E79218965EB72C92A549DD5A330112");
        map.put("objId","1486535776800");
        map.put("objType",1);
        model.getAllElectricitySendData().getAllElectricity(map)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new Observer<AllElectricitybean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(AllElectricitybean value) {
                Log.i(TAG, "onNext: "+value.getMeterList().size());
                view.setAllElectricitySend(value);
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
