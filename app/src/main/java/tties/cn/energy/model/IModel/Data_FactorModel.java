package tties.cn.energy.model.IModel;

import tties.cn.energy.api.Api;
import tties.cn.energy.api.RetrofitApi;

/**
 * Created by li on 2018/4/4
 * description：
 * author：guojlli
 */

public class Data_FactorModel implements IData_FactorModel {
    @Override
    public Api getData_FactorData() {
        return RetrofitApi.getServer();
    }

    @Override
    public Api getAllElectricity() {
        return RetrofitApi.getServer();
    }
}
