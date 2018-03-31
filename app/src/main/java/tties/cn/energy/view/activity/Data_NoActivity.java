package tties.cn.energy.view.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.mikephil.charting.components.AxisBase;
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
import tties.cn.energy.model.result.AllElectricitybean;
import tties.cn.energy.model.result.Data_Nobean;
import tties.cn.energy.presenter.Data_NoPresenter;
import tties.cn.energy.utils.StringUtil;
import tties.cn.energy.view.dialog.BottomStyleDialog;
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
    private BottomStyleDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        mPresenter.getData_NoData(0);
        mPresenter.getAllElectricityData();
        initView();
    }

    private void initView() {
        toolbarText.setText("电流不平衡");
        dataNoTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog dialogYearMonth = new TimePickerDialog.Builder()
                        .setType(Type.YEAR_MONTH)
                        .setTitleStringId("选择月份")
                        .setThemeColor(R.color.home_btn_lightblue)
                        .setWheelItemTextNormalColor(getResources().getColor(R.color.home_btn_lightblue))
                        .setWheelItemTextSelectorColor(getResources().getColor(R.color.radiobtn_meterlist_colorLineDefault))
                        .setThemeColor(getResources().getColor(R.color.radiobtn_meterlist_colorLineDefault))
                        .setWheelItemTextSelectorColor(getResources().getColor(R.color.black))
                        .setCallBack(new OnDateSetListener() {
                            @Override
                            public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
                                Date date = new Date(millseconds);
                                SimpleDateFormat format = new SimpleDateFormat("yyyy年MM日");
                                String time = format.format(date);
                                dataNoTimeTv.setText(time);
                            }
                        })
                        .build();
                dialogYearMonth.show(getSupportFragmentManager(), "年_月");
            }
        });
        dataNoAllelectric.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {

                if(dialog!=null){
                    dialog.show();
                }

//                ToastUtil.showShort(Data_PressActivity.this,""+dialog.getOnclickItem());
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
        if (bean != null) {
            //不平衡最大值
            ArrayList<Entry> values1 = new ArrayList<>();
            List<String> listDate = new ArrayList<String>();
//            List<String> listYDate = new ArrayList<String>();
            for (int i = 0; i < bean.getMaxTimeData().size(); i++) {
                Entry entry = new Entry(i, 0f);
//                String sub2 = new String();
                String[] split = StringUtil.split(bean.getMaxTimeData().get(i).getIUMAXTIME(), ":");
//                sub2 = split[0] + ":" + split[1];
                entry.setY(Float.parseFloat(split[0]));
                values1.add(entry);
                listDate.add(bean.getMaxTimeData().get(i).getFREEZETIME());
//                listYDate.add(sub2);
            }
            dataNoChart2.setDataSet(values1, "");
            dataNoChart2.setDayXAxis(listDate);
//            dataNoChart2.setDayYAxis(listYDate);
            dataNoChart2.getAxisLeft().setValueFormatter(new IAxisValueFormatter() {
                @Override
                public String getFormattedValue(float value, AxisBase axis) {
                    int i= (int) value;
//                    i*100/24
                    String str=i+":00";
                    return str;
                }
            });
            dataNoChart2.loadChart();
            //不平衡最大值发生时间
            ArrayList<Entry> values2 = new ArrayList<>();
            List<String> listDate2 = new ArrayList<String>();
            for (int i = 0; i < bean.getMaxData().size(); i++) {
                Entry entry = new Entry(i, 0f);
                entry.setY((float) bean.getMaxData().get(i).getIUMAX());
                values2.add(entry);
                String[] split = StringUtil.split(bean.getMaxData().get(i).getFREEZETIME(), " ");
                listDate2.add(split[0]);
            }
            dataNoChart1.setDataSet(values2, "");
            dataNoChart1.setDayXAxis(listDate2);
            dataNoChart1.loadChart();
            //不平衡度越限日累计时间
            ArrayList<Entry> values3 = new ArrayList<>();
            List<String> listDate3 = new ArrayList<String>();
            for (int i = 0; i < bean.getLimitData().size(); i++) {
                Entry entry = new Entry(i, 0f);
                entry.setY((float) bean.getLimitData().get(i).getIULIMIT());
                values3.add(entry);
                String[] split = StringUtil.split(bean.getLimitData().get(i).getFREEZETIME(), " ");
                listDate3.add(split[0]);
            }
            dataNoChart3.setDataSet(values3, "");
            dataNoChart3.setDayXAxis(listDate3);
            dataNoChart3.loadChart();
        } else {
            dataNoChart1.setNoDataText("无数据");
            dataNoChart2.setNoDataText("无数据");
            dataNoChart3.setNoDataText("无数据");
        }
    }

    @Override
    public void setAllElectricitySend(AllElectricitybean allElectricitySend) {
        dialog=new BottomStyleDialog(Data_NoActivity.this,allElectricitySend);
    }
}
