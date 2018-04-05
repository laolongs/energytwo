package tties.cn.energy.model.IModel;

import tties.cn.energy.api.Api;
import tties.cn.energy.api.RetrofitApi;

/**
 * Created by li on 2018/4/5
 * description：
 * author：guojlli
 */

public class EnergyFragmentModel implements IEnergyFragmentModel {
    @Override
    public Api getEnergyFragmentData() {
        return RetrofitApi.getServer();
    }
}
