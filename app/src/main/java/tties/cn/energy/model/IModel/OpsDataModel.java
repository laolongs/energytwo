package tties.cn.energy.model.IModel;

import java.util.ArrayList;
import java.util.List;

import tties.cn.energy.R;
import tties.cn.energy.api.Api;
import tties.cn.energy.api.RetrofitApi;
import tties.cn.energy.api.RetrofitApiOps;

/**
 * Created by li on 2018/3/23
 * description：
 * author：guojlli
 */

public class OpsDataModel implements IOpsModel {
    @Override
    public Api getOpsData() {
        return RetrofitApiOps.getServer();
    }

//    CallBack callBack;
//    List<String> list=new ArrayList<>();
//    public void getOpsitemRight(CallBack callBack){
//        for (int i = 0; i <20; i++) {
//            list.add("落红不是无情物");
//        }
//        callBack.getrightArray(list);
//    }
//
//    @Override
//    public Api getOpsData() {
//        return null;
//    }
//
//    public interface CallBack{
//        void getrightArray(List<String> list);
//
//    }
}
