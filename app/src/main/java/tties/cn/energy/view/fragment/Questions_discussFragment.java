package tties.cn.energy.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tties.cn.energy.R;
import tties.cn.energy.base.BaseFragment;
import tties.cn.energy.presenter.QuestionsPresenter;

/**
 * Created by li on 2018/3/23
 * description：
 * author：guojlli
 */

public class Questions_discussFragment  extends BaseFragment<QuestionsPresenter> {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflate = View.inflate(getActivity(), R.layout.fragment_questions_discuss, null);
        return inflate;
    }

    @Override
    protected void createPresenter() {

    }
}
