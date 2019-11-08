package app.ceva.petapp.data.network.model.ForgetPassword;

public class ForgetPasswordResponse {


    /**
     * responseCode : 1
     * responseText : Reset password has been sent to your mobile no and email.
     * responseData : xYeI4oCy
     */

    private int responseCode;
    private String responseText;
    private String responseData;

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
}
