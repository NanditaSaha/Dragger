package app.ceva.petapp.data.network.model.GetPet;

public class GetPetsRequest {


    /**
     * user_id : 12
     */

    private String user_id;

    public GetPetsRequest(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
