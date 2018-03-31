package tties.cn.energy.model.IModel;

import tties.cn.energy.api.Api;
import tties.cn.energy.api.RetrofitApiOps;

/**
 * Created by li on 2018/3/30
 * description：
 * author：guojlli
 */

public class IdentityFragmentModel implements IIdentityFragmentModel {
    @Override
    public Api getOpsLoginData() {
        return RetrofitApiOps.getServer();
    }
}
