package app.ceva.petapp.feature.Scaner;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.journeyapps.barcodescanner.CaptureManager;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;

import app.ceva.petapp.R;
import app.ceva.petapp.feature.AllPage.AllPagesActivity;
import app.ceva.petapp.feature.Dashboard.DashboardActivity;
import app.ceva.petapp.feature.Login.LoginActivity;
import app.ceva.petapp.feature.MyPets.HomeFragment;
import app.ceva.petapp.feature.Notification.NotificationActivity;
import app.ceva.petapp.share.wigeds.TextViewRegular;
import app.ceva.petapp.utils.BadgeDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ScannerActivity extends AppCompatActivity implements
        DecoratedBarcodeView.TorchListener,View.OnClickListener {
    private CaptureManager capture;
    private DecoratedBarcodeView barcodeScannerView;
    private Button switchFlashlightButton;
    private boolean isFlashLightOn = false;

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
    @BindView(R.id.ivPet)
    AppCompatImageView ivPet;
    @BindView(R.id.tvPet)
    TextViewRegular tvPet;
    @BindView(R.id.vPet)
    View vPet;

    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);



        toolbar.setTitleTextColor(0xFFFFFFFF);
        getSupportActionBar().setTitle("Ceva");

        barcodeScannerView = findViewById(R.id.zxing_barcode_scanner);
        barcodeScannerView.setTorchListener(this);

        switchFlashlightButton = (Button) findViewById(R.id.switch_flashlight);

        if (!hasFlash()) {
            switchFlashlightButton.setVisibility(View.GONE);
        } else {
            switchFlashlightButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    switchFlashlight();
                }
            });
        }

        //start capture
        capture = new CaptureManager(this, barcodeScannerView);
        capture.initializeFromIntent(getIntent(), savedInstanceState);
        capture.decode();
      //  setHomeFragment();

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


                Intent i =new Intent(getApplicationContext(),AllPagesActivity.class);
                i.putExtra("tag","contact-us");
                startActivity(i);
                finish();
                return true;
            case R.id.aboutus:

                Intent ia =new Intent(getApplicationContext(),AllPagesActivity.class);
                ia.putExtra("tag","about-us");
                startActivity(ia);
                finish();
                return true;
            case R.id.privacypolicy:

                Intent ib =new Intent(getApplicationContext(),AllPagesActivity.class);
                ib.putExtra("tag","privacy-policy");
                startActivity(ib);
                finish();
                return true;
            case R.id.logout:

                Intent il =new Intent(getApplicationContext(), LoginActivity.class);
                il.putExtra("tag","terms-and-condition");
                startActivity(il);
                finish();

                return true;
            case R.id.tearms:

                Intent ic =new Intent(getApplicationContext(),AllPagesActivity.class);
                ic.putExtra("tag","terms-and-condition");
                startActivity(ic);
                finish();
                return true;
            case R.id.action_cart:
                Intent iq =new Intent(getApplicationContext(),NotificationActivity.class);
                iq.putExtra("tag","terms-and-condition");
                startActivity(iq);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
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
       // loadFragment(fragment);
    }

    /**
     * Check if the device's camera has a Flashlight.
     *
     * @return true if there is Flashlight, otherwise false.
     */
    private boolean hasFlash() {
        return getApplicationContext().getPackageManager()
                .hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
    }

    public void switchFlashlight() {
        if (isFlashLightOn) {
            barcodeScannerView.setTorchOff();
            isFlashLightOn = false;
        } else {
            barcodeScannerView.setTorchOn();
            isFlashLightOn = true;
        }

    }

    @Override
    public void onTorchOn() {
        switchFlashlightButton.setText("off");
    }

    @Override
    public void onTorchOff() {
        switchFlashlightButton.setText("on");
    }

    @Override
    protected void onResume() {
        super.onResume();
        capture.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        capture.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        capture.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        capture.onSaveInstanceState(outState);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return barcodeScannerView.onKeyDown(keyCode, event) || super.onKeyDown(keyCode, event);
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

    @OnClick({R.id.menu_pets, R.id.menu_history, R.id.menu_settings, R.id.menu_profile,R.id.fbQrScan})
    public void onClick(View v) {
        fragment=null;
        switch (v.getId()) {
            case R.id.menu_pets:
//                vPet.setVisibility(View.VISIBLE);
//                vHistory.setVisibility(View.GONE);
//                vSettings.setVisibility(View.GONE);
//                vProfile.setVisibility(View.GONE);
//                ivPet.setImageDrawable(getResources().getDrawable(R.drawable.ic_paw));
//                ivHistory.setImageDrawable(getResources().getDrawable(R.drawable.ic_time_white));
//                ivProfile.setImageDrawable(getResources().getDrawable(R.drawable.ic_user_white));
//                ivSettings.setImageDrawable(getResources().getDrawable(R.drawable.ic_nut_icon_white));
//                fragment=new HomeFragment();

                Intent i=new Intent( this, DashboardActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                finish();

                break;
            case R.id.menu_history:
//                vHistory.setVisibility(View.VISIBLE);
//                vPet.setVisibility(View.GONE);
//                vSettings.setVisibility(View.GONE);
//                vProfile.setVisibility(View.GONE);
//                ivPet.setImageDrawable(getResources().getDrawable(R.drawable.ic_paw_white));
//                ivHistory.setImageDrawable(getResources().getDrawable(R.drawable.ic_time));
//                ivProfile.setImageDrawable(getResources().getDrawable(R.drawable.ic_user_white));
//                ivSettings.setImageDrawable(getResources().getDrawable(R.drawable.ic_nut_icon_white));
//                fragment=new HistoryFragments();
                Intent ih=new Intent( this, DashboardActivity.class);
                ih.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(ih);
                finish();
                break;
            case R.id.menu_settings:
//                vSettings.setVisibility(View.VISIBLE);
//                vHistory.setVisibility(View.GONE);
//                vPet.setVisibility(View.GONE);
//                vProfile.setVisibility(View.GONE);
//                ivPet.setImageDrawable(getResources().getDrawable(R.drawable.ic_paw_white));
//                ivHistory.setImageDrawable(getResources().getDrawable(R.drawable.ic_time_white));
//                ivProfile.setImageDrawable(getResources().getDrawable(R.drawable.ic_user_white));
//                ivSettings.setImageDrawable(getResources().getDrawable(R.drawable.ic_nut_icon));
//                fragment=new SettingsFragment();
                Intent is=new Intent( this, DashboardActivity.class);
                is.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(is);
                finish();
                break;
            case R.id.menu_profile:
//                vProfile.setVisibility(View.VISIBLE);
//                vPet.setVisibility(View.GONE);
//                vHistory.setVisibility(View.GONE);
//                vSettings.setVisibility(View.GONE);
//                ivPet.setImageDrawable(getResources().getDrawable(R.drawable.ic_paw_white));
//                ivHistory.setImageDrawable(getResources().getDrawable(R.drawable.ic_time_white));
//                ivProfile.setImageDrawable(getResources().getDrawable(R.drawable.ic_user_blue));
//                ivSettings.setImageDrawable(getResources().getDrawable(R.drawable.ic_nut_icon_white));
//                fragment=new ProfileFragment();
                Intent ip=new Intent( this, DashboardActivity.class);
                ip.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(ip);
                finish();
                break;

        }
        loadFragment(fragment);
    }
}
