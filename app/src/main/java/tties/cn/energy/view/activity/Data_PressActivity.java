package tties.cn.energy.view.activity;

import android.os.Bundle;
import android.util.Log;
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

import java.text.ParseException;
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
import tties.cn.energy.model.result.Data_Pressbean;
import tties.cn.energy.presenter.Data_PressPresenter;
import tties.cn.energy.utils.ACache;
import tties.cn.energy.utils.DateUtil;
import tties.cn.energy.utils.StringUtil;
import tties.cn.energy.utils.ToastUtil;
import tties.cn.energy.view.dialog.BottomStyleDialog;
import tties.cn.energy.view.dialog.BottomStyleDialogTwo;
import tties.cn.energy.view.dialog.MyTimePickerDialog;
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
    @BindView(R.id.data_press_allelectric)
    LinearLayout dataPressAllelectric;
    @BindView(R.id.data_press_ele_tv)
    TextView dataPressEleTv;
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
        dataTimeTv.setText(DateUtil.getCurrentYear()+"年"+DateUtil.getCurrentMonth()+"月");
        dialogtime = new MyTimePickerDialog();
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
                dialogtime.getTimePickerDialog(Data_PressActivity.this);
                dialogtime.setOnTimeClick(new MyTimePickerDialog.OnTimeClick() {
                    @Override
                    public void OnTimeClickListener(String text) {
                        ACache.getInstance().put(Constants.CACHE_OPS_BASEDATE, text);
                        dataTimeTv.setText(text);
                        mPresenter.getData_PressData();
                    }
                });
            }
        });
        dataPressAllelectric.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (dialog != null) {
                    dialog.show();
                }
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
        if (bean.getMaxTimeData().size()>0) {
            dataPressChart2.clearData();
            //不平衡最大值
            ArrayList<Entry> values1 = new ArrayList<>();
            List<String> listDate = new ArrayList<String>();
//            List<String> listYDate = new ArrayList<String>();
            for (int i = 0; i < bean.getMaxTimeData().size(); i++) {
                Entry entry = new Entry(i, 0f);
//                String sub2 = new String();
                String[] split = StringUtil.split(bean.getMaxTimeData().get(i).getVUMAXTIME(), ":");
//                sub2 = split[0] + ":" + split[1];
//                entry.setY(Float.parseFloat(split[0] + split[1]));
                entry.setY(Float.parseFloat(split[0]));
                values1.add(entry);
                listDate.add(bean.getMaxTimeData().get(i).getFREEZETIME());
//                listYDate.add(sub2);
            }
            dataPressChart2.setDataSet(values1, "");
            dataPressChart2.setDayXAxis(listDate);
//            dataPressChart2.setDayYAxis(listYDate);
            dataPressChart2.getAxisLeft().setValueFormatter(new IAxisValueFormatter() {
                @Override
                public String getFormattedValue(float value, AxisBase axis) {
                    int i = (int) value;
//                    i*100/24
                    String str = i + ":00";
                    return str;
                }
            });
            dataPressChart2.loadChart();
        }
        if(bean.getMaxData().size()>0) {
            dataPressChart1.clearData();
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
        }
        if(bean.getLimitData().size()>0){
            dataPressChart3.clearData();
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
        }

    }

    @Override
    public void setAllElectricity(final AllElectricitybean allElectricitybean) {
        ACache.getInstance().put(Constants.CACHE_OPS_OBJID, allElectricitybean.getMeterList().get(0).getMeterId());
        ACache.getInstance().put(Constants.CACHE_OPS_BASEDATE, DateUtil.getCurrentYear()+"-"+DateUtil.getCurrentMonth());
        mPresenter.getData_PressData();
        dialog = new BottomStyleDialogTwo(Data_PressActivity.this, allElectricitybean);
        dialog.setCliekAllElectricity(new BottomStyleDialogTwo.OnCliekAllElectricitytwo() {
            @Override
            public void OnCliekAllElectricityListener(int poaiton) {
                long meterId = allElectricitybean.getMeterList().get(poaiton).getMeterId();
                dataPressEleTv.setText(allElectricitybean.getMeterList().get(poaiton).getMeterName());
                ACache.getInstance().put(Constants.CACHE_OPS_OBJID, meterId);
                mPresenter.getData_PressData();
            }
        });
    }


    public static float minuteParse(String duration) {
        float allnumber = Float.parseFloat(duration);//1640
        float minute = allnumber * 60;
        return minute;
    }

    public int getTime(String str) {
//        String str="1640";
        int hour = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("hhmm");
        long time = 0;
        try {
            time = sdf.parse(str).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date newTime = new Date(time); //就得到普通的时间了
        hour = newTime.getHours();//就得到了小时
        Log.i("-------ss----", "onCreate: " + time);
        Log.i("-------sxxxs----", "onCreate: " + hour);
        return hour;
    }


}
