package app.ceva.petapp.feature.LoginPrevious;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import javax.inject.Inject;

import app.ceva.petapp.R;
import app.ceva.petapp.feature.Login.LoginActivity;
import app.ceva.petapp.feature.SendOtp.SendOtpActivity;
import app.ceva.petapp.share.base.BaseActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginPreviousPreviousActivity extends BaseActivity implements LoginPreviousMvpView, View.OnClickListener {

    @Inject
    LoginPreviousPresenter<LoginPreviousMvpView> presenter;

    @BindView(R.id.login)
    FrameLayout login;

    @BindView(R.id.llSignUp)
    LinearLayout llSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_previous);
        setUnBinder(ButterKnife.bind(this));
        getActivityComponent().inject(this);
        setUp();
    }

    protected void setUp()
    {
        presenter.onAttached(this);

    }

    @OnClick({R.id.login,R.id.llSignUp})
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.login:
                presenter.onLoginClick();
                break;
            case R.id.llSignUp:
                presenter.onSignUpClick();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void navigateToLogin() {

       // gotoNext(LoginActivity.class,null);
//        Intent ai=new Intent(LoginPreviousPreviousActivity.this, );
//        startActivity(ai);
//        finish();
        gotoNextWithfinish(LoginActivity.class,null);
    }

    @Override
    public void navigateToSignUp() {
            gotoNextWithfinish(SendOtpActivity.class,null);
    }
}
