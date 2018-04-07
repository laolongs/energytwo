package tties.cn.energy.model.IModel;

import tties.cn.energy.api.Api;
import tties.cn.energy.api.RetrofitApi;

/**
 * Created by li on 2018/4/6
 * description：
 * author：guojlli
 */

public class Identity_PassModel implements IIdentity_PassModel {
    @Override
    public Api getIdentity_PassData() {
        return RetrofitApi.getServer();
    }
}
