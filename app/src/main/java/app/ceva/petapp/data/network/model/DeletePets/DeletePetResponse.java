package app.ceva.petapp.data.network.model.DeletePets;

public class DeletePetResponse {


    /**
     * responseCode : 1
     * responseText : Pets Deleted Successfully.
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
