package app.ceva.petapp.feature.MyProfile;

import app.ceva.petapp.data.network.model.ChangePassword.ChangePasswordResponse;
import app.ceva.petapp.data.network.model.GetProfile.GetProfileResponse;
import app.ceva.petapp.share.baseFragment.BaseFragmentMvpView;

public interface ProfileFragmentMvpView extends BaseFragmentMvpView {

    void successfullyGetProfile(GetProfileResponse getProfileResponse);
    void successfullyChangepassword(ChangePasswordResponse changePasswordResponse);
}
