package app.ceva.petapp.feature.SendOtp;

import javax.inject.Inject;

import app.ceva.petapp.data.DataManager;
import app.ceva.petapp.data.network.model.CheckOtp.CheckOtpRequest;
import app.ceva.petapp.data.network.model.CheckOtp.CheckOtpResponse;
import app.ceva.petapp.data.network.model.SendOtp.SendOtpRequest;
import app.ceva.petapp.data.network.model.SendOtp.SendOtpResponse;
import app.ceva.petapp.share.base.BasePresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SendOtpPresenter <V extends SendOtpMvpView> extends BasePresenter<V> implements SendOtpMvpPresenter<V> {

    @Inject
    public SendOtpPresenter(DataManager mdataManager) {
        super(mdataManager);
    }


    @Override
    public void onCheckOtpClick(String phonNumber, String Otp) {
       // getMvpView().navigateToSignUp();
        checkOtp(phonNumber,Otp);
    }

    private void checkOtp(String phonNumber, String Otp)
    {
        if(Otp==null||Otp.isEmpty())
        {
            getMvpView().onError("Please Enter PhoneNumber");
        }
        else
        {
            if (getMvpView().isNetworkConnected()) {
                getMvpView().showLoading();
                CheckOtpRequest checkOtpRequest;

                if(getDataManager().getUserId().equals("")) {
                     checkOtpRequest = new CheckOtpRequest(phonNumber, Otp, "0");
                }
                else
                {
                     checkOtpRequest = new CheckOtpRequest(phonNumber, Otp, getDataManager().getUserId());
                }
                getDataManager().getCheckOtp(checkOtpRequest).enqueue(new Callback<CheckOtpResponse>() {
                    @Override
                    public void onResponse(Call<CheckOtpResponse> call, Response<CheckOtpResponse> response) {


                        if(response.isSuccessful())
                        {
                            getMvpView().hideLoading();
                            if(response.body().getResponseCode()==1)
                            {
                                getMvpView().navigateToSignUp("Success",response.body().getResponseData(),response.body().getUser_id());
                                getDataManager().setPhoneNumber(response.body().getResponseData());
                                getMvpView().onError(response.body().getResponseText());
                            }
                            else
                            {
                                getMvpView().navigateToSignUp("Failed","","");
                                getDataManager().setPhoneNumber("");
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
                    public void onFailure(Call<CheckOtpResponse> call, Throwable t) {
                        getMvpView().hideLoading();
                        getMvpView().onError("Server Error");
                    }
                });

            } else {
                getMvpView().showAlert("No internet Connection");
            }
        }
    }

    @Override
    public void onSendOtpClick(String phonNumber) {
        sendOtp(phonNumber);
    }

    @Override
    public void onReSendOtpClick(String phonNumber) {
        sendOtp(phonNumber);
    }


    private void sendOtp(String phonNumber)
    {
        if(phonNumber.equals("+66"))
        {
            getMvpView().onError("Please Enter PhoneNumber");
        }
        else {
            if (getMvpView().isNetworkConnected()) {
                getMvpView().showLoading();
                SendOtpRequest request=new SendOtpRequest(phonNumber);
                getDataManager().getSendOtp(request).enqueue(new Callback<SendOtpResponse>() {
                    @Override
                    public void onResponse(Call<SendOtpResponse> call, Response<SendOtpResponse> response) {
                        if(response.isSuccessful())
                        {
                            getMvpView().hideLoading();
                            if(response.body().getResponseCode()==1)
                            {
                                getMvpView().navigateToCheckOtp();
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
                    public void onFailure(Call<SendOtpResponse> call, Throwable t) {
                        getMvpView().hideLoading();
                        getMvpView().showAlert("Server Error");
                    }
                });



            } else {
                getMvpView().showAlert("No internet Connection");
            }
        }
    }
}
