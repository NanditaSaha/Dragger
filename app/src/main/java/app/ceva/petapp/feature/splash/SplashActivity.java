package app.ceva.petapp.feature.splash;

import android.content.Intent;
import android.os.Bundle;

import com.crashlytics.android.Crashlytics;

import javax.inject.Inject;

import app.ceva.petapp.R;
import app.ceva.petapp.feature.Dashboard.DashboardActivity;
import app.ceva.petapp.feature.LoginPrevious.LoginPreviousPreviousActivity;
import app.ceva.petapp.share.base.BaseActivity;
import butterknife.ButterKnife;
import io.fabric.sdk.android.Fabric;

public class SplashActivity extends BaseActivity implements SplashMvpView{

    @Inject
    SplashPresenter <SplashMvpView> presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fabric.with(this, new Crashlytics());
        setUnBinder(ButterKnife.bind(this));
        getActivityComponent().inject(this);
        setUp();
        initUi();

    }

    private void initUi()
    {
        presenter.decideNavigation();
    }

    protected void setUp()
    {
        presenter.onAttached(this);

    }
    @Override
    public void navigateToHOme() {

       // gotoNext(LoginPreviousPreviousActivity.class,null);
        Intent ai=new Intent(SplashActivity.this, DashboardActivity.class);
        startActivity(ai);
        finish();
    }

    @Override
    public void navigateToLogin() {

        Intent i=new Intent(SplashActivity.this, LoginPreviousPreviousActivity.class);
        startActivity(i);
        finish();
    }


    @Override
    protected void onDestroy() {
        presenter.onDettached();
        super.onDestroy();
    }
}
