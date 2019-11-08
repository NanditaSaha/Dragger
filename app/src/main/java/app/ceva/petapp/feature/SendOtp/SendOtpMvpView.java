package app.ceva.petapp.feature.SendOtp;

import app.ceva.petapp.share.base.MvpView;

public interface SendOtpMvpView extends MvpView {
    void navigateToSignUp(String result,String PhoneNumber,String userId);
    void navigateToCheckOtp();
    void navigateToReSendOtp();
}
