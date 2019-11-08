package app.ceva.petapp.feature.Dashboard;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;

import app.ceva.petapp.R;
import app.ceva.petapp.data.network.model.ScanQr.ScanQrResponse;
import app.ceva.petapp.feature.AllPage.AllPagesActivity;
import app.ceva.petapp.feature.Login.LoginActivity;
import app.ceva.petapp.feature.MyHistory.HistoryFragments;
import app.ceva.petapp.feature.MyPets.HomeFragment;
import app.ceva.petapp.feature.MyProfile.ProfileFragment;
import app.ceva.petapp.feature.MyQrScan.ScanResultReceiver;
import app.ceva.petapp.feature.MySettings.SettingsFragment;
import app.ceva.petapp.feature.ProductDetails.ProductDetailsActivity;
import app.ceva.petapp.feature.Scaner.ScannerActivity;
import app.ceva.petapp.share.base.BaseActivity;
import app.ceva.petapp.share.wigeds.TextViewRegular;
import app.ceva.petapp.utils.BadgeDrawable;
import app.ceva.petapp.utils.L;
import app.ceva.petapp.utils.NoScanResultException;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DashboardActivity extends BaseActivity implements DashboardMvpView, View.OnClickListener, ScanResultReceiver {
    private TextView mTextMessage;

    @BindView(R.id.menu_pets)
    LinearLayout menu_pets;
    @BindView(R.id.ivPet)
    AppCompatImageView ivPet;
    @BindView(R.id.tvPet)
    TextViewRegular tvPet;
    @BindView(R.id.vPet)
    View vPet;
    @BindView(R.id.menu_history)
    LinearLayout menu_history;
    @BindView(R.id.ivHistory)
    AppCompatImageView ivHistory;
    @BindView(R.id.tvHistory)
    TextViewRegular tvHistory;
    @BindView(R.id.vHistory)
    View vHistory;
    @BindView(R.id.menu_settings)
    LinearLayout menu_settings;
    @BindView(R.id.ivSettings)
    AppCompatImageView ivSettings;
    @BindView(R.id.tvSettings)
    TextViewRegular tvSettings;
    @BindView(R.id.vSettings)
    View vSettings;
    @BindView(R.id.menu_profile)
    LinearLayout menu_profile;
    @BindView(R.id.ivProfile)
    AppCompatImageView ivProfile;
    @BindView(R.id.tvProfile)
    TextViewRegular tvProfile;
    @BindView(R.id.vProfile)
    View vProfile;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fbQrScan)
    FloatingActionButton fbQrScan;

    private Fragment fragment;

    private String codeContent,codeFormat;

    private IntentIntegrator qrScan;


    @Inject
    DashboardPresenter<DashboardMvpView> presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        setUnBinder(ButterKnife.bind(this));
        getActivityComponent().inject(this);
        setUp();
        setToolbar();
        setHomeFragment();

    }

    protected void setUp() {
        presenter.onAttached(this);
        qrScan = new IntentIntegrator(this);


    }

    private void setToolbar() {

        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(0xFFFFFFFF);
        //  toolbar.setNavigationIcon(R.drawable.ic_left_arrow);
        getSupportActionBar().setTitle("Ceva");

    }
    private void setHomeFragment()
    {

        vPet.setVisibility(View.VISIBLE);
        vHistory.setVisibility(View.GONE);
        vSettings.setVisibility(View.GONE);
        vProfile.setVisibility(View.GONE);
        ivPet.setImageDrawable(getResources().getDrawable(R.drawable.ic_paw));
        ivHistory.setImageDrawable(getResources().getDrawable(R.drawable.ic_time_white));
        ivProfile.setImageDrawable(getResources().getDrawable(R.drawable.ic_user_white));
        ivSettings.setImageDrawable(getResources().getDrawable(R.drawable.ic_nut_icon_white));
        fragment=new HomeFragment();
        loadFragment(fragment);
    }

    @OnClick({R.id.menu_pets, R.id.menu_history, R.id.menu_settings, R.id.menu_profile,R.id.fbQrScan})
    public void onClick(View v) {
        fragment=null;
        switch (v.getId()) {
            case R.id.menu_pets:
                vPet.setVisibility(View.VISIBLE);
                vHistory.setVisibility(View.GONE);
                vSettings.setVisibility(View.GONE);
                vProfile.setVisibility(View.GONE);
                ivPet.setImageDrawable(getResources().getDrawable(R.drawable.ic_paw));
                ivHistory.setImageDrawable(getResources().getDrawable(R.drawable.ic_time_white));
                ivProfile.setImageDrawable(getResources().getDrawable(R.drawable.ic_user_white));
                ivSettings.setImageDrawable(getResources().getDrawable(R.drawable.ic_nut_icon_white));
                fragment=new HomeFragment();

                break;
            case R.id.menu_history:
                vHistory.setVisibility(View.VISIBLE);
                vPet.setVisibility(View.GONE);
                vSettings.setVisibility(View.GONE);
                vProfile.setVisibility(View.GONE);
                ivPet.setImageDrawable(getResources().getDrawable(R.drawable.ic_paw_white));
                ivHistory.setImageDrawable(getResources().getDrawable(R.drawable.ic_time));
                ivProfile.setImageDrawable(getResources().getDrawable(R.drawable.ic_user_white));
                ivSettings.setImageDrawable(getResources().getDrawable(R.drawable.ic_nut_icon_white));
                fragment=new HistoryFragments();
                break;
            case R.id.menu_settings:
                vSettings.setVisibility(View.VISIBLE);
                vHistory.setVisibility(View.GONE);
                vPet.setVisibility(View.GONE);
                vProfile.setVisibility(View.GONE);
                ivPet.setImageDrawable(getResources().getDrawable(R.drawable.ic_paw_white));
                ivHistory.setImageDrawable(getResources().getDrawable(R.drawable.ic_time_white));
                ivProfile.setImageDrawable(getResources().getDrawable(R.drawable.ic_user_white));
                ivSettings.setImageDrawable(getResources().getDrawable(R.drawable.ic_nut_icon));
                fragment=new SettingsFragment();
                break;
            case R.id.menu_profile:
                vProfile.setVisibility(View.VISIBLE);
                vPet.setVisibility(View.GONE);
                vHistory.setVisibility(View.GONE);
                vSettings.setVisibility(View.GONE);
                ivPet.setImageDrawable(getResources().getDrawable(R.drawable.ic_paw_white));
                ivHistory.setImageDrawable(getResources().getDrawable(R.drawable.ic_time_white));
                ivProfile.setImageDrawable(getResources().getDrawable(R.drawable.ic_user_blue));
                ivSettings.setImageDrawable(getResources().getDrawable(R.drawable.ic_nut_icon_white));
                fragment=new ProfileFragment();
                break;

            case R.id.fbQrScan:
                vProfile.setVisibility(View.INVISIBLE);
                vPet.setVisibility(View.INVISIBLE);
                vHistory.setVisibility(View.INVISIBLE);
                vSettings.setVisibility(View.INVISIBLE);
                ivPet.setImageDrawable(getResources().getDrawable(R.drawable.ic_paw_white));
                ivHistory.setImageDrawable(getResources().getDrawable(R.drawable.ic_time_white));
                ivProfile.setImageDrawable(getResources().getDrawable(R.drawable.ic_user_white));
                ivSettings.setImageDrawable(getResources().getDrawable(R.drawable.ic_nut_icon_white));
              //  fragment=new QrscanFragment();


                new IntentIntegrator(DashboardActivity.this).setCaptureActivity(ScannerActivity.class).initiateScan();



                break;

        }
        loadFragment(fragment);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem itemCart = menu.findItem(R.id.action_cart);
        LayerDrawable icon = (LayerDrawable) itemCart.getIcon();
        setBadgeCount(this, icon, "9");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.contactus:

                Bundle b=new Bundle();
                b.putString("tag","contact-us");
                gotoNext(AllPagesActivity.class,b);
                return true;
            case R.id.aboutus:
                Bundle bc=new Bundle();
                bc.putString("tag","about-us");
                gotoNext(AllPagesActivity.class,bc);
                return true;
            case R.id.privacypolicy:
                Bundle bb=new Bundle();
                bb.putString("tag","privacy-policy");
                gotoNext(AllPagesActivity.class,bb);
                return true;
            case R.id.logout:

                presenter.onLogoutClick();

                return true;
            case R.id.tearms:
                Bundle ba=new Bundle();
                ba.putString("tag","terms-and-condition");
                gotoNext(AllPagesActivity.class,ba);
                return true;
            case R.id.action_cart:
                    Toast.makeText(getApplicationContext(),"Comming Soon ",Toast.LENGTH_SHORT).show();
              //  gotoNext(NotificationActivity.class,null);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content_frame, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    public void scanResultData(String codeFormat, String codeContent) {
        Toast toast = Toast.makeText(this,codeFormat+""+codeContent, Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public void scanResultData(NoScanResultException noScanData) {

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            //if qrcode has nothing in it
            if (result.getContents() == null) {
                Toast.makeText(this, "Result Not Found", Toast.LENGTH_LONG).show();
            } else {

               // Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();

                L.e(result.getContents());
                checkValidQr(result.getContents().toString());


            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }


    private void checkValidQr(String data)
    {
        L.e(data);
        try {

            JSONObject obj = new JSONObject(data);
             if(obj.has("product_id")&&obj.has("product_uniqueId")&&obj.has("region_id"))
             {

                 presenter.onScanQr(obj.getString("region_id"),obj.getString("product_id"),obj.getString("product_uniqueId"));
                // Toast.makeText(this, "true", Toast.LENGTH_LONG).show();
             }

        } catch (JSONException e) {
            e.printStackTrace();

            Toast.makeText(this, "QR code is not valid", Toast.LENGTH_LONG).show();

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        setHomeFragment();
    }

    public static void setBadgeCount(Context context, LayerDrawable icon, String count) {

        BadgeDrawable badge;

        // Reuse drawable if possible
        Drawable reuse = icon.findDrawableByLayerId(R.id.ic_badge);
        if (reuse != null && reuse instanceof BadgeDrawable) {
            badge = (BadgeDrawable) reuse;
        } else {
            badge = new BadgeDrawable(context);
        }

        badge.setCount(count);
        icon.mutate();
        icon.setDrawableByLayerId(R.id.ic_badge, badge);
    }

    @Override
    public void navigateToLogout() {
        gotoNextWithfinish(LoginActivity.class,null);
    }

    @Override
    public void onSuccessfullScanQr(ScanQrResponse scanQrResponse) {

        Bundle b=new Bundle();
        b.putString("productId",scanQrResponse.getProduct_id());
        b.putString("productUniqueId",scanQrResponse.getProduct_uniqueId());
        gotoNext(ProductDetailsActivity.class,b);

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
            finish();
    }
}
