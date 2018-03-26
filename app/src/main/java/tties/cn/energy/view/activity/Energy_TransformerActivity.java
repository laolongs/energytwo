package tties.cn.energy.view.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import tties.cn.energy.R;
import tties.cn.energy.base.BaseActivity;
import tties.cn.energy.presenter.MainPresenter;
import tties.cn.energy.utils.ToastUtil;
import tties.cn.energy.view.iview.IMainView;

/**
 * 变压器优化
 */
public class Energy_TransformerActivity extends BaseActivity<MainPresenter> implements IMainView {

    @BindView(R.id.toolbar_left)
    TextView toolbarLeft;
    @BindView(R.id.toolbar_text)
    TextView toolbarText;
    @BindView(R.id.energy_transformer_tab)
    TabLayout energyTransformerTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        toolbarText.setText("变压器优化");
        initTab();

    }

    private void initTab() {
        energyTransformerTab.addTab(energyTransformerTab.newTab().setText("1#变压器"));
        energyTransformerTab.addTab(energyTransformerTab.newTab().setText("2#变压器"));
        energyTransformerTab.addTab(energyTransformerTab.newTab().setText("3#变压器"));
        energyTransformerTab.addTab(energyTransformerTab.newTab().setText("4#变压器"));
        energyTransformerTab.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                ToastUtil.showShort(Energy_TransformerActivity.this,tab.getText());
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
    protected void createPresenter() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_energy__transformer;
    }

    @Override
    public void setViewPageData(List<View> list) {

    }
}
