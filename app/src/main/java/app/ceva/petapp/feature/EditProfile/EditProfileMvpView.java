package app.ceva.petapp.feature.EditProfile;

import java.util.List;

import app.ceva.petapp.data.network.model.GetProfile.GetProfileResponse;
import app.ceva.petapp.data.network.model.Region.RegionResponse;
import app.ceva.petapp.data.network.model.UpdateProfile.UpdateProfileResponse;
import app.ceva.petapp.share.base.MvpView;

public interface EditProfileMvpView extends MvpView {
    void successfullyGetProfile(GetProfileResponse getProfileResponse);
    void successfullyGetRegoinList(List<RegionResponse.ResponseDataBean> regionResponse);
    void successfullyEditProfile(UpdateProfileResponse updateProfileResponse);
}
