package app.ceva.petapp.feature.Registration;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import app.ceva.petapp.R;
import app.ceva.petapp.data.network.model.Region.RegionResponse;
import app.ceva.petapp.feature.AddAnimal.AddAnimalActivity;
import app.ceva.petapp.feature.Dashboard.DashboardActivity;
import app.ceva.petapp.share.base.BaseActivity;
import app.ceva.petapp.utils.L;

public class RegistrationActivity extends BaseActivity implements RegistrationMvpView, View.OnClickListener ,ByAddPetsDialog.byAddPetDialogInterface{

    @BindView(R.id.toolbar)
    Toolbar toolbar;


    @BindView(R.id.flSubmit)
    FrameLayout flSubmit;
    @BindView(R.id.etName)
    EditText etName;
    @BindView(R.id.etAddress)
    EditText etAddress;
    @BindView(R.id.etPhone)
    EditText etPhone;
    @BindView(R.id.etRegion)
    AppCompatSpinner etRegion;
    @BindView(R.id.etEmail)
    EditText etEmail;
    @BindView(R.id.etPassword)
    EditText etPassword;
    @BindView(R.id.cbterm)
    AppCompatCheckBox cbterm;


    private ArrayList<String> region_name=new ArrayList<>();
    private ArrayList<String> region_id=new ArrayList<>();

    @Inject
    RegistrationPresenter<RegistrationMvpView> presenter;

    private ByAddPetsDialog byAddPetsDialog;
    private String RegionId,PhoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        setUnBinder(ButterKnife.bind(this));
        getActivityComponent().inject(this);
        setUp();
        setToolbar();
        getIntentData();
        getSpinnerSelectedId();
    }

    protected void setUp()
    {
        presenter.onAttached(this);
        presenter.onGetRegionList();

    }

    private void getIntentData()
    {
        PhoneNumber=getIntent().getStringExtra("PhoneNumber");
        presenter.onInit(PhoneNumber);
    }

    private void setToolbar()
    {

        setSupportActionBar(toolbar);
        final Drawable upArrow = getResources().getDrawable(R.drawable.ic_left_arrow);
        // upArrow.setColorFilter(getResources().getColor(R.color.grey), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("");
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

    @Override
    public void navigateToAddPetsDialough(String result) {
        if(result.equals("Success")) {
            byAddPetsDialog = new ByAddPetsDialog();
            byAddPetsDialog.show(getSupportFragmentManager(), "byaddpets");
        }
        else
        {

        }

    }

    @Override
    public void successfullyGetRegoinList(List<RegionResponse.ResponseDataBean> regionResponse) {


        if(regionResponse.size()>0)
        {
            for(int i =0;i<regionResponse.size();i++)
            {
                RegionResponse.ResponseDataBean responseDataBean=regionResponse.get(i);
                region_name.add(responseDataBean.getName());
                region_id.add(responseDataBean.getId());
            }
            etRegion.setAdapter(new ArrayAdapter<String>(RegistrationActivity.this, android.R.layout.simple_spinner_dropdown_item, region_name));
        }



    }

    @Override
    public void successfullGetIntentData(String phoneNumber) {

        etPhone.setText(phoneNumber);
    }

    private void getSpinnerSelectedId()
    {
        etRegion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                RegionId=region_id.get(position);
                L.e("Id=="+RegionId);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @OnClick(R.id.flSubmit)
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.flSubmit:
                if(cbterm.isChecked()) {
                    presenter.onSubmitClick(etName.getText().toString().trim(), etAddress.getText().toString().trim(), etPhone.getText().toString().trim(), RegionId, etEmail.getText().toString().trim(), etPassword.getText().toString().trim());
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Please Select Terms and Condition",Toast.LENGTH_SHORT).show();
                }
               break;
        }
    }

    @Override
    public void sendAddPetsBy(boolean low, boolean high) {

        if(low) {
            gotoNext(AddAnimalActivity.class, null);
        }
        else
        {
            gotoNext(DashboardActivity.class, null);
        }
       // Toast.makeText(getApplicationContext(),"add click",Toast.LENGTH_SHORT).show();
    }
}
