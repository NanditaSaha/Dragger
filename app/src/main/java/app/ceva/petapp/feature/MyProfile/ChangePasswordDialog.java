package app.ceva.petapp.feature.MyProfile;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatImageView;

import app.ceva.petapp.R;
import app.ceva.petapp.share.base.BaseDialog;
import app.ceva.petapp.utils.L;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChangePasswordDialog extends BaseDialog {
    Context mContext;

    @BindView(R.id.ivCross)
    AppCompatImageView ivCross;

    @BindView(R.id.etOldPassword)
    EditText etOldPassword;
    @BindView(R.id.etNewPassword)
    EditText etNewPassword;
    @BindView(R.id.etConfirmPassword)
    EditText etConfirmPassword;
    @BindView(R.id.changepassword)
    FrameLayout changepassword;

    public byChangePasswordDialogInterface dialogInterface;

    @Override
    protected void setUp(View view) {
        setUnBinder(ButterKnife.bind(this, view));
        mContext = getBaseActivity();



    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public  void setListner(ChangePasswordDialog.byChangePasswordDialogInterface listner)
    {
        this.dialogInterface=listner;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.layout_changepassword, container, false);
        setUp(v);

        return v;
    }
    public interface byChangePasswordDialogInterface {
        void sendChangePassword(String oldPassword,String newPassword);
    }

    @Override
    public void onResume() {
        super.onResume();
        L.e("onResume of dialoug");
        setValue();
    }

    @OnClick({R.id.ivCross,R.id.changepassword})
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.ivCross:
                dismiss();
                break;
            case R.id.changepassword:
                if(etOldPassword.getText().toString().equals(""))
                {
                    Toast.makeText(mContext,"Pls Enter Old Password",Toast.LENGTH_SHORT).show();
                }
                else if(etNewPassword.getText().toString().equals(""))
                {
                    Toast.makeText(mContext,"Pls Enter New Password",Toast.LENGTH_SHORT).show();
                }
                else if(etConfirmPassword.getText().toString().equals(""))
                {
                    Toast.makeText(mContext,"Pls Enter Confirm Password",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(!etNewPassword.getText().toString().trim().equals(etConfirmPassword.getText().toString().trim()))
                    {
                        Toast.makeText(mContext,"Password are not matched",Toast.LENGTH_SHORT).show();
                    }
                    else {

                        dialogInterface.sendChangePassword(etOldPassword.getText().toString().trim(), etNewPassword.getText().toString().trim());
                        dismiss();
                    }

                }


                break;

        }
    }


    public interface byChangePasswordDialogSetvalueInterface {
        void setValue();
    }


    public void setValue()
    {
        etConfirmPassword.setText("");
        etNewPassword.setText("");
        etOldPassword.setText("");
    }

}
