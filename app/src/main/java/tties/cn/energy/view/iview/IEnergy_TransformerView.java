package tties.cn.energy.view.iview;

import tties.cn.energy.base.BaseView;
import tties.cn.energy.model.result.Databean;
import tties.cn.energy.model.result.Energy_BasePlanbean;
import tties.cn.energy.model.result.Energy_TransformerListbean;

/**
 * Created by li on 2018/4/5
 * description：
 * author：guojlli
 */

public interface IEnergy_TransformerView extends BaseView {
    public void setEnergy_TransformerListbeanData(Energy_TransformerListbean bean);
}
