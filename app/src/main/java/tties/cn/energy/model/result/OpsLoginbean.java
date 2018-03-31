package tties.cn.energy.model.result;

import java.io.Serializable;
import java.util.List;

/**
 * Created by li on 2018/3/30
 * description：
 * author：guojlli
 */

public class OpsLoginbean implements Serializable {
    /**
     * result : {"maintUser":{"profilePhoto":"\\photo\\头像1.jpg","staffNo":"energy_test","loginPwd":"$shiro1$SHA-256$500000$K/90Nsao9yJGEY1HuiqAYg==$wWviCGx1vVVv6U3S6MUEREa6oyAnTl2UPaHaYcyWKAc=","createTime":"2018-03-26 15:48:35","staffTel":"0","staffName":"测试","staffId":266,"status":0},"energyLedgerList":[{"companyId":23,"energyLedgerId":1486535776800,"eleAccountId":54,"eleNo":"12322324"},{"companyId":23,"energyLedgerId":1520665743556,"eleAccountId":55,"eleNo":"13211s"}]}
     * errorMessage : 成功
     * errorCode : 0
     */

    private ResultBean result;
    private String errorMessage;
    private int errorCode;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public static class ResultBean {
        /**
         * maintUser : {"profilePhoto":"\\photo\\头像1.jpg","staffNo":"energy_test","loginPwd":"$shiro1$SHA-256$500000$K/90Nsao9yJGEY1HuiqAYg==$wWviCGx1vVVv6U3S6MUEREa6oyAnTl2UPaHaYcyWKAc=","createTime":"2018-03-26 15:48:35","staffTel":"0","staffName":"测试","staffId":266,"status":0}
         * energyLedgerList : [{"companyId":23,"energyLedgerId":1486535776800,"eleAccountId":54,"eleNo":"12322324"},{"companyId":23,"energyLedgerId":1520665743556,"eleAccountId":55,"eleNo":"13211s"}]
         */

        private MaintUserBean maintUser;
        private List<EnergyLedgerListBean> energyLedgerList;

        public MaintUserBean getMaintUser() {
            return maintUser;
        }

        public void setMaintUser(MaintUserBean maintUser) {
            this.maintUser = maintUser;
        }

        public List<EnergyLedgerListBean> getEnergyLedgerList() {
            return energyLedgerList;
        }

        public void setEnergyLedgerList(List<EnergyLedgerListBean> energyLedgerList) {
            this.energyLedgerList = energyLedgerList;
        }

        public static class MaintUserBean {
            /**
             * profilePhoto : \photo\头像1.jpg
             * staffNo : energy_test
             * loginPwd : $shiro1$SHA-256$500000$K/90Nsao9yJGEY1HuiqAYg==$wWviCGx1vVVv6U3S6MUEREa6oyAnTl2UPaHaYcyWKAc=
             * createTime : 2018-03-26 15:48:35
             * staffTel : 0
             * staffName : 测试
             * staffId : 266
             * status : 0
             */

            private String profilePhoto;
            private String staffNo;
            private String loginPwd;
            private String createTime;
            private String staffTel;
            private String staffName;
            private int staffId;
            private int status;

            public String getProfilePhoto() {
                return profilePhoto;
            }

            public void setProfilePhoto(String profilePhoto) {
                this.profilePhoto = profilePhoto;
            }

            public String getStaffNo() {
                return staffNo;
            }

            public void setStaffNo(String staffNo) {
                this.staffNo = staffNo;
            }

            public String getLoginPwd() {
                return loginPwd;
            }

            public void setLoginPwd(String loginPwd) {
                this.loginPwd = loginPwd;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getStaffTel() {
                return staffTel;
            }

            public void setStaffTel(String staffTel) {
                this.staffTel = staffTel;
            }

            public String getStaffName() {
                return staffName;
            }

            public void setStaffName(String staffName) {
                this.staffName = staffName;
            }

            public int getStaffId() {
                return staffId;
            }

            public void setStaffId(int staffId) {
                this.staffId = staffId;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }
        }

        public static class EnergyLedgerListBean {
            /**
             * companyId : 23
             * energyLedgerId : 1486535776800
             * eleAccountId : 54
             * eleNo : 12322324
             */

            private int companyId;
            private long energyLedgerId;
            private int eleAccountId;
            private String eleNo;

            public int getCompanyId() {
                return companyId;
            }

            public void setCompanyId(int companyId) {
                this.companyId = companyId;
            }

            public long getEnergyLedgerId() {
                return energyLedgerId;
            }

            public void setEnergyLedgerId(long energyLedgerId) {
                this.energyLedgerId = energyLedgerId;
            }

            public int getEleAccountId() {
                return eleAccountId;
            }

            public void setEleAccountId(int eleAccountId) {
                this.eleAccountId = eleAccountId;
            }

            public String getEleNo() {
                return eleNo;
            }

            public void setEleNo(String eleNo) {
                this.eleNo = eleNo;
            }
        }
    }
}
