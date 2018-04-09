package tties.cn.energy.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import tties.cn.energy.R;
import tties.cn.energy.base.BaseActivity;
import tties.cn.energy.chart.BarDataChart;
import tties.cn.energy.chart.BarDataCharttwo;
import tties.cn.energy.chart.LineDataChart;
import tties.cn.energy.model.result.AllElectricitybean;
import tties.cn.energy.model.result.DataFragmentbean;
import tties.cn.energy.model.result.Databean;
import tties.cn.energy.presenter.DataPresenter;
import tties.cn.energy.presenter.Energy_ForcePresenter;
import tties.cn.energy.utils.StringUtil;
import tties.cn.energy.view.iview.IDataView;
import tties.cn.energy.view.iview.IEnergy_ForceView;

/**
 * 力调电量优化
 */
public class Energy_ForceActivity extends BaseActivity<Energy_ForcePresenter> implements IEnergy_ForceView{

    @BindView(R.id.toolbar_left)
    ImageView toolbarLeft;
    @BindView(R.id.toolbar_text)
    TextView toolbarText;
    @BindView(R.id.energy_force_havekw)
    TextView energyForceHavekw;
    @BindView(R.id.energy_force_nokvar)
    TextView energyForceNokvar;
    @BindView(R.id.energy_force_num)
    TextView energyForceNum;
    @BindView(R.id.energy_force_select)
    ImageView energyForceSelect;
    @BindView(R.id.energy_force_chart1)
    LineDataChart energyForceChart1;
    @BindView(R.id.energy_force_chart2)
    LineDataChart energyForceChart2;
    int mYear;
    int mMonth;
    //这里指选择的年
    int year=2018;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initView();

    }

    private void initView() {
        Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR); // 获取当前年份
        mMonth = c.get(Calendar.MONTH)+1;// 获取当前月份
        //当月
        mPresenter.getEnergy_Force();
        //当年
        mPresenter.getEnergy_ForcechartData();
        mPresenter.getEnergy_Forcecharge();
//        XAxis xAxis =energyForceChart2.getXAxis();
//        xAxis.setDrawAxisLine(true);
//        YAxis axisLeft = energyForceChart2.getAxisLeft();
//        axisLeft.setDrawZeroLine(true);
        //每月节约活每年节约
//        if(mYear==year){
//            mPresenter.getEnergy_Forcecharge(mYear+"-"+mMonth,mMonth+1);
//        }else{
//            mPresenter.getEnergy_Forcecharge("这里填写当前年加12月就可以了",13);
//        }

        toolbarText.setText("力调电量优化");
        toolbarLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //选择年
        energyForceSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    protected void createPresenter() {
        mPresenter = new Energy_ForcePresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_energy__force;
    }


    @Override
    public void setEnergy_ForceData(Databean bean) {
        //有功功率
        energyForceHavekw.setText(bean.getDataList().get(0).getAp()+"kW");
        //无功
        energyForceNokvar.setText(bean.getDataList().get(0).getRp()+"kVar");
        //因数
        energyForceNum.setText(bean.getDataList().get(0).getRate()+"");
    }

    @Override
    public void setEnergy_ForceChartData(Databean bean) {
        ArrayList<Entry> values = new ArrayList<>();
        List<String> listDate = new ArrayList<String>();
        for (int i = 0; i < bean.getDataList().size(); i++) {
            Entry entry = new Entry(i, 0f);
            entry.setY((float) bean.getDataList().get(i).getRate());
            values.add(entry);
            String[] split = StringUtil.split(bean.getDataList().get(i).getBaseDate(), "-");
            listDate.add(split[1]);
        }
        energyForceChart1.setDataSet(values, "");
        energyForceChart1.setDayXAxis(listDate);
        energyForceChart1.loadChart();
    }

    @Override
    public void setEnergy_ForceCharge(DataFragmentbean bean) {
        double num;
        ArrayList<Entry> values = new ArrayList<>();
        List<String> listDate = new ArrayList<String>();
        for (int i = 0; i < bean.getDataList().size(); i++) {
            num= bean.getDataList().get(i).getFouceSum()-bean.getDataList().get(i++).getFouceSum();
            i--;
            Entry entry = new Entry(i, 0f);
            entry.setY((float) num);
            values.add(entry);
            String[] split = StringUtil.split(bean.getDataList().get(i).getBaseDate(), "-");
            listDate.add(split[1]);
        }
        energyForceChart2.setDataSet(values, "");
        energyForceChart2.setDayXAxis(listDate);
        energyForceChart2.loadChart();
    }
}
