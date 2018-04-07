package tties.cn.energy.model.result;

import java.util.List;

/**
 * Created by li on 2018/4/5
 * description：
 * author：guojlli
 */

public class Energy_TransformerVolumebean {


    /**
     * result : []
     * errorMessage : 成功
     * errorCode : 0
     */

    private String errorMessage;
    private int errorCode;
    private List<?> result;

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

    public List<?> getResult() {
        return result;
    }

    public void setResult(List<?> result) {
        this.result = result;
    }
}
