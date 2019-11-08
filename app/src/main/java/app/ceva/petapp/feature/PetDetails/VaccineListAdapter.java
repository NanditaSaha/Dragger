package app.ceva.petapp.feature.PetDetails;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import app.ceva.petapp.R;
import app.ceva.petapp.data.network.model.GetPetDetails.GetPetDetailsResponse;
import app.ceva.petapp.share.wigeds.TextViewSemibold;
import butterknife.BindView;
import butterknife.ButterKnife;

public class VaccineListAdapter extends RecyclerView.Adapter<VaccineListAdapter.ViewHolder> {


    private final ArrayList<GetPetDetailsResponse.ResponseDataBean.VaccineListBean> mValues;


    private VaccineListAdapter.VaccineListListner listListner;


     Context ctx;

    public VaccineListAdapter(ArrayList<GetPetDetailsResponse.ResponseDataBean.VaccineListBean> mValues) {
        this.mValues = mValues;
    }



    public void loadList(List<GetPetDetailsResponse.ResponseDataBean.VaccineListBean> items) {
        mValues.clear();
        mValues.addAll(items);
        notifyDataSetChanged();
    }
    public interface VaccineListListner{
        void onItemClick(GetPetDetailsResponse.ResponseDataBean.VaccineListBean item, int position, String VId,String UniqueId);
    }


    public void setAdapterListner(VaccineListAdapter.VaccineListListner listner)
    {
        this.listListner=listner;
    }


    @NonNull
    @Override
    public VaccineListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ctx = parent.getContext();
        View view = LayoutInflater.from( ctx)
                .inflate(R.layout.row_vaccine_item, parent, false);
        return new VaccineListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        GetPetDetailsResponse.ResponseDataBean.VaccineListBean mDataBean = mValues.get(position);

        holder.tvVName.setText(mDataBean.getName());
        holder.tvVId.setText(mDataBean.getId());
        holder.tvUniqueId.setText(mDataBean.getProduct_uniqueId());


        if(position%2==0)
        {
            holder.rlMain.setBackgroundColor(ctx.getResources().getColor(R.color.colorDeamWhite));
        }
        else
        {
            holder.rlMain.setBackgroundColor(ctx.getResources().getColor(R.color.colorWhite));
        }





    }


    @Override
    public int getItemCount() {
        return mValues.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {








        @BindView(R.id.rlMain)
        RelativeLayout rlMain;
        @BindView(R.id.tvVName)
        TextViewSemibold tvVName;
        @BindView(R.id.tvVId)
        TextViewSemibold tvVId;
        @BindView(R.id.tvUniqueId)
        TextViewSemibold tvUniqueId;




        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);



            rlMain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listListner.onItemClick(mValues.get(getLayoutPosition()),getLayoutPosition(),tvVId.getText().toString().trim(),tvUniqueId.getText().toString().trim());
                }
            });
        }

    }
}
