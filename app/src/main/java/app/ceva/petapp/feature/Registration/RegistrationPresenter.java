package app.ceva.petapp.feature.Registration;

import javax.inject.Inject;

import app.ceva.petapp.data.DataManager;
import app.ceva.petapp.data.network.model.Region.RegionResponse;
import app.ceva.petapp.data.network.model.Registration.RegistrationRequest;
import app.ceva.petapp.data.network.model.Registration.RegistrationResponse;
import app.ceva.petapp.share.base.BasePresenter;
import app.ceva.petapp.utils.AppData;
import app.ceva.petapp.utils.CommonUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationPresenter<V extends RegistrationMvpView>extends BasePresenter<V> implements RegistrationMvpPresenter<V>{

    @Inject
    public RegistrationPresenter(DataManager mdataManager) {
        super(mdataManager);
    }


    @Override
    public void onSubmitClick(String Name, String Address, String Phone, String Region, String Email, String Password) {

        getRegistration(Name,Address,Phone,Region,Email,Password);

    }

    @Override
    public void onGetRegionList() {
            getRegionList();
    }

    @Override
    public void onInit(String phoneNumber) {

        getMvpView().successfullGetIntentData(phoneNumber);
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



    private void getRegistration(String Name, String Address, String Phone, String Region, String Email, String Password)
    {

        if(Name==null||Name.isEmpty())
        {
            getMvpView().onError("Please Enter Name");
        }

        else if(Address==null||Address.isEmpty())
        {
            getMvpView().onError("Please Enter Address");
        }
        else if(Phone==null||Phone.isEmpty())
        {
            getMvpView().onError("Please Enter Phone");
        }
        else if(Region==null||Region.isEmpty())
        {
            getMvpView().onError("Please Enter Region");
        }
        else if(Email==null||Email.isEmpty())
        {
            getMvpView().onError("Please Enter Email");
        }
        else if(!CommonUtils.isEmailValid(Email))
        {
            getMvpView().onError("Please Enter Valid Email");
        }
        else if(Password==null||Password.isEmpty())
        {
            getMvpView().onError("Please Enter Email");
        }
        else
        {
            if(getMvpView().isNetworkConnected())
            {
                getMvpView().showLoading();
                RegistrationRequest registrationRequest=new RegistrationRequest(Name,Email,Phone,Password,Address,Region);
                getDataManager().getRegistration(registrationRequest).enqueue(new Callback<RegistrationResponse>() {
                    @Override
                    public void onResponse(Call<RegistrationResponse> call, Response<RegistrationResponse> response) {
                        if(response.isSuccessful())
                        {
                            getMvpView().hideLoading();
                            if(response.body().getResponseCode()==1)
                            {

                                getMvpView().onError(response.body().getResponseText());
                                getMvpView().navigateToAddPetsDialough("Success");

                                getDataManager().setUserId(response.body().getResponseData().getId());
                                getDataManager().setFullName(response.body().getResponseData().getName());
                                getDataManager().setAddress(response.body().getResponseData().getAddress());
                                getDataManager().setRegion(response.body().getResponseData().getRegion());
                                getDataManager().setEmail(response.body().getResponseData().getEmail());

                                AppData.USER_ID=response.body().getResponseData().getId();

                            }
                            else
                            {
                                getMvpView().onError(response.body().getResponseText());
                                getMvpView().navigateToAddPetsDialough("Failed");
                            }
                        }
                        else
                        {
                            getMvpView().hideLoading();
                            getMvpView().onError("Server Error");
                        }
                    }

                    @Override
                    public void onFailure(Call<RegistrationResponse> call, Throwable t) {
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
}
