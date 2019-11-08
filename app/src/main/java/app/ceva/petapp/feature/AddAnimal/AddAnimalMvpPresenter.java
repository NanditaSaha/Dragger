package app.ceva.petapp.feature.AddAnimal;


import app.ceva.petapp.di.PerActivity;
import app.ceva.petapp.share.base.MvpPresenter;

@PerActivity
public interface AddAnimalMvpPresenter <V extends AddAnimalMvpView>extends MvpPresenter<V> {
  void onPetCategoryList();
  void onAddPetsClick(String userId,String categoryId,String petName);
}
