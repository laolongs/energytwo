package tties.cn.energy.view.iview;

import tties.cn.energy.base.BaseView;
import tties.cn.energy.model.result.AllElectricitybean;
import tties.cn.energy.model.result.Data_Electricbean;
import tties.cn.energy.model.result.Databean;

/**
 * Created by li on 2018/3/23
 * description：
 * author：guojlli
 */

public interface IDataView extends BaseView {
    public void setDataData(Databean bean);
    public void setDataChartData(Databean bean);
    public void setAllElectricity(AllElectricitybean allElectricitybean);
}
