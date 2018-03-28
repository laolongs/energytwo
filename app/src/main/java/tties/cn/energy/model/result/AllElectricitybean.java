package tties.cn.energy.model.result;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by li on 2018/3/28
 * description：
 * author：guojlli
 */

public class AllElectricitybean {
    /**
     * ledgerId : 1486535776800
     * ledgerName : 南洋印染
     * meterList : [{"meterId":1486536454743,"meterName":"南洋1#主变室"},{"meterId":1486536481282,"meterName":"南洋2#主变室"},{"meterId":1486536421895,"meterName":"南洋3#主变室"},{"meterId":1486536395204,"meterName":"南洋4#主变室"},{"meterId":1486536312217,"meterName":"南洋5#主变室"},{"meterId":1486536361594,"meterName":"南洋6#主变室"}]
     */

    private long ledgerId;
    private String ledgerName;
    private List<MeterListBean> meterList=new ArrayList<>();

    public long getLedgerId() {
        return ledgerId;
    }

    public void setLedgerId(long ledgerId) {
        this.ledgerId = ledgerId;
    }

    public String getLedgerName() {
        return ledgerName;
    }

    public void setLedgerName(String ledgerName) {
        this.ledgerName = ledgerName;
    }

    public List<MeterListBean> getMeterList() {
        return meterList;
    }

    public void setMeterList(List<MeterListBean> meterList) {
        this.meterList = meterList;
    }

    public static class MeterListBean {
        /**
         * meterId : 1486536454743
         * meterName : 南洋1#主变室
         */

        private long meterId;
        private String meterName;

        public long getMeterId() {
            return meterId;
        }

        public void setMeterId(long meterId) {
            this.meterId = meterId;
        }

        public String getMeterName() {
            return meterName;
        }

        public void setMeterName(String meterName) {
            this.meterName = meterName;
        }
    }
}
