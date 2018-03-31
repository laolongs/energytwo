package tties.cn.energy.view.activity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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
import tties.cn.energy.view.dialog.MyPopupWindow;
import tties.cn.energy.view.iview.IMainView;

/**
 * 电费数据
 */
public class DataActivity extends BaseActivity<MainPresenter> implements IMainView, View.OnClickListener {

    @BindView(R.id.toolbar_left)
    ImageView toolbarLeft;
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
    @BindView(R.id.data_charge_img1)
    ImageView dataChargeImg1;
    @BindView(R.id.data_charge_img2)
    ImageView dataChargeImg2;
    @BindView(R.id.data_charge_img3)
    ImageView dataChargeImg3;
    @BindView(R.id.data_charge_img4)
    ImageView dataChargeImg4;
    private PopupWindow mCurPopupWindow;
    private MyPopupWindow popupWindow;
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
        dataTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                com.jzxiang.pickerview.TimePickerDialog dialogYearMonth = new com.jzxiang.pickerview.TimePickerDialog.Builder()
                        .setType(Type.YEAR_MONTH)
                        .setTitleStringId("选择月份")
                        .setThemeColor(R.color.home_btn_lightblue)
                        .setWheelItemTextNormalColor(getResources().getColor(R.color.home_btn_lightblue))
                        .setWheelItemTextSelectorColor(getResources().getColor(R.color.radiobtn_meterlist_colorLineDefault))
                        .setThemeColor(getResources().getColor(R.color.radiobtn_meterlist_colorLineDefault))
                        .setWheelItemTextSelectorColor(getResources().getColor(R.color.black))
                        .setCallBack(new OnDateSetListener() {
                            @Override
                            public void onDateSet(com.jzxiang.pickerview.TimePickerDialog timePickerView, long millseconds) {
                                Date date = new Date(millseconds);
                                SimpleDateFormat format = new SimpleDateFormat("yyyy年MM日");
                                String time= format.format(date);
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
        switch (view.getId()){
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

}
