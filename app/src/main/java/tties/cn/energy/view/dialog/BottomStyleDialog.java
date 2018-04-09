package tties.cn.energy.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import tties.cn.energy.R;
import tties.cn.energy.model.result.AllElectricitybean;
import tties.cn.energy.view.adapter.StyleAdapter;

/**
 * Created by li on 2018/3/28
 * description：总电量弹出框（从底部）
 * author：guojlli
 */

public class BottomStyleDialog extends Dialog implements AdapterView.OnItemClickListener {
    OnCliekAllElectricity listener;
    int postion=0;
    AllElectricitybean allElectricitybean;
    private ListView mLv;


    private StyleAdapter mAdapter;
    public void setCliekAllElectricity(OnCliekAllElectricity listener){
        this.listener=listener;
    }
    public BottomStyleDialog(Context context, AllElectricitybean allElectricitybean) {
        // 在构造方法里, 传入主题
        super(context, R.style.BottomDialogStyle);
        this.allElectricitybean=allElectricitybean;
        // 拿到Dialog的Window, 修改Window的属性
        Window window = getWindow();
        window.getDecorView().setPadding(0, 0, 0, 0);
        // 获取Window的LayoutParams
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = WindowManager.LayoutParams.MATCH_PARENT;
        attributes.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;
        // 一定要重新设置, 才能生效
        window.setAttributes(attributes);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_dialog);
        initView();
        initData();
    }

    private void initView() {
        mLv = (ListView) findViewById(R.id.lv_view_dialog);
        mLv.setOnItemClickListener(this);
    }

    private void initData() {
        // 填充数据集合
        mAdapter = new StyleAdapter(getContext(), allElectricitybean);
        mLv.setAdapter(mAdapter);
        View inflate = View.inflate(getContext(), R.layout.item_dialog, null);
        TextView viewhead = inflate.findViewById(R.id.tv_item_dialog_name);
        viewhead.setText(allElectricitybean.getLedgerName());
        mLv.addHeaderView(inflate);
        mLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                listener.OnCliekAllElectricityListener(i);
                dismiss();
            }
        });

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mAdapter.notifyDataSetChanged();
    }
    public interface OnCliekAllElectricity{
        void OnCliekAllElectricityListener(int poaiton);
    }
}