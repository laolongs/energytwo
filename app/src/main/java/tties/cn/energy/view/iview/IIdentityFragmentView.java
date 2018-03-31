package tties.cn.energy.view.iview;

import tties.cn.energy.base.BaseView;
import tties.cn.energy.model.result.OpsLoginbean;

/**
 * Created by li on 2018/3/30
 * description：
 * author：guojlli
 */

public interface IIdentityFragmentView extends BaseView {
    public void getOpsLoginData(OpsLoginbean opsLoginbean);
}
