package app.ceva.petapp.data.network.model.AddPet;

public class AddPetsResponse {

    /**
     * responseCode : 1
     * responseText : Successfull
     */

    private int responseCode;
    private String responseText;

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
}
