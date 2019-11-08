package app.ceva.petapp.data.network.model.EditPet;

public class EditPetResponse {


    /**
     * responseCode : 1
     * responseText : Successfull
     * responseData : {"pet_id":"2","category_id":"1","category_name":"Dog","name":"new"}
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
         * pet_id : 2
         * category_id : 1
         * category_name : Dog
         * name : new
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
