package app.ceva.petapp.data.network.model.ForgetPassword;

public class ForgetPasswordRequest {


    /**
     * phone : +667278188186
     */

    private String phone;

    public ForgetPasswordRequest(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
