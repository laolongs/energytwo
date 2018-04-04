package tties.cn.energy.view;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import tties.cn.energy.R;
import tties.cn.energy.base.BaseActivity;
import tties.cn.energy.common.Constants;
import tties.cn.energy.model.result.Versionbean;
import tties.cn.energy.presenter.MainPresenter;
import tties.cn.energy.utils.ACache;
import tties.cn.energy.view.activity.UpdateActivity;
import tties.cn.energy.view.fragment.DataFragment;
import tties.cn.energy.view.fragment.EnergyFragment;
import tties.cn.energy.view.fragment.IdentityFragment;
import tties.cn.energy.view.fragment.OpsFragment;
import tties.cn.energy.view.iview.IMainView;

public class MainActivity extends BaseActivity<MainPresenter> implements IMainView {

    @BindView(R.id.main_fl)
    FrameLayout mainFl;
    @BindView(R.id.main_bt1)
    RadioButton mainBt1;
    @BindView(R.id.main_bt2)
    RadioButton mainBt2;
    @BindView(R.id.main_bt3)
    RadioButton mainBt3;
    @BindView(R.id.main_bt4)
    RadioButton mainBt4;
    @BindView(R.id.main_rg)
    RadioGroup mainRg;
    private List<View> mViews;
    private Unbinder bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ButterKnife.bind(this);
        initView();
        checkVersion();
    }

    private void initView() {
        mainRg.check(R.id.main_bt1);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_fl,new OpsFragment()).commit();
        mainRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.main_bt1:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_fl,new OpsFragment()).commit();
                        break;
                    case R.id.main_bt2:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_fl,new EnergyFragment()).commit();
                        break;
                    case R.id.main_bt3:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_fl,new DataFragment()).commit();
                        break;
                    case R.id.main_bt4:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_fl,new IdentityFragment()).commit();
                        break;
                }
            }
        });
    }

    @Override
    protected void createPresenter() {
        mPresenter = new MainPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void setViewPageData(List<View> list) {
    }

    private void checkVersion() {
        try {
            Versionbean ret = ACache.getInstance().getAsObject(Constants.CACHEE_VERSION);
            if (ret == null) {
                return;
            }
            // 获取packagemanager的实例
            PackageManager packageManager = getPackageManager();
            // getPackageName()是你当前类的包名，0代表是获取版本信息
            PackageInfo packInfo = packageManager.getPackageInfo(getPackageName(), 0);
            int versionCode = packInfo.versionCode;
            if (ret.getVersionCode() > versionCode) {
                UpdateActivity.show(MainActivity.this);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }
}
