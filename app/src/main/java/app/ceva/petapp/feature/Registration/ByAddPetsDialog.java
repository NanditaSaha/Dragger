package app.ceva.petapp.feature.Registration;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.AppCompatImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import app.ceva.petapp.R;
import app.ceva.petapp.share.base.BaseDialog;
import app.ceva.petapp.share.wigeds.TextViewBold;

public class ByAddPetsDialog extends BaseDialog {
    Context mContext;

    @BindView(R.id.ivCross)
    AppCompatImageView ivCross;

    @BindView(R.id.ivAdd)
    AppCompatImageView ivAdd;

    @BindView(R.id.tvskip)
    TextViewBold tvskip;


    @Override
    protected void setUp(View view) {
        setUnBinder(ButterKnife.bind(this, view));
        mContext = getBaseActivity();

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.layout_add_pets, container, false);
        setUp(v);
        return v;
    }
    public interface byAddPetDialogInterface {
        void sendAddPetsBy(boolean low, boolean high);
    }

    @OnClick({R.id.ivCross,R.id.ivAdd,R.id.tvskip})
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.ivCross:
                dismiss();
                break;

            case R.id.ivAdd:
                ByAddPetsDialog.byAddPetDialogInterface interfaceAddPet = (ByAddPetsDialog.byAddPetDialogInterface) getActivity();
                if (interfaceAddPet != null) {
                    dismiss();
                    interfaceAddPet.sendAddPetsBy(true,false);
                }
                break;

                case R.id.tvskip:
                    ByAddPetsDialog.byAddPetDialogInterface interfaceAddPets = (ByAddPetsDialog.byAddPetDialogInterface) getActivity();
                    if (interfaceAddPets != null) {
                        dismiss();
                        interfaceAddPets.sendAddPetsBy(false,true);
                    }
                    break;
        }
    }


}
