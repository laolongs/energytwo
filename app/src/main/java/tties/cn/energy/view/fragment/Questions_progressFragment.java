package tties.cn.energy.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import tties.cn.energy.R;
import tties.cn.energy.model.result.Opsbean;
import tties.cn.energy.view.adapter.Questions_progressAdapter;

/**
 * Created by li on 2018/3/23
 * description：维修进度
 * author：guojlli
 */

public class Questions_progressFragment extends Fragment {
    List<Opsbean.ResultBean.QuestionListBean.ScheduleListBean> bean;
    @BindView(R.id.qu_tb_pr_rcy)
    RecyclerView quTbPrRcy;
    Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflate = View.inflate(getActivity(), R.layout.fragment_questions_progress, null);
        unbinder = ButterKnife.bind(this, inflate);
        initData();
        return inflate;
    }

    private void initData() {
        quTbPrRcy.setLayoutManager(new LinearLayoutManager(getActivity()));
        Questions_progressAdapter adapter=new Questions_progressAdapter(getActivity(),getProgressData());
        quTbPrRcy.setAdapter(adapter);
    }

    public void setProgressData(List<Opsbean.ResultBean.QuestionListBean.ScheduleListBean> bean) {
        this.bean = bean;
    }

    public List<Opsbean.ResultBean.QuestionListBean.ScheduleListBean> getProgressData() {
        return bean;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
