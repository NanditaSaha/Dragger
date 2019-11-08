package app.ceva.petapp.data.network.model.AddPet;

public class AddPetsRequest {

    /**
     * user_id : 5
     * category_id : 1
     * name : Test Dog
     */

    private String user_id;
    private String category_id;
    private String name;


    public AddPetsRequest(String user_id, String category_id, String name) {
        this.user_id = user_id;
        this.category_id = category_id;
        this.name = name;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
