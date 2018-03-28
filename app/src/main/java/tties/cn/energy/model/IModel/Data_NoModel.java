package tties.cn.energy.model.IModel;

import tties.cn.energy.api.Api;
import tties.cn.energy.api.RetrofitApi;
import tties.cn.energy.api.RetrofitApiPub;

/**
 * Created by li on 2018/3/27
 * description：
 * author：guojlli
 */

public class Data_NoModel implements IData_NoModel {
    @Override
    public Api getData_NoData() {
        return RetrofitApi.getServer();
    }

    @Override
    public Api getAllElectricitySendData() {
        return RetrofitApiPub.getServer();
    }

}
