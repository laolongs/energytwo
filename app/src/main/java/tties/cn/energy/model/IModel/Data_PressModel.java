package tties.cn.energy.model.IModel;

import tties.cn.energy.api.Api;
import tties.cn.energy.api.RetrofitApi;
import tties.cn.energy.api.RetrofitApiPub;

/**
 * Created by li on 2018/3/27
 * description：
 * author：guojlli
 */

public class Data_PressModel implements IData_PressModel {
    @Override
    public Api getData_PressData() {
        return RetrofitApi.getServer();
    }


}
