package app.ceva.petapp.data.network.model.GetPet;

import java.util.List;

public class GetPetsResponse {


    /**
     * responseCode : 1
     * responseText : Successfull
     * responseData : [{"pet_id":"10","category_id":"1","category_name":"Dog","name":"Test Dog"},{"pet_id":"11","category_id":"2","category_name":"Horse","name":"Horse Demo"},{"pet_id":"12","category_id":"1","category_name":"Dog","name":"Bulldog"}]
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
         * pet_id : 10
         * category_id : 1
         * category_name : Dog
         * name : Test Dog
         */

        private String pet_id;
        private String category_id;
        private String category_name;
        private String name;

        public String getPet_id() {
            return pet_id;
        }

        public void setPet_id(String pet_id) {
            this.pet_id = pet_id;
        }

        public String getCategory_id() {
            return category_id;
        }

        public void setCategory_id(String category_id) {
            this.category_id = category_id;
        }

        public String getCategory_name() {
            return category_name;
        }

        public void setCategory_name(String category_name) {
            this.category_name = category_name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
