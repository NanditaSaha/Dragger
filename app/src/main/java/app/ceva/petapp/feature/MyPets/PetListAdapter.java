package app.ceva.petapp.feature.MyPets;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import app.ceva.petapp.R;
import app.ceva.petapp.data.network.model.GetPet.GetPetsResponse;
import app.ceva.petapp.share.wigeds.TextViewSemibold;

public class PetListAdapter extends RecyclerView.Adapter<PetListAdapter.ViewHolder> {


    private final ArrayList<GetPetsResponse.ResponseDataBean> mValues;


    private PetListAdapter.PetListListner listListner;
    private PetListAdapter.PetEditListner petEditListner;
    private PetListAdapter.PetDeleteListner petDeleteListner;

     Context ctx;

    public PetListAdapter(ArrayList<GetPetsResponse.ResponseDataBean> mValues) {
        this.mValues = mValues;
    }



    public void loadList(List<GetPetsResponse.ResponseDataBean> items) {
        mValues.clear();
        mValues.addAll(items);
        notifyDataSetChanged();
    }
    public interface PetListListner{
        void onItemClick(GetPetsResponse.ResponseDataBean item, int position, String PetId);
    }
    public interface PetEditListner{
        void onEditPetClick(GetPetsResponse.ResponseDataBean item, int position, String PetId);
    }

    public interface PetDeleteListner{
        void onDeletePetClick(GetPetsResponse.ResponseDataBean item, int position, String PetId);
    }


    public void setAdapterListner(PetListAdapter.PetListListner listner)
    {
        this.listListner=listner;
    }
    public void setEditAdapterListner(PetListAdapter.PetEditListner listner)
    {
        this.petEditListner=listner;
    }
    public void setDeleteAdapterListner(PetListAdapter.PetDeleteListner listner)
    {
        this.petDeleteListner=listner;
    }


    @NonNull
    @Override
    public PetListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ctx = parent.getContext();
        View view = LayoutInflater.from( ctx)
                .inflate(R.layout.row_my_pets, parent, false);
        return new PetListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        GetPetsResponse.ResponseDataBean mDataBean = mValues.get(position);

        holder.tvPetName.setText(mDataBean.getName());
        holder.tvPetCategory.setText(", "+mDataBean.getCategory_name());
        holder.tvCatId.setText(mDataBean.getCategory_id());
        holder.tvPetId.setText(mDataBean.getPet_id());

        if(position%2==0)
        {
            holder.rlMain.setBackgroundColor(ctx.getResources().getColor(R.color.colorDeamWhite));
        }
        else
        {
            holder.rlMain.setBackgroundColor(ctx.getResources().getColor(R.color.colorWhite));
        }


        holder.ivdeletePet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                petDeleteListner.onDeletePetClick(mDataBean,position,holder.tvPetId.getText().toString().trim());
            }
        });
        holder.ivEditPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                petEditListner.onEditPetClick(mDataBean,position,holder.tvPetId.getText().toString().trim());
            }
        });


    }


    @Override
    public int getItemCount() {
        return mValues.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {



        @BindView(R.id.tvPetName)
        TextViewSemibold tvPetName;
        @BindView(R.id.tvPetCategory)
        TextViewSemibold tvPetCategory;
        @BindView(R.id.tvPetId)
        TextViewSemibold tvPetId;
        @BindView(R.id.tvCatId)
        TextViewSemibold tvCatId;


        @BindView(R.id.ivEditPet)
        ImageView ivEditPet;
        @BindView(R.id.rlMain)
        RelativeLayout rlMain;
        @BindView(R.id.ivdeletePet)
        ImageView ivdeletePet;




        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);



            rlMain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listListner.onItemClick(mValues.get(getLayoutPosition()),getLayoutPosition(),tvPetId.getText().toString().trim());
                }
            });
        }

    }
}
