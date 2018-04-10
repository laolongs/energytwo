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
import tties.cn.energy.common.Constants;
import tties.cn.energy.model.result.AllElectricitybean;
import tties.cn.energy.model.result.Data_CurrentPressbean;
import tties.cn.energy.model.result.Data_Currentbean;
import tties.cn.energy.presenter.Data_CurrentPresenter;
import tties.cn.energy.utils.ACache;
import tties.cn.energy.utils.DateUtil;
import tties.cn.energy.utils.StringUtil;
import tties.cn.energy.utils.ToastUtil;
import tties.cn.energy.view.dialog.BottomStyleDialog;
import tties.cn.energy.view.dialog.BottomStyleDialogTwo;
import tties.cn.energy.view.dialog.MyTimePickerDialog;
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
    @BindView(R.id.data_current_ele_tv)
    TextView dataCurrentEleTv;
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
        dataCurrentTimeTv.setText(DateUtil.getCurrentYear()+"年"+DateUtil.getCurrentMonth()+"月");
        dialogtime = new MyTimePickerDialog();
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
                dialogtime.getTimePickerDialog(Data_CurrentActivity.this);
                dialogtime.setOnTimeClick(new MyTimePickerDialog.OnTimeClick() {
                    @Override
                    public void OnTimeClickListener(String text) {
                        ACache.getInstance().put(Constants.CACHE_OPS_BASEDATE, text);
                        dataCurrentTimeTv.setText(text);
                        mPresenter.getData_CurrentData();
                        mPresenter.getData_CurrentPressKwData();
                    }
                });
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
        if(bean.getDataList().size()>0){
            dataCurrentChart.clearData();
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

    }

    @Override
    public void setData_CurrentPressData(Data_CurrentPressbean bean) {
        if(bean.getDataList().size()>0){
            dataCurrentpressChart.clearData();
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

    }

    @Override
    public void setAllElectricity(final AllElectricitybean allElectricitybean) {
        ACache.getInstance().put(Constants.CACHE_OPS_OBJID, allElectricitybean.getLedgerId());
        ACache.getInstance().put(Constants.CACHE_OPS_OBJTYPE, 1);
        ACache.getInstance().put(Constants.CACHE_OPS_BASEDATE, DateUtil.getCurrentYear()+"-"+DateUtil.getCurrentMonth());
        mPresenter.getData_CurrentData();
        mPresenter.getData_CurrentPressKwData();
        dataCurrentEleTv.setText(allElectricitybean.getMeterList().get(0).getMeterName());
        dialog = new BottomStyleDialog(Data_CurrentActivity.this, allElectricitybean);
        dialog.setCliekAllElectricity(new BottomStyleDialog.OnCliekAllElectricity() {
            @Override
            public void OnCliekAllElectricityListener(int poaiton) {
                if (poaiton == 0) {
                    long ledgerId = allElectricitybean.getLedgerId();
                    dataCurrentEleTv.setText(allElectricitybean.getLedgerName());
                    ACache.getInstance().put(Constants.CACHE_OPS_OBJID, ledgerId);
                    ACache.getInstance().put(Constants.CACHE_OPS_OBJTYPE,1);

                }
                if (poaiton > 0) {
                    long meterId = allElectricitybean.getMeterList().get(poaiton - 1).getMeterId();
                    dataCurrentEleTv.setText(allElectricitybean.getMeterList().get(poaiton - 1).getMeterName());
                    ACache.getInstance().put(Constants.CACHE_OPS_OBJID, meterId);
                    ACache.getInstance().put(Constants.CACHE_OPS_OBJTYPE, 2);

                }
                mPresenter.getData_CurrentData();
                mPresenter.getData_CurrentPressKwData();
            }
        });
    }
}
