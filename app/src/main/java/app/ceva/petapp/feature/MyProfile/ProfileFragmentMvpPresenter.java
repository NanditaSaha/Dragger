package app.ceva.petapp.feature.MyProfile;

import app.ceva.petapp.share.baseFragment.BaseFragmentMvpPresenter;

public interface ProfileFragmentMvpPresenter<V extends ProfileFragmentMvpView>extends BaseFragmentMvpPresenter<V> {

    void onGetProfile();

    void onChangepassword(String oldpassword, String newpassword);
}
