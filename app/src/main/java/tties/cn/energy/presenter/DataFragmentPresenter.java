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
import tties.cn.energy.model.IModel.DataFragmentModel;
import tties.cn.energy.model.IModel.DataModel;
import tties.cn.energy.model.IModel.IDataFragmentModel;
import tties.cn.energy.model.IModel.IDataModel;
import tties.cn.energy.model.result.AllElectricitybean;
import tties.cn.energy.model.result.DataAllbean;
import tties.cn.energy.model.result.DataFragmentbean;
import tties.cn.energy.model.result.Data_Electricbean;
import tties.cn.energy.utils.DateUtil;
import tties.cn.energy.view.iview.IDataFragmentView;
import tties.cn.energy.view.iview.IDataView;

/**
 * Created by li on 2018/4/3
 * description：
 * author：guojlli
 */

public class DataFragmentPresenter extends BasePresenter<IDataFragmentView> {
    private static final String TAG = "DataFragmentPresenter";
    IDataFragmentView view;
    IDataFragmentModel model;
    DataAllbean dataAllbean=new DataAllbean();
    public DataFragmentPresenter(IDataFragmentView view){
        this.view=view;
        model=new DataFragmentModel();
    }
    public void getDataFragment(){
        String baseDate=DateUtil.getCurrentYear()+"-"+(DateUtil.getCurrentMonth()-1);
        Map<String,Object> map=new HashMap<>();
        map.put("userName",dataAllbean.getUserName());
        map.put("password",dataAllbean.getPassword());
        map.put("objId",dataAllbean.getEnergyledgerId());
        map.put("objType",1);
        map.put("baseDate", baseDate);
        map.put("count","12");
        map.put("eleAccountId",dataAllbean.getEleAccountId());

        model.getDataFragmentData().getDataFragemet(map).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DataFragmentbean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DataFragmentbean value) {
                        if(value!=null){
                            view.setDataFragmentData(value);
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
