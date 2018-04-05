package tties.cn.energy.view.iview;

import tties.cn.energy.base.BaseView;
import tties.cn.energy.model.result.DataFragmentbean;
import tties.cn.energy.model.result.Databean;

/**
 * Created by li on 2018/4/5
 * description：
 * author：guojlli
 */

public interface IEnergy_ForceView extends BaseView{
    public void setEnergy_ForceData(Databean bean);
    public void setEnergy_ForceChartData(Databean bean);
    public void setEnergy_ForceCharge(DataFragmentbean bean);
}
