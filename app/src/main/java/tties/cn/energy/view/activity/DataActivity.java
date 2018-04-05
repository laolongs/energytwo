package tties.cn.energy.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

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
import tties.cn.energy.model.result.Databean;
import tties.cn.energy.presenter.DataPresenter;
import tties.cn.energy.utils.StringUtil;
import tties.cn.energy.utils.ToastUtil;
import tties.cn.energy.view.dialog.BottomStyleDialog;
import tties.cn.energy.view.dialog.MyPopupWindow;
import tties.cn.energy.view.iview.IDataView;

/**
 * 电费数据
 */
public class DataActivity extends BaseActivity<DataPresenter> implements View.OnClickListener, IDataView {

    @BindView(R.id.toolbar_left)
    ImageView toolbarLeft;
    @BindView(R.id.toolbar_text)
    TextView toolbarText;
    @BindView(R.id.data_time)
    LinearLayout dataTime;
    @BindView(R.id.data_allelectric)
    LinearLayout dataAllelectric;
    @BindView(R.id.data_time_tv)
    TextView dataTimeTv;
    @BindView(R.id.data_charge_img1)
    ImageView dataChargeImg1;
    @BindView(R.id.data_charge_img2)
    ImageView dataChargeImg2;
    @BindView(R.id.data_charge_img3)
    ImageView dataChargeImg3;
    @BindView(R.id.data_charge_img4)
    ImageView dataChargeImg4;
    @BindView(R.id.data_all_charge)
    TextView dataAllCharge;
    @BindView(R.id.data_base_charge)
    TextView dataBaseCharge;
    @BindView(R.id.data_year_charge)
    TextView dataYearCharge;
    @BindView(R.id.data_forces_charge)
    TextView dataForcesCharge;
    @BindView(R.id.data_chart)
    LineDataChart dataChart;
    private PopupWindow mCurPopupWindow;
    private MyPopupWindow popupWindow;
    private BottomStyleDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        popupWindow = new MyPopupWindow();
        dataChargeImg1.setOnClickListener(this);
        dataChargeImg2.setOnClickListener(this);
        dataChargeImg3.setOnClickListener(this);
        dataChargeImg4.setOnClickListener(this);
        initView();

    }

    private void initView() {
        toolbarText.setText("电费数据");
        toolbarLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mPresenter.getData();
        mPresenter.getchartData();
        mPresenter.getAllElectricityData();
        dataTime.setOnClickListener(new View.OnClickListener() {
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
                                dataTimeTv.setText(time);
                            }
                        })
                        .build();
                dialogYearMonth.show(getSupportFragmentManager(), "年_月");

            }
        });
        //总电量
        dataAllelectric.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dialog != null) {
                    dialog.show();
                }
            }
        });
    }

    @Override
    protected void createPresenter() {
        mPresenter = new DataPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_data;
    }

    @Override
    public void onBackPressed() {
        if (mCurPopupWindow != null && mCurPopupWindow.isShowing()) {
            mCurPopupWindow.dismiss();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.data_charge_img1:

                popupWindow.showTipPopupWindow(dataChargeImg1, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getBaseContext(), "点击到弹窗内容", Toast.LENGTH_SHORT).show();
                    }
                });

                break;
            case R.id.data_charge_img2:
                popupWindow.showTipPopupWindow(dataChargeImg2, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getBaseContext(), "点击到弹窗内容", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case R.id.data_charge_img3:
                popupWindow.showTipPopupWindow(dataChargeImg3, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getBaseContext(), "点击到弹窗内容", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case R.id.data_charge_img4:
                popupWindow.showTipPopupWindow(dataChargeImg4, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getBaseContext(), "点击到弹窗内容", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
        }
    }

    @Override
    public void setDataData(Databean bean) {
        //总电费
        dataAllCharge.setText(bean.getDataList().get(0).getTotalSum()+"");
        //基本电费
        dataBaseCharge.setText(bean.getDataList().get(0).getBaseSum()+"");
        //年度电费
        dataYearCharge.setText(bean.getDataList().get(0).getFeeSum()+"");
        //力调电费
        dataForcesCharge.setText(bean.getDataList().get(0).getFouceSum()+"");
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
            listDate.add(split[1]);
        }
        dataChart.setDataSet(values, "");
        dataChart.setDayXAxis(listDate);
        dataChart.loadChart();
    }

    @Override
    public void setAllElectricity(final AllElectricitybean allElectricitybean) {
        dialog = new BottomStyleDialog(DataActivity.this, allElectricitybean);
        dialog.setCliekAllElectricity(new BottomStyleDialog.OnCliekAllElectricity() {
            @Override
            public void OnCliekAllElectricityListener(int poaiton) {
                long meterId = allElectricitybean.getMeterList().get(poaiton).getMeterId();
                ToastUtil.showShort(DataActivity.this, "" + meterId);
            }
        });
    }
}
