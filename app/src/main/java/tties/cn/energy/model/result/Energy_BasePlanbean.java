package tties.cn.energy.model.result;

/**
 * Created by li on 2018/4/5
 * description：
 * author：guojlli
 */

public class Energy_BasePlanbean {

    /**
     * volumeValue : 1000
     * baseDate : 2017-03
     * bestFee : 30000
     * demandValue : 4998
     * demandFee : 40000
     * volumeFee : 30000
     * bestValue : 1000
     * bestType : 1
     */

    private int volumeValue;
    private String baseDate;
    private int bestFee;
    private int demandValue;
    private int demandFee;
    private int volumeFee;
    private int bestValue;
    private int bestType;

    public int getVolumeValue() {
        return volumeValue;
    }

    public void setVolumeValue(int volumeValue) {
        this.volumeValue = volumeValue;
    }

    public String getBaseDate() {
        return baseDate;
    }

    public void setBaseDate(String baseDate) {
        this.baseDate = baseDate;
    }

    public int getBestFee() {
        return bestFee;
    }

    public void setBestFee(int bestFee) {
        this.bestFee = bestFee;
    }

    public int getDemandValue() {
        return demandValue;
    }

    public void setDemandValue(int demandValue) {
        this.demandValue = demandValue;
    }

    public int getDemandFee() {
        return demandFee;
    }

    public void setDemandFee(int demandFee) {
        this.demandFee = demandFee;
    }

    public int getVolumeFee() {
        return volumeFee;
    }

    public void setVolumeFee(int volumeFee) {
        this.volumeFee = volumeFee;
    }

    public int getBestValue() {
        return bestValue;
    }

    public void setBestValue(int bestValue) {
        this.bestValue = bestValue;
    }

    public int getBestType() {
        return bestType;
    }

    public void setBestType(int bestType) {
        this.bestType = bestType;
    }
}
