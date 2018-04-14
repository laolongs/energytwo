package tties.cn.energy.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.umeng.socialize.bean.SHARE_MEDIA;

import butterknife.BindView;
import butterknife.ButterKnife;
import tties.cn.energy.R;
import tties.cn.energy.base.BaseActivity;
import tties.cn.energy.model.result.Energy_Monthlybean;
import tties.cn.energy.presenter.Energy_MonthlyPresenter;
import tties.cn.energy.utils.ShareUtils;
import tties.cn.energy.utils.ToastUtil;
import tties.cn.energy.view.adapter.MyMonthlyAdapter;
import tties.cn.energy.view.iview.IEnergy_MonthlyView;
import tties.cn.energy.wxapi.Defaultcontent;

/**
 * 运维月报
 * 现在获取报告接口 参数变成 1运维报告 2能效word报告 3能效pdf报告
 */
public class Energy_OpsActivity extends BaseActivity<Energy_MonthlyPresenter> implements IEnergy_MonthlyView {
    private static final String TAG = "Energy_OpsActivity";
    @BindView(R.id.toolbar_left)
    ImageView toolbarLeft;
    @BindView(R.id.toolbar_text)
    TextView toolbarText;
    @BindView(R.id.enerey_ops_rec)
    RecyclerView enereyOpsRec;
    @BindView(R.id.enerey_ops_ll)
    LinearLayout enereyOpsLl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        mPresenter.getEnergy_Monthly(1);
        toolbarText.setText("运维月报");
        toolbarLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        enereyOpsRec.setLayoutManager(new LinearLayoutManager(Energy_OpsActivity.this));
    }

    @Override
    protected void createPresenter() {
        mPresenter = new Energy_MonthlyPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_energy__ops;
    }

    @Override
    public void setEnergy_MonthlyData(Energy_Monthlybean bean) {
        if(bean.getResult().size()==0){
            enereyOpsLl.setVisibility(View.VISIBLE);
        }else{
            enereyOpsLl.setVisibility(View.GONE);
        }
        MyMonthlyAdapter adapter = new MyMonthlyAdapter(this, bean);
        enereyOpsRec.setAdapter(adapter);
    }
    public void start(View view){
        ToastUtil.showShort(this,"ok");
//        ShareUtils.shareWeb(this, Defaultcontent.url, Defaultcontent.title
//                , Defaultcontent.text, Defaultcontent.imageurl, R.mipmap.icon_logo_share, SHARE_MEDIA.WEIXIN
//        );
        ShareUtils.shareWeb(this, Defaultcontent.url, Defaultcontent.title
                , Defaultcontent.text, Defaultcontent.imageurl, R.mipmap.icon_logo_share, SHARE_MEDIA.QQ
        );
    }
}
