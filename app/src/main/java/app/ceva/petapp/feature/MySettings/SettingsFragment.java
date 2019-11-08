package app.ceva.petapp.feature.MySettings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatSpinner;

import java.util.ArrayList;

import javax.inject.Inject;

import app.ceva.petapp.R;
import app.ceva.petapp.data.network.model.AddSettings.AddsettingsResponse;
import app.ceva.petapp.data.network.model.FetchSettings.FetchSettingsResponse;
import app.ceva.petapp.di.components.ActivityComponent;
import app.ceva.petapp.share.baseFragment.BaseFragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingsFragment extends BaseFragment implements SettingsFragmentMvpView ,View.OnClickListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    @BindView(R.id.spNotificationType)
    AppCompatSpinner spNotificationType;
    @BindView(R.id.btnConfirmNotification)
    Button btnConfirmNotification;



    ArrayList<String> spinnerArray = new ArrayList<String>();
    ArrayList<String> spinnerArrayDate = new ArrayList<String>();


    @Inject
    SettingsFragmentPresenter<SettingsFragmentMvpView> presenter;

    int notificationDate;
    String reciveNotificationDate;

    public SettingsFragment() {
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_settings, container, false);
        setUnBinder(ButterKnife.bind(this,v));
        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
        }
        presenter.onAttach(this);
        setSpinner();
        getSpinnerSelectedId();
        setUp(v);
        presenter.ongetSettings();
        return v;
    }

    private void setSpinner()
    {

        spinnerArray.add("1 day before");
        spinnerArray.add("2 day before");
        spinnerArray.add("3 day before");

        spinnerArrayDate.add("1");
        spinnerArrayDate.add("2");
        spinnerArrayDate.add("3");

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>
                (getActivity(), android.R.layout.simple_spinner_item,
                        spinnerArray); //selected item will look like a spinner set from XML
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout
                .simple_spinner_dropdown_item);
        spNotificationType.setAdapter(spinnerArrayAdapter);
    }

    @Override
    public void successfullySubmitSettings(AddsettingsResponse addsettingsResponse) {

       // L.e("SubmitSettings");
        Toast.makeText(getActivity(),addsettingsResponse.getResponseText(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void successfullyGetSettings(FetchSettingsResponse fetchSettingsResponse) {

        if(fetchSettingsResponse.getResponseCode()==1)
        {
            reciveNotificationDate=fetchSettingsResponse.getResponseData().getNotify_before();

            for(int i =0;i< spinnerArrayDate.size();i++)
            {
                if (fetchSettingsResponse.getResponseData().getNotify_before().equals(spinnerArrayDate.get(i))) {
                    spNotificationType.setSelection(i);
                }
            }
        }

    }

    private void getSpinnerSelectedId()
    {
        spNotificationType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                notificationDate=position+1;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @OnClick(R.id.btnConfirmNotification)
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.btnConfirmNotification:
                presenter.onSubmitSettings(notificationDate+"");
                break;

        }
    }
}
