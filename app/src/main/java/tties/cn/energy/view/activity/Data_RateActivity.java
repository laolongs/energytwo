package tties.cn.energy.view.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineDataSet;
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
import tties.cn.energy.application.MyApplication;
import tties.cn.energy.base.BaseActivity;
import tties.cn.energy.chart.LineDataChart;
import tties.cn.energy.model.result.AllElectricitybean;
import tties.cn.energy.model.result.Data_HaveKwbean;
import tties.cn.energy.model.result.Data_NoKvarbean;
import tties.cn.energy.presenter.Data_RatePresenter;
import tties.cn.energy.utils.StringUtil;
import tties.cn.energy.utils.ToastUtil;
import tties.cn.energy.view.dialog.BottomStyleDialog;
import tties.cn.energy.view.iview.IData_RateView;

/**
 * 功率数据
 */
public class Data_RateActivity extends BaseActivity<Data_RatePresenter> implements IData_RateView {

    @BindView(R.id.toolbar_left)
    ImageView toolbarLeft;
    @BindView(R.id.toolbar_text)
    TextView toolbarText;
    @BindView(R.id.havekw_tv)
    TextView havekwTv;
    @BindView(R.id.havakw_time)
    TextView havakwTime;
    @BindView(R.id.havakw_year)
    TextView havakwYear;
    @BindView(R.id.nokvar_tv)
    TextView nokvarTv;
    @BindView(R.id.nokvar_time)
    TextView nokvarTime;
    @BindView(R.id.nokvar_year)
    TextView nokvarYear;
    @BindView(R.id.havakw_chart)
    LineDataChart havakwChart;
    @BindView(R.id.nokvar_chart)
    LineDataChart nokvarChart;
    @BindView(R.id.data_rate_tv)
    TextView dataRateTv;
    @BindView(R.id.data_rate_time)
    LinearLayout dataRateTime;
    @BindView(R.id.data_rate_electrical)
    LinearLayout dataRateElectrical;
    private BottomStyleDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initView();
        mPresenter.getAllElectricityData();
        mPresenter.getData_HaveKwData(3);
        mPresenter.getData_NoKvarKwData(4);
    }

    private void initView() {
        toolbarText.setText("功率数据");
        toolbarLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        dataRateTime.setOnClickListener(new View.OnClickListener() {
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
                                dataRateTv.setText(time);

                            }
                        })
                        .build();
                dialogYearMonth.show(getSupportFragmentManager(), "年_月");
            }
        });
        dataRateElectrical.setOnClickListener(new View.OnClickListener() {

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
        mPresenter = new Data_RatePresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_data__rate;
    }

    @Override
    public void setData_HaveKWData(Data_HaveKwbean data_haveKwbean) {
        ArrayList<Entry> values = new ArrayList<>();
        List<String> listDate = new ArrayList<String>();
        for (int i = 0; i < data_haveKwbean.getDataList().size(); i++) {
            Entry entry = new Entry(i, 0f);
            entry.setY((float) data_haveKwbean.getDataList().get(i).getD());
            values.add(entry);
            String[] split = StringUtil.split(data_haveKwbean.getDataList().get(i).getFreezeTime(), " ");
            listDate.add(split[0]);
        }
        LineDataSet lineDataSet = havakwChart.setDataSet(values, "");
        //图标填充色
        lineDataSet.setFillColor(ContextCompat.getColor(MyApplication.getInstance(), R.color.analysis_textview_right));
        havakwChart.setDayXAxis(listDate);
        havakwChart.loadChart();
    }

    @Override
    public void setData_NoKvarData(Data_NoKvarbean data_noKvarbean) {
        ArrayList<Entry> values = new ArrayList<>();
        List<String> listDate = new ArrayList<String>();
        for (int i = 0; i < data_noKvarbean.getDataList().size(); i++) {
            Entry entry = new Entry(i, 0f);
            entry.setY((float) data_noKvarbean.getDataList().get(i).getD());
            values.add(entry);
            String[] split = StringUtil.split(data_noKvarbean.getDataList().get(i).getFreezeTime(), " ");
            listDate.add(split[0]);
        }
        nokvarChart.setDataSet(values, "");
        nokvarChart.setDayXAxis(listDate);
        nokvarChart.loadChart();
    }

    @Override
    public void setAllElectricity(final AllElectricitybean allElectricitybean) {
        dialog=new BottomStyleDialog(Data_RateActivity.this,allElectricitybean);
        dialog.setCliekAllElectricity(new BottomStyleDialog.OnCliekAllElectricity() {
            @Override
            public void OnCliekAllElectricityListener(int poaiton) {
                long meterId = allElectricitybean.getMeterList().get(poaiton).getMeterId();
                ToastUtil.showShort(Data_RateActivity.this,""+meterId);
            }
        });
    }
}
