package tties.cn.energy.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import tties.cn.energy.R;
import tties.cn.energy.base.BaseActivity;
import tties.cn.energy.common.MyProgressRound;
import tties.cn.energy.presenter.MainPresenter;
import tties.cn.energy.view.iview.IMainView;

/**
 * 电度电量优化
 */
public class Energy_ElectricalActivity extends BaseActivity<MainPresenter> implements IMainView {

    @BindView(R.id.data_toolbar_text)
    TextView dataToolbarText;
    @BindView(R.id.electrical_myview)
    MyProgressRound electricalMyview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        dataToolbarText.setText("电度电费优化");
        electricalMyview.setProgressMax(160);
        electricalMyview.setProgressCenter(130);
        electricalMyview.setProgressMin(80);
    }

    @Override
    protected void createPresenter() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_energy__electrical;
    }

    @Override
    public void setViewPageData(List<View> list) {

    }
}
