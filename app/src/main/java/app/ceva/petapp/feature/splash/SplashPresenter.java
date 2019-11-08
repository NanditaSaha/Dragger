package app.ceva.petapp.feature.splash;

import android.os.Handler;

import javax.inject.Inject;

import app.ceva.petapp.data.DataManager;
import app.ceva.petapp.share.base.BasePresenter;

public class SplashPresenter<V extends SplashMvpView > extends BasePresenter<V> implements SplashMvpPresenter<V> {
    @Inject
    public SplashPresenter(DataManager mdataManager) {
        super(mdataManager);
    }

    @Override
    public void decideNavigation() {
     Navigate();
    }

    private void Navigate()
    {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

               // getMvpView().onError(getDataManager().getUserId());

                if(getDataManager().getUserId().equals(""))
                {
                    getMvpView().navigateToLogin();
                }
                else
                {
                    getMvpView().navigateToHOme();
                }
            }
        }, 2000);
    }
}
