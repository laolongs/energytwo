package tties.cn.energy.presenter;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import tties.cn.energy.base.BasePresenter;
import tties.cn.energy.common.Constants;
import tties.cn.energy.model.IModel.Energy_MonthlyModel;
import tties.cn.energy.model.IModel.Energy_TransformerModel;
import tties.cn.energy.model.IModel.IEnergy_MonthlyModel;
import tties.cn.energy.model.IModel.IEnergy_TransformerModel;
import tties.cn.energy.model.result.Energy_Monthlybean;
import tties.cn.energy.model.result.Energy_TransformerDamgebean;
import tties.cn.energy.model.result.Energy_TransformerListbean;
import tties.cn.energy.model.result.Energy_TransformerTemperaturebean;
import tties.cn.energy.model.result.Energy_TransformerVolumebean;
import tties.cn.energy.utils.ACache;
import tties.cn.energy.view.iview.IEnergy_MonthlyView;
import tties.cn.energy.view.iview.IEnergy_TransformerView;

/**
 * Created by li on 2018/4/3
 * description：
 * author：guojlli
 */

public class Energy_MonthlyPresenter extends BasePresenter<IEnergy_MonthlyView> {
    private static final String TAG = "Energy_MonthlyPresenter";
    IEnergy_MonthlyView view;
    IEnergy_MonthlyModel model;
    public Energy_MonthlyPresenter(IEnergy_MonthlyView view){
        this.view=view;
        model=new Energy_MonthlyModel();
    }
    //月报， 运维 能效
    public void getEnergy_Monthly(int reportType){
        String companyid = ACache.getInstance().getAsString(Constants.CACHE_OPS_COMPANDID);
        Map<String,Object> map=new HashMap<>();
        map.put("reportType",reportType);
        map.put("companyId",companyid);
        model.getEnergy_MonthlyData().getEnergy_Monthly(map).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Energy_Monthlybean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Energy_Monthlybean value) {
                        if(value!=null){
                            view.setEnergy_MonthlyData(value);
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
