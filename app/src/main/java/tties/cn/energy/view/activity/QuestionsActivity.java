package tties.cn.energy.view.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import tties.cn.energy.R;
import tties.cn.energy.application.MyApplication;
import tties.cn.energy.base.BaseActivity;
import tties.cn.energy.enums.FaultType;
import tties.cn.energy.model.result.Loginbean;
import tties.cn.energy.model.result.Opsbean;
import tties.cn.energy.presenter.QuestionsPresenter;
import tties.cn.energy.utils.ToastUtil;
import tties.cn.energy.view.adapter.MyQurestionTabAdapter;
import tties.cn.energy.view.adapter.QuestionListAdapter;
import tties.cn.energy.view.fragment.Questions_discussFragment;
import tties.cn.energy.view.fragment.Questions_progressFragment;
import tties.cn.energy.view.iview.IQuestionsView;

/**
 * 问题详情
 */
@RequiresApi(api = Build.VERSION_CODES.M)
public class QuestionsActivity extends BaseActivity<QuestionsPresenter> implements IQuestionsView {

    @BindView(R.id.toolbar_left)
    ImageView toolbarLeft;
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
    @BindView(R.id.ques_index_title)
    TextView quesIndexTitle;
    @BindView(R.id.ques_index)
    TextView quesIndex;
    @BindView(R.id.ques_rank)
    TextView quesRank;
    @BindView(R.id.ques_createtime)
    TextView quesCreatetime;
    @BindView(R.id.ques_address)
    TextView quesAddress;
    @BindView(R.id.list_question_list)
    RecyclerView listQuestionList;
    @BindView(R.id.ptrlayout_question)
    FrameLayout ptrlayoutQuestion;
    @BindView(R.id.data_time_tv)
    TextView dataTimeTv;
    @BindView(R.id.qu_bt_ll)
    LinearLayout quBtLl;
    private String questionId;
    private static final String TAG = "QuestionsActivity";
    //打电话
    private String[] perms = {Manifest.permission.CALL_PHONE};
    private final int PERMS_REQUEST_CODE = 200;
    private String staffTel;
    Opsbean.ResultBean.QuestionListBean listbean;
    List<Fragment> listFragment;
    private Questions_discussFragment discussFragment;
    private Questions_progressFragment progressFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        toolbarText.setText("问题详情");
        initView();
    }

    private void initView() {
        listFragment=new ArrayList<>();
        //问题讨论
        discussFragment = new Questions_discussFragment();
        //问题进度
        progressFragment = new Questions_progressFragment();
        Intent intent = getIntent();
        questionId = intent.getStringExtra("questionId");
        Log.i(TAG, "initView: " + questionId);
        mPresenter.getQuestion(questionId);
//        if(listFragment.size()>0){

//        }

        //联系人
        quLinkman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {//Android 6.0以上版本需要获取权限
                    requestPermissions(perms, PERMS_REQUEST_CODE);//请求权限
                } else {
                    callPhone();
                }
            }
        });
        //回复
        quReply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Loginbean userInfo = MyApplication.getUserInfo();
//                Log.i(TAG, "onClick: "+userInfo.g);
                ToastUtil.showShort(QuestionsActivity.this, "回复");
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
    public void setTabData(String[] array) {
        MyQurestionTabAdapter adapter = new MyQurestionTabAdapter(getSupportFragmentManager(), array, listFragment);
        quVp.setAdapter(adapter);
        quTab.setupWithViewPager(quVp);
        //设置可以滑动
        quTab.setTabMode(TabLayout.MODE_SCROLLABLE);
    }

    @Override
    public void setQuestionData(Opsbean bean) {
        if (bean.getResult().getQuestionList().size() >= 0) {
            listbean=bean.getResult().getQuestionList().get(0);
            quesIndexTitle.setText(bean.getResult().getQuestionList().get(0).getEquipmentName());
            staffTel = bean.getResult().getQuestionList().get(0).getMaintUser().getStaffTel();
            quesIndex.setText(bean.getResult().getQuestionList().get(0).getQuestionIndex());
            quesCreatetime.setText(bean.getResult().getQuestionList().get(0).getCreateTime());
            quesAddress.setText(bean.getResult().getQuestionList().get(0).getDescription());
            quesRank.setText(FaultType.getInfo(bean.getResult().getQuestionList().get(0).getFaultType()) + "");
            discussFragment.setDiscussData(bean.getResult().getQuestionList().get(0).getAdviceList());
            progressFragment.setProgressData(bean.getResult().getQuestionList().get(0).getScheduleList());
            listFragment.add(progressFragment);
            listFragment.add(discussFragment);
            mPresenter.getQuestionsTabData();
            initRecycleView(listbean);
        } else {
            Log.i(TAG, "setQuestionData: " + "无数据");
        }
    }

    @Override
    public void onRequestPermissionsResult(int permsRequestCode, String[] permissions, int[] grantResults) {
        switch (permsRequestCode) {
            case PERMS_REQUEST_CODE:
                boolean storageAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                if (storageAccepted) {
                    callPhone();
                } else {
                    Log.i("MainActivity", "没有权限操作这个请求");
                }
                break;

        }
    }

    //拨打电话
    private void callPhone() {
        //检查拨打电话权限
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:" + staffTel));
            startActivity(intent);
        }
    }
    public void initRecycleView(Opsbean.ResultBean.QuestionListBean listbean){
        listQuestionList.setLayoutManager(new LinearLayoutManager(QuestionsActivity.this));
        QuestionListAdapter adapter=new QuestionListAdapter(QuestionsActivity.this,listbean);
        listQuestionList.setAdapter(adapter);
    }


}
