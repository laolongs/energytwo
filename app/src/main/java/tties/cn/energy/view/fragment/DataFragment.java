package tties.cn.energy.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import tties.cn.energy.R;
import tties.cn.energy.base.BaseFragment;
import tties.cn.energy.chart.LineDataChart;
import tties.cn.energy.presenter.MainPresenter;
import tties.cn.energy.view.activity.DataActivity;
import tties.cn.energy.view.activity.Data_CurrentActivity;
import tties.cn.energy.view.activity.Data_ElectricActivity;
import tties.cn.energy.view.activity.Data_FactorActivity;
import tties.cn.energy.view.activity.Data_NoActivity;
import tties.cn.energy.view.activity.Data_PressActivity;
import tties.cn.energy.view.activity.Data_RateActivity;

/**
 * Created by li on 2018/3/21
 * description：
 * author：guojlli
 */

public class DataFragment extends BaseFragment<MainPresenter> implements View.OnClickListener {

    @BindView(R.id.data_toolbar_text)
    TextView toolbarText;
    @BindView(R.id.data_toolbar)
    Toolbar dataToolbar;
    @BindView(R.id.data_time_tv)
    TextView dataTimeTv;
    @BindView(R.id.chart)
    LineDataChart chart;
    @BindView(R.id.data_charge)
    LinearLayout dataCharge;
    @BindView(R.id.data_amount)
    LinearLayout dataAmount;
    @BindView(R.id.data_rate)
    LinearLayout dataRate;
    @BindView(R.id.data_factor)
    LinearLayout dataFactor;
    @BindView(R.id.data_flow)
    LinearLayout dataFlow;
    @BindView(R.id.data_no)
    LinearLayout dataNo;
    @BindView(R.id.data_press)
    LinearLayout dataPress;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View inflate = inflater.inflate(R.layout.fragment_data, null);
        unbinder = ButterKnife.bind(this, inflate);
        initView();
        dataCharge.setOnClickListener(this);
        dataAmount.setOnClickListener(this);
        dataRate.setOnClickListener(this);
        dataFactor.setOnClickListener(this);
        dataFlow.setOnClickListener(this);
        dataNo.setOnClickListener(this);
        dataPress.setOnClickListener(this);
        return inflate;
    }

    private void initView() {
        toolbarText.setText("电力数据");
    }

    @Override
    protected void createPresenter() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()){
            //电费数据
            case R.id.data_charge:
                intent=new Intent(getActivity(), DataActivity.class);
                startActivity(intent);
                break;
            //电量数据
            case R.id.data_amount:
                intent=new Intent(getActivity(), Data_ElectricActivity.class);
                startActivity(intent);
                break;
            //功率数据
            case R.id.data_rate:
                intent=new Intent(getActivity(), Data_RateActivity.class);
                startActivity(intent);
                break;
            //功率因素
            case R.id.data_factor:
                intent=new Intent(getActivity(), Data_FactorActivity.class);
                startActivity(intent);
                break;
                //电流电压
            case R.id.data_flow:
                intent=new Intent(getActivity(), Data_CurrentActivity.class);
                startActivity(intent);
                break;
                //电流不平衡
            case R.id.data_no:
                intent=new Intent(getActivity(), Data_NoActivity.class);
                startActivity(intent);
                break;
                //电压不平衡
            case R.id.data_press:
                intent=new Intent(getActivity(), Data_PressActivity.class);
                startActivity(intent);
                break;



        }
    }
}
