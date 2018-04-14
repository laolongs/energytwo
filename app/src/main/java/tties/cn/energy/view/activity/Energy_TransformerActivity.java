package tties.cn.energy.view.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import tties.cn.energy.R;
import tties.cn.energy.base.BaseActivity;
import tties.cn.energy.chart.LineDataChart;
import tties.cn.energy.chart.LineDataTwoChart;
import tties.cn.energy.common.Constants;
import tties.cn.energy.model.result.DataAllbean;
import tties.cn.energy.model.result.Energy_TransformerDamgebean;
import tties.cn.energy.model.result.Energy_TransformerListbean;
import tties.cn.energy.model.result.Energy_TransformerTemperaturebean;
import tties.cn.energy.model.result.Energy_TransformerVolumebean;
import tties.cn.energy.presenter.Energy_TransformerPresenter;
import tties.cn.energy.utils.ACache;
import tties.cn.energy.utils.DateUtil;
import tties.cn.energy.utils.StringUtil;
import tties.cn.energy.view.dialog.MyTimePickerDialog;
import tties.cn.energy.view.iview.IEnergy_TransformerView;

/**
 * 变压器优化
 */
public class Energy_TransformerActivity extends BaseActivity<Energy_TransformerPresenter> implements IEnergy_TransformerView {
    private static final String TAG = "Energy_TransformerActiv";
    @BindView(R.id.toolbar_left)
    ImageView toolbarLeft;
    @BindView(R.id.toolbar_text)
    TextView toolbarText;
    @BindView(R.id.energy_transformer_tab)
    TabLayout energyTransformerTab;
    @BindView(R.id.energy_transformer_select1)
    ImageView energyTransformerSelect1;
    @BindView(R.id.energy_transformer_chart1)
    LineDataChart energyTransformerChart1;
    @BindView(R.id.energy_transformer_select2)
    ImageView energyTransformerSelect2;
    @BindView(R.id.energy_transformer_chart2)
    LineDataTwoChart energyTransformerChart2;
    @BindView(R.id.energy_transformer_damge)
    TextView energyTransformerDamge;
    @BindView(R.id.energy_transformer_kwh)
    TextView energyTransformerKwh;
    int transformerId = 0;
    MyTimePickerDialog dialogtime;
    @BindView(R.id.energy_transformer_year1)
    TextView energyTransformerYear1;
    @BindView(R.id.energy_transformer_year2)
    TextView energyTransformerYear2;
    DataAllbean dataAllbean=new DataAllbean();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initView();

    }

    private void initView() {
        mPresenter.getEnergy_TransformerList();
        toolbarText.setText("变压器优化");
        toolbarLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        dialogtime = new MyTimePickerDialog();
        energyTransformerSelect1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogtime.getTimeYearPickerDialog(Energy_TransformerActivity.this);
                dialogtime.setOnTimeClick(new MyTimePickerDialog.OnTimeClick() {
                    @Override
                    public void OnTimeClickListener(String text) {
                        ACache.getInstance().put(Constants.CACHE_OPS_BASEDATE, text);
                        energyTransformerYear1.setText(text + "年");
                        mPresenter.getEnergy_TransformerTemperature(transformerId);
                    }
                });
            }
        });
        energyTransformerSelect2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogtime.getTimeYearPickerDialog(Energy_TransformerActivity.this);
                dialogtime.setOnTimeClick(new MyTimePickerDialog.OnTimeClick() {
                    @Override
                    public void OnTimeClickListener(String text) {
                        ACache.getInstance().put(Constants.CACHE_OPS_TRANSFORMERVOLUMEBASEDATE, text  );
                        energyTransformerYear2.setText(text + "年");
                        mPresenter.getEnergy_TransformerVolume(transformerId);
                    }
                });
            }
        });
    }

    @Override
    protected void createPresenter() {
        mPresenter = new Energy_TransformerPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_energy__transformer;
    }


    @Override
    public void setEnergy_TransformerListbeanData(final Energy_TransformerListbean bean) {
        if (bean.getResult().size() > 0) {
            for (int i = 0; i < bean.getResult().size(); i++) {
                energyTransformerTab.addTab(energyTransformerTab.newTab().setText(bean.getResult().get(i).getName()));
            }
            transformerId = bean.getResult().get(0).getCompanyEquipmentId();
            ACache.getInstance().put(Constants.CACHE_OPS_BASEDATE, DateUtil.getCurrentYear()+"");
            ACache.getInstance().put(Constants.CACHE_OPS_TRANSFORMERVOLUMEBASEDATE, DateUtil.getCurrentYear()+"");
            mPresenter.getEnergy_TransformerDamge(transformerId);
            mPresenter.getEnergy_TransformerTemperature(transformerId);
            mPresenter.getEnergy_TransformerVolume(transformerId);
            energyTransformerTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
//                ToastUtil.showShort(Energy_TransformerActivity.this, tab.getText());
                    int position = tab.getPosition();
                    transformerId = bean.getResult().get(position).getCompanyEquipmentId();
                    mPresenter.getEnergy_TransformerDamge(transformerId);
                    mPresenter.getEnergy_TransformerTemperature(transformerId);
                    mPresenter.getEnergy_TransformerVolume(transformerId);
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });
        }

    }
    @Override
    public void setEnergy_TransformerDamgebeanData(Energy_TransformerDamgebean bean) {
        if (bean.getResult().getConsume() == 0) {
            energyTransformerKwh.setText("∞");
        }else{
            energyTransformerKwh.setText(bean.getResult().getConsume() + "");
        }
        if (bean.getResult().getDamge() == 0) {
            energyTransformerDamge.setText("∞");
        }else{
            energyTransformerDamge.setText(bean.getResult().getDamge() + "%");
        }


    }
    @Override
    public void setEnergy_TransformerTemperaturebeanData(Energy_TransformerTemperaturebean bean) {
        if(bean.getResult().size()>0){
            energyTransformerChart1.clearData();
            ArrayList<Entry> values = new ArrayList<>();
            List<String> listDate = new ArrayList<String>();
            for (int i = 0; i < bean.getResult().size(); i++) {
                Entry entry = new Entry(i, 0f);
                entry.setY((float) bean.getResult().get(i).getData());
                values.add(entry);
                String[] split = StringUtil.split(bean.getResult().get(i).getTime(), "-");
                listDate.add(split[1]);
            }
            getChartXCount(energyTransformerChart1);
            energyTransformerChart1.setDataSet(values, "");
            energyTransformerChart1.setDayXAxis(listDate);
            energyTransformerChart1.loadChart();
        }

    }


    @Override
    public void setEnergy_TransformerVolumebeanData(Energy_TransformerVolumebean bean) {
        //实体bean暂无数据
        if(bean.getResult().size()>0){
            energyTransformerChart2.clearData();
            ArrayList<Entry> values = new ArrayList<>();
            List<String> listDate = new ArrayList<String>();
            for (int i = 0; i < bean.getResult().size(); i++) {
                Entry entry = new Entry(i, 0f);
                entry.setY((float) bean.getResult().get(i).getData());
                values.add(entry);
                String[] split = StringUtil.split(bean.getResult().get(i).getBaseDate(), "-");
                listDate.add(split[1]);
            }
            getChartTwoXCount(energyTransformerChart2);
            energyTransformerChart2.setDataSet(values, "");
            energyTransformerChart2.setDayXAxis(listDate);
            energyTransformerChart2.loadChart();
        }
    }
    //计算x数量
    public void getChartXCount(LineDataChart lineDataChart){
        //得到当前年月 确定chart表x轴加载的数量
        int currentYear = DateUtil.getCurrentYear();
        int currentMonth= DateUtil.getCurrentMonth();
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
    //计算x数量
    public void getChartTwoXCount(LineDataTwoChart lineDataChart){
        //得到当前年月 确定chart表x轴加载的数量
        int currentYear = DateUtil.getCurrentYear();
        int currentMonth= DateUtil.getCurrentMonth();
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
