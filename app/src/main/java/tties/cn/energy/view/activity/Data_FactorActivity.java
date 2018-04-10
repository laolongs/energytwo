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
import tties.cn.energy.common.Constants;
import tties.cn.energy.model.result.AllElectricitybean;
import tties.cn.energy.model.result.Data_Factorbean;
import tties.cn.energy.presenter.Data_FactorPresenter;
import tties.cn.energy.utils.ACache;
import tties.cn.energy.utils.DateUtil;
import tties.cn.energy.utils.StringUtil;
import tties.cn.energy.utils.ToastUtil;
import tties.cn.energy.view.dialog.BottomStyleDialog;
import tties.cn.energy.view.dialog.MyTimePickerDialog;
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
    @BindView(R.id.data_factor_ele_tv)
    TextView dataFactorEleTv;
    private BottomStyleDialog dialog;
    double num = 0;
    MyTimePickerDialog dialogtime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initView();
        mPresenter.getAllElectricityData();
    }

    private void initView() {
        dataFactorTv.setText(DateUtil.getCurrentYear()+"年"+DateUtil.getCurrentMonth()+"月");
        dialogtime = new MyTimePickerDialog();
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
                dialogtime.getTimePickerDialog(Data_FactorActivity.this);
                dialogtime.setOnTimeClick(new MyTimePickerDialog.OnTimeClick() {
                    @Override
                    public void OnTimeClickListener(String text) {
                        ACache.getInstance().put(Constants.CACHE_OPS_BASEDATE, text);
                        dataFactorTv.setText(text);
                        mPresenter.getData_Electric();
                    }
                });
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
        if(bean.getDataList().size()>0){
            dataFactorChart.clearData();
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
            BigDecimal b = new BigDecimal(num / bean.getDataList().size());
            double df = b.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
            factorNum.setText(df + "");
            dataFactorChart.setDataSet(values, "");
            dataFactorChart.setDayXAxis(listDate);
            dataFactorChart.loadChart();
        }

    }

    @Override
    public void setAllElectricity(final AllElectricitybean allElectricitybean) {
        ACache.getInstance().put(Constants.CACHE_OPS_OBJID, allElectricitybean.getLedgerId());
        ACache.getInstance().put(Constants.CACHE_OPS_OBJTYPE, 1);
        ACache.getInstance().put(Constants.CACHE_OPS_BASEDATE, DateUtil.getCurrentYear()+"-"+DateUtil.getCurrentMonth());
        mPresenter.getData_Electric();
        dataFactorEleTv.setText(allElectricitybean.getLedgerName());
        dialog = new BottomStyleDialog(Data_FactorActivity.this, allElectricitybean);
        dialog.setCliekAllElectricity(new BottomStyleDialog.OnCliekAllElectricity() {
            @Override
            public void OnCliekAllElectricityListener(int poaiton) {
                if (poaiton == 0) {
                    long ledgerId = allElectricitybean.getLedgerId();
                    dataFactorEleTv.setText(allElectricitybean.getLedgerName());
                    ACache.getInstance().put(Constants.CACHE_OPS_OBJID, ledgerId);
                    ACache.getInstance().put(Constants.CACHE_OPS_OBJTYPE, 1);

                }
                if (poaiton > 0) {
                    long meterId = allElectricitybean.getMeterList().get(poaiton - 1).getMeterId();
                    dataFactorEleTv.setText(allElectricitybean.getMeterList().get(poaiton - 1).getMeterName());
                    ACache.getInstance().put(Constants.CACHE_OPS_OBJID, meterId);
                    ACache.getInstance().put(Constants.CACHE_OPS_OBJTYPE, 2);

                }
                mPresenter.getData_Electric();
            }
        });
    }
}
