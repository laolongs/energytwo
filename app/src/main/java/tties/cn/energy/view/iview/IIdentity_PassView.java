package tties.cn.energy.view.iview;


import tties.cn.energy.base.BaseView;
import tties.cn.energy.model.result.Identity_Passbean;

/**
 * Created by wpz on 2017/10/13 0013.
 * 类作用：
 */

public interface IIdentity_PassView extends BaseView {
    public void setIdentity_PassData(Identity_Passbean bean);
    public String getOldPass();
    public String getNewPass();
}
