package app.ceva.petapp.feature.Registration;

import java.util.List;

import app.ceva.petapp.data.network.model.Region.RegionResponse;
import app.ceva.petapp.share.base.MvpView;

public interface RegistrationMvpView extends MvpView {

    void navigateToAddPetsDialough(String result);
    void successfullyGetRegoinList(List<RegionResponse.ResponseDataBean> regionResponse);
    void successfullGetIntentData(String phoneNumber);
}
