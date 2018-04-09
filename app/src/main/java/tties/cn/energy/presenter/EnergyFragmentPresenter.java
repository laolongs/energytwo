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
import tties.cn.energy.common.Constants;
import tties.cn.energy.model.IModel.Data_ElectricModel;
import tties.cn.energy.model.IModel.EnergyFragmentModel;
import tties.cn.energy.model.IModel.IData_ElectricModel;
import tties.cn.energy.model.IModel.IEnergyFragmentModel;
import tties.cn.energy.model.result.AllElectricitybean;
import tties.cn.energy.model.result.Data_Electricbean;
import tties.cn.energy.model.result.EnergyFragmentbean;
import tties.cn.energy.model.result.Loginbean;
import tties.cn.energy.utils.ACache;
import tties.cn.energy.view.iview.IData_ElectricView;
import tties.cn.energy.view.iview.IEnergyFragmentView;

/**
 * Created by li on 2018/4/3
 * description：
 * author：guojlli
 */

public class EnergyFragmentPresenter extends BasePresenter<IEnergyFragmentView> {
    private static final String TAG = "EnergyFragmentPresenter";
    IEnergyFragmentView view;
    IEnergyFragmentModel model;
    public EnergyFragmentPresenter(IEnergyFragmentView view){
        this.view=view;
        model=new EnergyFragmentModel();
    }
    public void getEnergyFragment(){
        String name = ACache.getInstance().getAsObject(Constants.CACHE_LOGIN_USERNAME);
        String pass = ACache.getInstance().getAsObject(Constants.CACHE_LOGIN_PASSWORDMD5);
        Loginbean bean = ACache.getInstance().getAsObject(Constants.CACHE_USERINFO);
        Map<String,Object> map=new HashMap<>();
        map.put("userName",name);
        map.put("password",pass);
        map.put("accountId",bean.getAccountId());
        model.getEnergyFragmentData().getEnergyFragment(map).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<EnergyFragmentbean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(EnergyFragmentbean value) {
                        if(value!=null){
                            view.setEnergyFragmentData(value);
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
