package tties.cn.energy.view.iview;

import tties.cn.energy.base.BaseView;
import tties.cn.energy.model.result.AllElectricitybean;
import tties.cn.energy.model.result.Data_Nobean;
import tties.cn.energy.model.result.Data_Pressbean;

/**
 * Created by li on 2018/3/23
 * description：
 * author：guojlli
 */

public interface IData_NoView extends BaseView {
    public void setData_NoData(Data_Nobean bean);

}
