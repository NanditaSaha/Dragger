package app.ceva.petapp.feature.MyProfile;

import javax.inject.Inject;

import app.ceva.petapp.data.DataManager;
import app.ceva.petapp.data.network.model.ChangePassword.CgangePasswordRequest;
import app.ceva.petapp.data.network.model.ChangePassword.ChangePasswordResponse;
import app.ceva.petapp.data.network.model.GetProfile.GetProfileRequest;
import app.ceva.petapp.data.network.model.GetProfile.GetProfileResponse;
import app.ceva.petapp.share.baseFragment.BaseFragmentPresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragmentPresenter <V extends ProfileFragmentMvpView> extends BaseFragmentPresenter<V> implements ProfileFragmentMvpPresenter<V> {

    @Inject
    public ProfileFragmentPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void onGetProfile() {
        doGetProfile();
    }

    @Override
    public void onChangepassword(String oldpassword, String newpassword) {
        doChangePassword(oldpassword,newpassword);

    }

    private void doChangePassword(String oldpassword, String newpassword)
    {
        if(getMvpView().isNetworkConnected())
        {
            getMvpView().showLoading();
            CgangePasswordRequest cgangePasswordRequest=new CgangePasswordRequest(getDataManager().getUserId(),oldpassword,newpassword);
            getDataManager().getChangePassword(cgangePasswordRequest).enqueue(new Callback<ChangePasswordResponse>() {
                @Override
                public void onResponse(Call<ChangePasswordResponse> call, Response<ChangePasswordResponse> response) {

                    if(response.isSuccessful())
                    {
                        getMvpView().hideLoading();
                        if(response.body().getResponseCode()==1)
                        {

                            getMvpView().onError(response.body().getResponseText());
                            getMvpView().successfullyChangepassword(response.body());

                        }
                        else
                        {
                            getMvpView().onError(response.body().getResponseText());
                            getMvpView().successfullyChangepassword(response.body());
                        }
                    }
                    else
                    {
                        getMvpView().hideLoading();
                        getMvpView().onError("Server Error");
                    }

                }

                @Override
                public void onFailure(Call<ChangePasswordResponse> call, Throwable t) {
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

   private void doGetProfile()
    {

            if(getMvpView().isNetworkConnected())
            {
                getMvpView().showLoading();
                GetProfileRequest getProfileRequest=new GetProfileRequest(getDataManager().getUserId());
                getDataManager().getProfile(getProfileRequest).enqueue(new Callback<GetProfileResponse>() {
                    @Override
                    public void onResponse(Call<GetProfileResponse> call, Response<GetProfileResponse> response) {

                        if(response.isSuccessful())
                        {
                            getMvpView().hideLoading();
                            if(response.body().getResponseCode()==1)
                            {

                                getMvpView().successfullyGetProfile(response.body());

                            }
                            else
                            {
                                getMvpView().onError(response.body().getResponseText());
                            }
                        }
                        else
                        {
                            getMvpView().hideLoading();
                            getMvpView().onError("Server Error");
                        }

                    }

                    @Override
                    public void onFailure(Call<GetProfileResponse> call, Throwable t) {
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
