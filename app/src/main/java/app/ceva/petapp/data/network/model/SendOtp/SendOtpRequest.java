package app.ceva.petapp.data.network.model.SendOtp;

public class SendOtpRequest {


    /**
     * phone : 1235567890
     */

    private String phone;



    public SendOtpRequest(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
