package tties.cn.energy.presenter;

import android.util.Log;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import tties.cn.energy.base.BasePresenter;
import tties.cn.energy.model.IModel.OpsDataModel;
import tties.cn.energy.model.result.Opsbean;
import tties.cn.energy.view.iview.IOpsView;

/**
 * Created by li on 2018/3/23
 * description：
 * author：guojlli
 */

public class OpsPresenter extends BasePresenter<IOpsView> {
    IOpsView view;
    OpsDataModel model;
    public OpsPresenter(IOpsView view) {
        this.view = view;
        this.model = new OpsDataModel();
    }
    public void getOpsRightData(){
        Map<String,Object> map=new HashMap<>();
        map.put("compamyId",53);
        map.put("staffId",169);
        model.getOpsData().getOps(map).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Opsbean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Opsbean value) {
                        Log.i("-----------", "onNext: ");
                        view.setOpsRightData(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("-----------", "onError: ");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
