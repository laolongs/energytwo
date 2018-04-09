package tties.cn.energy.view.dialog;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;

import java.text.SimpleDateFormat;
import java.util.Date;

import tties.cn.energy.R;

/**
 * Created by li on 2018/4/7
 * description：
 * author：guojlli
 */

public class MyTimePickerDialog {
    public  void getTimePickerDialog(Context context, final TextView tv){
//        时间选择器
        com.jzxiang.pickerview.TimePickerDialog dialogYearMonth = new com.jzxiang.pickerview.TimePickerDialog.Builder()
                .setType(Type.YEAR)
                .setTitleStringId("选择月份")
                .setThemeColor(R.color.home_btn_lightblue)
                .setWheelItemTextNormalColor(context.getResources().getColor(R.color.home_btn_lightblue))
                .setWheelItemTextSelectorColor(context.getResources().getColor(R.color.radiobtn_meterlist_colorLineDefault))
                .setThemeColor(context.getResources().getColor(R.color.radiobtn_meterlist_colorLineDefault))
                .setWheelItemTextSelectorColor(context.getResources().getColor(R.color.black))
                .setCallBack(new OnDateSetListener() {
                    @Override
                    public void onDateSet(com.jzxiang.pickerview.TimePickerDialog timePickerView, long millseconds) {
                        Date date = new Date(millseconds);
                        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM日");
                        String time= format.format(date);
                        tv.setText(time);
                    }
                })
                .build();
//        dialogYearMonth.show(getSupportFragmentManager(), "年_月");
        dialogYearMonth.show(((AppCompatActivity)context).getSupportFragmentManager(), "年_月");
    }

}
