package tties.cn.energy.view.iview;

import java.util.List;

import tties.cn.energy.base.BaseView;
import tties.cn.energy.model.result.AllElectricitybean;
import tties.cn.energy.model.result.Data_Pressbean;
import tties.cn.energy.model.result.Opsbean;

/**
 * Created by li on 2018/3/23
 * description：
 * author：guojlli
 */

public interface IData_PressView extends BaseView {
    public void setData_PressData(Data_Pressbean bean);
    public void setAllElectricity(AllElectricitybean allElectricitybean);
}
