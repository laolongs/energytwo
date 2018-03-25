package tties.cn.energy.presenter;

import android.support.v4.app.Fragment;

import java.util.List;

import tties.cn.energy.base.BasePresenter;
import tties.cn.energy.model.IModel.OpsDataModel;
import tties.cn.energy.model.IModel.QuestionsModel;
import tties.cn.energy.view.iview.IOpsView;
import tties.cn.energy.view.iview.IQuestionsView;

/**
 * Created by li on 2018/3/23
 * description：
 * author：guojlli
 */

public class QuestionsPresenter extends BasePresenter<IQuestionsView> {
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
}
