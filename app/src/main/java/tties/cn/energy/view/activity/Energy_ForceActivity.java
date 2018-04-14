package tties.cn.energy.view.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import tties.cn.energy.R;
import tties.cn.energy.base.BaseActivity;
import tties.cn.energy.chart.BarDataChart;
import tties.cn.energy.chart.BarDataCharttwo;
import tties.cn.energy.chart.LineDataChart;
import tties.cn.energy.common.Constants;
import tties.cn.energy.model.result.DataAllbean;
import tties.cn.energy.model.result.DataFragmentbean;
import tties.cn.energy.model.result.Databean;
import tties.cn.energy.presenter.Energy_ForcePresenter;
import tties.cn.energy.utils.ACache;
import tties.cn.energy.utils.DateUtil;
import tties.cn.energy.utils.StringUtil;
import tties.cn.energy.view.dialog.MyTimePickerDialog;
import tties.cn.energy.view.iview.IEnergy_ForceView;

/**
 * 力调电量优化
 */
public class Energy_ForceActivity extends BaseActivity<Energy_ForcePresenter> implements IEnergy_ForceView {
    private static final String TAG = "Energy_ForceActivity";
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
    BarDataCharttwo energyForceChart2;
    @BindView(R.id.energy_force_year)
    TextView energyForceYear;
    MyTimePickerDialog dialogtime;
    int currentYear;
    int currentMonth;
    DataAllbean dataAllbean=new DataAllbean();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initView();

    }

    private void initView() {
        dialogtime = new MyTimePickerDialog();
        //当月
        mPresenter.getEnergy_Force();

        mPresenter.getEnergy_Forcecharge();
        toolbarText.setText("力调电量优化");
        toolbarLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        energyForceYear.setText(DateUtil.getCurrentYear()+"年");
        //选择年
        energyForceSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogtime.getTimeYearPickerDialog(Energy_ForceActivity.this);
                dialogtime.setOnTimeClick(new MyTimePickerDialog.OnTimeClick() {
                    @Override
                    public void OnTimeClickListener(String text) {
                        ACache.getInstance().put(Constants.CACHE_OPS_BASEDATE, text+"-01");
                        energyForceYear.setText(text+"年");
                        mPresenter.getEnergy_ForcechartData();
                    }
                });
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
        if (bean.getDataList().size() > 0) {
            ACache.getInstance().put(Constants.CACHE_OPS_BASEDATE, DateUtil.getCurrentYear()+"-01");
            mPresenter.getEnergy_ForcechartData();
            //有功功率
            energyForceHavekw.setText(bean.getDataList().get(0).getAp() + "kW");
            //无功
            energyForceNokvar.setText(bean.getDataList().get(0).getRp() + "kVar");
            //因数
            energyForceNum.setText(bean.getDataList().get(0).getRate() + "");
        }

    }

    @Override
    public void setEnergy_ForceChartData(Databean bean) {
        if(bean.getDataList().size()>0){
            energyForceChart1.clearData();
            ArrayList<Entry> values = new ArrayList<>();
            List<String> listDate = new ArrayList<String>();
            for (int i = 0; i < bean.getDataList().size(); i++) {
                Entry entry = new Entry(i, 0f);
                entry.setY((float) bean.getDataList().get(i).getRate());
                values.add(entry);
                String[] split = StringUtil.split(bean.getDataList().get(i).getBaseDate(), "-");
                listDate.add(split[1]);
            }
            getChartXCount(energyForceChart1);
            energyForceChart1.setDataSet(values, "");
            energyForceChart1.setDayXAxis(listDate);
            energyForceChart1.loadChart();
        }

    }
    //每月节约电费
    @Override
    public void setEnergy_ForceCharge(DataFragmentbean bean) {
        if (bean.getDataList().size() > 0) {
            energyForceChart2.clearData();
//            double num;
            ArrayList<BarEntry> values = new ArrayList<>();
            List<String> listDate = new ArrayList<String>();
            for (int i = 0; i < bean.getDataList().size(); i++) {
//                Log.i(TAG, "setEnergy_ForceCharge111: "+bean.getDataList().get(i).getFouceSum());
//                Log.i(TAG, "setEnergy_ForceCharge222: "+bean.getDataList().get(i+1).getFouceSum());
//                num = bean.getDataList().get(i).getFouceSum() - bean.getDataList().get(i++).getFouceSum();
//                i--;
                BarEntry entry = new BarEntry(i, 0f);
//                Log.i(TAG, "setEnergy_ForceCharge: "+num);
                entry.setY((float) bean.getDataList().get(i).getFouceSum()*-1);
                values.add(entry);
                String[] split = StringUtil.split(bean.getDataList().get(i).getBaseDate(), "-");
                listDate.add(split[1]+"月");
            }
            energyForceChart2.setDataSet(values, "");
            energyForceChart2.setDayXAxis(listDate);
            energyForceChart2.loadChart();
        }

    }
    //计算x数量
    public void getChartXCount(LineDataChart lineDataChart){
        //得到当前年月 确定chart表x轴加载的数量
        currentYear = DateUtil.getCurrentYear();
        currentMonth= DateUtil.getCurrentMonth();
        XAxis xAxis = lineDataChart.getXAxis();
//        xAxis.setLabelRotationAngle(0);
        String baseData = dataAllbean.getBaseData();
        String[] split = StringUtil.split(baseData, "-");
        if(split[0].equals(currentYear+"")){
//            if(currentMonth>8){
//                xAxis.setLabelRotationAngle(-50);
//            }
            xAxis.setLabelCount(currentMonth,true);
        }else{
            xAxis.setLabelCount(12,true);
//            xAxis.setLabelRotationAngle(-50);
        }
    }

}
