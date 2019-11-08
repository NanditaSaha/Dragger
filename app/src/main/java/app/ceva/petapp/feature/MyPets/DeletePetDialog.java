package app.ceva.petapp.feature.MyPets;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.appcompat.widget.AppCompatImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import app.ceva.petapp.R;
import app.ceva.petapp.share.base.BaseDialog;
import app.ceva.petapp.share.wigeds.TextViewBold;

public class DeletePetDialog extends BaseDialog {
    Context mContext;

    @BindView(R.id.ivCross)
    AppCompatImageView ivCross;


    @BindView(R.id.btnYes)
    TextViewBold btnYes;
    @BindView(R.id.btnNo)
    TextViewBold btnNo;
    @BindView(R.id.rlmain)
    RelativeLayout rlmain;


    public byDeletePetInterface bydeletePetInterface;
    @Override
    protected void setUp(View view) {
        setUnBinder(ButterKnife.bind(this, view));

    }


     public  void setListner(byDeletePetInterface listner)
     {
         this.bydeletePetInterface=listner;
     }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.layout_delete_pet, container, false);
        setUp(v);
        return v;
    }
    public interface byDeletePetInterface {
        void sendDeletePet(String result);
    }

    @OnClick({R.id.ivCross,R.id.btnYes,R.id.btnNo,R.id.rlmain})
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.ivCross:
                dismiss();
                break;

            case R.id.btnYes:

                bydeletePetInterface.sendDeletePet("yes");
                dismiss();
                break;
            case R.id.btnNo:
                dismiss();
                break;
            case R.id.rlmain:
                dismiss();
                break;

        }
    }




}
