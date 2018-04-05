package tties.cn.energy.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import tties.cn.energy.R;
import tties.cn.energy.base.BaseFragment;
import tties.cn.energy.chart.LineDataChart;
import tties.cn.energy.model.result.DataFragmentbean;
import tties.cn.energy.presenter.DataFragmentPresenter;
import tties.cn.energy.utils.StringUtil;
import tties.cn.energy.view.activity.DataActivity;
import tties.cn.energy.view.activity.Data_CurrentActivity;
import tties.cn.energy.view.activity.Data_ElectricActivity;
import tties.cn.energy.view.activity.Data_FactorActivity;
import tties.cn.energy.view.activity.Data_NoActivity;
import tties.cn.energy.view.activity.Data_PressActivity;
import tties.cn.energy.view.activity.Data_RateActivity;
import tties.cn.energy.view.iview.IDataFragmentView;

/**
 * Created by li on 2018/3/21
 * description：
 * author：guojlli
 */

public class DataFragment extends BaseFragment<DataFragmentPresenter> implements View.OnClickListener, IDataFragmentView {

    @BindView(R.id.data_toolbar_text)
    TextView toolbarText;
    @BindView(R.id.data_toolbar)
    Toolbar dataToolbar;
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
    @BindView(R.id.datafragment_time_tv)
    TextView datafragmentTimeTv;
    @BindView(R.id.datafragment_chart)
    LineDataChart datafragmentChart;
    @BindView(R.id.datafragment_price)
    TextView datafragmentPrice;
    int mYear;
    int mMonth;
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
        Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR); // 获取当前年份
        mMonth = c.get(Calendar.MONTH)+1;// 获取当前月份
        datafragmentTimeTv.setText(mMonth+"月");
        toolbarText.setText("电力数据");
        mPresenter.getDataFragment();
    }

    @Override
    protected void createPresenter() {
        mPresenter = new DataFragmentPresenter(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            //电费数据
            case R.id.data_charge:
                intent = new Intent(getActivity(), DataActivity.class);
                startActivity(intent);
                break;
            //电量数据
            case R.id.data_amount:
                intent = new Intent(getActivity(), Data_ElectricActivity.class);
                startActivity(intent);
                break;
            //功率数据
            case R.id.data_rate:
                intent = new Intent(getActivity(), Data_RateActivity.class);
                startActivity(intent);
                break;
            //功率因素
            case R.id.data_factor:
                intent = new Intent(getActivity(), Data_FactorActivity.class);
                startActivity(intent);
                break;
            //电流电压
            case R.id.data_flow:
                intent = new Intent(getActivity(), Data_CurrentActivity.class);
                startActivity(intent);
                break;
            //电流不平衡
            case R.id.data_no:
                intent = new Intent(getActivity(), Data_NoActivity.class);
                startActivity(intent);
                break;
            //电压不平衡
            case R.id.data_press:
                intent = new Intent(getActivity(), Data_PressActivity.class);
                startActivity(intent);
                break;


        }
    }

    @Override
    public void setDataFragmentData(DataFragmentbean bean) {
        ArrayList<Entry> values = new ArrayList<>();
        for (int i = 0; i < bean.getDataList().size(); i++) {
            Entry entry = new Entry(i, 0f);
            entry.setY((float) bean.getDataList().get(i).getCost());
            values.add(entry);
        }
        datafragmentChart.setDataSet(values, "");
        datafragmentChart.loadChart();
        datafragmentPrice.setText(bean.getDataList().get(0).getCost()+"");
    }
}
