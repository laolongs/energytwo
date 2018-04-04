package tties.cn.energy.view.iview;

import tties.cn.energy.base.BaseView;
import tties.cn.energy.model.result.AllElectricitybean;
import tties.cn.energy.model.result.Data_CurrentPressbean;
import tties.cn.energy.model.result.Data_Currentbean;
import tties.cn.energy.model.result.Data_Factorbean;

/**
 * Created by li on 2018/4/4
 * description：
 * author：guojlli
 */

public interface IData_FactorView extends BaseView {
    public void setData_FactorData(Data_Factorbean bean);
    public void setAllElectricity(AllElectricitybean allElectricitybean);
}
