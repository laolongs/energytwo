package tties.cn.energy.presenter;

import android.content.Context;
import android.view.View;

import java.lang.ref.SoftReference;
import java.util.List;

import tties.cn.energy.base.BasePresenter;
import tties.cn.energy.model.IModel.IMainModel;
import tties.cn.energy.model.IModel.MainModel;
import tties.cn.energy.view.MainActivity;
import tties.cn.energy.view.iview.IMainView;

/**
 * mainpresenter
 */

public class MainPresenter extends BasePresenter<IMainView> {
    IMainView view;
    IMainModel mainModel;
    public MainPresenter(MainActivity view){
       this.view=view;
        mainModel=new MainModel();

    }
    public void showViewpageData(List<View> list){
        view.setViewPageData(list);
    }
}
