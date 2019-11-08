package app.ceva.petapp.data.network.model.UpdateProfile;

public class UpdateProfileRequest {


    /**
     * user_id : 5
     * name : payal new
     * email : payal@gmail.com
     * phone : 3456789012
     * address : y8 ep block
     * region : 1
     */

    private String user_id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String region;

    public UpdateProfileRequest(String user_id, String name, String email, String phone, String address, String region) {
        this.user_id = user_id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.region = region;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
