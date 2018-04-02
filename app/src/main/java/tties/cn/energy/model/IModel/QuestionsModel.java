package tties.cn.energy.model.IModel;

import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import tties.cn.energy.R;
import tties.cn.energy.api.Api;
import tties.cn.energy.api.RetrofitApiOps;
import tties.cn.energy.view.fragment.Questions_discussFragment;
import tties.cn.energy.view.fragment.Questions_progressFragment;

/**
 * Created by li on 2018/3/23
 * description：
 * author：guojlli
 */

public class QuestionsModel implements IQuestionsModel {
    String[] array={"维修进度","问题讨论"};
//    List<Fragment> list=new ArrayList<>();
   public void getQuestaionsTab(CallBack callBack){
//       list.add(new Questions_progressFragment());
//       list.add(new Questions_discussFragment());
        callBack.getTabArray(array);
    }

    @Override
    public Api getQuestionsData() {
        return RetrofitApiOps.getServer();
    }

    public interface CallBack{
        void getTabArray(String[] array);

    }
}
