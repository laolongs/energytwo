package tties.cn.energy.presenter;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import tties.cn.energy.base.BasePresenter;
import tties.cn.energy.model.IModel.IIdentity_PassModel;
import tties.cn.energy.model.IModel.Identity_PassModel;
import tties.cn.energy.model.result.Identity_Passbean;
import tties.cn.energy.view.iview.IIdentity_PassView;

/**
 * Created by li on 2018/4/6
 * description：
 * author：guojlli
 */

public class Identity_PassPresenter extends BasePresenter<IIdentity_PassView> {
    private static final String TAG = "Identity_PassPresenter";
    IIdentity_PassView view;
    IIdentity_PassModel model;

    public Identity_PassPresenter(IIdentity_PassView view) {
        this.view = view;
        this.model=new Identity_PassModel();
    }
    public void getIdentity_Pass(){
//        String oldPass = view.getOldPass();
//        String newPass = view.getNewPass();
        Map<String,Object> map=new HashMap<>();
//        map.put("accountId","");
//        map.put("oldPassword","");
//        map.put("password","");
        model.getIdentity_PassData().gettIdentity_Pass(map).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Identity_Passbean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Identity_Passbean value) {

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
