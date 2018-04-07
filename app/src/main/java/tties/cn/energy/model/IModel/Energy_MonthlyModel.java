package tties.cn.energy.model.IModel;

import tties.cn.energy.api.Api;
import tties.cn.energy.api.RetrofitApiOps;

/**
 * Created by li on 2018/4/6
 * description：
 * author：guojlli
 */

public class Energy_MonthlyModel implements IEnergy_MonthlyModel {
    @Override
    public Api getEnergy_MonthlyData() {
        return RetrofitApiOps.getServer();
    }
}
