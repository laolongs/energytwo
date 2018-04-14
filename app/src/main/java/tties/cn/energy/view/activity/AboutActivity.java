package tties.cn.energy.view.activity;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import tties.cn.energy.R;
import tties.cn.energy.base.BaseActivity;
import tties.cn.energy.common.Constants;
import tties.cn.energy.common.EventKind;
import tties.cn.energy.model.bean.EventBusBean;
import tties.cn.energy.model.httputils.send.VersionSend;
import tties.cn.energy.model.result.Versionbean;
import tties.cn.energy.presenter.MainPresenter;
import tties.cn.energy.utils.ACache;
import tties.cn.energy.utils.AppUtils;
import tties.cn.energy.utils.ToastUtil;
import tties.cn.energy.view.iview.IMainView;

/**
 * 关于我们
 */
public class AboutActivity extends AppCompatActivity{

    @BindView(R.id.toolbar_text)
    TextView toolbarText;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.logo)
    ImageView logo;
    @BindView(R.id.textview_newversion)
    TextView textviewNewversion;
    @BindView(R.id.layout_newversion)
    LinearLayout layoutNewversion;
    @BindView(R.id.textview_curversion)
    TextView textviewCurversion;
    @BindView(R.id.textview_about)
    TextView textviewAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);
        initToolBar();
        initClick();
//        Versionbean versionResult = ACache.getInstance().getAsObject(Constants.CACHEE_VERSION);
//        textviewCurversion.setText(versionResult.getVersionName());
//        textviewNewversion.setText(AppUtils.getVersionName());
        textviewAbout.setText("天天智电是以探索“智慧售电”新模式为特点，以构建“设备智能化、信息一体化、大数据增值服务”为己任的现代化智慧售电公司。广州天天智慧售电有限公司已是经广东省经济和信息化委员会批准设立的第三类独立售电公司。");

    }



    private void initClick() {
        layoutNewversion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VersionSend.getVersionData(AboutActivity.this);
            }
        });

    }

    private void initToolBar() {
        toolbar.setTitle("");
//        toolbarText.setTextColor();
        toolbarText.setText("关于我们");
        //设置是否有返回箭头
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //重写ToolBar返回按钮的行为，防止重新打开父Activity重走生命周期方法
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(EventBusBean bean) {
        if (bean != null && bean.getKind().equals(EventKind.EVENT_VERSION_SYCN)) {
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
                    UpdateActivity.show(AboutActivity.this);
                } else {
                    ToastUtil.showShort(AboutActivity.this,"已是最新版本");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
