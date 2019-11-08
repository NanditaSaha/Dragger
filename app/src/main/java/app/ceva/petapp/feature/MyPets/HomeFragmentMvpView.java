package app.ceva.petapp.feature.MyPets;

import app.ceva.petapp.data.network.model.GetPet.GetPetsResponse;
import app.ceva.petapp.share.baseFragment.BaseFragmentMvpView;

public interface HomeFragmentMvpView  extends BaseFragmentMvpView {

    void successfullyPetList(GetPetsResponse response);
    void successfullyDeletePetList(String yes);

}
