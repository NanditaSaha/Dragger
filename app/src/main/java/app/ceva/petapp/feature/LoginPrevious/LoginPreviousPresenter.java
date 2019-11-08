package app.ceva.petapp.feature.LoginPrevious;

import javax.inject.Inject;

import app.ceva.petapp.data.DataManager;
import app.ceva.petapp.share.base.BasePresenter;

public class LoginPreviousPresenter<V extends LoginPreviousMvpView>extends BasePresenter<V>implements LoginPreviousMvpPresenter<V> {
    @Inject
    public LoginPreviousPresenter(DataManager mdataManager) {
        super(mdataManager);
    }

    @Override
    public void onLoginClick() {
        getMvpView().navigateToLogin();
    }

    @Override
    public void onSignUpClick() {

        getMvpView().navigateToSignUp();
    }
}
