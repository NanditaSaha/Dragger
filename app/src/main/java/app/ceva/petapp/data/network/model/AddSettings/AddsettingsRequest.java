package app.ceva.petapp.data.network.model.AddSettings;

public class AddsettingsRequest {

    /**
     * user_id : 7
     * notify_before : 1
     */

    private String user_id;
    private String notify_before;

    public AddsettingsRequest(String user_id, String notify_before) {
        this.user_id = user_id;
        this.notify_before = notify_before;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getNotify_before() {
        return notify_before;
    }

    public void setNotify_before(String notify_before) {
        this.notify_before = notify_before;
    }
}
