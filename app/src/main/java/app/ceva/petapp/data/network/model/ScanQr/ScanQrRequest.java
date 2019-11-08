package app.ceva.petapp.data.network.model.ScanQr;

public class ScanQrRequest {


    /**
     * user_id : 5
     * region_id : 2
     * product_id : 1
     * product_uniqueId : 5da707a39117c
     */

    private String user_id;
    private String region_id;
    private String product_id;
    private String product_uniqueId;

    public ScanQrRequest(String user_id, String region_id, String product_id, String product_uniqueId) {
        this.user_id = user_id;
        this.region_id = region_id;
        this.product_id = product_id;
        this.product_uniqueId = product_uniqueId;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getRegion_id() {
        return region_id;
    }

    public void setRegion_id(String region_id) {
        this.region_id = region_id;
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
