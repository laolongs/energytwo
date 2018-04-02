package tties.cn.energy.model.result;

import com.lzy.imagepicker.bean.ImageItem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import tties.cn.energy.common.Constants;

/**
 * Created by li on 2018/3/24
 * description：
 * author：guojlli
 */

public class Opsbean implements Serializable{


    /**
     * result : {"count":0,"questionList":[{"patrolItemTypeId":8,"questionId":170,"patrolType":3,"maintUser":{"profilePhoto":"\\photo\\头像1.jpg","staffNo":"000","loginPwd":"$shiro1$SHA-256$500000$e3Pw3VvYELfLCge5No91JA==$chpacZcYFz1kvPkImRrUvNE5jbrSl1DCOmNpXzk7PDk=","createTime":"2018-03-14 18:52:06","staffTel":"13701125155","staffName":"fdsv4","staffId":168,"email":"3425324743@qq.com","status":0},"descriptionList":[{"questionId":170,"createTime":"2018-03-16 11:56:09","questionDescriptionId":26,"imageList":[{"createTime":"2018-03-16 11:56:12","imagePath":"questionDescription\\1521172572395.jpg","questionDescriptionImageId":22,"questionDescriptionId":26}],"staffId":168,"content":"牌子未挂，有可能会出重大问题"}],"description":"[电房]","faultHarm":"设备标志缺失，严重存在人员误操作，误伤害风险","patrolRecordId":750,"isTransfomer":true,"title":"变压器室或箱体未挂\u201c#x变压器\u201d字样的标示牌。","equipmentId":140,"equipmentType":5,"questionIndex":"Q5000750","patrolItemId":26,"scheduleList":[{"questionId":170,"questionScheduleId":34,"createTime":"2018-03-16 11:53:57","content":"宸ョ▼甯�tuo姝ｅ湪澶勭悊","staffId":168},{"questionId":170,"questionScheduleId":45,"createTime":"2018-03-16 11:56:30","content":"正在处理中","staffId":168},{"questionId":170,"questionScheduleId":46,"createTime":"2018-03-16 11:57:57","content":"宸ョ▼甯�tuo宸茶В鍐虫闂","staffId":168}],"createTime":"2018-03-16 11:54:00.0","valueType":0,"faultType":2,"equipmentName":"电房","hasquestion":true,"adviceList":[{"mbStaff":{"profilePhoto":"\\photo\\头像1.jpg","staffNo":"000","loginPwd":"$shiro1$SHA-256$500000$e3Pw3VvYELfLCge5No91JA==$chpacZcYFz1kvPkImRrUvNE5jbrSl1DCOmNpXzk7PDk=","createTime":"2018-03-14 18:52:06","staffTel":"13701125155","staffName":"fdsv4","staffId":168,"email":"3425324743@qq.com","status":0},"questionId":170,"createTime":"2018-03-16 11:56:51","questionAdviceId":15,"staffId":168,"content":"及时更替"}],"dealDesc":"补充完善","status":1}]}
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
         * count : 0
         * questionList : [{"patrolItemTypeId":8,"questionId":170,"patrolType":3,"maintUser":{"profilePhoto":"\\photo\\头像1.jpg","staffNo":"000","loginPwd":"$shiro1$SHA-256$500000$e3Pw3VvYELfLCge5No91JA==$chpacZcYFz1kvPkImRrUvNE5jbrSl1DCOmNpXzk7PDk=","createTime":"2018-03-14 18:52:06","staffTel":"13701125155","staffName":"fdsv4","staffId":168,"email":"3425324743@qq.com","status":0},"descriptionList":[{"questionId":170,"createTime":"2018-03-16 11:56:09","questionDescriptionId":26,"imageList":[{"createTime":"2018-03-16 11:56:12","imagePath":"questionDescription\\1521172572395.jpg","questionDescriptionImageId":22,"questionDescriptionId":26}],"staffId":168,"content":"牌子未挂，有可能会出重大问题"}],"description":"[电房]","faultHarm":"设备标志缺失，严重存在人员误操作，误伤害风险","patrolRecordId":750,"isTransfomer":true,"title":"变压器室或箱体未挂\u201c#x变压器\u201d字样的标示牌。","equipmentId":140,"equipmentType":5,"questionIndex":"Q5000750","patrolItemId":26,"scheduleList":[{"questionId":170,"questionScheduleId":34,"createTime":"2018-03-16 11:53:57","content":"宸ョ▼甯�tuo姝ｅ湪澶勭悊","staffId":168},{"questionId":170,"questionScheduleId":45,"createTime":"2018-03-16 11:56:30","content":"正在处理中","staffId":168},{"questionId":170,"questionScheduleId":46,"createTime":"2018-03-16 11:57:57","content":"宸ョ▼甯�tuo宸茶В鍐虫闂","staffId":168}],"createTime":"2018-03-16 11:54:00.0","valueType":0,"faultType":2,"equipmentName":"电房","hasquestion":true,"adviceList":[{"mbStaff":{"profilePhoto":"\\photo\\头像1.jpg","staffNo":"000","loginPwd":"$shiro1$SHA-256$500000$e3Pw3VvYELfLCge5No91JA==$chpacZcYFz1kvPkImRrUvNE5jbrSl1DCOmNpXzk7PDk=","createTime":"2018-03-14 18:52:06","staffTel":"13701125155","staffName":"fdsv4","staffId":168,"email":"3425324743@qq.com","status":0},"questionId":170,"createTime":"2018-03-16 11:56:51","questionAdviceId":15,"staffId":168,"content":"及时更替"}],"dealDesc":"补充完善","status":1}]
         */

