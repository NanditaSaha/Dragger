package app.ceva.petapp.feature.splash;

import app.ceva.petapp.di.PerActivity;
import app.ceva.petapp.share.base.MvpPresenter;


@PerActivity
public interface SplashMvpPresenter<V extends SplashMvpView > extends MvpPresenter<V> {

    void decideNavigation();
}
