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

import java.math.BigDecimal;
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
import tties.cn.energy.model.result.Data_Factorbean;
import tties.cn.energy.presenter.Data_FactorPresenter;
import tties.cn.energy.utils.StringUtil;
import tties.cn.energy.utils.ToastUtil;
import tties.cn.energy.view.dialog.BottomStyleDialog;
import tties.cn.energy.view.iview.IData_FactorView;

/**
 * 功率因素
 */
public class Data_FactorActivity extends BaseActivity<Data_FactorPresenter> implements IData_FactorView {

    @BindView(R.id.toolbar_left)
    ImageView toolbarLeft;
    @BindView(R.id.toolbar_text)
    TextView toolbarText;
    @BindView(R.id.factor_num)
    TextView factorNum;
    @BindView(R.id.data_factor_chart)
    LineDataChart dataFactorChart;
    @BindView(R.id.data_factor_tv)
    TextView dataFactorTv;
    @BindView(R.id.data_factor_time)
    LinearLayout dataFactorTime;
    @BindView(R.id.data_factor_electrical)
    LinearLayout dataFactorElectrical;
    private BottomStyleDialog dialog;
    double num = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initView();
        mPresenter.getAllElectricityData();
        mPresenter.getData_Electric(5);
    }

    private void initView() {
        toolbarText.setText("功率因数");
        toolbarLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        dataFactorTime.setOnClickListener(new View.OnClickListener() {
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
                                dataFactorTv.setText(time);
                            }
                        })
                        .build();
                dialogYearMonth.show(getSupportFragmentManager(), "年_月");
            }
        });
        dataFactorElectrical.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                if (dialog != null) {
                    dialog.show();
                }

//                ToastUtil.showShort(Data_NoActivity.this,"");
            }
        });
    }

    @Override
    protected void createPresenter() {
        mPresenter = new Data_FactorPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_data__factor;
    }

    @Override
    public void setData_FactorData(Data_Factorbean bean) {
        ArrayList<Entry> values = new ArrayList<>();
        List<String> listDate = new ArrayList<String>();
        for (int i = 0; i < bean.getDataList().size(); i++) {
            num = num + bean.getDataList().get(i).getD();
            Entry entry = new Entry(i, 0f);
            entry.setY((float) bean.getDataList().get(i).getD());
            values.add(entry);
            String[] split = StringUtil.split(bean.getDataList().get(i).getFreezeTime(), " ");
            listDate.add(split[0]);
        }
        //保留三位小数
        BigDecimal b = new BigDecimal(num/bean.getDataList().size());
        double df = b.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
        factorNum.setText( df+ "");
        dataFactorChart.setDataSet(values, "");
        dataFactorChart.setDayXAxis(listDate);
        dataFactorChart.loadChart();
    }

    @Override
    public void setAllElectricity(final AllElectricitybean allElectricitybean) {
        dialog = new BottomStyleDialog(Data_FactorActivity.this, allElectricitybean);
        dialog.setCliekAllElectricity(new BottomStyleDialog.OnCliekAllElectricity() {
            @Override
            public void OnCliekAllElectricityListener(int poaiton) {
                long meterId = allElectricitybean.getMeterList().get(poaiton).getMeterId();
                ToastUtil.showShort(Data_FactorActivity.this, "" + meterId);
            }
        });
    }
}
