package tties.cn.energy.model.IModel;

import tties.cn.energy.api.Api;
import tties.cn.energy.api.RetrofitApi;

/**
 * Created by li on 2018/4/5
 * description：电力数据
 * author：guojlli
 */

public class DataFragmentModel implements IDataFragmentModel {
    @Override
    public Api getDataFragmentData() {
        return RetrofitApi.getServer();
    }
}
