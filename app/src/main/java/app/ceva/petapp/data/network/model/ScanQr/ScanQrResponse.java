package app.ceva.petapp.data.network.model.ScanQr;

public class ScanQrResponse {


    /**
     * responseCode : 1
     * responseText : Already Sold.
     * product_id : 1
     * product_uniqueId : 5da707a39117c
     */

    private int responseCode;
    private String responseText;
    private String product_id;
    private String product_uniqueId;

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
