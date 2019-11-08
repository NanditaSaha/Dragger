package app.ceva.petapp.data.network.model.History;

import java.util.List;

public class Historyresponse {


    /**
     * responseCode : 1
     * responseText : Successfull.
     * responseData : [{"product_id":"1","product_uniqueId":"5da707a388c14","product_name":"Test Product","pet_id":"3","pet_name":"Alex","category_name":"Horse","schedule_date":"2019-10-16"}]
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
         * product_id : 1
         * product_uniqueId : 5da707a388c14
         * product_name : Test Product
         * pet_id : 3
         * pet_name : Alex
         * category_name : Horse
         * schedule_date : 2019-10-16
         */

        private String product_id;
        private String product_uniqueId;
        private String product_name;
        private String pet_id;
        private String pet_name;
        private String category_name;
        private String schedule_date;

        public String getProduct_id() {
            return product_id;
        }

        public void setProduct_id(String product_id) {
            this.product_id = product_id;
        }

        public String getProduct_uniqueId() {
            return product_uniqueId;
        }

        public void setProduct_uniqueId(String product_uniqueId) {
            this.product_uniqueId = product_uniqueId;
        }

        public String getProduct_name() {
            return product_name;
        }

        public void setProduct_name(String product_name) {
            this.product_name = product_name;
        }

        public String getPet_id() {
            return pet_id;
        }

        public void setPet_id(String pet_id) {
            this.pet_id = pet_id;
        }

        public String getPet_name() {
            return pet_name;
        }

        public void setPet_name(String pet_name) {
            this.pet_name = pet_name;
        }

        public String getCategory_name() {
            return category_name;
        }

        public void setCategory_name(String category_name) {
            this.category_name = category_name;
        }

        public String getSchedule_date() {
            return schedule_date;
        }

        public void setSchedule_date(String schedule_date) {
            this.schedule_date = schedule_date;
        }
    }
}
