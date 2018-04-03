package tties.cn.energy.api;


import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;
import tties.cn.energy.model.result.AllElectricitybean;
import tties.cn.energy.model.result.Data_Electricbean;
import tties.cn.energy.model.result.Data_Nobean;
import tties.cn.energy.model.result.Data_Pressbean;
import tties.cn.energy.model.result.Discussbean;
import tties.cn.energy.model.result.Loginbean;
import tties.cn.energy.model.result.OpsLoginbean;
import tties.cn.energy.model.result.Opsbean;
import tties.cn.energy.model.result.Versionbean;


public interface Api {


    // login请求方法
    @POST("login.htm")
    @FormUrlEncoded
    Observable<Loginbean> getLogin(@FieldMap Map<String,Object> map);
    //版本号请求方法
    @POST("getUpdateVersion.htm")
    Observable<Versionbean> getVersion();
    //Ops问题接口
    @POST("queryQuertionForEnergy.do")
    @FormUrlEncoded
    Observable<Opsbean> getOps(@FieldMap Map<String,Object> map);
    //回复问题
    @POST("AddAdvice.do")
    @FormUrlEncoded
    Observable<Discussbean> getdiscuss(@FieldMap Map<String,Object> map);
    //电费数据
    @POST("queryUnbalance.htm")
    @FormUrlEncoded
    Observable<Data_Electricbean> getData_Electric(@FieldMap Map<String,Object> map);
    //电压不平衡
    @POST("queryUnbalance.htm")
    @FormUrlEncoded
    Observable<Data_Pressbean> getData_Press(@FieldMap Map<String,Object> map);
    //电流不平衡
    @POST("queryUnbalance.htm")
    @FormUrlEncoded
    Observable<Data_Nobean> getData_No(@FieldMap Map<String,Object> map);
    //总电量
    @POST("getMeterListByLedgerOrMeterId.htm")
    @FormUrlEncoded
    Observable<AllElectricitybean> getAllElectricity(@FieldMap Map<String,Object> map);
    //运维信息登录
    @POST("queryMaintStaff.do")
    Observable<OpsLoginbean> getOpsLogin(@Query("accountId") String id);
//    //获取订单计划详情
//    @POST(Content.PAGE_DETAILS)
//    Observable<ConfirmBean> getDetatlsData(@Query("id") int id);
//    //获取用户地址
//    @POST(Content.USER_ADDRESS)
//    Observable<ReceiptInformationBean> getAdderssData();
//    //登录
//    @POST(Content.LANDING)
//    Observable<LandingBean> getLandData(@Query("username") String username,
//                                        @Query("password") String password);



}
