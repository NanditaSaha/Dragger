package app.ceva.petapp.feature.Login;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatSpinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import app.ceva.petapp.R;
import app.ceva.petapp.share.base.BaseDialog;
import app.ceva.petapp.utils.L;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ForgetPasswordDialog extends BaseDialog {
    Context mContext;

    @BindView(R.id.ivCross)
    AppCompatImageView ivCross;

    @BindView(R.id.etPhoneNumber)
    EditText etPhoneNumber;

    @BindView(R.id.forgetpwd)
    FrameLayout forgetpwd;
    @BindView(R.id.spCountryCode)
    AppCompatSpinner spCountryCode;

    private ArrayList<String> countrycode_list=new ArrayList<>();

    private byForgetPasswordDialogInterface forgetPasswordDialogInterface;

    private String countrycode;

    @Override
    protected void setUp(View view) {
        setUnBinder(ButterKnife.bind(this, view));
        mContext = getBaseActivity();
        setEditText();

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.layout_forgetpassword, container, false);
        setUp(v);
        setCountryCodeAdapter();
        getSpinnerSelectedId();
        return v;
    }
    public interface byForgetPasswordDialogInterface {
        void sendforgetPassword(String phoneNumber);
    }

    public void setAdapter(byForgetPasswordDialogInterface listner)
    {
        forgetPasswordDialogInterface=listner;
    }

    @OnClick({R.id.ivCross,R.id.forgetpwd})
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.ivCross:
                dismiss();
                break;
            case R.id.forgetpwd:
                forgetPasswordDialogInterface.sendforgetPassword(countrycode+etPhoneNumber.getText().toString().trim());
                dismiss();
                break;

        }
    }


    private void setEditText()
    {
//        etPhoneNumber.setText("");
//        Selection.setSelection(etPhoneNumber.getText(), etPhoneNumber.getText().length());
//        etPhoneNumber.addTextChangedListener(new TextWatcher() {
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                // TODO Auto-generated method stub
//
//            }
//
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count,
//                                          int after) {
//                // TODO Auto-generated method stub
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                if(!s.toString().startsWith("")){
//                    etPhoneNumber.setText("");
//                    Selection.setSelection(etPhoneNumber.getText(), etPhoneNumber.getText().length());
//
//                }
//
//            }
//        });
    }



    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = mContext.getAssets().open("countrycodes.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public void setCountryCodeAdapter()
    {
        try {
            JSONArray obj_array = new JSONArray(loadJSONFromAsset());



            for (int i = 0; i < obj_array.length(); i++) {
                JSONObject jo_inside = obj_array.getJSONObject(i);
                countrycode_list.add(jo_inside.getString("dial_code"));

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        spCountryCode.setAdapter(new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_dropdown_item, countrycode_list));
    }


    private void getSpinnerSelectedId()
    {
        spCountryCode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                countrycode=countrycode_list.get(position);
                L.e("Id Pet=="+countrycode);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

}
