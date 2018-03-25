package tties.cn.energy.model.httputils.send;


import android.util.Log;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

import okhttp3.Call;
import tties.cn.energy.model.httputils.HttpClientSend;
import tties.cn.energy.model.httputils.OkUiCallback;
import tties.cn.energy.model.httputils.params.LoginParams;

/**
 * 登录
 * author chensi
 */
public class LoginSend extends HttpClientSend {

    private static final String TAG = "LoginSend";

    public void send(LoginParams params) {
        Log.i("-----send----", "send: "+params.toString());
        OkUiCallback callback=new OkUiCallback() {
            @Override
            public void onFailed(Call call, IOException e) {

                Log.i("--------", "onSuccess: "+"请求失败了");
            }

            @Override
            public void onSuccess(String str) throws IOException {
                Log.i("----------", "onFailed: "+str.toString());

            }
        };
        super.send(params, callback);
    }
}