        private int count;
        private List<QuestionListBean> questionList;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public List<QuestionListBean> getQuestionList() {
            return questionList;
        }

        public void setQuestionList(List<QuestionListBean> questionList) {
            this.questionList = questionList;
        }

        public static class QuestionListBean {
            /**
             * patrolItemTypeId : 8
             * questionId : 170
             * patrolType : 3
             * maintUser : {"profilePhoto":"\\photo\\头像1.jpg","staffNo":"000","loginPwd":"$shiro1$SHA-256$500000$e3Pw3VvYELfLCge5No91JA==$chpacZcYFz1kvPkImRrUvNE5jbrSl1DCOmNpXzk7PDk=","createTime":"2018-03-14 18:52:06","staffTel":"13701125155","staffName":"fdsv4","staffId":168,"email":"3425324743@qq.com","status":0}
             * descriptionList : [{"questionId":170,"createTime":"2018-03-16 11:56:09","questionDescriptionId":26,"imageList":[{"createTime":"2018-03-16 11:56:12","imagePath":"questionDescription\\1521172572395.jpg","questionDescriptionImageId":22,"questionDescriptionId":26}],"staffId":168,"content":"牌子未挂，有可能会出重大问题"}]
             * description : [电房]
             * faultHarm : 设备标志缺失，严重存在人员误操作，误伤害风险
             * patrolRecordId : 750
             * isTransfomer : true
             * title : 变压器室或箱体未挂“#x变压器”字样的标示牌。
             * equipmentId : 140
             * equipmentType : 5
             * questionIndex : Q5000750
             * patrolItemId : 26
             * scheduleList : [{"questionId":170,"questionScheduleId":34,"createTime":"2018-03-16 11:53:57","content":"宸ョ▼甯�tuo姝ｅ湪澶勭悊","staffId":168},{"questionId":170,"questionScheduleId":45,"createTime":"2018-03-16 11:56:30","content":"正在处理中","staffId":168},{"questionId":170,"questionScheduleId":46,"createTime":"2018-03-16 11:57:57","content":"宸ョ▼甯�tuo宸茶В鍐虫闂","staffId":168}]
             * createTime : 2018-03-16 11:54:00.0
             * valueType : 0
             * faultType : 2
             * equipmentName : 电房
             * hasquestion : true
             * adviceList : [{"mbStaff":{"profilePhoto":"\\photo\\头像1.jpg","staffNo":"000","loginPwd":"$shiro1$SHA-256$500000$e3Pw3VvYELfLCge5No91JA==$chpacZcYFz1kvPkImRrUvNE5jbrSl1DCOmNpXzk7PDk=","createTime":"2018-03-14 18:52:06","staffTel":"13701125155","staffName":"fdsv4","staffId":168,"email":"3425324743@qq.com","status":0},"questionId":170,"createTime":"2018-03-16 11:56:51","questionAdviceId":15,"staffId":168,"content":"及时更替"}]
             * dealDesc : 补充完善
             * status : 1
             */

