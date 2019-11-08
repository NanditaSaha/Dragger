package app.ceva.petapp.data.network.model.SubmitScheduling;

public class SubmitSchedulingRequest {


    /**
     * user_id : 27
     * pet_id : 3
     * product_id : 1
     * product_uniqueId : 5da707a39117c
     * date : 2019-10-11
     */

    private String user_id;
    private String pet_id;
    private String product_id;
    private String product_uniqueId;
    private String date;

    public SubmitSchedulingRequest(String user_id, String pet_id, String product_id, String product_uniqueId, String date) {
        this.user_id = user_id;
        this.pet_id = pet_id;
        this.product_id = product_id;
        this.product_uniqueId = product_uniqueId;
        this.date = date;
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

    public String getProduct_uniqueId() {
        return product_uniqueId;
    }

    public void setProduct_uniqueId(String product_uniqueId) {
        this.product_uniqueId = product_uniqueId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
