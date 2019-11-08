package app.ceva.petapp.feature.PetDetails;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import app.ceva.petapp.R;
import app.ceva.petapp.data.network.model.EditPet.EditPetResponse;
import app.ceva.petapp.data.network.model.GetPetDetails.GetPetDetailsResponse;
import app.ceva.petapp.data.network.model.PetCategory.PetCategoryResponse;
import app.ceva.petapp.feature.Calendar.CalenderActivity;
import app.ceva.petapp.share.base.BaseActivity;
import app.ceva.petapp.utils.L;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PetDetailsActivity extends BaseActivity implements PetDetailsMvpView , View.OnClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;




    @BindView(R.id.editPet)
    FrameLayout editPet;
    @BindView(R.id.ivEdit)
    ImageView ivEdit;
    @BindView(R.id.etPetName)
    EditText etPetName;
    @BindView(R.id.spAnimalType)
    AppCompatSpinner spAnimalType;
    @BindView(R.id.llVaccine)
    LinearLayout llVaccine;
    @BindView(R.id.llNameView)
    LinearLayout llNameView;
    @BindView(R.id.cvNameEdit)
    CardView cvNameEdit;
    @BindView(R.id.etPetNames)
     EditText etPetNames;
    @BindView(R.id.etPetTypes)
    EditText etPetTypes;
    @BindView(R.id.rvPetVaccination)
    RecyclerView rvPetVaccination;
    @BindView(R.id.llEmpty)
    LinearLayout llEmpty;


    String type,PetId,CategoryId;

    private ArrayList<String> pet_cat_name=new ArrayList<>();
    private ArrayList<String> pet_cat_id=new ArrayList<>();


    @Inject
    VaccineListAdapter vaccineListAdapter;

    @Inject
    PetDetailsPresenter<PetDetailsMvpView> presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_details);
        setUnBinder(ButterKnife.bind(this));
        getActivityComponent().inject(this);
        setUp();
        setToolbar();
        getIntentData();
        setRecylerView();


    }


    private void getIntentData()
    {
        PetId=getIntent().getStringExtra("PetId");
        presenter.onGetPetDetails(PetId);
    }

    @OnClick({R.id.editPet,R.id.ivEdit})
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.editPet:

                presenter.onEditPets(PetId,CategoryId,etPetName.getText().toString().trim());
                break;

            case R.id.ivEdit:
                etPetName.setFocusableInTouchMode(true);
                etPetName.setFocusable(true);

                editPet.setVisibility(View.VISIBLE);
                break;



        }
    }

    protected void setUp()
    {
        presenter.onAttached(this);

        type=getIntent().getStringExtra("Type");
        setLayoutVisiblity();

        presenter.onPetCategoryList();
        getSpinnerSelectedId();


    }


  private void setLayoutVisiblity()
  {
      if(type.equals("View"))
      {
          cvNameEdit.setVisibility(View.GONE);
          llNameView.setVisibility(View.VISIBLE);
          llVaccine.setVisibility(View.VISIBLE);
      }
      else
      {
          cvNameEdit.setVisibility(View.VISIBLE);
          llNameView.setVisibility(View.GONE);
          llVaccine.setVisibility(View.GONE);
          etPetName.setFocusableInTouchMode(true);
          etPetName.setFocusable(true);

          editPet.setVisibility(View.VISIBLE);

      }
  }


    private void setRecylerView() {
        rvPetVaccination.setLayoutManager(new LinearLayoutManager(this));
        rvPetVaccination.setAdapter(vaccineListAdapter);

        vaccineListAdapter.setAdapterListner(new VaccineListAdapter.VaccineListListner() {
            @Override
            public void onItemClick(GetPetDetailsResponse.ResponseDataBean.VaccineListBean item, int position, String VId,String UniqueId) {

                Bundle b=new Bundle();
                b.putString("PetId",PetId);
                b.putString("ProductId",VId);
                b.putString("ProductUniqueId",UniqueId);
              //  Toast.makeText(getApplicationContext(),UniqueId,Toast.LENGTH_SHORT).show();
                gotoNext(CalenderActivity.class,b);
            }
        });


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
    public void onBackPressed() {
        super.onBackPressed();

    }


    @Override
    public void successfullyEditPetList(EditPetResponse editPetResponse) {
        onBackPressed();
    }

    @Override
    public void successfullyPetCatList(List<PetCategoryResponse.ResponseDataBean> regionResponse) {
        if(regionResponse.size()>0)
        {
            for(int i =0;i<regionResponse.size();i++)
            {
                PetCategoryResponse.ResponseDataBean responseDataBean=regionResponse.get(i);
                pet_cat_name.add(responseDataBean.getName());
                pet_cat_id.add(responseDataBean.getId());
            }
            spAnimalType.setAdapter(new ArrayAdapter<String>(PetDetailsActivity.this, android.R.layout.simple_spinner_dropdown_item, pet_cat_name));
        }
    }

    @Override
    public void successfullyGetPetDetails(GetPetDetailsResponse getPetDetailsResponse) {

        if(getPetDetailsResponse.getResponseCode()==1)
        {
            etPetNames.setText(getPetDetailsResponse.getResponseData().getName());
            etPetTypes.setText(getPetDetailsResponse.getResponseData().getCategory_name());
            CategoryId=getPetDetailsResponse.getResponseData().getCategory_id();
            etPetName.setText(getPetDetailsResponse.getResponseData().getName());

            if(getPetDetailsResponse.getResponseData().getVaccine_list().size()>0) {
               rvPetVaccination.setVisibility(View.VISIBLE);
               llEmpty.setVisibility(View.GONE);
                vaccineListAdapter.loadList(getPetDetailsResponse.getResponseData().getVaccine_list());
            }
            else
            {
                rvPetVaccination.setVisibility(View.GONE);
                llEmpty.setVisibility(View.VISIBLE);
            }


            for(int i =0;i< pet_cat_id.size();i++)
            {
                if (getPetDetailsResponse.getResponseData().getCategory_id().equals(pet_cat_id.get(i))) {
                    spAnimalType.setSelection(i);
                }
            }

        }

    }


    private void getSpinnerSelectedId()
    {
        spAnimalType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                CategoryId=pet_cat_id.get(position);
                L.e("Id Pet=="+CategoryId);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
