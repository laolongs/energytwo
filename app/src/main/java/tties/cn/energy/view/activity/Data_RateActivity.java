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
import tties.cn.energy.common.Constants;
import tties.cn.energy.model.result.AllElectricitybean;
import tties.cn.energy.model.result.Data_HaveKwbean;
import tties.cn.energy.model.result.Data_NoKvarbean;
import tties.cn.energy.presenter.Data_RatePresenter;
import tties.cn.energy.utils.ACache;
import tties.cn.energy.utils.DateUtil;
import tties.cn.energy.utils.StringUtil;
import tties.cn.energy.view.dialog.BottomStyleDialog;
import tties.cn.energy.view.dialog.MyTimePickerDialog;
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
    @BindView(R.id.data_rate_ele_tv)
    TextView dataRateEleTv;
    private BottomStyleDialog dialog;
    MyTimePickerDialog dialogtime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initView();
        mPresenter.getAllElectricityData();

    }

    private void initView() {
        dialogtime = new MyTimePickerDialog();
        dataRateTv.setText(DateUtil.getCurrentYear()+"年"+DateUtil.getCurrentMonth()+"月");
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
                dialogtime.getTimePickerDialog(Data_RateActivity.this);
                dialogtime.setOnTimeClick(new MyTimePickerDialog.OnTimeClick() {
                    @Override
                    public void OnTimeClickListener(String text) {
                        ACache.getInstance().put(Constants.CACHE_OPS_BASEDATE, text);
                        dataRateTv.setText(text);
                        mPresenter.getData_HaveKwData();
                        mPresenter.getData_NoKvarKwData();

                    }
                });
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
        if(data_haveKwbean.getDataList().size()>0){
            havakwChart.clearData();
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

    }

    @Override
    public void setData_NoKvarData(Data_NoKvarbean data_noKvarbean) {
        if(data_noKvarbean.getDataList().size()>0){
            nokvarChart.clearData();
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

    }

    @Override
    public void setAllElectricity(final AllElectricitybean allElectricitybean) {
        ACache.getInstance().put(Constants.CACHE_OPS_OBJID, allElectricitybean.getLedgerId());
        ACache.getInstance().put(Constants.CACHE_OPS_OBJTYPE, 1);
        ACache.getInstance().put(Constants.CACHE_OPS_BASEDATE, DateUtil.getCurrentYear() + "-" + DateUtil.getCurrentMonth());
        mPresenter.getData_HaveKwData();
        mPresenter.getData_NoKvarKwData();
        dataRateEleTv.setText(allElectricitybean.getLedgerName());
        dialog = new BottomStyleDialog(Data_RateActivity.this, allElectricitybean);
        dialog.setCliekAllElectricity(new BottomStyleDialog.OnCliekAllElectricity() {
            @Override
            public void OnCliekAllElectricityListener(int poaiton) {
                if (poaiton == 0) {
                    long ledgerId = allElectricitybean.getLedgerId();
                    dataRateEleTv.setText(allElectricitybean.getLedgerName());
                    ACache.getInstance().put(Constants.CACHE_OPS_OBJID, ledgerId);
                    ACache.getInstance().put(Constants.CACHE_OPS_OBJTYPE, 1);

                }
                if (poaiton > 0) {
                    long meterId = allElectricitybean.getMeterList().get(poaiton - 1).getMeterId();
                    dataRateEleTv.setText(allElectricitybean.getMeterList().get(poaiton - 1).getMeterName());
                    ACache.getInstance().put(Constants.CACHE_OPS_OBJID, meterId);
                    ACache.getInstance().put(Constants.CACHE_OPS_OBJTYPE, 2);

                }
                mPresenter.getData_HaveKwData();
                mPresenter.getData_NoKvarKwData();

            }
        });
    }
}
