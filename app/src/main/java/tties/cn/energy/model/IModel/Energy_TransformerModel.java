package tties.cn.energy.model.IModel;

import tties.cn.energy.api.Api;
import tties.cn.energy.api.RetrofitApi;
import tties.cn.energy.api.RetrofitApiOps;

/**
 * Created by li on 2018/4/5
 * description：
 * author：guojlli
 */

public class Energy_TransformerModel implements IEnergy_TransformerModel {
    @Override
    public Api getEnergy_TransformerListData() {
        return RetrofitApiOps.getServer();
    }

    @Override
    public Api getEnergy_TransformerTemperatureData() {
        return RetrofitApiOps.getServer();
    }

    @Override
    public Api getEnergy_TransformerDamgeData() {
        return RetrofitApiOps.getServer();
    }

    @Override
    public Api getEnergy_TransformerVolumeData() {
        return RetrofitApiOps.getServer();
    }
}
