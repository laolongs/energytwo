package tties.cn.energy.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import tties.cn.energy.R;
import tties.cn.energy.base.BaseFragment;
import tties.cn.energy.common.CircleProgressBar;
import tties.cn.energy.presenter.MainPresenter;
import tties.cn.energy.utils.ToastUtil;
import tties.cn.energy.view.activity.Energy_BaseenergyActivity;
import tties.cn.energy.view.activity.Energy_ElectricalActivity;
import tties.cn.energy.view.activity.Energy_EnergyActivity;
import tties.cn.energy.view.activity.Energy_ForceActivity;
import tties.cn.energy.view.activity.Energy_OpsActivity;
import tties.cn.energy.view.activity.Energy_TransformerActivity;

/**
 * Created by li on 2018/3/21
 * description：
 * author：guojlli
 */

public class EnergyFragment extends BaseFragment<MainPresenter> implements View.OnClickListener {
    @BindView(R.id.tasks_view)
    CircleProgressBar tasksView;
    Unbinder unbinder;
    @BindView(R.id.toolbar_left)
    ImageView toolbarLeft;
    @BindView(R.id.toolbar_text)
    TextView toolbarText;
    @BindView(R.id.energy_usermark)
    TextView energyUsermark;
    @BindView(R.id.energy_bar)
    RatingBar energyBar;
    @BindView(R.id.energy_baseenergy)
    LinearLayout energyBaseenergy;
    @BindView(R.id.energy_electrical)
    LinearLayout energyElectrical;
    @BindView(R.id.energy_force)
    LinearLayout energyForce;
    @BindView(R.id.energy_ops)
    LinearLayout energyOps;
    @BindView(R.id.energy_energy)
    LinearLayout energyEnergy;
    @BindView(R.id.energy_transformer)
    LinearLayout energyTransformer;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View inflate = inflater.inflate(R.layout.fragment_energy, null);
        unbinder = ButterKnife.bind(this, inflate);
        tasksView.setProgress(35);
        energyBaseenergy.setOnClickListener(this);
        energyElectrical.setOnClickListener(this);
        energyForce.setOnClickListener(this);
        energyOps.setOnClickListener(this);
        energyEnergy.setOnClickListener(this);
        energyTransformer.setOnClickListener(this);
        initView();
        return inflate;
    }

    private void initView() {
        toolbarText.setText("电力能效");
        energyBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                ToastUtil.showShort(getActivity(),String.format(""+v,b?1:0) );
            }
        });
    }

    @Override
    protected void createPresenter() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()){
//            基本电量优化
            case R.id.energy_baseenergy:
                intent=new Intent(getActivity(), Energy_BaseenergyActivity.class);
                startActivity(intent);
                break;
//            电度电量优化
            case R.id.energy_electrical:
                intent=new Intent(getActivity(), Energy_ElectricalActivity.class);
                startActivity(intent);
                break;
//            力调电费优化
            case R.id.energy_force:
                intent=new Intent(getActivity(), Energy_ForceActivity.class);
                startActivity(intent);
                break;
                //变压器优化
            case R.id.energy_transformer:
                intent=new Intent(getActivity(), Energy_TransformerActivity.class);
                startActivity(intent);
                break;
                //运维月报
            case R.id.energy_ops:
                intent=new Intent(getActivity(), Energy_OpsActivity.class);
                startActivity(intent);
                break;
                //能效月报
            case R.id.energy_energy:
                intent=new Intent(getActivity(), Energy_EnergyActivity.class);
                startActivity(intent);
                break;



        }
    }
}
