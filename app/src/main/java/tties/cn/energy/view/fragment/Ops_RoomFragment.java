package tties.cn.energy.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import in.srain.cube.views.ptr.PtrDefaultHandler2;
import in.srain.cube.views.ptr.PtrFrameLayout;
import tties.cn.energy.R;
import tties.cn.energy.base.BaseFragment;
import tties.cn.energy.model.result.Opsbean;
import tties.cn.energy.presenter.OpsPresenter;
import tties.cn.energy.utils.PtrClassicFoot;
import tties.cn.energy.utils.PtrClassicHeader;
import tties.cn.energy.view.activity.QuestionsActivity;
import tties.cn.energy.view.adapter.MyOpsrightAdapter;
import tties.cn.energy.view.iview.IOpsView;

/**
 * Created by li on 2018/4/4
 * description：房间
 * author：guojlli
 */

public class Ops_RoomFragment extends BaseFragment<OpsPresenter> implements IOpsView {
    @BindView(R.id.ops_rcy_right)
    RecyclerView opsRcyRight;
    Unbinder unbinder;
    List<Opsbean.ResultBean.QuestionListBean> list;
    List<String> listview;
    Opsbean opsbean;
    int pagenum = 1;
    @BindView(R.id.ops_right_RL)
    RelativeLayout opsRightRL;
    private MyOpsrightAdapter adapter;
    boolean flag;
    private static final String TAG = "OpsFragment";
    private TextView tv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View inflate = inflater.inflate(R.layout.fragment_ops_right, null);
        unbinder = ButterKnife.bind(this, inflate);
        initView();
        initRefresh();
        return inflate;
    }

    private void initRefresh() {
//        //上拉加载 下拉刷新
//        PtrClassicFoot foot = new PtrClassicFoot(getActivity());
//        PtrClassicHeader header = new PtrClassicHeader(getActivity());
//        opsRefreshLayout.setHeaderView(header);
//        opsRefreshLayout.setFooterView(foot);
//        opsRefreshLayout.addPtrUIHandler(header);
//        opsRefreshLayout.addPtrUIHandler(foot);
//        opsToolbarImg.setImageResource(R.mipmap.ic_login_logo);
//        opsRefreshLayout.setPtrHandler(new PtrDefaultHandler2() {
//            @Override
//            public void onLoadMoreBegin(PtrFrameLayout frame) {
//                flag = true;
//                pagenum++;
//                mPresenter.setPageNum(pagenum);
//                mPresenter.getOpsRightData();
//                adapter.notifyDataSetChanged();
//                opsRefreshLayout.refreshComplete();
//                Log.i("-----------", "onLoadMoreBegin: " + "111111");
//            }
//
//            @Override
//            public void onRefreshBegin(PtrFrameLayout frame) {
////                Log.i("-----------", "onLoadMoreBegin: "+"111111");
//                opsRefreshLayout.refreshComplete();
//            }
//        });
//
    }

    private void initView() {
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        opsRcyRight.setLayoutManager(manager);
    }

    @Override
    protected void createPresenter() {
        mPresenter=new OpsPresenter(this);
    }

    @Override
    public void setOpsRightData(Opsbean opsbean) {
        if (opsbean.getResult().getQuestionList().size() > 0) {
//            opsRefreshLayout.setVisibility(View.VISIBLE);
//            opsRightRL.setVisibility(View.GONE);
            int count=opsbean.getResult().getQuestionList().size();
//            if(count)
            if (flag) {
//                list=opsbean.getResult().getQuestionList();
                list.addAll(list);
            } else {
                list = opsbean.getResult().getQuestionList();
            }
//            adapter.setOpsbean(opsbean);
            adapter.setApapterData(list);
//            adapter.notifyDataSetChanged();
            opsRcyRight.setAdapter(adapter);
            adapter.setonClickListener(new MyOpsrightAdapter.onClickListener() {
                @Override
                public void onClickItemListener(int postion) {
                    Intent intent = new Intent(getActivity(), QuestionsActivity.class);
                    intent.putExtra("questionId", list.get(postion).getQuestionId() + "");
                    startActivity(intent);
                }
            });
        } else {
//            opsRightRL.setVisibility(View.VISIBLE);
//            opsRefreshLayout.setVisibility(View.GONE);
            Log.i(TAG, "setOpsRightData: " + "当前bean里无数据");
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
