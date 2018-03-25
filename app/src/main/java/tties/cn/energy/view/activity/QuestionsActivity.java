package tties.cn.energy.view.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import tties.cn.energy.R;
import tties.cn.energy.application.MyApplication;
import tties.cn.energy.base.BaseActivity;
import tties.cn.energy.model.result.Loginbean;
import tties.cn.energy.presenter.QuestionsPresenter;
import tties.cn.energy.utils.ToastUtil;
import tties.cn.energy.view.adapter.MyQurestionTabAdapter;
import tties.cn.energy.view.iview.IQuestionsView;

/**
 * 问题详情
 */
@RequiresApi(api = Build.VERSION_CODES.M)
public class QuestionsActivity extends BaseActivity<QuestionsPresenter> implements IQuestionsView {

    @BindView(R.id.toolbar_left)
    TextView toolbarLeft;
    @BindView(R.id.toolbar_text)
    TextView toolbarText;
    @BindView(R.id.qu_tab)
    TabLayout quTab;
    @BindView(R.id.qu_linkman)
    LinearLayout quLinkman;
    @BindView(R.id.qu_reply)
    LinearLayout quReply;
    @BindView(R.id.qu_vp)
    ViewPager quVp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        mPresenter.getQuestionsTabData();
        toolbarText.setText("问题详情");
        initView();
    }

    private void initView() {
        //联系人
        quLinkman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtil.showShort(QuestionsActivity.this,"联系人");
            }
        });
        //回复
        quReply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Loginbean userInfo = MyApplication.getUserInfo();
//                Log.i(TAG, "onClick: "+userInfo.g);
                ToastUtil.showShort(QuestionsActivity.this,"回复");
            }
        });
    }

    @Override
    protected void createPresenter() {
        mPresenter = new QuestionsPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_questions;
    }


    @Override
    public void setTabData(String[] array, List<Fragment> list) {
        MyQurestionTabAdapter adapter=new MyQurestionTabAdapter(getSupportFragmentManager(),array,list);
        quVp.setAdapter(adapter);
        quTab.setupWithViewPager(quVp);
        //设置可以滑动
//        quTab.setTabMode(TabLayout.MODE_SCROLLABLE);
    }
}
