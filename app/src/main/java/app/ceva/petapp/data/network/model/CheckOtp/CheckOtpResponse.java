package app.ceva.petapp.data.network.model.CheckOtp;

public class CheckOtpResponse {


    /**
     * responseCode : 1
     * responseText : Verified Successfully.
     * responseData : +668888888888
     * user_id : 0
     */

    private int responseCode;
    private String responseText;
    private String responseData;
    private String user_id;

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

    public String getResponseData() {
        return responseData;
    }

    public void setResponseData(String responseData) {
        this.responseData = responseData;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