            private int patrolItemTypeId;
            private int questionId;
            private int patrolType;
            private MaintUserBean maintUser;
            private String description;
            private String faultHarm;
            private int patrolRecordId;
            private boolean isTransfomer;
            private String title;
            private int equipmentId;
            private int equipmentType;
            private String questionIndex;
            private int patrolItemId;
            private String createTime;
            private int valueType;
            private int faultType;
            private String equipmentName;
            private boolean hasquestion;
            private String dealDesc;
            private int status;
            private List<DescriptionListBean> descriptionList;
            private List<ScheduleListBean> scheduleList;
            private List<AdviceListBean> adviceList;

            public int getPatrolItemTypeId() {
                return patrolItemTypeId;
            }

            public void setPatrolItemTypeId(int patrolItemTypeId) {
                this.patrolItemTypeId = patrolItemTypeId;
            }

            public int getQuestionId() {
                return questionId;
            }

            public void setQuestionId(int questionId) {
                this.questionId = questionId;
            }

            public int getPatrolType() {
                return patrolType;
            }

            public void setPatrolType(int patrolType) {
                this.patrolType = patrolType;
            }

            public MaintUserBean getMaintUser() {
                return maintUser;
            }

            public void setMaintUser(MaintUserBean maintUser) {
                this.maintUser = maintUser;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getFaultHarm() {
                return faultHarm;
            }

            public void setFaultHarm(String faultHarm) {
                this.faultHarm = faultHarm;
            }

            public int getPatrolRecordId() {
                return patrolRecordId;
            }

            public void setPatrolRecordId(int patrolRecordId) {
                this.patrolRecordId = patrolRecordId;
            }

            public boolean isIsTransfomer() {
                return isTransfomer;
            }

            public void setIsTransfomer(boolean isTransfomer) {
                this.isTransfomer = isTransfomer;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getEquipmentId() {
                return equipmentId;
            }

            public void setEquipmentId(int equipmentId) {
                this.equipmentId = equipmentId;
            }

            public int getEquipmentType() {
                return equipmentType;
            }

            public void setEquipmentType(int equipmentType) {
                this.equipmentType = equipmentType;
            }

            public String getQuestionIndex() {
                return questionIndex;
            }

            public void setQuestionIndex(String questionIndex) {
                this.questionIndex = questionIndex;
            }

            public int getPatrolItemId() {
                return patrolItemId;
            }

            public void setPatrolItemId(int patrolItemId) {
                this.patrolItemId = patrolItemId;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public int getValueType() {
                return valueType;
            }

            public void setValueType(int valueType) {
                this.valueType = valueType;
            }

            public int getFaultType() {
                return faultType;
            }

            public void setFaultType(int faultType) {
                this.faultType = faultType;
            }

            public String getEquipmentName() {
                return equipmentName;
            }

            public void setEquipmentName(String equipmentName) {
                this.equipmentName = equipmentName;
            }

            public boolean isHasquestion() {
                return hasquestion;
            }

            public void setHasquestion(boolean hasquestion) {
                this.hasquestion = hasquestion;
            }

            public String getDealDesc() {
                return dealDesc;
            }

            public void setDealDesc(String dealDesc) {
                this.dealDesc = dealDesc;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public List<DescriptionListBean> getDescriptionList() {
                return descriptionList;
            }

            public void setDescriptionList(List<DescriptionListBean> descriptionList) {
                this.descriptionList = descriptionList;
            }

            public List<ScheduleListBean> getScheduleList() {
                return scheduleList;
            }

            public void setScheduleList(List<ScheduleListBean> scheduleList) {
                this.scheduleList = scheduleList;
            }

            public List<AdviceListBean> getAdviceList() {
                return adviceList;
            }

            public void setAdviceList(List<AdviceListBean> adviceList) {
                this.adviceList = adviceList;
            }

            public static class MaintUserBean {
                /**
                 * profilePhoto : \photo\头像1.jpg
                 * staffNo : 000
                 * loginPwd : $shiro1$SHA-256$500000$e3Pw3VvYELfLCge5No91JA==$chpacZcYFz1kvPkImRrUvNE5jbrSl1DCOmNpXzk7PDk=
                 * createTime : 2018-03-14 18:52:06
                 * staffTel : 13701125155
                 * staffName : fdsv4
                 * staffId : 168
                 * email : 3425324743@qq.com
                 * status : 0
                 */

                private String profilePhoto;
                private String staffNo;
                private String loginPwd;
                private String createTime;
                private String staffTel;
                private String staffName;
                private int staffId;
                private String email;
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

                public String getEmail() {
                    return email;
                }

                public void setEmail(String email) {
                    this.email = email;
                }

                public int getStatus() {
                    return status;
                }

                public void setStatus(int status) {
                    this.status = status;
                }
            }

            public static class DescriptionListBean {
                /**
                 * questionId : 170
                 * createTime : 2018-03-16 11:56:09
                 * questionDescriptionId : 26
                 * imageList : [{"createTime":"2018-03-16 11:56:12","imagePath":"questionDescription\\1521172572395.jpg","questionDescriptionImageId":22,"questionDescriptionId":26}]
                 * staffId : 168
                 * content : 牌子未挂，有可能会出重大问题
                 */

                private int questionId;
                private String createTime;
                private int questionDescriptionId;
                private int staffId;
                private String content;
                private List<ImageListBean> imageList;
                private List<ImageItem> imageBeanList;
                public int getQuestionId() {
                    return questionId;
                }

                public void setQuestionId(int questionId) {
                    this.questionId = questionId;
                }

                public String getCreateTime() {
                    return createTime;
                }

                public void setCreateTime(String createTime) {
                    this.createTime = createTime;
                }

                public int getQuestionDescriptionId() {
                    return questionDescriptionId;
                }

                public void setQuestionDescriptionId(int questionDescriptionId) {
                    this.questionDescriptionId = questionDescriptionId;
                }

                public int getStaffId() {
                    return staffId;
                }

                public void setStaffId(int staffId) {
                    this.staffId = staffId;
                }

                public String getContent() {
                    return content;
                }

                public void setContent(String content) {
                    this.content = content;
                }

                public List<ImageItem> getImageList() {
                    if (imageBeanList == null && imageList != null) {
                        this.imageBeanList = new ArrayList<>();
                        for (ImageListBean result : imageList) {
                            ImageItem imageItem = new ImageItem();
                            imageItem.path = Constants.OpsBASE_RUL + result.getImagePath();
                            this.imageBeanList.add(imageItem);
                        }
                    }
                    return imageBeanList;
                }

                public void setImageList(List<ImageListBean> imageList) {
                    this.imageList = imageList;
                }

                public static class ImageListBean {
                    /**
                     * createTime : 2018-03-16 11:56:12
                     * imagePath : questionDescription\1521172572395.jpg
                     * questionDescriptionImageId : 22
                     * questionDescriptionId : 26
                     */

                    private String createTime;
                    private String imagePath;
                    private int questionDescriptionImageId;
                    private int questionDescriptionId;

                    public String getCreateTime() {
                        return createTime;
                    }

                    public void setCreateTime(String createTime) {
                        this.createTime = createTime;
                    }

                    public String getImagePath() {
                        return imagePath;
                    }

                    public void setImagePath(String imagePath) {
                        this.imagePath = imagePath;
                    }

                    public int getQuestionDescriptionImageId() {
                        return questionDescriptionImageId;
                    }

                    public void setQuestionDescriptionImageId(int questionDescriptionImageId) {
                        this.questionDescriptionImageId = questionDescriptionImageId;
                    }

                    public int getQuestionDescriptionId() {
                        return questionDescriptionId;
                    }

                    public void setQuestionDescriptionId(int questionDescriptionId) {
                        this.questionDescriptionId = questionDescriptionId;
                    }
                }
            }

            public static class ScheduleListBean {
                /**
                 * questionId : 170
                 * questionScheduleId : 34
                 * createTime : 2018-03-16 11:53:57
                 * content : 宸ョ▼甯�tuo姝ｅ湪澶勭悊
                 * staffId : 168
                 */

                private int questionId;
                private int questionScheduleId;
                private String createTime;
                private String content;
                private int staffId;

                public int getQuestionId() {
                    return questionId;
                }

                public void setQuestionId(int questionId) {
                    this.questionId = questionId;
                }

                public int getQuestionScheduleId() {
                    return questionScheduleId;
                }

                public void setQuestionScheduleId(int questionScheduleId) {
                    this.questionScheduleId = questionScheduleId;
                }

                public String getCreateTime() {
                    return createTime;
                }

                public void setCreateTime(String createTime) {
                    this.createTime = createTime;
                }

                public String getContent() {
                    return content;
                }

                public void setContent(String content) {
                    this.content = content;
                }

                public int getStaffId() {
                    return staffId;
                }

                public void setStaffId(int staffId) {
                    this.staffId = staffId;
                }
            }

            public static class AdviceListBean {
                /**
                 * mbStaff : {"profilePhoto":"\\photo\\头像1.jpg","staffNo":"000","loginPwd":"$shiro1$SHA-256$500000$e3Pw3VvYELfLCge5No91JA==$chpacZcYFz1kvPkImRrUvNE5jbrSl1DCOmNpXzk7PDk=","createTime":"2018-03-14 18:52:06","staffTel":"13701125155","staffName":"fdsv4","staffId":168,"email":"3425324743@qq.com","status":0}
                 * questionId : 170
                 * createTime : 2018-03-16 11:56:51
                 * questionAdviceId : 15
                 * staffId : 168
                 * content : 及时更替
                 */

                private MbStaffBean mbStaff;
                private int questionId;
                private String createTime;
                private int questionAdviceId;
                private int staffId;
                private String content;

                public MbStaffBean getMbStaff() {
                    return mbStaff;
                }

                public void setMbStaff(MbStaffBean mbStaff) {
                    this.mbStaff = mbStaff;
                }

                public int getQuestionId() {
                    return questionId;
                }

                public void setQuestionId(int questionId) {
                    this.questionId = questionId;
                }

                public String getCreateTime() {
                    return createTime;
                }

                public void setCreateTime(String createTime) {
                    this.createTime = createTime;
                }

                public int getQuestionAdviceId() {
                    return questionAdviceId;
                }

                public void setQuestionAdviceId(int questionAdviceId) {
                    this.questionAdviceId = questionAdviceId;
                }

                public int getStaffId() {
                    return staffId;
                }

                public void setStaffId(int staffId) {
                    this.staffId = staffId;
                }

                public String getContent() {
                    return content;
                }

                public void setContent(String content) {
                    this.content = content;
                }

                public static class MbStaffBean {
                    /**
                     * profilePhoto : \photo\头像1.jpg
                     * staffNo : 000
                     * loginPwd : $shiro1$SHA-256$500000$e3Pw3VvYELfLCge5No91JA==$chpacZcYFz1kvPkImRrUvNE5jbrSl1DCOmNpXzk7PDk=
                     * createTime : 2018-03-14 18:52:06
                     * staffTel : 13701125155
                     * staffName : fdsv4
                     * staffId : 168
                     * email : 3425324743@qq.com
                     * status : 0
                     */

                    private String profilePhoto;
                    private String staffNo;
                    private String loginPwd;
                    private String createTime;
                    private String staffTel;
                    private String staffName;
                    private int staffId;
                    private String email;
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

                    public String getEmail() {
                        return email;
                    }

                    public void setEmail(String email) {
                        this.email = email;
                    }

                    public int getStatus() {
                        return status;
                    }

                    public void setStatus(int status) {
                        this.status = status;
                    }
                }
            }
        }
    }
}
