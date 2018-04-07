package tties.cn.energy.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import tties.cn.energy.R;
import tties.cn.energy.base.BaseActivity;
import tties.cn.energy.model.result.Identity_Passbean;
import tties.cn.energy.presenter.Identity_PassPresenter;
import tties.cn.energy.view.iview.IIdentity_PassView;

public class PasswordActivity extends BaseActivity<Identity_PassPresenter> implements IIdentity_PassView {

    @BindView(R.id.toolbar_left)
    ImageView toolbarLeft;
    @BindView(R.id.toolbar_text)
    TextView toolbarText;
    @BindView(R.id.identity_pass_oldpwd)
    EditText identityPassOldpwd;
    @BindView(R.id.identity_pass_newpwd)
    EditText identityPassNewpwd;
    @BindView(R.id.identity_pass_confirm)
    Button identityPassConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        mPresenter.getIdentity_Pass();
        toolbarText.setText("修改密码");
        toolbarLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void createPresenter() {
        mPresenter=new Identity_PassPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_password;
    }

    @Override
    public void setIdentity_PassData(Identity_Passbean bean) {

    }

    @Override
    public String getOldPass() {
        return identityPassOldpwd.getText().toString();
    }

    @Override
    public String getNewPass() {
        return identityPassNewpwd.getText().toString();
    }
}
