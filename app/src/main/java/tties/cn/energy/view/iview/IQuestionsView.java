package tties.cn.energy.view.iview;



import android.support.v4.app.Fragment;

import java.util.List;

import tties.cn.energy.base.BaseView;
import tties.cn.energy.model.result.Opsbean;

/**
 * Created by li on 2018/3/23
 * description：
 * author：guojlli
 */

public interface IQuestionsView extends BaseView {
    public void setTabData(String[] array);
    public void setQuestionData(Opsbean bean);
//    public void setQuestionDiscussData();
}
