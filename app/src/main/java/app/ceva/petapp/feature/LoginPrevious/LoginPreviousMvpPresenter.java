package app.ceva.petapp.feature.LoginPrevious;

import app.ceva.petapp.di.PerActivity;
import app.ceva.petapp.share.base.MvpPresenter;

@PerActivity
public interface LoginPreviousMvpPresenter<V extends LoginPreviousMvpView>extends MvpPresenter<V> {
    void onLoginClick();
    void onSignUpClick();

}
