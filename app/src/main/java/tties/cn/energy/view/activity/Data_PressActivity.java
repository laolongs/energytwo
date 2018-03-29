package tties.cn.energy.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
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
import tties.cn.energy.model.result.Data_Pressbean;
import tties.cn.energy.presenter.Data_PressPresenter;
import tties.cn.energy.utils.StringUtil;
import tties.cn.energy.view.dialog.BottomStyleDialog;
import tties.cn.energy.view.iview.IData_PressView;

/**
 * 电压不平衡
 */
public class Data_PressActivity extends BaseActivity<Data_PressPresenter> implements IData_PressView {

    @BindView(R.id.toolbar_left)
    ImageView toolbarLeft;
    @BindView(R.id.toolbar_text)
    TextView toolbarText;
    @BindView(R.id.data_press_chart1)
    LineDataChart dataPressChart1;
    @BindView(R.id.data_press_chart2)
    LineDataChart dataPressChart2;
    @BindView(R.id.data_press_chart3)
    LineDataChart dataPressChart3;
    @BindView(R.id.data_press_time_tv)
    TextView dataTimeTv;
    @BindView(R.id.data_press_time)
    LinearLayout dataPressTime;
    @BindView(R.id.data_press_allelectric_tv)
    TextView dataPressAllelectricTv;
    @BindView(R.id.data_press_allelectric)
    LinearLayout dataPressAllelectric;
    private BottomStyleDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        mPresenter.getData_PressData(1);
        mPresenter.getAllElectricityData();
        initView();
    }

    private void initView() {
        toolbarLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        toolbarText.setText("电压不平衡");
        dataPressTime.setOnClickListener(new View.OnClickListener() {
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
                                dataTimeTv.setText(time);
                            }
                        })
                        .build();
                dialogYearMonth.show(getSupportFragmentManager(), "年_月");
            }
        });
        dataPressAllelectric.setOnClickListener(new View.OnClickListener() {

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
        mPresenter = new Data_PressPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_data__press;
    }


    @Override
    public void setData_PressData(Data_Pressbean bean) {
        if (bean != null) {
            //不平衡最大值
            ArrayList<Entry> values1 = new ArrayList<>();
            List<String> listDate = new ArrayList<String>();
            List<String> listYDate = new ArrayList<String>();
            for (int i = 0; i < bean.getMaxTimeData().size(); i++) {
                Entry entry = new Entry(i, 0f);
                String sub2 = new String();
                String[] split = StringUtil.split(bean.getMaxTimeData().get(i).getVUMAXTIME(), ":");
                sub2 = split[0] + ":" + split[1];
                entry.setY(Float.parseFloat(split[0] + split[1]));
                values1.add(entry);
                listDate.add(bean.getMaxTimeData().get(i).getFREEZETIME());
                listYDate.add(sub2);
            }
            dataPressChart2.setDataSet(values1, "");
            dataPressChart2.setDayXAxis(listDate);
            dataPressChart2.setDayYAxis(listYDate);
            dataPressChart2.loadChart();
            //不平衡最大值发生时间
            ArrayList<Entry> values2 = new ArrayList<>();
            List<String> listDate2 = new ArrayList<String>();
            for (int i = 0; i < bean.getMaxData().size(); i++) {
                Entry entry = new Entry(i, 0f);
                entry.setY((float) bean.getMaxData().get(i).getVUMAX());
                values2.add(entry);
                String[] split = StringUtil.split(bean.getMaxData().get(i).getFREEZETIME(), " ");
                listDate2.add(split[0]);
            }
            dataPressChart1.setDataSet(values2, "");
            dataPressChart1.setDayXAxis(listDate2);
            dataPressChart1.loadChart();
            //不平衡度越限日累计时间
            ArrayList<Entry> values3 = new ArrayList<>();
            List<String> listDate3 = new ArrayList<String>();
            for (int i = 0; i < bean.getLimitData().size(); i++) {
                Entry entry = new Entry(i, 0f);
                entry.setY((float) bean.getLimitData().get(i).getVULIMIT());
                values3.add(entry);
                String[] split = StringUtil.split(bean.getLimitData().get(i).getFREEZETIME(), " ");
                listDate3.add(split[0]);
            }
            dataPressChart3.setDataSet(values3, "");
            dataPressChart3.setDayXAxis(listDate3);
            dataPressChart3.loadChart();
        } else {
            dataPressChart1.setNoDataText("无数据");
            dataPressChart2.setNoDataText("无数据");
            dataPressChart3.setNoDataText("无数据");
        }

    }

    @Override
    public void setAllElectricitySend(AllElectricitybean allElectricitySend) {
        dialog=new BottomStyleDialog(Data_PressActivity.this,allElectricitySend);
    }
    public static float minuteParse(String duration) {
        float allnumber = Float.parseFloat(duration);//1640
        float minute = allnumber * 60 ;
        return minute ;
    }

}
