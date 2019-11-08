package app.ceva.petapp.feature.AddAnimal;

import java.util.List;

import app.ceva.petapp.data.network.model.PetCategory.PetCategoryResponse;
import app.ceva.petapp.share.base.MvpView;

public interface AddAnimalMvpView extends MvpView {
    void successfullyPetCatList(List<PetCategoryResponse.ResponseDataBean> regionResponse);
    void successfullyAddPets(String result);
}
