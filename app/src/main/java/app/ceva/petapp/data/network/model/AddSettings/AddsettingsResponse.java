package app.ceva.petapp.data.network.model.AddSettings;

public class AddsettingsResponse {


    /**
     * responseCode : 1
     * responseText : Successfull.
     * responseData : {"notify_before":"1"}
     */

    private int responseCode;
    private String responseText;
    private ResponseDataBean responseData;

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

    public ResponseDataBean getResponseData() {
        return responseData;
    }

    public void setResponseData(ResponseDataBean responseData) {
        this.responseData = responseData;
    }

    public static class ResponseDataBean {
        /**
         * notify_before : 1
         */

        private String notify_before;

        public String getNotify_before() {
            return notify_before;
        }

        public void setNotify_before(String notify_before) {
            this.notify_before = notify_before;
        }
    }
}
