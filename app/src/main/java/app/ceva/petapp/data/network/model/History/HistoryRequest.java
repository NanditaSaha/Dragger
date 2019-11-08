package app.ceva.petapp.data.network.model.History;

public class HistoryRequest {


    /**
     * user_id : 7
     */

    private String user_id;


    public HistoryRequest(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
