package app.ceva.petapp.feature.Login;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.Toolbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.inject.Inject;

import app.ceva.petapp.R;
import app.ceva.petapp.data.network.model.ForgetPassword.ForgetPasswordResponse;
import app.ceva.petapp.feature.Dashboard.DashboardActivity;
import app.ceva.petapp.feature.SendOtp.SendOtpActivity;
import app.ceva.petapp.share.base.BaseActivity;
import app.ceva.petapp.share.wigeds.TextViewBold;
import app.ceva.petapp.share.wigeds.TextViewSemibold;
import app.ceva.petapp.utils.L;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements LoginMvpView, View.OnClickListener,ForgetPasswordDialog.byForgetPasswordDialogInterface {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.login)
    FrameLayout login;
    @BindView(R.id.signup)
    LinearLayout signup;
    @BindView(R.id.etName)
    EditText etMobile;
    @BindView(R.id.etPassword)
    EditText etPassword;
    @BindView(R.id.tvSignIn)
    TextViewSemibold tvSignIn;
    @BindView(R.id.tvForgetPassword)
    TextViewBold tvForgetPassword;
    @BindView(R.id.spCountryCode)
    AppCompatSpinner spCountryCode;

    private String phone,ForgetPhone,countrycode;
    private ArrayList<String> countrycode_list=new ArrayList<>();



    @Inject
    LoginPresenter<LoginMvpView> presenter;

    private ForgetPasswordDialog forgetPasswordDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setUnBinder(ButterKnife.bind(this));
        getActivityComponent().inject(this);
        setUp();
        setToolbar();
        setEditText();
        deletePetDialougSet();
        setCountryCodeAdapter();
        getSpinnerSelectedId();
    }

    protected void setUp()
    {
        presenter.onAttached(this);

    }

    private void setToolbar()
    {

        setSupportActionBar(toolbar);
      //  toolbar.setNavigationIcon(R.drawable.ic_left_arrow);
        final Drawable upArrow = getResources().getDrawable(R.drawable.ic_left_arrow);
       // upArrow.setColorFilter(getResources().getColor(R.color.grey), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("");
    }

    private void setEditText()
    {
//        etMobile.setText("");
//        Selection.setSelection(etMobile.getText(), etMobile.getText().length());
//        etMobile.addTextChangedListener(new TextWatcher() {
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                // TODO Auto-generated method stub
//
//            }
//
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count,
//                                          int after) {
//                // TODO Auto-generated method stub
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                if(!s.toString().startsWith("")){
//                    etMobile.setText("");
//                 //   Selection.setSelection(etMobile.getText(), etMobile.getText().length());
//
//                }
//
//            }
//        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here


                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void deletePetDialougSet() {
        forgetPasswordDialog = new ForgetPasswordDialog();
        forgetPasswordDialog.setAdapter(new ForgetPasswordDialog.byForgetPasswordDialogInterface() {
            @Override
            public void sendforgetPassword(String phoneNumber) {
                ForgetPhone=phoneNumber;
                presenter.onForgetPasswordClick(phoneNumber);
            }
        });
    }

    @OnClick({R.id.login,R.id.signup,R.id.tvSignIn,R.id.tvForgetPassword})
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.signup:
                gotoNext(SendOtpActivity.class,null);
                break;
            case R.id.tvSignIn:
               // phone=etMobile.getText().toString().trim().substring(3,13);
                phone=countrycode+etMobile.getText().toString().trim();
                presenter.onLiginClick(phone,etPassword.getText().toString().trim());


                break;
            case R.id.tvForgetPassword:
              //  Toast.makeText(getApplicationContext(), "Coming Soon", Toast.LENGTH_SHORT).show();
               // forgetPasswordDialog=new ForgetPasswordDialog();
                forgetPasswordDialog.show(getSupportFragmentManager(),"byaddpets");
                break;
        }
    }

    @Override
    public void sendforgetPassword(String phoneNumber) {
        Toast.makeText(getApplicationContext(), "Coming Soon", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void navigateToDashboard() {
        Intent ai=new Intent(LoginActivity.this, DashboardActivity.class);
        startActivity(ai);
        finish();
    }

    @Override
    public void successfullyForgetPassword(ForgetPasswordResponse forgetPasswordResponse) {

    }


    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("countrycodes.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public void setCountryCodeAdapter()
    {
        try {
            JSONArray obj_array = new JSONArray(loadJSONFromAsset());



            for (int i = 0; i < obj_array.length(); i++) {
                JSONObject jo_inside = obj_array.getJSONObject(i);
                countrycode_list.add(jo_inside.getString("dial_code"));

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        spCountryCode.setAdapter(new ArrayAdapter<String>(LoginActivity.this, android.R.layout.simple_spinner_dropdown_item, countrycode_list));
    }


    private void getSpinnerSelectedId()
    {
        spCountryCode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                countrycode=countrycode_list.get(position);
                L.e("Id Pet=="+countrycode);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
