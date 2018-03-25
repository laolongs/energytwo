package tties.cn.energy.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.ButterKnife;
import tties.cn.energy.R;
import tties.cn.energy.base.BaseFragment;
import tties.cn.energy.presenter.MainPresenter;

/**
 * Created by li on 2018/3/21
 * description：
 * author：guojlli
 */

public class EnergyFragment extends BaseFragment<MainPresenter> {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View inflate = inflater.inflate(R.layout.fragment_energy, null);
        return inflate;
    }

    @Override
    protected void createPresenter() {

    }

}
