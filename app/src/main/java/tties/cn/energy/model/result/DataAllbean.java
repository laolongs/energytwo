package tties.cn.energy.model.result;

import android.util.Log;

import org.xml.sax.helpers.LocatorImpl;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import tties.cn.energy.common.Constants;
import tties.cn.energy.utils.ACache;
import tties.cn.energy.utils.DateUtil;

/**
 * Created by li on 2018/4/10
 * description：
 * author：guojlli
 */

public class DataAllbean {
//    Map<String,Object> map=new HashMap<>();
//        map.put("userName","test");
//        map.put("password","E10ADC3949BA59ABBE56E057F20F883E");
//        map.put("objId","1486535776800");
//        map.put("objType","1");
//        map.put("baseDate","2017-03");
//        map.put("eleAccountId","54");
//        map.put("dateType","1");
        public String userName;
        public String password;
        public long objId;
        public int objType=1;
        public String baseData;
        public int eleAccountId;
        public int energyledgerId;
        //设置默认月份
        int mYear;
        int mMonth;


    public String getUserName() {
        userName = ACache.getInstance().getAsString(Constants.CACHE_LOGIN_USERNAME);
        return userName;
    }


    public String getPassword() {
        password = ACache.getInstance().getAsString(Constants.CACHE_LOGIN_PASSWORDMD5);
        return password;
    }


    public long getObjId() {
        long energyledgerId = ACache.getInstance().getAsObject(Constants.CACHE_OPS_OBJID);
        if(objId==0){
            return energyledgerId;
        }
        return objId;
    }


    public int getObjType() {
        int objTypeDefault=ACache.getInstance().getAsObject(Constants.CACHE_OPS_OBJTYPE);
        Log.i("objTypeDefault", "getObjType: "+objTypeDefault);
        if(objTypeDefault==0){
            return objType;
        }
        return objTypeDefault;
    }


    public String getBaseData() {
       String baseDataDegault=ACache.getInstance().getAsString(Constants.CACHE_OPS_BASEDATE);
        if(baseDataDegault==null){
            return DateUtil.getCurrentYear()+"-"+DateUtil.getCurrentMonth();
        }
        return baseDataDegault;
    }


    public int getEleAccountId() {
        int eleAccountIdDefault= ACache.getInstance().getAsObject(Constants.CACHE_OPS_ELEACCOUNTID);;
        if(eleAccountId==0){
            return eleAccountIdDefault;
        }
        return eleAccountId;
    }
    public long getEnergyledgerId() {
        long eleAccountIdDefault = ACache.getInstance().getAsObject(Constants.CACHE_OPS_ENERGYLEDGERID);
        if (energyledgerId == 0) {
            return eleAccountIdDefault;
        }
        return eleAccountId;

    }

}
