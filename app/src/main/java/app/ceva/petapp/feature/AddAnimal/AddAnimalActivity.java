package app.ceva.petapp.feature.AddAnimal;

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
import app.ceva.petapp.data.network.model.PetCategory.PetCategoryResponse;
import app.ceva.petapp.feature.Dashboard.DashboardActivity;
import app.ceva.petapp.share.base.BaseActivity;
import app.ceva.petapp.share.wigeds.TextViewSemibold;
import app.ceva.petapp.utils.AppData;
import app.ceva.petapp.utils.L;

public class AddAnimalActivity extends BaseActivity implements AddAnimalMvpView, View.OnClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tvAddPet)
    TextViewSemibold tvAddPet;
    @BindView(R.id.spAnimalType)
    AppCompatSpinner spAnimalType;
    @BindView(R.id.llgoToDashboard)
    FrameLayout llgoToDashboard;
    @BindView(R.id.etPetName)
    EditText etPetName;


    ArrayList<String> spinnerArray = new ArrayList<String>();
    private String PetCatId,UserId;

    @Inject
    AddAnimalPresenter<AddAnimalMvpView> presenter;
    private ArrayList<String> pet_cat_name=new ArrayList<>();
    private ArrayList<String> pet_cat_id=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_animal);

        setUnBinder(ButterKnife.bind(this));
        getActivityComponent().inject(this);
        setUp();
        setToolbar();
        setInit();
        getSpinnerSelectedId();

    }

    protected void setUp()
    {
        presenter.onAttached(this);

    }

    private void setInit()
    {
        presenter.onPetCategoryList();
       // L.e("UserId"+AppData.USER_ID);
        UserId=AppData.USER_ID;
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

    @OnClick(R.id.llgoToDashboard)
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.llgoToDashboard:
                presenter.onAddPetsClick(UserId,PetCatId,etPetName.getText().toString().trim());

        }
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
    public void successfullyPetCatList(List<PetCategoryResponse.ResponseDataBean> petCatResponse) {
        if(petCatResponse.size()>0)
        {
            for(int i =0;i<petCatResponse.size();i++)
            {
                PetCategoryResponse.ResponseDataBean responseDataBean=petCatResponse.get(i);
                pet_cat_name.add(responseDataBean.getName());
                pet_cat_id.add(responseDataBean.getId());
            }
            spAnimalType.setAdapter(new ArrayAdapter<String>(AddAnimalActivity.this, android.R.layout.simple_spinner_dropdown_item, pet_cat_name));
        }
    }

    @Override
    public void successfullyAddPets(String result) {

        if(result.equals("Success")) {
            gotoNextWithfinish(DashboardActivity.class, null);
        }
        else
        {

        }
    }

    private void getSpinnerSelectedId()
    {
        spAnimalType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                PetCatId=pet_cat_id.get(position);
                L.e("Id Pet=="+PetCatId);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
