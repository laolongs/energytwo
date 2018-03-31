package tties.cn.energy.presenter;

import android.support.v4.app.Fragment;
import android.util.Log;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import tties.cn.energy.base.BasePresenter;
import tties.cn.energy.model.IModel.OpsDataModel;
import tties.cn.energy.model.IModel.QuestionsModel;
import tties.cn.energy.model.result.Opsbean;
import tties.cn.energy.view.iview.IOpsView;
import tties.cn.energy.view.iview.IQuestionsView;

/**
 * Created by li on 2018/3/23
 * description：
 * author：guojlli
 */

public class QuestionsPresenter extends BasePresenter<IQuestionsView> {
    private static final String TAG = "QuestionsPresenter";
    IQuestionsView view;
    QuestionsModel model;

    public QuestionsPresenter(IQuestionsView view) {
        this.view = view;
        this.model = new QuestionsModel();
    }
    public void getQuestionsTabData(){
        model.getQuestaionsTab(new QuestionsModel.CallBack() {
            @Override
            public void getTabArray(String[] array, List<Fragment> list) {
                view.setTabData(array,list);
            }
        });
    }
    public void getQuestion(String questionId){
        HashMap<String,Object> map=new HashMap<>();
//        map.put("compamyId",bean.getResult().getEnergyLedgerList().get(0).getCompanyId());
//        map.put("staffId",bean.getResult().getMaintUser().getStaffId());
        map.put("companyId",54);
        map.put("patrolType",0);
        map.put("pagesize",10);
        map.put("pagenum",1);
        map.put("questionId",170);
        model.getQuestionsData().getOps(map).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Opsbean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Opsbean value) {
                        if(value!=null&&value.getErrorCode()==0){
                            Log.i(TAG, "onNext: "+"有数据");
                            view.setQuestionData(value);
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
