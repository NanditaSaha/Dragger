package app.ceva.petapp.feature.SendOtp;


import app.ceva.petapp.di.PerActivity;
import app.ceva.petapp.share.base.MvpPresenter;

@PerActivity
public interface SendOtpMvpPresenter<V extends SendOtpMvpView>extends MvpPresenter<V> {
 void onCheckOtpClick(String phonNumber,String Otp);

 void onSendOtpClick(String phonNumber);
 void onReSendOtpClick(String phonNumber);
}
