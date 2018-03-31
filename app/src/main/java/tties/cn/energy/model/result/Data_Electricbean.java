package tties.cn.energy.model.result;

import java.util.List;

/**
 * Created by li on 2018/3/30
 * description：电量数据
 * author：guojlli
 */

public class Data_Electricbean {
    /**
     * energySum : 445080.00
     * min : {"data":64,"dataTime":"2017-03-04 00:00:00"}
     * max : {"data":21024,"dataTime":"2017-03-17 00:00:00"}
     * dataList : [{"data":19164,"dataTime":"2017-03-01 00:00:00"},{"data":19432,"dataTime":"2017-03-02 00:00:00"},{"data":19240,"dataTime":"2017-03-03 00:00:00"},{"data":64,"dataTime":"2017-03-04 00:00:00"},{"data":4220,"dataTime":"2017-03-05 00:00:00"},{"data":20816,"dataTime":"2017-03-06 00:00:00"},{"data":20564,"dataTime":"2017-03-07 00:00:00"},{"data":20028,"dataTime":"2017-03-08 00:00:00"},{"data":18960,"dataTime":"2017-03-09 00:00:00"},{"data":18732,"dataTime":"2017-03-10 00:00:00"},{"data":18364,"dataTime":"2017-03-11 00:00:00"},{"data":17988,"dataTime":"2017-03-12 00:00:00"},{"data":17832,"dataTime":"2017-03-13 00:00:00"},{"data":16332,"dataTime":"2017-03-14 00:00:00"},{"data":4232,"dataTime":"2017-03-15 00:00:00"},{"data":20464,"dataTime":"2017-03-16 00:00:00"},{"data":21024,"dataTime":"2017-03-17 00:00:00"},{"data":20440,"dataTime":"2017-03-18 00:00:00"},{"data":1248,"dataTime":"2017-03-19 00:00:00"},{"data":19096,"dataTime":"2017-03-20 00:00:00"},{"data":18260,"dataTime":"2017-03-21 00:00:00"},{"data":17996,"dataTime":"2017-03-22 00:00:00"},{"data":16112,"dataTime":"2017-03-23 00:00:00"},{"data":17676,"dataTime":"2017-03-24 00:00:00"},{"data":18632,"dataTime":"2017-03-25 00:00:00"},{"data":19216,"dataTime":"2017-03-26 00:00:00"},{"data":18948,"dataTime":"2017-03-27 00:00:00"}]
     * ledgerpf : 0.90
     */

    private String energySum;
    private MinBean min;
    private MaxBean max;
    private String ledgerpf;
    private List<DataListBean> dataList;

    public String getEnergySum() {
        return energySum;
    }

    public void setEnergySum(String energySum) {
        this.energySum = energySum;
    }

    public MinBean getMin() {
        return min;
    }

    public void setMin(MinBean min) {
        this.min = min;
    }

    public MaxBean getMax() {
        return max;
    }

    public void setMax(MaxBean max) {
        this.max = max;
    }

    public String getLedgerpf() {
        return ledgerpf;
    }

    public void setLedgerpf(String ledgerpf) {
        this.ledgerpf = ledgerpf;
    }

    public List<DataListBean> getDataList() {
        return dataList;
    }

    public void setDataList(List<DataListBean> dataList) {
        this.dataList = dataList;
    }

    public static class MinBean {
        /**
         * data : 64
         * dataTime : 2017-03-04 00:00:00
         */

        private int data;
        private String dataTime;

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public String getDataTime() {
            return dataTime;
        }

        public void setDataTime(String dataTime) {
            this.dataTime = dataTime;
        }
    }

    public static class MaxBean {
        /**
         * data : 21024
         * dataTime : 2017-03-17 00:00:00
         */

        private int data;
        private String dataTime;

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public String getDataTime() {
            return dataTime;
        }

        public void setDataTime(String dataTime) {
            this.dataTime = dataTime;
        }
    }

    public static class DataListBean {
        /**
         * data : 19164
         * dataTime : 2017-03-01 00:00:00
         */

        private int data;
        private String dataTime;

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public String getDataTime() {
            return dataTime;
        }

        public void setDataTime(String dataTime) {
            this.dataTime = dataTime;
        }
    }
}
