package tties.cn.energy.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import tties.cn.energy.R;
import tties.cn.energy.base.BaseActivity;
import tties.cn.energy.common.Constants;
import tties.cn.energy.model.httputils.params.LoginParams;
import tties.cn.energy.model.httputils.send.LoginSend;
import tties.cn.energy.presenter.LoginPresenter;
import tties.cn.energy.utils.ACache;
import tties.cn.energy.view.iview.ILoginView;

public class LoginActivity extends BaseActivity<LoginPresenter> implements ILoginView {

    @BindView(R.id.edittext_username)
    EditText edittextUsername;
    @BindView(R.id.edittext_password)
    EditText edittextPassword;
    @BindView(R.id.btn_intoMain)
    Button btnIntoMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        edittextUsername.setText(ACache.getInstance().getAsString(Constants.CACHE_LOGIN_USERNAME));
        edittextPassword.setText(ACache.getInstance().getAsString(Constants.CACHE_LOGIN_PASSWORD));
//        edittextUsername.setText("南洋印染");
//        edittextPassword.setText("123456");
        initView();
    }

    private void initView() {
        btnIntoMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.showloginData();
                finish();
            }
        });
    }

    @Override
    protected void createPresenter() {
        mPresenter=new LoginPresenter(this,this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public String getLoginName() {
        return edittextUsername.getText().toString();
    }

    @Override
    public String getLoginPass() {
        return edittextPassword.getText().toString();
    }
}
