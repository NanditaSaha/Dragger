package app.ceva.petapp.feature.ProductDetails;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;

import app.ceva.petapp.R;
import app.ceva.petapp.data.network.model.GetPet.GetPetsResponse;
import app.ceva.petapp.data.network.model.ProductDetails.ProductDetailsResponse;
import app.ceva.petapp.data.network.model.SubmitScheduling.SubmitSchedulingResponse;
import app.ceva.petapp.feature.Calendar.CalenderActivity;
import app.ceva.petapp.share.base.BaseActivity;
import app.ceva.petapp.share.wigeds.TextViewBold;
import app.ceva.petapp.share.wigeds.TextViewRegular;
import app.ceva.petapp.utils.L;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProductDetailsActivity extends BaseActivity implements ProductDetailsMvpView, View.OnClickListener {




    @BindView(R.id.tvDatePicker)
    TextView tvDatePicker;
    @BindView(R.id.pName)
    TextViewBold pName;
    @BindView(R.id.ivImage)
    ImageView ivImage;
    @BindView(R.id.productInformation)
    TextViewRegular productInformation;
    @BindView(R.id.pIndication)
    TextViewRegular pIndication;
    @BindView(R.id.pDirection)
    TextViewRegular pDirection;
    @BindView(R.id.rvPdf)
    RecyclerView rvPdf;
    @BindView(R.id.submit)
    FrameLayout submit;



    @BindView(R.id.spAnimaltype)
    AppCompatSpinner spAnimaltype;

    @Inject
    PdfListAdapter pdfListAdapter;

    ArrayList<String> spinnerArray = new ArrayList<String>();

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private String product_id,PetCatId,pet_id,Product_uniqye_id;
    private Context context;
    private ArrayList<String> pet_cat_name=new ArrayList<>();
    private ArrayList<String> pet_cat_id=new ArrayList<>();

    @Inject
    ProductDetailsPresenter<ProductDetailsMvpView> presenter;

    private Calendar calendar;
    boolean flag=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        context=this;
        setUnBinder(ButterKnife.bind(this));
        getActivityComponent().inject(this);
        setUp();
        setToolbar();

        showdate();
      //  setSpinner();
        setAdapter();
        getSpinnerSelectedId();

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

    protected void setUp()
    {
        presenter.onAttached(this);

    }

    private void getIntentData()
    {

        product_id=getIntent().getStringExtra("productId");
        Product_uniqye_id=getIntent().getStringExtra("productUniqueId");
      //  Toast.makeText(getApplicationContext(),product_id,Toast.LENGTH_SHORT).show();
        presenter.onGetProductDetaild(product_id,Product_uniqye_id);
        presenter.onGetAllPets("");

    }


    @Override
    protected void onResume() {
        super.onResume();
        getIntentData();

    }

    private void showdate()
    {
        final Calendar myCalendar = Calendar.getInstance();

        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                tvDatePicker.setText(sdf.format(myCalendar.getTime()));
            }

        };

        tvDatePicker.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(!flag) {
                    new DatePickerDialog(ProductDetailsActivity.this, date, myCalendar
                            .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                            myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                }
                else
                {
                    Bundle b=new Bundle();
                    b.putString("PetId",pet_id);
                    b.putString("ProductId",product_id);
                    b.putString("ProductUniqueId",Product_uniqye_id);
                    gotoNext(CalenderActivity.class,b);
                }
            }
        });





    }


    private void setAdapter()
    {
        rvPdf.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvPdf.setAdapter(pdfListAdapter);
        pdfListAdapter.setAdapterListner(new PdfListAdapter.PdfListListner() {
            @Override
            public void onItemClick(ProductDetailsResponse.ResponseDataBean.ProductPdfBean item, int position, String PetId) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(PetId));
                startActivity(browserIntent);
            }
        });
    }




    @Override
    public void successfullyPetList(GetPetsResponse response) {
        if(response.getResponseData().size()>0)
        {
            for(int i =0;i<response.getResponseData().size();i++)
            {
                GetPetsResponse.ResponseDataBean responseDataBean=response.getResponseData().get(i);
                pet_cat_name.add(responseDataBean.getName());
                pet_cat_id.add(responseDataBean.getPet_id());
            }
            spAnimaltype.setAdapter(new ArrayAdapter<String>(ProductDetailsActivity.this, android.R.layout.simple_spinner_dropdown_item, pet_cat_name));
        }
    }

    @Override
    public void successfullyGetProductDetails(ProductDetailsResponse productDetailsResponse) {

        pName.setText(productDetailsResponse.getResponseData().getProduct_name());
        Glide.with(context)
                .load(productDetailsResponse.getResponseData().getProduct_image())
                .placeholder(R.drawable.ic_launcher_background)
                .into(ivImage);

        productInformation.setText(productDetailsResponse.getResponseData().getProduct_info());
        pIndication.setText(productDetailsResponse.getResponseData().getProduct_indications());
        pDirection.setText(productDetailsResponse.getResponseData().getProduct_description());

        pdfListAdapter.loadList(productDetailsResponse.getResponseData().getProduct_pdf());

        if(productDetailsResponse.getResponseData().getDate().equals("")&&productDetailsResponse.getResponseData().getPet_id().equals(""))
        {
            submit.setVisibility(View.VISIBLE);
        }
        else
        {
            flag=true;
            submit.setVisibility(View.GONE);
            tvDatePicker.setText(productDetailsResponse.getResponseData().getDate());
            pet_id=productDetailsResponse.getResponseData().getPet_id();
            spAnimaltype.setEnabled(false);

            for(int i=0;i<pet_cat_id.size();i++)
            {
               // Toast.makeText(getApplicationContext(),productDetailsResponse.getResponseData().getPet_name()+"-"+pet_cat_name.get(i),Toast.LENGTH_SHORT).show();
                if(productDetailsResponse.getResponseData().getPet_id().equals(pet_cat_id.get(i)))
                {
                    spAnimaltype.setSelection((i));
                }
            }
        }



    }

    @Override
    public void successfullyGetSchedule(SubmitSchedulingResponse submitSchedulingResponse) {
        flag=true;
        presenter.onGetProductDetaild(product_id,Product_uniqye_id);

    }

    @OnClick(R.id.submit)
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.submit:
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                String date = format.format(Date.parse(tvDatePicker.getText().toString().trim()));
                presenter.onSubmitSchedule(PetCatId,product_id,Product_uniqye_id,date);
                break;
        }
    }


    private void getSpinnerSelectedId()
    {
        spAnimaltype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
    public void onBackPressed() {
        super.onBackPressed();

    }

}
