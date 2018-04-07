package tties.cn.energy.view.iview;

import tties.cn.energy.base.BaseView;
import tties.cn.energy.model.result.Databean;
import tties.cn.energy.model.result.Energy_BasePlanbean;
import tties.cn.energy.model.result.Energy_TransformerDamgebean;
import tties.cn.energy.model.result.Energy_TransformerListbean;
import tties.cn.energy.model.result.Energy_TransformerTemperaturebean;
import tties.cn.energy.model.result.Energy_TransformerVolumebean;

/**
 * Created by li on 2018/4/5
 * description：
 * author：guojlli
 */

public interface IEnergy_TransformerView extends BaseView {
    public void setEnergy_TransformerListbeanData(Energy_TransformerListbean bean);
    public void setEnergy_TransformerTemperaturebeanData(Energy_TransformerTemperaturebean bean);
    public void setEnergy_TransformerDamgebeanData(Energy_TransformerDamgebean bean);
    public void setEnergy_TransformerVolumebeanData(Energy_TransformerVolumebean bean);
}
