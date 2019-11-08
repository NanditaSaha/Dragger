package app.ceva.petapp.data.network.model.EditPet;

public class EditPetRequest {

    /**
     * pet_id : 2
     * category_id : 1
     * name : new
     */

    private String pet_id;
    private String category_id;
    private String name;


    public EditPetRequest(String pet_id, String category_id, String name) {
        this.pet_id = pet_id;
        this.category_id = category_id;
        this.name = name;
    }

    public String getPet_id() {
        return pet_id;
    }

    public void setPet_id(String pet_id) {
        this.pet_id = pet_id;
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
