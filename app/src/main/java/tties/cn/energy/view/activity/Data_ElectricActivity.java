package tties.cn.energy.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import tties.cn.energy.R;
import tties.cn.energy.base.BaseActivity;
import tties.cn.energy.base.BasePresenter;
import tties.cn.energy.model.result.Data_Electricbean;
import tties.cn.energy.presenter.Data_ElectricPresenter;
import tties.cn.energy.view.iview.IData_ElectricView;

/**
 * 电量数据
 */
public class Data_ElectricActivity extends BaseActivity<Data_ElectricPresenter> implements IData_ElectricView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void createPresenter() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_data__electric;
    }


    @Override
    public void setData_ElectricData(Data_Electricbean bean) {

    }
}
