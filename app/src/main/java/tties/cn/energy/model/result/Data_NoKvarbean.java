package tties.cn.energy.model.result;

import java.util.List;

/**
 * Created by li on 2018/4/4
 * description：
 * author：guojlli
 */

public class Data_NoKvarbean {
    private List<DataListBean> dataList;

    public List<DataListBean> getDataList() {
        return dataList;
    }

    public void setDataList(List<DataListBean> dataList) {
        this.dataList = dataList;
    }

    public static class DataListBean {
        /**
         * meterId : 1486536312217
         * freezeTime : 2017-02-20 00:00:00
         * a : 0
         * b : null
         * c : null
         * d : 1912
         * dm : 76
         * max : 0
         * maxTime : null
         * min : 0
         * minTime : null
         * upperTime : 0
         * lowerTime : 0
         * limitRate : 0
         * fpy : 0
         * monTime : 0
         * md : null
         * hasOver : false
         */

        private long meterId;
        private String freezeTime;
        private int a;
        private Object b;
        private Object c;
        private int d;
        private int dm;
        private int max;
        private Object maxTime;
        private int min;
        private Object minTime;
        private int upperTime;
        private int lowerTime;
        private int limitRate;
        private int fpy;
        private int monTime;
        private Object md;
        private boolean hasOver;

        public long getMeterId() {
            return meterId;
        }

        public void setMeterId(long meterId) {
            this.meterId = meterId;
        }

        public String getFreezeTime() {
            return freezeTime;
        }

        public void setFreezeTime(String freezeTime) {
            this.freezeTime = freezeTime;
        }

        public int getA() {
            return a;
        }

        public void setA(int a) {
            this.a = a;
        }

        public Object getB() {
            return b;
        }

        public void setB(Object b) {
            this.b = b;
        }

        public Object getC() {
            return c;
        }

        public void setC(Object c) {
            this.c = c;
        }

        public int getD() {
            return d;
        }

        public void setD(int d) {
            this.d = d;
        }

        public int getDm() {
            return dm;
        }

        public void setDm(int dm) {
            this.dm = dm;
        }

        public int getMax() {
            return max;
        }

        public void setMax(int max) {
            this.max = max;
        }

        public Object getMaxTime() {
            return maxTime;
        }

        public void setMaxTime(Object maxTime) {
            this.maxTime = maxTime;
        }

        public int getMin() {
            return min;
        }

        public void setMin(int min) {
            this.min = min;
        }

        public Object getMinTime() {
            return minTime;
        }

        public void setMinTime(Object minTime) {
            this.minTime = minTime;
        }

        public int getUpperTime() {
            return upperTime;
        }

        public void setUpperTime(int upperTime) {
            this.upperTime = upperTime;
        }

        public int getLowerTime() {
            return lowerTime;
        }

        public void setLowerTime(int lowerTime) {
            this.lowerTime = lowerTime;
        }

        public int getLimitRate() {
            return limitRate;
        }

        public void setLimitRate(int limitRate) {
            this.limitRate = limitRate;
        }

        public int getFpy() {
            return fpy;
        }

        public void setFpy(int fpy) {
            this.fpy = fpy;
        }

        public int getMonTime() {
            return monTime;
        }

        public void setMonTime(int monTime) {
            this.monTime = monTime;
        }

        public Object getMd() {
            return md;
        }

        public void setMd(Object md) {
            this.md = md;
        }

        public boolean isHasOver() {
            return hasOver;
        }

        public void setHasOver(boolean hasOver) {
            this.hasOver = hasOver;
        }
    }
}
