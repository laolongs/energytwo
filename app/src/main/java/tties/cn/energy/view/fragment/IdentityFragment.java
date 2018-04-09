package tties.cn.energy.view.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.util.LogTime;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import tties.cn.energy.R;
import tties.cn.energy.base.BaseFragment;
import tties.cn.energy.common.Constants;
import tties.cn.energy.model.result.Loginbean;
import tties.cn.energy.model.result.OpsLoginbean;
import tties.cn.energy.presenter.IdentityFragmentPresenter;
import tties.cn.energy.utils.ACache;
import tties.cn.energy.utils.ToastUtil;
import tties.cn.energy.view.activity.AboutActivity;
import tties.cn.energy.view.activity.ChangeTableActivity;
import tties.cn.energy.view.activity.LoginActivity;
import tties.cn.energy.view.activity.PasswordActivity;
import tties.cn.energy.view.activity.VersionActivity;
import tties.cn.energy.view.dialog.ConfirmDialog;
import tties.cn.energy.view.dialog.MyElectricityPopupWindow;
import tties.cn.energy.view.iview.IIdentityFragmentView;

/**
 * Created by li on 2018/3/21
 * description：
 * author：guojlli
 */

public class IdentityFragment extends BaseFragment<IdentityFragmentPresenter> implements View.OnClickListener, IIdentityFragmentView {
    private static final String TAG = "IdentityFragment";
    Unbinder unbinder;
    @BindView(R.id.identity_toolbar)
    Toolbar identityToolbar;
//    @BindView(R.id.identity_name)
    TextView identityName;
    @BindView(R.id.identity_company)
    TextView identityCompany;
    @BindView(R.id.identity_number)
    TextView identityNumber;
    @BindView(R.id.layout_password)
    LinearLayout layoutPassword;
    @BindView(R.id.layout_version)
    LinearLayout layoutVersion;
    @BindView(R.id.identity_about)
    LinearLayout identityAbout;
    @BindView(R.id.layout_loginout)
    LinearLayout layoutLoginout;
    @BindView(R.id.identity_switch_electricity)
    TextView identitySwitchElectricity;
    @BindView(R.id.identity_img)
    ImageView identityImg;
    OpsLoginbean bean;
    int num=0;
    long electricitynum=0;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View inflate = inflater.inflate(R.layout.fragment_identity, null);
        identityName=inflate.findViewById(R.id.identity_name);
        unbinder = ButterKnife.bind(this, inflate);
        Loginbean loginbean = ACache.getInstance().getAsObject(Constants.CACHE_USERINFO);
        mPresenter.getOpsloginData();//1502183891109
        layoutPassword.setOnClickListener(this);
        layoutVersion.setOnClickListener(this);
        identityAbout.setOnClickListener(this);
        layoutLoginout.setOnClickListener(this);
        return inflate;
    }

    @Override
    protected void createPresenter() {
        mPresenter = new IdentityFragmentPresenter(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            //切换表号
//            case R.id.layout_tablenumber:
//                intent = new Intent(getActivity(), TablenNumberActivity.class);
//                startActivity(intent);
//                break;
            //修改密码
            case R.id.layout_password:
                intent = new Intent(getActivity(), PasswordActivity.class);
                startActivity(intent);
                break;
            //版本更新
            case R.id.layout_version:
                intent = new Intent(getActivity(), VersionActivity.class);
                startActivity(intent);
                break;
            //关于我们
            case R.id.identity_about:
                intent = new Intent(getActivity(), AboutActivity.class);
                startActivity(intent);
                break;
            //设置
            case R.id.layout_loginout:
                ConfirmDialog dialog = new ConfirmDialog(getActivity());
                dialog.loadDialog("退出登录", "", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ToastUtil.showLong(getActivity(), "已退出登录");

                        ACache.getInstance().put(Constants.CACHE_LOGIN_STATUS, false);
                        final Intent intent = new Intent(getActivity(), LoginActivity.class);
                        startActivity(intent);
                        getActivity().finish();
                    }
                });
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void getOpsLoginData(final OpsLoginbean opsLoginbean) {
            this.bean=opsLoginbean;
            identityName.setText(opsLoginbean.getResult().getMaintUser().getStaffName());
            num=opsLoginbean.getResult().getEnergyLedgerList().size();
            electricitynum=opsLoginbean.getResult().getEnergyLedgerList().get(0).getEnergyLedgerId();
            if(num>0&&num==1){
                identitySwitchElectricity.setText("仅有1个电表");
                identityNumber.setText(electricitynum+"");
            }else{
                identityNumber.setText(electricitynum+"");
                identitySwitchElectricity.setText("共有"+num+"个电表 切换电表");
                identitySwitchElectricity.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent1 = new Intent(getActivity(), ChangeTableActivity.class);
                        intent1.putExtra("bean",opsLoginbean);
                        startActivity(intent1);
                    }
                });
            }
    }
}
