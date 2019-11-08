package app.ceva.petapp.feature.MyQrScan;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.google.zxing.integration.android.IntentIntegrator;
import com.journeyapps.barcodescanner.CaptureManager;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;

import app.ceva.petapp.R;
import app.ceva.petapp.di.components.ActivityComponent;
import app.ceva.petapp.share.baseFragment.BaseFragment;
import butterknife.ButterKnife;

public class QrscanFragment extends BaseFragment implements QrscanFragmentMvpView ,
        DecoratedBarcodeView.TorchListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private CaptureManager capture;
    private DecoratedBarcodeView barcodeScannerView;
    private Button switchFlashlightButton;
    private boolean isFlashLightOn = false;





    private Fragment fragment;

    private String mParam1;
    private String mParam2;

    private IntentIntegrator qrScan;
    private String codeContent,codeFormat;

    public QrscanFragment() {
        // Required empty public constructor
    }


    @Override
    protected void setUp(View view) {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        qrScan = new IntentIntegrator(getActivity());
        qrScan.initiateScan();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_qrscan, container, false);
        setUnBinder(ButterKnife.bind(this,v));
        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
        }

        setUp(v);

        barcodeScannerView = v.findViewById(R.id.zxing_barcode_scanner);
        barcodeScannerView.setTorchListener(this);

        switchFlashlightButton = (Button)v. findViewById(R.id.switch_flashlight);



        //start capture
        capture = new CaptureManager(getActivity(), barcodeScannerView);
        capture.initializeFromIntent(getActivity().getIntent(), savedInstanceState);
        capture.decode();
        return v;
    }


    @Override
    public void onTorchOn() {

    }

    @Override
    public void onTorchOff() {

    }


    @Override
    public void onResume() {
        super.onResume();
        capture.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        capture.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        capture.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        capture.onSaveInstanceState(outState);
    }


    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return barcodeScannerView.onKeyDown(keyCode, event);
    }
}
