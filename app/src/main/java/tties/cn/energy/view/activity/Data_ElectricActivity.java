package tties.cn.energy.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import tties.cn.energy.R;
import tties.cn.energy.base.BaseActivity;
import tties.cn.energy.chart.LineDataChart;
import tties.cn.energy.common.Constants;
import tties.cn.energy.model.result.AllElectricitybean;
import tties.cn.energy.model.result.DataAllbean;
import tties.cn.energy.model.result.Data_Electricbean;
import tties.cn.energy.presenter.Data_ElectricPresenter;
import tties.cn.energy.utils.ACache;
import tties.cn.energy.utils.DateUtil;
import tties.cn.energy.utils.StringUtil;
import tties.cn.energy.view.dialog.BottomStyleDialog;
import tties.cn.energy.view.dialog.MyTimePickerDialog;
import tties.cn.energy.view.iview.IData_ElectricView;

/**
 * 电量数据
 */
public class Data_ElectricActivity extends BaseActivity<Data_ElectricPresenter> implements IData_ElectricView {

    @BindView(R.id.toolbar_left)
    ImageView toolbarLeft;
    @BindView(R.id.electrical_num)
    TextView electricalNum;
    @BindView(R.id.electrical_chart)
    LineDataChart electricalChart;

    @BindView(R.id.toolbar_text)
    TextView toolbarText;
    @BindView(R.id.data_electrical_tv)
    TextView dataElectricalTv;
    @BindView(R.id.data_electrical_time)
    LinearLayout dataElectricalTime;
    @BindView(R.id.data_allelectric_electrical)
    LinearLayout dataAllelectricElectrical;
    @BindView(R.id.data_electrical_time_tv)
    TextView dataElectricalTimeTv;
    private BottomStyleDialog dialog;
    double num = 0;
    MyTimePickerDialog dialogtime;
    DataAllbean allbean=new DataAllbean();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        mPresenter.getAllElectricityData();
        dataElectricalTv.setText(DateUtil.getCurrentYear()+"年"+DateUtil.getCurrentMonth()+"月");
        dialogtime = new MyTimePickerDialog();
        toolbarText.setText("电量数据");
        toolbarLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        dataElectricalTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogtime.getTimePickerDialog(Data_ElectricActivity.this);
                dialogtime.setOnTimeClick(new MyTimePickerDialog.OnTimeClick() {
                    @Override
                    public void OnTimeClickListener(String text) {
                        ACache.getInstance().put(Constants.CACHE_OPS_BASEDATE, text);
                        dataElectricalTv.setText(text);
                        mPresenter.getData_Electric();
                    }
                });
            }
        });
        dataAllelectricElectrical.setOnClickListener(new View.OnClickListener() {


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
        mPresenter = new Data_ElectricPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_data__electric;
    }


    @Override
    public void setData_ElectricData(Data_Electricbean bean) {
        if (bean.getDataList().size() > 0) {
            electricalChart.clearData();
            ArrayList<Entry> values = new ArrayList<>();
            List<String> listDate = new ArrayList<String>();
            for (int i = 0; i < bean.getDataList().size(); i++) {
                num = num + bean.getDataList().get(i).getA();
                Entry entry = new Entry(i, 0f);
                entry.setY((float) bean.getDataList().get(i).getA());
                values.add(entry);
                String split = StringUtil.substring(bean.getDataList().get(i).getFreezeTime(),5,10);
                listDate.add(split);
            }
            XAxis xAxis = electricalChart.getXAxis();
            xAxis.setLabelCount(bean.getDataList().size(),true);
            xAxis.setLabelRotationAngle(-50);
            electricalNum.setText(num + "");
            electricalChart.setDataSet(values, "");
            electricalChart.setDayXAxis(listDate);
            electricalChart.loadChart();
        }


    }

    @Override
    public void setAllElectricity(final AllElectricitybean allElectricitybean) {
        if(allElectricitybean.getMeterList().size()>0){
            ACache.getInstance().put(Constants.CACHE_OPS_OBJID, allElectricitybean.getLedgerId());
            ACache.getInstance().put(Constants.CACHE_OPS_OBJTYPE, 1);
            ACache.getInstance().put(Constants.CACHE_OPS_BASEDATE, DateUtil.getCurrentYear()+"-"+DateUtil.getCurrentMonth());
            mPresenter.getData_Electric();
            dataElectricalTimeTv.setText("总电量");
            dialog = new BottomStyleDialog(Data_ElectricActivity.this, allElectricitybean);
            dialog.setCliekAllElectricity(new BottomStyleDialog.OnCliekAllElectricity() {
                @Override
                public void OnCliekAllElectricityListener(int poaiton) {
                    if (poaiton == 0) {
                        long ledgerId = allElectricitybean.getLedgerId();
                        dataElectricalTimeTv.setText("总电量");
                        ACache.getInstance().put(Constants.CACHE_OPS_OBJID, ledgerId);
                        ACache.getInstance().put(Constants.CACHE_OPS_OBJTYPE, 1);

                    }
                    if (poaiton > 0) {
                        long meterId = allElectricitybean.getMeterList().get(poaiton - 1).getMeterId();
                        dataElectricalTimeTv.setText(allElectricitybean.getMeterList().get(poaiton - 1).getMeterName());
                        ACache.getInstance().put(Constants.CACHE_OPS_OBJID, meterId);
                        ACache.getInstance().put(Constants.CACHE_OPS_OBJTYPE, 2);

                    }
                    mPresenter.getData_Electric();
                }
            });
        }

    }

}
