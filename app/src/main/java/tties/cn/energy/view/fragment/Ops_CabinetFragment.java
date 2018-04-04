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
import tties.cn.energy.R;
import tties.cn.energy.base.BaseFragment;
import tties.cn.energy.model.result.Opsbean;
import tties.cn.energy.presenter.OpsPresenter;
import tties.cn.energy.view.activity.QuestionsActivity;
import tties.cn.energy.view.adapter.MyOpsrightAdapter;
import tties.cn.energy.view.iview.IOpsView;

/**
 * Created by li on 2018/4/4
 * description：柜子
 * author：guojlli
 */

public class Ops_CabinetFragment extends BaseFragment<OpsPresenter> implements IOpsView {
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
        return inflate;
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
