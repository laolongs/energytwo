package tties.cn.energy.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import in.srain.cube.views.ptr.PtrDefaultHandler2;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.PtrHandler2;
import tties.cn.energy.R;
import tties.cn.energy.base.BaseFragment;
import tties.cn.energy.common.Constants;
import tties.cn.energy.model.result.OpsLoginbean;
import tties.cn.energy.model.result.Opsbean;
import tties.cn.energy.presenter.OpsPresenter;
import tties.cn.energy.utils.ACache;
import tties.cn.energy.utils.PtrClassicFoot;
import tties.cn.energy.utils.PtrClassicHeader;
import tties.cn.energy.utils.ToastUtil;
import tties.cn.energy.view.activity.QuestionsActivity;
import tties.cn.energy.view.adapter.MyOpsrightAdapter;
import tties.cn.energy.view.iview.IOpsView;

/**
 * Created by li on 2018/3/21
 * description：
 * author：guojlli
 */

public class OpsFragment extends BaseFragment<OpsPresenter> implements IOpsView {


    @BindView(R.id.ops_toolbar_img)
    ImageView opsToolbarImg;
    @BindView(R.id.ops_toolbar_text)
    TextView opsToolbarText;
    @BindView(R.id.data_toolbar)
    Toolbar dataToolbar;
    Unbinder unbinder;
    @BindView(R.id.ops_rcy_right)
    RecyclerView opsRcyRight;
    @BindView(R.id.ops_rcy_left_bt1)
    RadioButton opsRcyLeftBt1;
    @BindView(R.id.ops_rcy_left_bt2)
    RadioButton opsRcyLeftBt2;
    @BindView(R.id.ops_rcy_left_bt3)
    RadioButton opsRcyLeftBt3;
    @BindView(R.id.ops_rcy_left_bt4)
    RadioButton opsRcyLeftBt4;
    @BindView(R.id.ops_rcy_left_bt5)
    RadioButton opsRcyLeftBt5;
    @BindView(R.id.ops_rcy_left_bt6)
    RadioButton opsRcyLeftBt6;
    @BindView(R.id.ops_rcy_left_rg)
    RadioGroup opsRcyLeftRg;
    @BindView(R.id.ops_number)
    TextView opsNumber;
    @BindView(R.id.ops_refreshLayout)
    PtrFrameLayout opsRefreshLayout;
    List<Opsbean.ResultBean.QuestionListBean> list;
    int pagenum=1;
    private MyOpsrightAdapter adapter;
    boolean flag;
    private static final String TAG = "OpsFragment";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View inflate = inflater.inflate(R.layout.fragment_ops, null);
        unbinder = ButterKnife.bind(this, inflate);
        initView();
        initRefresh();
        return inflate;
    }

    private void initView() {
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        opsRcyRight.setLayoutManager(manager);
        list=new ArrayList<>();
        mPresenter.setPageNum(pagenum);
        mPresenter.setPatrolType(0);
        mPresenter.getOpsRightData();
        opsRcyLeftRg.check(R.id.ops_rcy_left_bt1);
        opsRcyLeftRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    //里头patrolType对应左边竖列
//                  0  全部 1, 柜子 2 变压器 4 绝缘 5 房间  6 附属环境
                    //全部
                    case R.id.ops_rcy_left_bt1:
                        adapter.notifyDataSetChanged();
                        mPresenter.setPageNum(pagenum);
                        mPresenter.setPatrolType(0);
                        mPresenter.getOpsRightData();
                        break;
                        //房间
                    case R.id.ops_rcy_left_bt2:
                        adapter.notifyDataSetChanged();
                        mPresenter.setPageNum(pagenum);
                        mPresenter.setPatrolType(5);
                        mPresenter.getOpsRightData();
                        break;
                        //柜子
                    case R.id.ops_rcy_left_bt3:
                        adapter.notifyDataSetChanged();
                        mPresenter.setPageNum(pagenum);
                        mPresenter.setPatrolType(1);
                        mPresenter.getOpsRightData();
                        break;
                        //变压器
                    case R.id.ops_rcy_left_bt4:
                        adapter.notifyDataSetChanged();
                        mPresenter.setPageNum(pagenum);
                        mPresenter.setPatrolType(2);
                        mPresenter.getOpsRightData();
                        break;
                        //绝缘器
                    case R.id.ops_rcy_left_bt5:
                        adapter.notifyDataSetChanged();
                        mPresenter.setPageNum(pagenum);
                        mPresenter.setPatrolType(4);
                        mPresenter.getOpsRightData();
                        break;
                        //环境
                    case R.id.ops_rcy_left_bt6:
                        adapter.notifyDataSetChanged();
                        mPresenter.setPageNum(pagenum);
                        mPresenter.setPatrolType(6);
                        mPresenter.getOpsRightData();
                        break;

                }
            }
        });
    }

    private void initRefresh() {
        //上拉加载 下拉刷新
        PtrClassicFoot foot = new PtrClassicFoot(getActivity());
//        PtrClassicHeader header = new PtrClassicHeader(getActivity());
//        opsRefreshLayout.setHeaderView(header);
        opsRefreshLayout.setFooterView(foot);
//        opsRefreshLayout.addPtrUIHandler(header);
        opsRefreshLayout.addPtrUIHandler(foot);
        opsToolbarImg.setImageResource(R.mipmap.ic_login_logo);
//        opsRefreshLayout.setPtrHandler(new PtrDefaultHandler2() {
//            @Override
//            public void onLoadMoreBegin(PtrFrameLayout frame) {
//                pagenum++;
//                mPresenter.setPageNum(pagenum);
//                mPresenter.getOpsRightData();
//                adapter.notifyDataSetChanged();
//                opsRefreshLayout.refreshComplete();
//            }
//
//            @Override
//            public void onRefreshBegin(PtrFrameLayout frame) {
////                Log.i("-----------", "onLoadMoreBegin: "+"111111");
//                opsRefreshLayout.refreshComplete();
//            }
//        });
        opsRefreshLayout.setPtrHandler(new PtrHandler2() {
            @Override
            public boolean checkCanDoLoadMore(PtrFrameLayout frame, View content, View footer) {
                return false;
            }

            @Override
            public void onLoadMoreBegin(PtrFrameLayout frame) {

            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return false;
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {

            }
        });
    }

    @Override
    protected void createPresenter() {
        mPresenter = new OpsPresenter(this);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void setOpsRightData(Opsbean opsbean) {
//        list=opsbean.getResult().getQuestionList();
        if(opsbean.getResult().getQuestionList().size()>0){
            View view=View.inflate(getActivity(),R.layout.activity_ops_item_right_no,null);
            if(flag){
                list.addAll(opsbean.getResult().getQuestionList());
            }else{
                list=opsbean.getResult().getQuestionList();
            }
            adapter = new MyOpsrightAdapter(list);
            adapter.setOpsbean(opsbean);
            adapter.setHeadView(view);
            opsRcyRight.setAdapter(adapter);
            opsNumber.setText(opsbean.getResult().getCount() + "");
            adapter.setonClickListener(new MyOpsrightAdapter.onClickListener() {
                @Override
                public void onClickItemListener(int postion) {
                    Intent intent = new Intent(getActivity(), QuestionsActivity.class);
                    intent.putExtra("questionId",list.get(postion).getQuestionId()+"");
                    startActivity(intent);
                }
            });
        }

    }


}
