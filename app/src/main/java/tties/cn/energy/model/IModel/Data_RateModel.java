package tties.cn.energy.model.IModel;

import tties.cn.energy.api.Api;
import tties.cn.energy.api.RetrofitApi;

/**
 * Created by li on 2018/4/4
 * description：功率数据
 * author：guojlli
 */

public class Data_RateModel implements IData_RateModel {
    @Override
    public Api getData_HaveKWData() {
        return RetrofitApi.getServer();
    }

    @Override
    public Api getData_NoKvarData() {
        return RetrofitApi.getServer();
    }

    @Override
    public Api getAllElectricity() {
        return RetrofitApi.getServer();
    }
}
