package tties.cn.energy.model.IModel;

import tties.cn.energy.api.Api;
import tties.cn.energy.api.RetrofitApi;

/**
 * Created by li on 2018/4/4
 * description：
 * author：guojlli
 */

public class Data_CurrentModel implements IData_CurrentModel {
    @Override
    public Api getData_CurrentData() {
        return RetrofitApi.getServer();
    }

    @Override
    public Api getData_CurrentPressData() {
        return RetrofitApi.getServer();
    }

    @Override
    public Api getAllElectricity() {
        return RetrofitApi.getServer();
    }
}
