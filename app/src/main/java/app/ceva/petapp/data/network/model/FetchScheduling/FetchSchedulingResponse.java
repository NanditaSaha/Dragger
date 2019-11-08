package app.ceva.petapp.data.network.model.FetchScheduling;

import java.util.List;

public class FetchSchedulingResponse {


    /**
     * responseCode : 1
     * responseText : Scheduling Found Successfully.
     * responseData : ["2019-10-17","2019-10-19","2019-10-23","2019-10-29","2019-11-06"]
     */

    private int responseCode;
    private String responseText;
    private List<String> responseData;

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseText() {
        return responseText;
    }

    public void setResponseText(String responseText) {
        this.responseText = responseText;
    }

    public List<String> getResponseData() {
        return responseData;
    }

    public void setResponseData(List<String> responseData) {
        this.responseData = responseData;
    }
}
