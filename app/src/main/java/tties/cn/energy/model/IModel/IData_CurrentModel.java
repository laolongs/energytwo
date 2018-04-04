package tties.cn.energy.model.IModel;

import tties.cn.energy.api.Api;

/**
 * Created by li on 2018/4/4
 * description：
 * author：guojlli
 */

public interface IData_CurrentModel {
    public Api getData_CurrentData();
    public Api getData_CurrentPressData();
    public Api getAllElectricity();
}
