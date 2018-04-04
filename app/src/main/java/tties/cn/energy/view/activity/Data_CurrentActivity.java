package tties.cn.energy.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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
import tties.cn.energy.model.result.Data_CurrentPressbean;
import tties.cn.energy.model.result.Data_Currentbean;
import tties.cn.energy.presenter.Data_CurrentPresenter;
import tties.cn.energy.utils.StringUtil;
import tties.cn.energy.utils.ToastUtil;
import tties.cn.energy.view.dialog.BottomStyleDialog;
import tties.cn.energy.view.iview.IData_CurrentView;

/**
 * 电流电压
 */
public class Data_CurrentActivity extends BaseActivity<Data_CurrentPresenter> implements IData_CurrentView {

    @BindView(R.id.toolbar_left)
    ImageView toolbarLeft;
    @BindView(R.id.toolbar_text)
    TextView toolbarText;
    @BindView(R.id.data_current_chart)
    LineDataChart dataCurrentChart;
    @BindView(R.id.data_currentpress_chart)
    LineDataChart dataCurrentpressChart;
    @BindView(R.id.data_current_time_tv)
    TextView dataCurrentTimeTv;
    @BindView(R.id.data_current_time)
    LinearLayout dataCurrentTime;
    @BindView(R.id.data_current_allelectric)
    LinearLayout dataCurrentAllelectric;
    private BottomStyleDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initView();
        mPresenter.getAllElectricityData();
        mPresenter.getData_CurrentData(2);
        mPresenter.getData_CurrentPressKwData(1);
    }

    private void initView() {
        toolbarText.setText("电流电压");
        toolbarLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        dataCurrentTime.setOnClickListener(new View.OnClickListener() {
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
                                SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月");
                                SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
                                String time = format.format(date);
                                //时间参数值
                                String time2 = format2.format(date);
                                dataCurrentTimeTv.setText(time);

                            }
                        })
                        .build();
                dialogYearMonth.show(getSupportFragmentManager(), "年_月");
            }
        });
        dataCurrentAllelectric.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (dialog != null) {
                    dialog.show();
                }
//                ToastUtil.showShort(Data_PressActivity.this,""+dialog.getOnclickItem());
            }
        });
    }

    @Override
    protected void createPresenter() {
        mPresenter = new Data_CurrentPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_data__current;
    }

    @Override
    public void setData_CurrentData(Data_Currentbean bean) {
        ArrayList<Entry> values1 = new ArrayList<>();
        ArrayList<Entry> values2 = new ArrayList<>();
        ArrayList<Entry> values3 = new ArrayList<>();
        List<String> listDate = new ArrayList<String>();
        for (int i = 0; i < bean.getDataList().size(); i++) {
            Entry entry1 = new Entry(i, 0f);
            Entry entry2 = new Entry(i, 0f);
            Entry entry3 = new Entry(i, 0f);
            entry1.setY((float) bean.getDataList().get(i).getA());
            entry2.setY((float) bean.getDataList().get(i).getB());
            entry3.setY((float) bean.getDataList().get(i).getC());
            values1.add(entry1);
            values2.add(entry2);
            values3.add(entry3);
            String[] split = StringUtil.split(bean.getDataList().get(i).getFreezeTime(), " ");
            listDate.add(split[0]);
        }
        dataCurrentChart.setDataSet(values1, "");
        dataCurrentChart.setDataSet(values2, "");
        dataCurrentChart.setDataSet(values3, "");
        dataCurrentChart.setDayXAxis(listDate);
        dataCurrentChart.loadChart();
    }

    @Override
    public void setData_CurrentPressData(Data_CurrentPressbean bean) {
        ArrayList<Entry> values1 = new ArrayList<>();
        ArrayList<Entry> values2 = new ArrayList<>();
        ArrayList<Entry> values3 = new ArrayList<>();
        List<String> listDate = new ArrayList<String>();
        for (int i = 0; i < bean.getDataList().size(); i++) {
            Entry entry1 = new Entry(i, 0f);
            Entry entry2 = new Entry(i, 0f);
            Entry entry3 = new Entry(i, 0f);
            entry1.setY((float) bean.getDataList().get(i).getA());
            entry2.setY((float) bean.getDataList().get(i).getB());
            entry3.setY((float) bean.getDataList().get(i).getC());
            values1.add(entry1);
            values2.add(entry2);
            values3.add(entry3);
            String[] split = StringUtil.split(bean.getDataList().get(i).getFreezeTime(), " ");
            listDate.add(split[0]);
        }
        dataCurrentpressChart.setDataSet(values1, "");
        dataCurrentpressChart.setDataSet(values2, "");
        dataCurrentpressChart.setDataSet(values3, "");
        dataCurrentpressChart.setDayXAxis(listDate);
        dataCurrentpressChart.loadChart();
    }

    @Override
    public void setAllElectricity(final AllElectricitybean allElectricitybean) {
        dialog=new BottomStyleDialog(Data_CurrentActivity.this,allElectricitybean);
        dialog.setCliekAllElectricity(new BottomStyleDialog.OnCliekAllElectricity() {
            @Override
            public void OnCliekAllElectricityListener(int poaiton) {
                long meterId = allElectricitybean.getMeterList().get(poaiton).getMeterId();
                ToastUtil.showShort(Data_CurrentActivity.this,""+meterId);
            }
        });
    }
}
