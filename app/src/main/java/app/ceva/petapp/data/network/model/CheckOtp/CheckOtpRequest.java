package app.ceva.petapp.data.network.model.CheckOtp;

public class CheckOtpRequest {


    /**
     * phone : +668888888888
     * otp : 1234
     * user_id : 0
     */

    private String phone;
    private String otp;
    private String user_id;

    public CheckOtpRequest(String phone, String otp, String user_id) {
        this.phone = phone;
        this.otp = otp;
        this.user_id = user_id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
