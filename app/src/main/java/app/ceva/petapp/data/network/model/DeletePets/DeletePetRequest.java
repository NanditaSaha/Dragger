package app.ceva.petapp.data.network.model.DeletePets;

public class DeletePetRequest {


    /**
     * pet_id : 3
     */

    private String pet_id;

    public DeletePetRequest(String pet_id) {
        this.pet_id = pet_id;
    }

    public String getPet_id() {
        return pet_id;
    }

    public void setPet_id(String pet_id) {
        this.pet_id = pet_id;
    }
}
