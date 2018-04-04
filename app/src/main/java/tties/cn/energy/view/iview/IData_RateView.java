package tties.cn.energy.view.iview;

import tties.cn.energy.base.BaseView;
import tties.cn.energy.model.result.AllElectricitybean;
import tties.cn.energy.model.result.Data_HaveKwbean;
import tties.cn.energy.model.result.Data_NoKvarbean;

/**
 * Created by li on 2018/4/4
 * description：
 * author：guojlli
 */

public interface IData_RateView extends BaseView {
    public void setData_HaveKWData(Data_HaveKwbean data_haveKwbean);
    public void setData_NoKvarData(Data_NoKvarbean data_noKvarbean);
    public void setAllElectricity(AllElectricitybean allElectricitybean);
}
