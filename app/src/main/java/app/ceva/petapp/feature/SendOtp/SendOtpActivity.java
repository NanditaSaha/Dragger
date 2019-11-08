package app.ceva.petapp.feature.SendOtp;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.Toolbar;

import com.konaire.numerickeyboard.NumericKeyboard;
import com.mukesh.OtpView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.inject.Inject;

import app.ceva.petapp.R;
import app.ceva.petapp.feature.Dashboard.DashboardActivity;
import app.ceva.petapp.feature.Registration.RegistrationActivity;
import app.ceva.petapp.share.base.BaseActivity;
import app.ceva.petapp.share.wigeds.TextViewMedium;
import app.ceva.petapp.share.wigeds.TextViewRegular;
import app.ceva.petapp.utils.L;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SendOtpActivity extends BaseActivity implements SendOtpMvpView, View.OnClickListener {

    @BindView(R.id.custom_number_pad)
    NumericKeyboard custom_number_pad;

    @BindView(R.id.et_mobile)
    EditText et_mobile;
    @BindView(R.id.tvThird)
    TextViewRegular tvThird;


    @BindView(R.id.ivSendOtp)
    AppCompatImageView ivSendOtp;

    @BindView(R.id.llOtpVerification)
    RelativeLayout llOtpVerification;
    @BindView(R.id.llSendOtp)
    RelativeLayout llSendOtp;
    @BindView(R.id.otp_view)
    OtpView otp_view;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.ivCheckOtp)
    AppCompatImageView ivCheckOtp;
    @BindView(R.id.tvResendOtp)
    TextViewMedium tvResendOtp;
    @BindView(R.id.spCountryCode)
    AppCompatSpinner spCountryCode;

    private String phone,countrycode;

    private ArrayList<String> countrycode_list=new ArrayList<>();



    private boolean flag=false;


    @Inject
    SendOtpPresenter<SendOtpMvpView> presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_otp);
        setUnBinder(ButterKnife.bind(this));
        getActivityComponent().inject(this);
        setUp();
        setToolbar();
        setEditText();
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
//        et_mobile.setText("");
//        Selection.setSelection(et_mobile.getText(), et_mobile.getText().length());
//        et_mobile.addTextChangedListener(new TextWatcher() {
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
//                    et_mobile.setText("");
//                    Selection.setSelection(et_mobile.getText(), et_mobile.getText().length());
//
//                }
//
//            }
//        });
    }

    @Override
    public void onBackPressed() {


        if(flag)
        {
            flag=false;
            final Drawable upArrow = getResources().getDrawable(R.drawable.ic_left_arrow);
            // upArrow.setColorFilter(getResources().getColor(R.color.grey), PorterDuff.Mode.SRC_ATOP);
            toolbar.setBackgroundColor(getResources().getColor(R.color.colorWhite));
            getSupportActionBar().setHomeAsUpIndicator(upArrow);
            llSendOtp.setVisibility(View.VISIBLE);
            llOtpVerification.setVisibility(View.GONE);
            custom_number_pad.setField(et_mobile);
            otp_view.setText("");
        }
        else {
            super.onBackPressed();
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here
                if(flag)
                {
                    flag=false;
                    final Drawable upArrow = getResources().getDrawable(R.drawable.ic_left_arrow);
                    // upArrow.setColorFilter(getResources().getColor(R.color.grey), PorterDuff.Mode.SRC_ATOP);
                    toolbar.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                    getSupportActionBar().setHomeAsUpIndicator(upArrow);
                    llSendOtp.setVisibility(View.VISIBLE);
                    llOtpVerification.setVisibility(View.GONE);
                    custom_number_pad.setField(et_mobile);

                }
                else {
                    onBackPressed();
                }
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @OnClick({R.id.ivSendOtp,R.id.llOtpVerification,R.id.ivCheckOtp,R.id.tvResendOtp})
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.ivSendOtp:
                phone = countrycode+et_mobile.getText().toString().trim();
                presenter.onSendOtpClick(phone);
                tvThird.setText(phone);


                break;
            case  R.id.llOtpVerification:
                break;
            case R.id.ivCheckOtp:
                    if(otp_view.getText().toString().equals("")) {
                        Toast.makeText(getApplicationContext(), "Please Enter Otp", Toast.LENGTH_SHORT).show();
                    }else {
                        presenter.onCheckOtpClick(phone,otp_view.getText().toString().trim());
                    }
                break;
            case R.id.tvResendOtp:
              //  Toast.makeText(getApplicationContext(), "Coming Soon", Toast.LENGTH_SHORT).show();
                presenter.onReSendOtpClick(phone);
                break;


        }
    }

    @Override
    public void navigateToSignUp(String result,String PhoneNumber,String userId) {

        if(result.equals("Success")) {

            if(userId.equals("0")) {
                Bundle b = new Bundle();
                b.putString("PhoneNumber", PhoneNumber);
                gotoNext(RegistrationActivity.class, b);
            }
            else
            {
                gotoNextWithfinish(DashboardActivity.class,null);
            }
        }
        else
        {
            Toast.makeText(this,"Otp Not Matched",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void navigateToCheckOtp() {
        flag=true;
        final Drawable upArrowOne = getResources().getDrawable(R.drawable.ic_left_arrow_white);
        // upArrow.setColorFilter(getResources().getColor(R.color.grey), PorterDuff.Mode.SRC_ATOP);
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        getSupportActionBar().setHomeAsUpIndicator(upArrowOne);
        llSendOtp.setVisibility(View.GONE);
        llOtpVerification.setVisibility(View.VISIBLE);
        custom_number_pad.setField(otp_view);
    }

    @Override
    public void navigateToReSendOtp() {

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

        spCountryCode.setAdapter(new ArrayAdapter<String>(SendOtpActivity.this, android.R.layout.simple_spinner_dropdown_item, countrycode_list));
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
