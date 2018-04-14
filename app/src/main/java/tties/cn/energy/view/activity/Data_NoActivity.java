package tties.cn.energy.view.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import tties.cn.energy.R;
import tties.cn.energy.base.BaseActivity;
import tties.cn.energy.chart.LineDataChart;
import tties.cn.energy.common.Constants;
import tties.cn.energy.model.result.AllElectricitybean;
import tties.cn.energy.model.result.Data_Nobean;
import tties.cn.energy.presenter.Data_NoPresenter;
import tties.cn.energy.utils.ACache;
import tties.cn.energy.utils.DateUtil;
import tties.cn.energy.utils.StringUtil;
import tties.cn.energy.utils.ToastUtil;
import tties.cn.energy.view.dialog.BottomStyleDialog;
import tties.cn.energy.view.dialog.BottomStyleDialogTwo;
import tties.cn.energy.view.dialog.MyTimePickerDialog;
import tties.cn.energy.view.iview.IData_NoView;

/**
 * 电流不平衡
 */
public class Data_NoActivity extends BaseActivity<Data_NoPresenter> implements IData_NoView {


    @BindView(R.id.toolbar_left)
    ImageView toolbarLeft;
    @BindView(R.id.toolbar_text)
    TextView toolbarText;
    @BindView(R.id.data_toolbar)
    Toolbar dataToolbar;
    @BindView(R.id.data_no_chart1)
    LineDataChart dataNoChart1;
    @BindView(R.id.data_no_chart2)
    LineDataChart dataNoChart2;
    @BindView(R.id.data_no_chart3)
    LineDataChart dataNoChart3;
    @BindView(R.id.data_no_time_tv)
    TextView dataNoTimeTv;
    @BindView(R.id.data_no_time)
    LinearLayout dataNoTime;
    @BindView(R.id.data_no_allelectric)
    LinearLayout dataNoAllelectric;
    @BindView(R.id.data_no_ele_tv)
    TextView dataNoEleTv;
    private BottomStyleDialogTwo dialog;
    MyTimePickerDialog dialogtime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        mPresenter.getAllElectricityData();
        initView();
    }


    private void initView() {
        dataNoTimeTv.setText(DateUtil.getCurrentYear()+"年"+DateUtil.getCurrentMonth()+"月");
        dialogtime = new MyTimePickerDialog();
        toolbarText.setText("电流不平衡");
        dataNoTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogtime.getTimePickerDialog(Data_NoActivity.this);
                dialogtime.setOnTimeClick(new MyTimePickerDialog.OnTimeClick() {
                    @Override
                    public void OnTimeClickListener(String text) {
                        ACache.getInstance().put(Constants.CACHE_OPS_BASEDATE, text);
                        dataNoTimeTv.setText(text);
                        mPresenter.getData_NoData();
                    }
                });
            }
        });
        dataNoAllelectric.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dialog != null) {
                    dialog.show();
                }

