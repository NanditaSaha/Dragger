package app.ceva.petapp.feature.PetDetails;

import app.ceva.petapp.di.PerActivity;
import app.ceva.petapp.share.base.MvpPresenter;

@PerActivity
public interface PetDetailsMvpPresenter<V extends PetDetailsMvpView> extends MvpPresenter<V> {
    void onPetCategoryList();
    void onEditPets(String petId,String categoryId,String petName);
    void onGetPetDetails(String petId);

}
