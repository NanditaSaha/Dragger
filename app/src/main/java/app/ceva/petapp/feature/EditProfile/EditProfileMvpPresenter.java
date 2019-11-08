package app.ceva.petapp.feature.EditProfile;

import app.ceva.petapp.share.base.MvpPresenter;

public interface EditProfileMvpPresenter <V extends EditProfileMvpView>extends MvpPresenter<V> {
    void onGetProfile();
    void onGetRegionList();
    void onUpdateProfile(String name,String email,String phone,String address,String region);
}
