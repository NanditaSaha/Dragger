package app.ceva.petapp.data.network.model.GetProfile;

public class GetProfileRequest {


    /**
     * user_id : 5
     */

    private String user_id;

    public GetProfileRequest(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
