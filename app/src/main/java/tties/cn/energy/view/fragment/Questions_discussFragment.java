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
import tties.cn.energy.view.adapter.Questions_discussAdapter;

/**
 * Created by li on 2018/3/23
 * description：问题讨论
 * author：guojlli
 */

public class Questions_discussFragment extends Fragment {
    List<Opsbean.ResultBean.QuestionListBean.AdviceListBean> bean;
    @BindView(R.id.qu_tb_ds_rcy)
    RecyclerView quTbDsRcy;
    Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflate = View.inflate(getActivity(), R.layout.fragment_questions_discuss, null);
        unbinder = ButterKnife.bind(this, inflate);
        initData();
        return inflate;
    }

    private void initData() {
        quTbDsRcy.setLayoutManager(new LinearLayoutManager(getActivity()));
        Questions_discussAdapter adapter=new Questions_discussAdapter(getActivity(),getDiscussData());
        quTbDsRcy.setAdapter(adapter);
    }

    public void setDiscussData(List<Opsbean.ResultBean.QuestionListBean.AdviceListBean> bean) {
        this.bean = bean;
    }

    public List<Opsbean.ResultBean.QuestionListBean.AdviceListBean> getDiscussData() {
        return bean;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
