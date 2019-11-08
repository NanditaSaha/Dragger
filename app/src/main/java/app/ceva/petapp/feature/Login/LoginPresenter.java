package app.ceva.petapp.feature.Login;

import javax.inject.Inject;

import app.ceva.petapp.data.DataManager;
import app.ceva.petapp.data.network.model.ForgetPassword.ForgetPasswordRequest;
import app.ceva.petapp.data.network.model.ForgetPassword.ForgetPasswordResponse;
import app.ceva.petapp.data.network.model.Login.LoginRequest;
import app.ceva.petapp.data.network.model.Login.LoginResponse;
import app.ceva.petapp.share.base.BasePresenter;
import app.ceva.petapp.utils.AppData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter <V extends LoginMvpView> extends BasePresenter<V> implements LoginMvpPresenter<V>{

    @Inject
    public LoginPresenter(DataManager mdataManager) {
        super(mdataManager);
    }

    @Override
    public void onLiginClick(String Phone, String Password) {

        getLogin(Phone,Password);

    }

    @Override
    public void onForgetPasswordClick(String Phone) {

        if(Phone==null||Phone.isEmpty())
        {
            getMvpView().onError("Please Enter PhoneNumber");
        }
        else
        {
            if(getMvpView().isNetworkConnected())
            {
                getMvpView().showLoading();
                ForgetPasswordRequest forgetPasswordRequest=new ForgetPasswordRequest(Phone);
                getDataManager().getForgetPassword(forgetPasswordRequest).enqueue(new Callback<ForgetPasswordResponse>() {
                    @Override
                    public void onResponse(Call<ForgetPasswordResponse> call, Response<ForgetPasswordResponse> response) {

                        if(response.isSuccessful())
                        {
                            getMvpView().hideLoading();
                            if(response.body().getResponseCode()==1)
                            {
                                getMvpView().onError(response.body().getResponseText());

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
                    public void onFailure(Call<ForgetPasswordResponse> call, Throwable t) {
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




    private void getLogin(String Phone, String Password)
    {

        if(Phone==null||Phone.isEmpty())
        {
            getMvpView().onError("Please Enter PhoneNumber");
        }

        else if(Password==null||Password.isEmpty())
        {
            getMvpView().onError("Please Enter Password");
        }
        else
        {
            if(getMvpView().isNetworkConnected())
            {
                getMvpView().showLoading();
                LoginRequest loginRequest=new LoginRequest(Phone,Password);
                getDataManager().getLogin(loginRequest).enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                        if(response.isSuccessful())
                        {
                            getMvpView().hideLoading();
                            if(response.body().getResponseCode()==1)
                            {

                                getMvpView().navigateToDashboard();
                                getMvpView().onError(response.body().getResponseText());
                                getDataManager().setUserId(response.body().getResponseData().getId());
                                getDataManager().setFullName(response.body().getResponseData().getName());
                                getDataManager().setEmail(response.body().getResponseData().getEmail());
                                getDataManager().setPhoneNumber(Phone);
                                getDataManager().setAddress(response.body().getResponseData().getAddress());
                                getDataManager().setRegion(response.body().getResponseData().getRegion());

                                AppData.USER_ID=response.body().getResponseData().getId();
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
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
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
