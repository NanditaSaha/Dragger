package app.ceva.petapp.feature.MySettings;

import app.ceva.petapp.data.network.model.AddSettings.AddsettingsResponse;
import app.ceva.petapp.data.network.model.FetchSettings.FetchSettingsResponse;
import app.ceva.petapp.share.baseFragment.BaseFragmentMvpView;

public interface SettingsFragmentMvpView extends BaseFragmentMvpView {
    void successfullySubmitSettings(AddsettingsResponse addsettingsResponse);
    void successfullyGetSettings(FetchSettingsResponse fetchSettingsResponse);

}
