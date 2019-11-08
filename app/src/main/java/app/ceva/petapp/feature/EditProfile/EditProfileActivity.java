package app.ceva.petapp.feature.EditProfile;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;

import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import app.ceva.petapp.R;
import app.ceva.petapp.data.network.model.GetProfile.GetProfileResponse;
import app.ceva.petapp.data.network.model.Region.RegionResponse;
import app.ceva.petapp.data.network.model.UpdateProfile.UpdateProfileResponse;
import app.ceva.petapp.share.base.BaseActivity;
import app.ceva.petapp.utils.L;

public class EditProfileActivity extends BaseActivity implements EditProfileMvpView, View.OnClickListener {


    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.editprofile)
    FrameLayout editprofile;
    @BindView(R.id.etEmail)
    EditText etEmail;
    @BindView(R.id.etAddress)
    EditText etAddress;
    @BindView(R.id.etName)
    EditText etName;
    @BindView(R.id.etRegion)
    AppCompatSpinner etRegion;

    private String RegionId;



    private ArrayList<String> region_name=new ArrayList<>();
    private ArrayList<String> region_id=new ArrayList<>();


    @Inject
    EditProfilePresenter<EditProfileMvpView> presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        setUnBinder(ButterKnife.bind(this));
        getActivityComponent().inject(this);
        setUp();
        setToolbar();
        getSpinnerSelectedId();
    }

    @OnClick(R.id.editprofile)
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.editprofile:
               presenter.onUpdateProfile(etName.getText().toString().trim(),etEmail.getText().toString().trim(),"",etAddress.getText().toString().trim(),RegionId);
                break;
        }
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
    protected void onResume() {
        super.onResume();

        presenter.onGetRegionList();
        presenter.onGetProfile();
    }

    @Override
    public void successfullyGetProfile(GetProfileResponse getProfileResponse) {

        if(getProfileResponse.getResponseCode()==1)
        {
            etEmail.setText(getProfileResponse.getResponseData().getEmail());
            etAddress.setText(getProfileResponse.getResponseData().getAddress());

            etName.setText(getProfileResponse.getResponseData().getName());


            for(int i=1;i<region_name.size();i++)
            {
                if(getProfileResponse.getResponseData().getRegion().equals(region_name.get(i)))
                {
                    etRegion.setSelection((i));
                }
            }
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
            etRegion.setAdapter(new ArrayAdapter<String>(EditProfileActivity.this, android.R.layout.simple_spinner_dropdown_item, region_name));
        }

    }

    @Override
    public void successfullyEditProfile(UpdateProfileResponse updateProfileResponse) {

        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
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
}
