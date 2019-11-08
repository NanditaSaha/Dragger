package app.ceva.petapp.feature.Login;

import app.ceva.petapp.di.PerActivity;
import app.ceva.petapp.share.base.MvpPresenter;
@PerActivity
public interface LoginMvpPresenter<V extends LoginMvpView>extends MvpPresenter<V>{

    void onLiginClick(String Phone,String Password);

    void onForgetPasswordClick(String Phone);
}
