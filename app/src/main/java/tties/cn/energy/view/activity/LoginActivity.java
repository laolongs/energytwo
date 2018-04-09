package tties.cn.energy.view.activity;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import tties.cn.energy.R;
import tties.cn.energy.base.BaseActivity;
import tties.cn.energy.common.Constants;
import tties.cn.energy.presenter.LoginPresenter;
import tties.cn.energy.utils.ACache;
import tties.cn.energy.view.iview.ILoginView;

public class LoginActivity extends BaseActivity<LoginPresenter> implements ILoginView {
    private static final String TAG = "LoginActivity";
    @BindView(R.id.edittext_username)
    EditText edittextUsername;
    @BindView(R.id.edittext_password)
    EditText edittextPassword;
    @BindView(R.id.btn_intoMain)
    Button btnIntoMain;
    @BindView(R.id.toolbar_left)
    ImageView toolbarLeft;
    @BindView(R.id.toolbar_text)
    TextView toolbarText;
    @BindView(R.id.login_check)
    CheckBox loginCheck;
    @BindView(R.id.login_call)
    TextView loginCall;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    //打电话
    private String[] perms = {Manifest.permission.CALL_PHONE};
    private final int PERMS_REQUEST_CODE = 200;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initView();
    }
    private void initView() {
        toolbarText.setText("登录");
        toolbarLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        sp=getSharedPreferences("login",MODE_PRIVATE);
        editor= sp.edit();
        if(sp.getBoolean("islogin",false)){
            loginCheck.setChecked(true);
            edittextUsername.setText(ACache.getInstance().getAsString(Constants.CACHE_LOGIN_USERNAME));
            Log.i("111111111", "onCreate: " + ACache.getInstance().getAsString(Constants.CACHE_LOGIN_PASSWORD));
            edittextPassword.setText(ACache.getInstance().getAsString(Constants.CACHE_LOGIN_PASSWORD));
        }
        loginCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    editor.putBoolean("islogin",true);
                    editor.commit();
                }else {
                    editor.putBoolean("islogin",false);
                    editor.commit();
                }
            }
        });
        btnIntoMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.showloginData();
            }
        });
        loginCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {//Android 6.0以上版本需要获取权限
                    requestPermissions(perms, PERMS_REQUEST_CODE);//请求权限
                } else {
                    callPhone();
                }
            }
        });
    }

    @Override
    protected void createPresenter() {
        mPresenter = new LoginPresenter(this, this);
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
    @Override
    public void onRequestPermissionsResult(int permsRequestCode, String[] permissions, int[] grantResults) {
        switch (permsRequestCode) {
            case PERMS_REQUEST_CODE:
                boolean storageAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                if (storageAccepted) {
                    callPhone();
                } else {
                    Log.i(TAG, "没有权限操作这个请求");
                }
                break;

        }
    }
    //拨打电话
    private void callPhone() {
        //检查拨打电话权限
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:" +"4006682879"));
            startActivity(intent);
        }
    }
}
