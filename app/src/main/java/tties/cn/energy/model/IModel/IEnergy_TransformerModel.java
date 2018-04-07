package tties.cn.energy.model.IModel;

import tties.cn.energy.api.Api;

/**
 * Created by li on 2018/4/4
 * description：
 * author：guojlli
 */

public interface IEnergy_TransformerModel {
    public Api getEnergy_TransformerListData();
    public Api getEnergy_TransformerTemperatureData();
    public Api getEnergy_TransformerDamgeData();
    public Api getEnergy_TransformerVolumeData();

}