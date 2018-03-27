package tties.cn.energy.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import tties.cn.energy.R;
import tties.cn.energy.base.BaseActivity;
import tties.cn.energy.chart.LineDataChart;
import tties.cn.energy.chart.PieDataChart;
import tties.cn.energy.common.Dictionary;
import tties.cn.energy.model.result.Data_Pressbean;
import tties.cn.energy.presenter.Data_PressPresenter;
import tties.cn.energy.presenter.MainPresenter;
import tties.cn.energy.utils.DateUtils;
import tties.cn.energy.view.iview.IData_PressView;
import tties.cn.energy.view.iview.IMainView;

/**
 * 电压不平衡
 */
public class Data_PressActivity extends BaseActivity<Data_PressPresenter> implements IData_PressView {

    @BindView(R.id.toolbar_left)
    TextView toolbarLeft;
    @BindView(R.id.toolbar_text)
    TextView toolbarText;
    @BindView(R.id.data_press_chart1)
    LineDataChart dataPressChart1;
    @BindView(R.id.data_press_chart2)
    LineDataChart dataPressChart2;
    @BindView(R.id.data_press_chart3)
    PieDataChart dataPressChart3;
    @BindView(R.id.data_time_tv)
    TextView dataTimeTv;
    @BindView(R.id.data_press_time)
    LinearLayout dataPressTime;
    @BindView(R.id.data_press_allelectric_tv)
    TextView dataPressAllelectricTv;
    @BindView(R.id.data_press_allelectric)
    LinearLayout dataPressAllelectric;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        mPresenter.getData_PressData(1);
    }

    @Override
    protected void createPresenter() {
        mPresenter=new Data_PressPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_data__press;
    }


    @Override
    public void setData_PressData(Data_Pressbean bean) {
        //折线图
        ArrayList<Entry> values1 = new ArrayList<>();
        List<String> listDate = new ArrayList<String>();
        for (int i = 0; i <bean.getLimitData().size() ; i++) {
//            List<Date> miList = DateUtils.getTimeAxis(bean.getLimitData().get(bean.getLimitData().size() - 1).getFREEZETIME(), Dictionary.DateType.DAY);
                Entry entry = new Entry(i++, 0f);

                values1.add(entry);
                listDate.add(55+"");
            }
        dataPressChart1.setDataSet(values1,"");
        dataPressChart1.setDayXAxis(listDate);
        dataPressChart1.loadChart();
        //折线图
        ArrayList<PieEntry> values2 = new ArrayList<>();
        for (int i = 0; i <bean.getLimitData().size() ; i++) {
//            List<Date> miList = DateUtils.getTimeAxis(bean.getLimitData().get(bean.getLimitData().size() - 1).getFREEZETIME(), Dictionary.DateType.DAY);
            PieEntry entry2 = new PieEntry(i++, 0f);

            values2.add(entry2);
            listDate.add(55+"");
        }
        dataPressChart3.setDataSet(values2);
        dataPressChart3.loadChart();

        }
}
