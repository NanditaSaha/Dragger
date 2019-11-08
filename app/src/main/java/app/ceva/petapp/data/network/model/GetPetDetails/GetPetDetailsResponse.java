package app.ceva.petapp.data.network.model.GetPetDetails;

import java.util.List;

public class GetPetDetailsResponse {


    /**
     * responseCode : 1
     * responseText : Successfully Get Animal
     * responseData : {"pet_id":"6","name":"test","category_id":"2","category_name":"Horse","vaccine_list":[{"id":"1","name":"Test Product","product_uniqueId":"5da714d74b131"},{"id":"1","name":"Test Product","product_uniqueId":"5da717a1f234b"}]}
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
         * pet_id : 6
         * name : test
         * category_id : 2
         * category_name : Horse
         * vaccine_list : [{"id":"1","name":"Test Product","product_uniqueId":"5da714d74b131"},{"id":"1","name":"Test Product","product_uniqueId":"5da717a1f234b"}]
         */

        private String pet_id;
        private String name;
        private String category_id;
        private String category_name;
        private List<VaccineListBean> vaccine_list;

        public String getPet_id() {
            return pet_id;
        }

        public void setPet_id(String pet_id) {
            this.pet_id = pet_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
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

        public List<VaccineListBean> getVaccine_list() {
            return vaccine_list;
        }

        public void setVaccine_list(List<VaccineListBean> vaccine_list) {
            this.vaccine_list = vaccine_list;
        }

        public static class VaccineListBean {
            /**
             * id : 1
             * name : Test Product
             * product_uniqueId : 5da714d74b131
             */

            private String id;
            private String name;
            private String product_uniqueId;

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

            public String getProduct_uniqueId() {
                return product_uniqueId;
            }

            public void setProduct_uniqueId(String product_uniqueId) {
                this.product_uniqueId = product_uniqueId;
            }
        }
    }
}
