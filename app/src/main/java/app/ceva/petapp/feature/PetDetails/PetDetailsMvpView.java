package app.ceva.petapp.feature.PetDetails;

import java.util.List;

import app.ceva.petapp.data.network.model.EditPet.EditPetResponse;
import app.ceva.petapp.data.network.model.GetPetDetails.GetPetDetailsResponse;
import app.ceva.petapp.data.network.model.PetCategory.PetCategoryResponse;
import app.ceva.petapp.data.network.model.SubmitScheduling.SubmitSchedulingResponse;
import app.ceva.petapp.share.base.MvpView;

public interface PetDetailsMvpView extends MvpView {
    void successfullyEditPetList(EditPetResponse editPetResponse);
    void successfullyPetCatList(List<PetCategoryResponse.ResponseDataBean> regionResponse);
    void successfullyGetPetDetails(GetPetDetailsResponse getPetDetailsResponse);

}
