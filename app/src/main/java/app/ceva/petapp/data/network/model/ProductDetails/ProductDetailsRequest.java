package app.ceva.petapp.data.network.model.ProductDetails;

public class ProductDetailsRequest {


    /**
     * user_id : 5
     * product_id : 1
     */

    private String user_id;
    private String product_id;
    private String product_uniqueId;


    public ProductDetailsRequest(String user_id, String product_id, String product_uniqueId) {
        this.user_id = user_id;
        this.product_id = product_id;
        this.product_uniqueId = product_uniqueId;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

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
}
