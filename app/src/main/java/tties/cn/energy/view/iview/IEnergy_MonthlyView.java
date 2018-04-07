package tties.cn.energy.view.iview;

import tties.cn.energy.base.BaseView;
import tties.cn.energy.model.result.Energy_Monthlybean;

/**
 * Created by li on 2018/4/5
 * description：
 * author：guojlli
 */

public interface IEnergy_MonthlyView extends BaseView {
    public void setEnergy_MonthlyData(Energy_Monthlybean bean);
}
