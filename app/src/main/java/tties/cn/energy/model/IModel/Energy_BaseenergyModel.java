package tties.cn.energy.model.IModel;

import tties.cn.energy.api.Api;
import tties.cn.energy.api.RetrofitApi;

/**
 * Created by li on 2018/4/5
 * description：
 * author：guojlli
 */

public class Energy_BaseenergyModel implements IEnergy_BaseenergyModel {
    @Override
    public Api getEnergy_BaseenergyData() {
        return RetrofitApi.getServer();
    }

    @Override
    public Api getEnergy_BasePlanData() {
        return RetrofitApi.getServer();
    }
}
