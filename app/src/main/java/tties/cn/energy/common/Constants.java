package tties.cn.energy.common;

/**
 *
 */
public class Constants {
//      public static final String OpsBASE_RUL = "http://192.168.2.220:8683/api/";//测试环境运维
//      public static final String BASE_RUL = "http://192.168.2.220:8089/Energy/phoneInterface/";//测试环境能效
      public static final String OpsBASE_RUL = "http://192.168.2.127:8080/api/";//自己本地Ip
      public static final String BASE_RUL = "http://192.168.2.127:8083/phoneInterface/";//自己本地Ip
      public static final String PUBBASE_RUL = "https://app.tties.cn/phoneInterface/";//自己本地Ip
//      public static final String BASE_RUL = "http://192.168.2.127:8080/phoneInterface/";//自己本地Ip
//    public static final String BASE_RUL = "http://192.168.2.116:8080/phoneInterface/";
//    public static final String BASE_RUL =  "https://app.tties.cn/phoneInterface/";
//    public static final String BASE_RUL = "http://192.168.2.111:8080/phoneInterface/";

    public static final String CACHE_LOGIN_STATUS = "CACHE_LOGIN_STATUS";
    public static final String CACHE_OPSLOGIN_USERINFO = "CACHE_OPSLOGIN_USERINFO";
    public static final String CACHE_LOGIN_USERNAME = "CACHE_LOGIN_USERNAME";
    public static final String CACHE_LOGIN_PASSWORD = "CACHE_LOGIN_PASSWORD";
    public static final String CACHE_LOGIN_PASSWORDMD5 = "CACHE_LOGIN_PASSWORDMD5";
    public static final String CACHE_USERINFO = "CACHE_USERINFO";
    public static final String CACHE_MONITOR_DATATYPE = "CACHE_MONITOR_DATATYPE";
    //数据功能块
    //运维表号  energyLedgerId
    public static final String CACHE_OPS_ENERGYLEDGERID = "CACHE_OPS_ENERGYLEDGERID";
    //objid
    public static final String CACHE_OPS_OBJID = "CACHE_OPS_OBJID";
    //运维表号  energyLedgerId 对应的能效 eleaccountid
    public static final String CACHE_OPS_ELEACCOUNTID = "CACHE_OPS_ELEACCOUNTID";
    // 对应的能效 时间 baseDate
    public static final String CACHE_OPS_BASEDATE = "CACHE_OPS_BASEDATE";
    //   objType 对象类型（1、分户；2、计量点）
    public static final String CACHE_OPS_OBJTYPE = "CACHE_OPS_OBJTYPE";
    //
    public static final String CACHEE_VERSION = "CACHEE_VERSION";
    public static final String CACHEE_VERSION_ALERT = "CACHEE_VERSION_ALERT";
    //更新提示间隔
    public static final int UPDATE_INTERVALS = 43200;

    public static final String DOWNLOAD_ID = "DOWNLOAD_ID";
}
