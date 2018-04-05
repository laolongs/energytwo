package tties.cn.energy.model.result;

import java.util.List;

/**
 * Created by li on 2018/4/5
 * description：
 * author：guojlli
 */

public class DataFragmentbean {
    private List<DataListBean> dataList;

    public List<DataListBean> getDataList() {
        return dataList;
    }

    public void setDataList(List<DataListBean> dataList) {
        this.dataList = dataList;
    }

    public static class DataListBean {
        /**
         * sectorFengFee : 1047468.837
         * sectorGuFee : 441957.435
         * cost : 0.7156
         * baseDate : 2017-03
         * declareType : 1
         * totalEnergy : 3339738
         * sectorJianValue : 0
         * sectorPingValue : 1232734
         * feeSum : 2348641.87
         * fouceSum : 11266.3875
         * maxMDDate : 2017-03-19 10:45:00
         * ap : 2249741
         * totalSum : 2389908.2575
         * volume : 1000
         * sectorFengValue : 0
         * sectorPingFee : 859215.598
         * sectorJianFee : 0
         * rate : 684.372
         * baseSum : 30000
         * maxMD : 4998
         * sectorGuValue : 984315
         * feeValue : 2217049
         * rp : 2249741
         */

        private double sectorFengFee;
        private double sectorGuFee;
        private double cost;
        private String baseDate;
        private int declareType;
        private int totalEnergy;
        private int sectorJianValue;
        private int sectorPingValue;
        private double feeSum;
        private double fouceSum;
        private String maxMDDate;
        private int ap;
        private double totalSum;
        private int volume;
        private int sectorFengValue;
        private double sectorPingFee;
        private int sectorJianFee;
        private double rate;
        private int baseSum;
        private int maxMD;
        private int sectorGuValue;
        private int feeValue;
        private int rp;

        public double getSectorFengFee() {
            return sectorFengFee;
        }

        public void setSectorFengFee(double sectorFengFee) {
            this.sectorFengFee = sectorFengFee;
        }

        public double getSectorGuFee() {
            return sectorGuFee;
        }

        public void setSectorGuFee(double sectorGuFee) {
            this.sectorGuFee = sectorGuFee;
        }

        public double getCost() {
            return cost;
        }

        public void setCost(double cost) {
            this.cost = cost;
        }

        public String getBaseDate() {
            return baseDate;
        }

        public void setBaseDate(String baseDate) {
            this.baseDate = baseDate;
        }

        public int getDeclareType() {
            return declareType;
        }

        public void setDeclareType(int declareType) {
            this.declareType = declareType;
        }

        public int getTotalEnergy() {
            return totalEnergy;
        }

        public void setTotalEnergy(int totalEnergy) {
            this.totalEnergy = totalEnergy;
        }

        public int getSectorJianValue() {
            return sectorJianValue;
        }

        public void setSectorJianValue(int sectorJianValue) {
            this.sectorJianValue = sectorJianValue;
        }

        public int getSectorPingValue() {
            return sectorPingValue;
        }

        public void setSectorPingValue(int sectorPingValue) {
            this.sectorPingValue = sectorPingValue;
        }

        public double getFeeSum() {
            return feeSum;
        }

        public void setFeeSum(double feeSum) {
            this.feeSum = feeSum;
        }

        public double getFouceSum() {
            return fouceSum;
        }

        public void setFouceSum(double fouceSum) {
            this.fouceSum = fouceSum;
        }

        public String getMaxMDDate() {
            return maxMDDate;
        }

        public void setMaxMDDate(String maxMDDate) {
            this.maxMDDate = maxMDDate;
        }

        public int getAp() {
            return ap;
        }

        public void setAp(int ap) {
            this.ap = ap;
        }

        public double getTotalSum() {
            return totalSum;
        }

        public void setTotalSum(double totalSum) {
            this.totalSum = totalSum;
        }

        public int getVolume() {
            return volume;
        }

        public void setVolume(int volume) {
            this.volume = volume;
        }

        public int getSectorFengValue() {
            return sectorFengValue;
        }

        public void setSectorFengValue(int sectorFengValue) {
            this.sectorFengValue = sectorFengValue;
        }

        public double getSectorPingFee() {
            return sectorPingFee;
        }

        public void setSectorPingFee(double sectorPingFee) {
            this.sectorPingFee = sectorPingFee;
        }

        public int getSectorJianFee() {
            return sectorJianFee;
        }

        public void setSectorJianFee(int sectorJianFee) {
            this.sectorJianFee = sectorJianFee;
        }

        public double getRate() {
            return rate;
        }

        public void setRate(double rate) {
            this.rate = rate;
        }

        public int getBaseSum() {
            return baseSum;
        }

        public void setBaseSum(int baseSum) {
            this.baseSum = baseSum;
        }

        public int getMaxMD() {
            return maxMD;
        }

        public void setMaxMD(int maxMD) {
            this.maxMD = maxMD;
        }

        public int getSectorGuValue() {
            return sectorGuValue;
        }

        public void setSectorGuValue(int sectorGuValue) {
            this.sectorGuValue = sectorGuValue;
        }

        public int getFeeValue() {
            return feeValue;
        }

        public void setFeeValue(int feeValue) {
            this.feeValue = feeValue;
        }

        public int getRp() {
            return rp;
        }

        public void setRp(int rp) {
            this.rp = rp;
        }
    }
}
