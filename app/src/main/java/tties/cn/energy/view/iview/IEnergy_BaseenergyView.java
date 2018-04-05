package tties.cn.energy.view.iview;

import tties.cn.energy.base.BaseView;
import tties.cn.energy.model.result.DataFragmentbean;
import tties.cn.energy.model.result.Databean;
import tties.cn.energy.model.result.Energy_BasePlanbean;

/**
 * Created by li on 2018/4/5
 * description：
 * author：guojlli
 */

public interface IEnergy_BaseenergyView extends BaseView {
    public void setEnergy_BaseenergyData(Databean bean);
    public void setEnergy_BaseenergyYearData(Databean bean);
    public void setEnergy_BasePlanData(Energy_BasePlanbean bean);
}
