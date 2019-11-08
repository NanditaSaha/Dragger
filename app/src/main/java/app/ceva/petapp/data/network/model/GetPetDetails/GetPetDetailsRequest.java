package app.ceva.petapp.data.network.model.GetPetDetails;

public class GetPetDetailsRequest {


    /**
     * pet_id : 1
     * user_id : 7
     */

    private String pet_id;
    private String user_id;

    public GetPetDetailsRequest(String pet_id, String user_id) {
        this.pet_id = pet_id;
        this.user_id = user_id;
    }

    public String getPet_id() {
        return pet_id;
    }

    public void setPet_id(String pet_id) {
        this.pet_id = pet_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
