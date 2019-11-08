package app.ceva.petapp.data.network.model.FetchSettings;

public class FetchSettingsRequest {

    /**
     * user_id : 7
     */

    private String user_id;

    public FetchSettingsRequest(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
