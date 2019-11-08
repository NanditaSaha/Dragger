package app.ceva.petapp.data.network.model.FetchScheduling;

public class FetchschedulingRequest {


    /**
     * user_id : 7
     * pet_id : 18
     * product_id : 1
     */

    private String user_id;
    private String pet_id;
    private String product_id;
    private String product_uniqueId;

    public String getProduct_uniqueId() {
        return product_uniqueId;
    }

    public void setProduct_uniqueId(String product_uniqueId) {
        this.product_uniqueId = product_uniqueId;
    }

    public FetchschedulingRequest(String user_id, String pet_id, String product_id, String product_uniqueId) {
        this.user_id = user_id;
        this.pet_id = pet_id;
        this.product_id = product_id;
        this.product_uniqueId = product_uniqueId;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getPet_id() {
        return pet_id;
    }

    public void setPet_id(String pet_id) {
        this.pet_id = pet_id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }
}
