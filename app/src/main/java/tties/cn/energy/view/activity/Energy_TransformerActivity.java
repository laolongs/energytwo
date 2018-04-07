package tties.cn.energy.view.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import tties.cn.energy.R;
import tties.cn.energy.base.BaseActivity;
import tties.cn.energy.chart.LineDataChart;
import tties.cn.energy.model.result.Energy_TransformerDamgebean;
import tties.cn.energy.model.result.Energy_TransformerListbean;
import tties.cn.energy.model.result.Energy_TransformerTemperaturebean;
import tties.cn.energy.model.result.Energy_TransformerVolumebean;
import tties.cn.energy.presenter.Energy_TransformerPresenter;
import tties.cn.energy.utils.StringUtil;
import tties.cn.energy.view.iview.IEnergy_TransformerView;

/**
 * 变压器优化
 */
public class Energy_TransformerActivity extends BaseActivity<Energy_TransformerPresenter> implements IEnergy_TransformerView {
    private static final String TAG = "Energy_TransformerActiv";
    @BindView(R.id.toolbar_left)
    ImageView toolbarLeft;
    @BindView(R.id.toolbar_text)
    TextView toolbarText;
    @BindView(R.id.energy_transformer_tab)
    TabLayout energyTransformerTab;
    @BindView(R.id.energy_transformer_select1)
    ImageView energyTransformerSelect1;
    @BindView(R.id.energy_transformer_chart1)
    LineDataChart energyTransformerChart1;
    @BindView(R.id.energy_transformer_select2)
    ImageView energyTransformerSelect2;
    @BindView(R.id.energy_transformer_chart2)
    LineDataChart energyTransformerChart2;
    @BindView(R.id.energy_transformer_damge)
    TextView energyTransformerDamge;
    @BindView(R.id.energy_transformer_kwh)
    TextView energyTransformerKwh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initView();

    }

    private void initView() {
        mPresenter.getEnergy_TransformerList();
        toolbarText.setText("变压器优化");
        toolbarLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void createPresenter() {
        mPresenter = new Energy_TransformerPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_energy__transformer;
    }


    @Override
    public void setEnergy_TransformerListbeanData(final Energy_TransformerListbean bean) {
        for (int i = 0; i < bean.getResult().size(); i++) {
            energyTransformerTab.addTab(energyTransformerTab.newTab().setText(bean.getResult().get(i).getName()));
        }
        mPresenter.getEnergy_TransformerTemperature(bean.getResult().get(0).getCompanyEquipmentId());
        mPresenter.getEnergy_TransformerDamge(bean.getResult().get(0).getCompanyEquipmentId());
//        mPresenter.getEnergy_TransformerVolume(bean.getResult().get(0).getCompanyEquipmentId());
        energyTransformerTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
//                ToastUtil.showShort(Energy_TransformerActivity.this, tab.getText());
                int position = tab.getPosition();
                mPresenter.getEnergy_TransformerTemperature(bean.getResult().get(position).getCompanyEquipmentId());
                mPresenter.getEnergy_TransformerDamge(bean.getResult().get(position).getCompanyEquipmentId());
//                mPresenter.getEnergy_TransformerVolume(bean.getResult().get(position).getCompanyEquipmentId());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void setEnergy_TransformerTemperaturebeanData(Energy_TransformerTemperaturebean bean) {
        ArrayList<Entry> values = new ArrayList<>();
        List<String> listDate = new ArrayList<String>();
        for (int i = 0; i < bean.getResult().size(); i++) {
            Entry entry = new Entry(i, 0f);
            entry.setY((float) bean.getResult().get(i).getData());
            values.add(entry);
            String[] split = StringUtil.split(bean.getResult().get(i).getTime(), "-");
            listDate.add(split[1]);
        }
        energyTransformerChart1.setDataSet(values, "");
        energyTransformerChart1.setDayXAxis(listDate);
        energyTransformerChart1.loadChart();
    }

    @Override
    public void setEnergy_TransformerDamgebeanData(Energy_TransformerDamgebean bean) {
        if(bean.getResult().getConsume()==0){
            energyTransformerKwh.setText("∞");
        }
        if(bean.getResult().getDamge()==0){
            energyTransformerDamge.setText("∞");
        }
        energyTransformerDamge.setText(bean.getResult().getDamge()+"%");
        energyTransformerKwh.setText(bean.getResult().getConsume()+"");
    }

    @Override
    public void setEnergy_TransformerVolumebeanData(Energy_TransformerVolumebean bean) {

    }
}
