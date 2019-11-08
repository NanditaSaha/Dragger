package app.ceva.petapp.feature.MyHistory;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import app.ceva.petapp.R;
import app.ceva.petapp.data.network.model.History.Historyresponse;
import app.ceva.petapp.share.wigeds.TextViewRegular;
import app.ceva.petapp.share.wigeds.TextViewSemibold;
import butterknife.BindView;
import butterknife.ButterKnife;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {


    private final ArrayList<Historyresponse.ResponseDataBean> mValues;


    private HistoryAdapter.HistoryListListner listListner;


     Context ctx;

    public HistoryAdapter(ArrayList<Historyresponse.ResponseDataBean> mValues) {
        this.mValues = mValues;
    }



    public void loadList(List<Historyresponse.ResponseDataBean> items) {
        mValues.clear();
        mValues.addAll(items);
        notifyDataSetChanged();
    }
    public interface HistoryListListner{
        void onItemClick(Historyresponse.ResponseDataBean item, int position, String PetId,String ProductId,String vacineUniqueId);
    }



    public void setAdapterListner(HistoryAdapter.HistoryListListner listner)
    {
        this.listListner=listner;
    }



    @NonNull
    @Override
    public HistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ctx = parent.getContext();
        View view = LayoutInflater.from( ctx)
                .inflate(R.layout.row_history, parent, false);
        return new HistoryAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        Historyresponse.ResponseDataBean mDataBean = mValues.get(position);

        holder.tvPetName.setText(mDataBean.getPet_name());
        holder.tvPetCatoeyName.setText(", "+mDataBean.getCategory_name());
        holder.tvProductName.setText(mDataBean.getProduct_name());
        holder.tvDate.setText(mDataBean.getSchedule_date());
        holder.petid.setText(mDataBean.getPet_id());
        holder.productid.setText(mDataBean.getProduct_id());
        holder.vacineUniqueId.setText(mDataBean.getProduct_uniqueId());


    }


    @Override
    public int getItemCount() {
        return mValues.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {



        @BindView(R.id.tvPetName)
        TextViewSemibold tvPetName;
        @BindView(R.id.tvPetCatoeyName)
        TextViewRegular tvPetCatoeyName;
        @BindView(R.id.tvProductName)
        TextViewRegular tvProductName;
        @BindView(R.id.tvDate)
        TextViewRegular tvDate;
        @BindView(R.id.productid)
        TextViewRegular productid;
        @BindView(R.id.petid)
        TextViewRegular petid;
        @BindView(R.id.vacineUniqueId)
        TextViewRegular vacineUniqueId;



        @BindView(R.id.llMain)
        LinearLayout llMain;





        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);



            llMain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listListner.onItemClick(mValues.get(getLayoutPosition()),getLayoutPosition(),petid.getText().toString().trim(),productid.getText().toString().trim(),vacineUniqueId.getText().toString().trim());
                }
            });
        }

    }
}