//                ToastUtil.showShort(Data_NoActivity.this,"0000000");
            }
        });


    }

    @Override
    protected void createPresenter() {
        mPresenter = new Data_NoPresenter(Data_NoActivity.this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_data__no;
    }

    @Override
    public void setData_NoData(Data_Nobean bean) {
        if (bean.getMaxTimeData().size()>0) {
            dataNoChart2.clearData();
            //不平衡最大值
            ArrayList<Entry> values1 = new ArrayList<>();
            List<String> listDate = new ArrayList<String>();
            for (int i = 0; i < bean.getMaxTimeData().size(); i++) {
                Entry entry = new Entry(i, 0f);
                String[] split1 = StringUtil.split(bean.getMaxTimeData().get(i).getIUMAXTIME(), ":");
                entry.setY((float)Float.parseFloat(split1[0])/24*100);
                values1.add(entry);
                String split = StringUtil.substring(bean.getMaxTimeData().get(i).getFREEZETIME(),5,10);
                listDate.add(split);
            }
            XAxis xAxis = dataNoChart2.getXAxis();
            xAxis.setLabelCount(bean.getMaxTimeData().size(),true);
            xAxis.setLabelRotationAngle(-50);
            YAxis axisLeft = dataNoChart2.getAxisLeft();
            axisLeft.setLabelCount(7,true);
            axisLeft.setStartAtZero(true);
            dataNoChart2.setDataSet(values1, "");
            dataNoChart2.setDayXAxis(listDate);
//            dataNoChart2.setDayYAxis(listYDate);
            dataNoChart2.getAxisLeft().setValueFormatter(new IAxisValueFormatter() {
                @Override
                public String getFormattedValue(float value, AxisBase axis) {
                    int i = (int) value;
                    String str = i*24/100 + ":00";
                    return str;
                }
            });
            dataNoChart2.loadChart();
        }
        if(bean.getMaxData().size()>0){
            dataNoChart1.clearData();
            //不平衡最大值发生时间
            ArrayList<Entry> values2 = new ArrayList<>();
            List<String> listDate2 = new ArrayList<String>();
            for (int i = 0; i < bean.getMaxData().size(); i++) {
                Entry entry = new Entry(i, 0f);
                entry.setY((float) bean.getMaxData().get(i).getIUMAX());
                values2.add(entry);
                String split = StringUtil.substring(bean.getMaxData().get(i).getFREEZETIME(),5,10);
                listDate2.add(split);
            }
            XAxis xAxis = dataNoChart1.getXAxis();
            xAxis.setLabelCount(bean.getMaxData().size(),true);
            xAxis.setLabelRotationAngle(-50);
            dataNoChart1.setDataSet(values2, "");
            dataNoChart1.setDayXAxis(listDate2);
            dataNoChart1.loadChart();
        }
        if(bean.getLimitData().size()>0){
            dataNoChart3.clearData();
            //不平衡度越限日累计时间
            ArrayList<Entry> values3 = new ArrayList<>();
            List<String> listDate3 = new ArrayList<String>();
            for (int i = 0; i < bean.getLimitData().size(); i++) {
                Entry entry = new Entry(i, 0f);
                entry.setY((float) bean.getLimitData().get(i).getIULIMIT());
                values3.add(entry);
                String split = StringUtil.substring(bean.getLimitData().get(i).getFREEZETIME(),5,10);
                listDate3.add(split);
            }
            XAxis xAxis = dataNoChart3.getXAxis();
            xAxis.setLabelCount(bean.getLimitData().size(),true);
            xAxis.setLabelRotationAngle(-50);
            dataNoChart3.setDataSet(values3, "");
            dataNoChart3.setDayXAxis(listDate3);
            dataNoChart3.loadChart();
        }
    }

    @Override
    public void setAllElectricity(final AllElectricitybean allElectricitybean) {
        if(allElectricitybean.getMeterList().size()>0){
            ACache.getInstance().put(Constants.CACHE_OPS_OBJID, allElectricitybean.getMeterList().get(0).getMeterId());
            ACache.getInstance().put(Constants.CACHE_OPS_BASEDATE, DateUtil.getCurrentYear()+"-"+DateUtil.getCurrentMonth());
            mPresenter.getData_NoData();
            dataNoEleTv.setText(allElectricitybean.getMeterList().get(0).getMeterName());
            dialog = new BottomStyleDialogTwo(Data_NoActivity.this, allElectricitybean);
            dialog.setCliekAllElectricity(new BottomStyleDialogTwo.OnCliekAllElectricitytwo() {
                @Override
                public void OnCliekAllElectricityListener(int poaiton) {
                    long meterId = allElectricitybean.getMeterList().get(poaiton).getMeterId();
                    dataNoEleTv.setText(allElectricitybean.getMeterList().get(poaiton).getMeterName());
                    ACache.getInstance().put(Constants.CACHE_OPS_OBJID, meterId);
                    mPresenter.getData_NoData();
                }
            });
        }

    }
}
