package tties.cn.energy.model.IModel;

import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import tties.cn.energy.R;
import tties.cn.energy.view.fragment.Questions_discussFragment;
import tties.cn.energy.view.fragment.Questions_progressFragment;

/**
 * Created by li on 2018/3/23
 * description：
 * author：guojlli
 */

public class QuestionsModel {
    String[] array={"维修进度","问题讨论"};
    List<Fragment> list=new ArrayList<>();
    CallBack callBack;
   public void getQuestaionsTab(CallBack callBack){
       list.add(new Questions_progressFragment());
       list.add(new Questions_discussFragment());
        callBack.getTabArray(array,list);
    }
    public interface CallBack{
        void getTabArray(String[] array,List<Fragment> list);

    }
}
