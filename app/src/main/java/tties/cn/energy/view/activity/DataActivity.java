package tties.cn.energy.view.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import tties.cn.energy.R;
import tties.cn.energy.base.BaseActivity;
import tties.cn.energy.presenter.MainPresenter;
import tties.cn.energy.view.iview.IMainView;

/**
 * 电费数据
 */
public class DataActivity extends BaseActivity<MainPresenter> implements IMainView {

    @BindView(R.id.toolbar_left)
    TextView toolbarLeft;
    @BindView(R.id.toolbar_text)
    TextView toolbarText;
    @BindView(R.id.data_toolbar)
    Toolbar dataToolbar;
    @BindView(R.id.data_time)
    LinearLayout dataTime;
    @BindView(R.id.data_allelectric)
    LinearLayout dataAllelectric;
    @BindView(R.id.data_time_tv)
    TextView dataTimeTv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initView();

    }

    private void initView() {
        toolbarText.setText("电费数据");
        dataTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //时间选择器
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

            }
        });
    }

    @Override
    protected void createPresenter() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_data;
    }

    @Override
    public void setViewPageData(List<View> list) {

    }
}
