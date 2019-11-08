package app.ceva.petapp.feature.MyPets;


import app.ceva.petapp.share.baseFragment.BaseFragmentMvpPresenter;

public interface HomeFragmentMvpPresenter<V extends HomeFragmentMvpView>extends BaseFragmentMvpPresenter<V> {


    void onGetAllPets(String userId);
    void onDeletePets(String petId);


}
