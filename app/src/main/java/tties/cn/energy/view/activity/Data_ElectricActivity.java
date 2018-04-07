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
import tties.cn.energy.model.result.Data_Electricbean;
import tties.cn.energy.presenter.Data_ElectricPresenter;
import tties.cn.energy.utils.StringUtil;
import tties.cn.energy.utils.ToastUtil;
import tties.cn.energy.view.adapter.MyMonthlyAdapter;
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
    private BottomStyleDialog dialog;
    int num = 0;
    MyTimePickerDialog dialogtime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initView();
        mPresenter.getAllElectricityData();
        mPresenter.getData_Electric(6);
    }

    private void initView() {
        dialogtime=new MyTimePickerDialog();
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
                dialogtime.getTimePickerDialog(Data_ElectricActivity.this,dataElectricalTv);
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
        ArrayList<Entry> values = new ArrayList<>();
        List<String> listDate = new ArrayList<String>();
        for (int i = 0; i < bean.getDataList().size(); i++) {
            num = num + bean.getDataList().get(i).getA();
            Entry entry = new Entry(i, 0f);
            entry.setY((float) bean.getDataList().get(i).getA());
            values.add(entry);
            String[] split = StringUtil.split(bean.getDataList().get(i).getFreezeTime(), " ");
            listDate.add(split[0]);
        }
        electricalNum.setText(num + "");
        electricalChart.setDataSet(values, "");
        electricalChart.setDayXAxis(listDate);
        electricalChart.loadChart();

    }

    @Override
    public void setAllElectricity(final AllElectricitybean allElectricitybean) {
        dialog = new BottomStyleDialog(Data_ElectricActivity.this, allElectricitybean);
        dialog.setCliekAllElectricity(new BottomStyleDialog.OnCliekAllElectricity() {
            @Override
            public void OnCliekAllElectricityListener(int poaiton) {
                long meterId = allElectricitybean.getMeterList().get(poaiton).getMeterId();
                ToastUtil.showShort(Data_ElectricActivity.this, "" + meterId);
            }
        });
    }
}
