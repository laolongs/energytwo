package tties.cn.energy.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import tties.cn.energy.R;
import tties.cn.energy.base.BaseActivity;
import tties.cn.energy.presenter.MainPresenter;
import tties.cn.energy.view.iview.IMainView;

/**
 * 力调电量优化
 */
public class Energy_ForceActivity extends BaseActivity<MainPresenter> implements IMainView {

    @BindView(R.id.toolbar_left)
    ImageView toolbarLeft;
    @BindView(R.id.toolbar_text)
    TextView toolbarText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        toolbarText.setText("力调电量优化");
    }

    @Override
    protected void createPresenter() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_energy__force;
    }

    @Override
    public void setViewPageData(List<View> list) {

    }
}
