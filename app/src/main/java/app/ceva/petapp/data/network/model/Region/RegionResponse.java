package app.ceva.petapp.data.network.model.Region;

import java.util.List;

public class RegionResponse {


    /**
     * responseCode : 1
     * responseText : Successfull
     * responseData : [{"id":"4","name":"Mumbai"},{"id":"3","name":"Delhi"},{"id":"2","name":"Kolkata"},{"id":"1","name":"Kolkata"}]
     */

    private int responseCode;
    private String responseText;
    private List<ResponseDataBean> responseData;

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

    public List<ResponseDataBean> getResponseData() {
        return responseData;
    }

    public void setResponseData(List<ResponseDataBean> responseData) {
        this.responseData = responseData;
    }

    public static class ResponseDataBean {
        /**
         * id : 4
         * name : Mumbai
         */

        private String id;
        private String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
