package app.ceva.petapp.data.network.model.UpdateProfile;

public class UpdateProfileResponse {


    /**
     * responseCode : 1
     * responseText : Successfull
     * responseData : {"id":"5","name":"payal new","email":"payal@gmail.com","address":"y8 ep block","phone":"3456789012","region":"Kolkata"}
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
         * id : 5
         * name : payal new
         * email : payal@gmail.com
         * address : y8 ep block
         * phone : 3456789012
         * region : Kolkata
         */

        private String id;
        private String name;
        private String email;
        private String address;
        private String phone;
        private String region;

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

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }
    }
}
