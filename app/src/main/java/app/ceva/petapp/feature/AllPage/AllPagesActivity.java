package app.ceva.petapp.feature.AllPage;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.widget.Toolbar;

import javax.inject.Inject;

import app.ceva.petapp.R;
import app.ceva.petapp.data.network.model.Allpages.AllpagesResponse;
import app.ceva.petapp.share.base.BaseActivity;
import app.ceva.petapp.share.wigeds.TextViewBold;
import app.ceva.petapp.share.wigeds.TextViewRegular;
import butterknife.BindView;
import butterknife.ButterKnife;

public class AllPagesActivity extends BaseActivity implements AllPagesMvpView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @Inject
    AllpagePresenter<AllPagesMvpView> presenter;
    @BindView(R.id.tvtop)
    TextViewBold tvtop;
    @BindView(R.id.tvContent)
    TextViewRegular tvContent;
    private String tag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        setUnBinder(ButterKnife.bind(this));
        getActivityComponent().inject(this);
        setUp();
        setToolbar();
    }
    protected void setUp()
    {
        presenter.onAttached(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        getIntentData();
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

    private void getIntentData()
    {
        tag=getIntent().getStringExtra("tag");
        presenter.ongetContent(tag);
    }

    @Override
    public void onsuccessfullyGetAllPeg(AllpagesResponse allpagesResponse) {


        if(allpagesResponse.getResponseCode()==1)
        {
            tvContent.setText(allpagesResponse.getResponseData().getPage_data());
            tvtop.setText(allpagesResponse.getResponseData().getPage_name());
        }
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
}
