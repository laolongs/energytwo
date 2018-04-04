package tties.cn.energy.view.iview;

import tties.cn.energy.base.BaseView;
import tties.cn.energy.model.result.AllElectricitybean;
import tties.cn.energy.model.result.Data_CurrentPressbean;
import tties.cn.energy.model.result.Data_Currentbean;
import tties.cn.energy.model.result.Data_HaveKwbean;
import tties.cn.energy.model.result.Data_NoKvarbean;

/**
 * Created by li on 2018/4/4
 * description：
 * author：guojlli
 */

public interface IData_CurrentView extends BaseView {
    public void setData_CurrentData(Data_Currentbean bean);
    public void setData_CurrentPressData(Data_CurrentPressbean bean);
    public void setAllElectricity(AllElectricitybean allElectricitybean);
}
