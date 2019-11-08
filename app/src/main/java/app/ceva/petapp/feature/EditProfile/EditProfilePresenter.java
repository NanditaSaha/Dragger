package app.ceva.petapp.feature.EditProfile;

import javax.inject.Inject;

import app.ceva.petapp.data.DataManager;
import app.ceva.petapp.data.network.model.GetProfile.GetProfileRequest;
import app.ceva.petapp.data.network.model.GetProfile.GetProfileResponse;
import app.ceva.petapp.data.network.model.Region.RegionResponse;
import app.ceva.petapp.data.network.model.UpdateProfile.UpdateProfileRequest;
import app.ceva.petapp.data.network.model.UpdateProfile.UpdateProfileResponse;
import app.ceva.petapp.share.base.BasePresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfilePresenter <V extends EditProfileMvpView> extends BasePresenter<V> implements EditProfileMvpPresenter<V> {
    @Inject
    public EditProfilePresenter(DataManager mdataManager) {
        super(mdataManager);
    }

    @Override
    public void onGetProfile() {
        doGetProfile();
    }

    @Override
    public void onGetRegionList() {
            getRegionList();
    }

    @Override
    public void onUpdateProfile(String name, String email, String phone, String address, String region) {

        doUpdateProfile(name,email,phone,address,region);
    }

    void doUpdateProfile(String name, String email, String phone, String address, String region)
    {
        if(name==null||name.isEmpty())
        {
            getMvpView().onError("Please Enter Name");
        }
        else if(address==null||address.isEmpty())
        {
            getMvpView().onError("Please Enter Address");
        }
        else if(email==null||email.isEmpty())
        {
            getMvpView().onError("Please Enter Email");
        }


        else
        {
            if(getMvpView().isNetworkConnected())
            {
                getMvpView().showLoading();
                UpdateProfileRequest updateProfileRequest=new UpdateProfileRequest(getDataManager().getUserId(),name,email,getDataManager().getPhoneNumber(),address,region);
                getDataManager().getEditProfile(updateProfileRequest).enqueue(new Callback<UpdateProfileResponse>() {
                    @Override
                    public void onResponse(Call<UpdateProfileResponse> call, Response<UpdateProfileResponse> response) {

                        if(response.isSuccessful())
                        {
                            getMvpView().hideLoading();
                            if(response.body().getResponseCode()==1)
                            {
                                getMvpView().onError(response.body().getResponseText());
                                getMvpView().successfullyEditProfile(response.body());

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
                    public void onFailure(Call<UpdateProfileResponse> call, Throwable t) {
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

    void doGetProfile()
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


    private  void getRegionList()
    {
        if(getMvpView().isNetworkConnected())
        {
            getMvpView().showLoading();
            getDataManager().getRegionList().enqueue(new Callback<RegionResponse>() {
                @Override
                public void onResponse(Call<RegionResponse> call, Response<RegionResponse> response) {
                    if(response.isSuccessful())
                    {
                        getMvpView().hideLoading();
                        if(response.body().getResponseCode()==1)
                        {

                            getMvpView().successfullyGetRegoinList(response.body().getResponseData());

                        }
                        else
                        {
                            getMvpView().successfullyGetRegoinList(response.body().getResponseData());
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
                public void onFailure(Call<RegionResponse> call, Throwable t) {
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
