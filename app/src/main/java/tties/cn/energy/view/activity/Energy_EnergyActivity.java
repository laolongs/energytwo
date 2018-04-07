package tties.cn.energy.view.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import tties.cn.energy.R;
import tties.cn.energy.base.BaseActivity;
import tties.cn.energy.model.result.Energy_Monthlybean;
import tties.cn.energy.presenter.Energy_MonthlyPresenter;
import tties.cn.energy.view.adapter.MyMonthlyAdapter;
import tties.cn.energy.view.iview.IEnergy_MonthlyView;

/**
 * 能效月报
 */
public class Energy_EnergyActivity extends BaseActivity<Energy_MonthlyPresenter> implements IEnergy_MonthlyView {

    @BindView(R.id.toolbar_left)
    ImageView toolbarLeft;
    @BindView(R.id.toolbar_text)
    TextView toolbarText;
    @BindView(R.id.data_toolbar)
    Toolbar dataToolbar;
    @BindView(R.id.energy_energy_Recy)
    RecyclerView energyEnergyRecy;
    @BindView(R.id.enerey_energy_ll)
    LinearLayout enereyEnergyLl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        mPresenter.getEnergy_Monthly(2);
        toolbarText.setText("能效月报");
        toolbarLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        energyEnergyRecy.setLayoutManager(new LinearLayoutManager(Energy_EnergyActivity.this));
    }

    @Override
    protected void createPresenter() {
        mPresenter=new Energy_MonthlyPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_energy__energy;
    }

    @Override
    public void setEnergy_MonthlyData(Energy_Monthlybean bean) {
        if(bean.getResult().size()==0){
            enereyEnergyLl.setVisibility(View.VISIBLE);
        }else{
            enereyEnergyLl.setVisibility(View.GONE);
        }

        MyMonthlyAdapter adapter = new MyMonthlyAdapter(this, bean);
        energyEnergyRecy.setAdapter(adapter);
    }
}
