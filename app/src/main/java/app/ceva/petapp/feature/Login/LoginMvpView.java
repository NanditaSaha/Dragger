package app.ceva.petapp.feature.Login;

import app.ceva.petapp.data.network.model.ForgetPassword.ForgetPasswordResponse;
import app.ceva.petapp.data.network.model.ScanQr.ScanQrResponse;
import app.ceva.petapp.share.base.MvpView;

public interface LoginMvpView extends MvpView {

    void navigateToDashboard();
    void successfullyForgetPassword(ForgetPasswordResponse forgetPasswordResponse);


}
