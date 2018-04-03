package tties.cn.energy.model.IModel;

import tties.cn.energy.api.Api;
import tties.cn.energy.api.RetrofitApi;

/**
 * Created by li on 2018/4/3
 * description：
 * author：guojlli
 */

public class Data_ElectricModel implements IData_ElectricModel {
    @Override
    public Api getData_ElectricData() {
        return RetrofitApi.getServer();
    }
}
