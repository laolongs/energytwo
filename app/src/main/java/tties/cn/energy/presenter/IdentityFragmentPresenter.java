package tties.cn.energy.presenter;

import android.util.Log;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import tties.cn.energy.base.BasePresenter;
import tties.cn.energy.common.Constants;
import tties.cn.energy.model.IModel.IIdentityFragmentModel;
import tties.cn.energy.model.IModel.IdentityFragmentModel;
import tties.cn.energy.model.result.OpsLoginbean;
import tties.cn.energy.utils.ACache;
import tties.cn.energy.view.iview.IIdentityFragmentView;

/**
 * Created by li on 2018/3/30
 * description：
 * author：guojlli
 */

public class IdentityFragmentPresenter extends BasePresenter<IIdentityFragmentView> {
    private static final String TAG = "IdentityFragmentPresent";
    IIdentityFragmentView view;
    IIdentityFragmentModel model;
    public IdentityFragmentPresenter(IIdentityFragmentView view){
        this.view=view;
        model= new IdentityFragmentModel();
    }
    public void getOpsloginData(String accountid){
        model.getOpsLoginData().getOpsLogin(accountid).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<OpsLoginbean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(OpsLoginbean value) {
                        if(value != null&&value.getErrorCode()==0){
                            ACache.getInstance().put(Constants.CACHE_OPSLOGIN_STATUS,value);
                            Log.i(TAG, "onNext: "+value.getResult().getMaintUser().getStaffName());
                            view.getOpsLoginData(value);
                        }else{
                            Log.i(TAG, "getOpsLoginData: "+"请求失败");
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
