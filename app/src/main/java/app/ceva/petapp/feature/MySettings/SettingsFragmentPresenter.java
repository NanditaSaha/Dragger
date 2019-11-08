package app.ceva.petapp.feature.MySettings;

import javax.inject.Inject;

import app.ceva.petapp.data.DataManager;
import app.ceva.petapp.data.network.model.AddSettings.AddsettingsRequest;
import app.ceva.petapp.data.network.model.AddSettings.AddsettingsResponse;
import app.ceva.petapp.data.network.model.FetchSettings.FetchSettingsRequest;
import app.ceva.petapp.data.network.model.FetchSettings.FetchSettingsResponse;
import app.ceva.petapp.share.baseFragment.BaseFragmentPresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SettingsFragmentPresenter<V extends SettingsFragmentMvpView>extends BaseFragmentPresenter<V> implements SettingsFragmentMvpPresenter<V> {
    @Inject
    public SettingsFragmentPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void onSubmitSettings(String nofiticationBefor) {
        if(getMvpView().isNetworkConnected())
        {
            getMvpView().showLoading();
            AddsettingsRequest addsettingsRequest=new AddsettingsRequest(getDataManager().getUserId(),nofiticationBefor);
            getDataManager().addSettings(addsettingsRequest).enqueue(new Callback<AddsettingsResponse>() {
                @Override
                public void onResponse(Call<AddsettingsResponse> call, Response<AddsettingsResponse> response) {

                    if(response.isSuccessful())
                    {
                        getMvpView().hideLoading();
                        if(response.body().getResponseCode()==1)
                        {
                           // L.e("SubmitSettings");
                            getMvpView().onError(response.body().getResponseText());
                            getMvpView().successfullySubmitSettings(response.body());


                        }
                        else
                        {
                            getMvpView().onError(response.body().getResponseText());
                            getMvpView().successfullySubmitSettings(response.body());
                        }
                    }
                    else
                    {
                        getMvpView().hideLoading();
                        getMvpView().onError("Server Error");
                    }

                }

                @Override
                public void onFailure(Call<AddsettingsResponse> call, Throwable t) {
                    getMvpView().hideLoading();
                    getMvpView().showAlert("Server Error");
                }
            });

        }
        else
        {
            getMvpView().showAlert("No internet Connection");
        }
    }

    @Override
    public void ongetSettings() {
        if(getMvpView().isNetworkConnected())
        {
            getMvpView().showLoading();
            FetchSettingsRequest fetchSettingsRequest=new FetchSettingsRequest(getDataManager().getUserId());
            getDataManager().getSettings(fetchSettingsRequest).enqueue(new Callback<FetchSettingsResponse>() {
                @Override
                public void onResponse(Call<FetchSettingsResponse> call, Response<FetchSettingsResponse> response) {

                    if(response.isSuccessful())
                    {
                        getMvpView().hideLoading();
                        if(response.body().getResponseCode()==1)
                        {
                            getMvpView().onError(response.body().getResponseText());
                            getMvpView().successfullyGetSettings(response.body());


                        }
                        else
                        {
                            getMvpView().onError(response.body().getResponseText());
                            getMvpView().successfullyGetSettings(response.body());
                        }
                    }
                    else
                    {
                        getMvpView().hideLoading();
                        getMvpView().onError("Server Error");
                    }

                }

                @Override
                public void onFailure(Call<FetchSettingsResponse> call, Throwable t) {
                    getMvpView().hideLoading();
                    getMvpView().showAlert("Server Error");
                }
            });

        }
        else
        {
            getMvpView().showAlert("No internet Connection");
        }
    }
}
