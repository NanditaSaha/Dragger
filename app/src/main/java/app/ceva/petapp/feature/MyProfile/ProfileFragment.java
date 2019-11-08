package app.ceva.petapp.feature.MyProfile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import app.ceva.petapp.R;
import app.ceva.petapp.data.network.model.ChangePassword.ChangePasswordResponse;
import app.ceva.petapp.data.network.model.GetProfile.GetProfileResponse;
import app.ceva.petapp.di.components.ActivityComponent;
import app.ceva.petapp.feature.EditProfile.EditProfileActivity;
import app.ceva.petapp.feature.SendOtp.SendOtpActivity;
import app.ceva.petapp.share.baseFragment.BaseFragment;
import app.ceva.petapp.share.wigeds.TextViewRegular;
import app.ceva.petapp.share.wigeds.TextViewSemibold;

public class ProfileFragment extends BaseFragment implements ProfileFragmentMvpView,View.OnClickListener,ChangePasswordDialog.byChangePasswordDialogInterface{
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;


    @BindView(R.id.ivEditProfile)
    ImageView ivEditProfile;

    @BindView(R.id.tvChangePhoneno)
    TextViewSemibold tvChangePhoneno;
    @BindView(R.id.tvChangePassword)
    TextViewSemibold tvChangePassword;


    @BindView(R.id.tvName)
    TextViewRegular tvName;
    @BindView(R.id.tvAddress)
    TextViewRegular tvAddress;
    @BindView(R.id.tvPhoneNumber)
    TextViewRegular tvPhoneNumber;
    @BindView(R.id.tvEmail)
    TextViewRegular tvEmail;
    @BindView(R.id.tvPassword)
    TextViewRegular tvPassword;
    @BindView(R.id.tvRegion)
    TextViewRegular tvRegion;

    private ChangePasswordDialog changePasswordDialog;

    @Inject
    ProfileFragmentPresenter<ProfileFragmentMvpView> presenter;

    public ProfileFragment() {
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



    private void setChangePasswordDialog()
    {
        changePasswordDialog=new ChangePasswordDialog();
        changePasswordDialog.setListner(new ChangePasswordDialog.byChangePasswordDialogInterface() {
            @Override
            public void sendChangePassword(String oldPassword, String newPassword) {

                presenter.onChangepassword(oldPassword,newPassword);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragmenmt_profile, container, false);
        setUnBinder(ButterKnife.bind(this,v));
        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
        }

        setUp(v);
        presenter.onAttach(this);
        presenter.onGetProfile();

        setChangePasswordDialog();
        return v;
    }



    @OnClick({R.id.ivEditProfile,R.id.tvChangePhoneno,R.id.tvChangePassword})
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.ivEditProfile:
                Intent i=new Intent(getActivity(), EditProfileActivity.class);
                getActivity().startActivity(i);
                break;
            case R.id.tvChangePhoneno:
                Intent ia=new Intent(getActivity(), SendOtpActivity.class);
                getActivity().startActivity(ia);
                break;
            case R.id.tvChangePassword:

                changePasswordDialog.show(getFragmentManager(),"byaddpets");
               // changePasswordDialog.setValue();
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void successfullyGetProfile(GetProfileResponse getProfileResponse) {

        if(getProfileResponse.getResponseCode()==1)
        {
            tvName.setText(getProfileResponse.getResponseData().getName());
            tvAddress.setText(getProfileResponse.getResponseData().getAddress());
            tvEmail.setText(getProfileResponse.getResponseData().getEmail());
            tvRegion.setText(getProfileResponse.getResponseData().getRegion());
            tvPhoneNumber.setText(getProfileResponse.getResponseData().getPhone());

        }
    }

    @Override
    public void successfullyChangepassword(ChangePasswordResponse changePasswordResponse) {


        if(changePasswordResponse.getResponseCode()==1)
        {
            Toast.makeText(getActivity(),changePasswordResponse.getResponseText(),Toast.LENGTH_SHORT).show();
        }else
        {
            Toast.makeText(getActivity(),changePasswordResponse.getResponseText(),Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void sendChangePassword(String oldPassword, String newPassword) {
            presenter.onChangepassword(oldPassword,newPassword);
    }
}
