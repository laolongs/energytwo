package tties.cn.energy.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import tties.cn.energy.R;
import tties.cn.energy.base.BaseActivity;
import tties.cn.energy.presenter.MainPresenter;
import tties.cn.energy.view.iview.IMainView;

/**
 * 基本电量优化
 */
public class Energy_BaseenergyActivity extends BaseActivity<MainPresenter> implements IMainView {


    @BindView(R.id.energy_toolbar_text)
    TextView energyToolbarText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        energyToolbarText.setText("基本电费优化");
    }

    @Override
    protected void createPresenter() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_energy__baseenergy;
    }

    @Override
    public void setViewPageData(List<View> list) {

    }
}
