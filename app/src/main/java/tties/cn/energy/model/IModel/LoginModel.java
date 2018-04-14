package tties.cn.energy.model.IModel;

import tties.cn.energy.api.Api;
import tties.cn.energy.api.RetrofitApi;
import tties.cn.energy.api.RetrofitApiOps;
import tties.cn.energy.view.iview.ILoginView;

/**
 * Created by li on 2018/3/22
 * description：
 * author：guojlli
 */

public class LoginModel implements ILoginModel {
    @Override
    public Api getloginData() {
        return RetrofitApi.getServer();
    }

    @Override
    public Api getOpsLoginData() {
        return RetrofitApiOps.getServer();
    }

}
