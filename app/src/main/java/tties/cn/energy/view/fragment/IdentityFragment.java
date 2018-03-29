package tties.cn.energy.view.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import tties.cn.energy.R;
import tties.cn.energy.base.BaseFragment;
import tties.cn.energy.common.Constants;
import tties.cn.energy.presenter.MainPresenter;
import tties.cn.energy.utils.ACache;
import tties.cn.energy.utils.ToastUtil;
import tties.cn.energy.view.activity.AboutActivity;
import tties.cn.energy.view.activity.LoginActivity;
import tties.cn.energy.view.activity.PasswordActivity;
import tties.cn.energy.view.activity.TablenNumberActivity;
import tties.cn.energy.view.activity.VersionActivity;
import tties.cn.energy.view.dialog.ConfirmDialog;

/**
 * Created by li on 2018/3/21
 * description：
 * author：guojlli
 */

public class IdentityFragment extends BaseFragment<MainPresenter> implements View.OnClickListener {

    Unbinder unbinder;
    @BindView(R.id.identity_toolbar)
    Toolbar identityToolbar;
    @BindView(R.id.identity_name)
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


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View inflate = inflater.inflate(R.layout.fragment_identity, null);
        unbinder = ButterKnife.bind(this, inflate);
        layoutPassword.setOnClickListener(this);
        layoutVersion.setOnClickListener(this);
        identityAbout.setOnClickListener(this);
        layoutLoginout.setOnClickListener(this);
        return inflate;
    }

    @Override
    protected void createPresenter() {

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
}
