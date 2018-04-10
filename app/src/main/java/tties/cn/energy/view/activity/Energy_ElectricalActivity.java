package tties.cn.energy.view.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import tties.cn.energy.R;
import tties.cn.energy.base.BaseActivity;
import tties.cn.energy.chart.LineDataChart;
import tties.cn.energy.common.MyProgressRound;
import tties.cn.energy.model.result.AllElectricitybean;
import tties.cn.energy.model.result.Databean;
import tties.cn.energy.presenter.DataPresenter;
import tties.cn.energy.presenter.MainPresenter;
import tties.cn.energy.utils.StringUtil;
import tties.cn.energy.view.iview.IDataView;
import tties.cn.energy.view.iview.IMainView;

/**
 * 电度电量优化
 */
public class Energy_ElectricalActivity extends BaseActivity<DataPresenter> implements IDataView {
    private static final String TAG = "Energy_ElectricalActivi";
    @BindView(R.id.electrical_myview)
    MyProgressRound electricalMyview;
    @BindView(R.id.toolbar_left)
    ImageView toolbarLeft;
    @BindView(R.id.toolbar_text)
    TextView toolbarText;
    @BindView(R.id.enerey_electrical_month)
    TextView enereyElectricalMonth;
    @BindView(R.id.enerey_electrical_cusp)
    TextView enereyElectricalCusp;
    @BindView(R.id.enerey_electrical_hight)
    TextView enereyElectricalHight;
    @BindView(R.id.enerey_electrical_low)
    TextView enereyElectricalLow;
    @BindView(R.id.enerey_electrical_kva)
    TextView enereyElectricalKva;
    @BindView(R.id.enerey_electrical_price)
    TextView enereyElectricalPrice;
    @BindView(R.id.enerey_electrical_select)
    ImageView enereyElectricalSelect;
    @BindView(R.id.enerey_electrical_chart)
    LineDataChart enereyElectricalChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initView();

    }

    private void initView() {
        mPresenter.getData();
        mPresenter.getchartData();
        toolbarText.setText("电度电费优化");
        toolbarLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    @Override
    protected void createPresenter() {
        mPresenter=new DataPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_energy__electrical;
    }

    @Override
    public void setDataData(Databean bean) {
        int percentage=0;
        double num=0;
        int low=bean.getDataList().get(0).getSectorGuValue();
        int hight=bean.getDataList().get(0).getSectorJianValue();
        int cusp=bean.getDataList().get(0).getSectorFengValue();
        num=low+hight+cusp;
        Log.i(TAG, "setDataDatalow: "+low);
        Log.i(TAG, "setDataDatanum: "+num);
        enereyElectricalMonth.setText(bean.getDataList().get(0).getBaseDate()+"");
        //尖峰
        electricalMyview.setProgressMax(cusp,num);
        //高峰
        electricalMyview.setProgressCenter(hight,num);
        //低谷
        electricalMyview.setProgressMin(low,num);
        enereyElectricalCusp.setText(bean.getDataList().get(0).getSectorJianValue()+"度");
        enereyElectricalHight.setText(bean.getDataList().get(0).getSectorFengValue()+"度");
        enereyElectricalLow.setText(bean.getDataList().get(0).getSectorGuValue()+"度");
        enereyElectricalKva.setText(bean.getDataList().get(0).getTotalEnergy()+"kVA");
        enereyElectricalPrice.setText((int)bean.getDataList().get(0).getTotalSum()+"元");
    }

    @Override
    public void setDataChartData(Databean bean) {
        ArrayList<Entry> values = new ArrayList<>();
        List<String> listDate = new ArrayList<String>();
        for (int i = 0; i < bean.getDataList().size(); i++) {
            Entry entry = new Entry(i, 0f);
            entry.setY((float) bean.getDataList().get(i).getCost());
            values.add(entry);
            String[] split = StringUtil.split(bean.getDataList().get(i).getBaseDate(), "-");
            listDate.add(split[1]+"月");
        }
        enereyElectricalChart.setDataSet(values, "");
        enereyElectricalChart.setDayXAxis(listDate);
        enereyElectricalChart.loadChart();
    }

    @Override
    public void setAllElectricity(AllElectricitybean allElectricitybean) {

    }
}
