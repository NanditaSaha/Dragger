package app.ceva.petapp.data.network.model.ChangePassword;

public class CgangePasswordRequest {


    /**
     * user_id : 5
     * old_password : 123456
     * new_password : nopass
     */

    private String user_id;
    private String old_password;
    private String new_password;

    public CgangePasswordRequest(String user_id, String old_password, String new_password) {
        this.user_id = user_id;
        this.old_password = old_password;
        this.new_password = new_password;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getOld_password() {
        return old_password;
    }

    public void setOld_password(String old_password) {
        this.old_password = old_password;
    }

    public String getNew_password() {
        return new_password;
    }

    public void setNew_password(String new_password) {
        this.new_password = new_password;
    }
}
