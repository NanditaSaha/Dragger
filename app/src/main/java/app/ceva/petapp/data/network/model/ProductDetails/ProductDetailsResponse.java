package app.ceva.petapp.data.network.model.ProductDetails;

import java.util.List;

public class ProductDetailsResponse {


    /**
     * responseCode : 1
     * responseText : Successfully.
     * responseData : {"product_id":"1","product_name":"Test Product","product_info":"test","product_indications":"test","product_description":"test","product_pdf":[{"image":"http://192.168.5.51/QRcode/storage/app/pdf/fc.For designers - Development Proposal For QR Code Scanning and Pet Vaccination Scheduling Application_V1.pdf"},{"image":"http://192.168.5.51/QRcode/storage/app/pdf/EG.Proposal for Offers Listing System_V3 (For Android and iPhone Team).pdf"}],"product_image":"http://192.168.5.51/QRcode/storage/app/productImage/1569404131.png","pet_id":"6","date":"2019-10-11"}
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
         * product_id : 1
         * product_name : Test Product
         * product_info : test
         * product_indications : test
         * product_description : test
         * product_pdf : [{"image":"http://192.168.5.51/QRcode/storage/app/pdf/fc.For designers - Development Proposal For QR Code Scanning and Pet Vaccination Scheduling Application_V1.pdf"},{"image":"http://192.168.5.51/QRcode/storage/app/pdf/EG.Proposal for Offers Listing System_V3 (For Android and iPhone Team).pdf"}]
         * product_image : http://192.168.5.51/QRcode/storage/app/productImage/1569404131.png
         * pet_id : 6
         * date : 2019-10-11
         */

        private String product_id;
        private String product_name;
        private String product_info;
        private String product_indications;
        private String product_description;
        private String product_image;
        private String pet_id;
        private String pet_name;
        private String date;
        private List<ProductPdfBean> product_pdf;

        public String getPet_name() {
            return pet_name;
        }

        public void setPet_name(String pet_name) {
            this.pet_name = pet_name;
        }

        public String getProduct_id() {
            return product_id;
        }

        public void setProduct_id(String product_id) {
            this.product_id = product_id;
        }

        public String getProduct_name() {
            return product_name;
        }

        public void setProduct_name(String product_name) {
            this.product_name = product_name;
        }

        public String getProduct_info() {
            return product_info;
        }

        public void setProduct_info(String product_info) {
            this.product_info = product_info;
        }

        public String getProduct_indications() {
            return product_indications;
        }

        public void setProduct_indications(String product_indications) {
            this.product_indications = product_indications;
        }

        public String getProduct_description() {
            return product_description;
        }

        public void setProduct_description(String product_description) {
            this.product_description = product_description;
        }

        public String getProduct_image() {
            return product_image;
        }

        public void setProduct_image(String product_image) {
            this.product_image = product_image;
        }

        public String getPet_id() {
            return pet_id;
        }

        public void setPet_id(String pet_id) {
            this.pet_id = pet_id;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public List<ProductPdfBean> getProduct_pdf() {
            return product_pdf;
        }

        public void setProduct_pdf(List<ProductPdfBean> product_pdf) {
            this.product_pdf = product_pdf;
        }

        public static class ProductPdfBean {
            /**
             * image : http://192.168.5.51/QRcode/storage/app/pdf/fc.For designers - Development Proposal For QR Code Scanning and Pet Vaccination Scheduling Application_V1.pdf
             */

            private String image;

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }
        }
    }
}
