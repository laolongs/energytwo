package tties.cn.energy.view.iview;

import tties.cn.energy.base.BaseView;
import tties.cn.energy.model.result.AllElectricitybean;
import tties.cn.energy.model.result.DataFragmentbean;
import tties.cn.energy.model.result.Databean;

/**
 * Created by li on 2018/3/23
 * description：
 * author：guojlli
 */

public interface IDataFragmentView extends BaseView {
    public void setDataFragmentData(DataFragmentbean bean);

}
