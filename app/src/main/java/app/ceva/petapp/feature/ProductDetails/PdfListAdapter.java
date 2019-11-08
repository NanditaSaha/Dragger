package app.ceva.petapp.feature.ProductDetails;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import app.ceva.petapp.R;
import app.ceva.petapp.data.network.model.ProductDetails.ProductDetailsResponse;
import butterknife.BindView;
import butterknife.ButterKnife;

public class PdfListAdapter extends RecyclerView.Adapter<PdfListAdapter.ViewHolder> {


    private final ArrayList<ProductDetailsResponse.ResponseDataBean.ProductPdfBean> mValues;


    private PdfListListner listListner;


     Context ctx;

    public PdfListAdapter(ArrayList<ProductDetailsResponse.ResponseDataBean.ProductPdfBean> mValues) {
        this.mValues = mValues;
    }



    public void loadList(List<ProductDetailsResponse.ResponseDataBean.ProductPdfBean> items) {
        mValues.clear();
        mValues.addAll(items);
        notifyDataSetChanged();
    }
    public interface PdfListListner {
        void onItemClick(ProductDetailsResponse.ResponseDataBean.ProductPdfBean item, int position, String PetId);
    }


    public void setAdapterListner(PdfListListner listner)
    {
        this.listListner=listner;
    }


    @NonNull
    @Override
    public PdfListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ctx = parent.getContext();
        View view = LayoutInflater.from( ctx)
                .inflate(R.layout.layout_pdf_list, parent, false);
        return new PdfListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        ProductDetailsResponse.ResponseDataBean.ProductPdfBean mDataBean = mValues.get(position);

        holder.tvPdfLink.setText(mDataBean.getImage());



    }


    @Override
    public int getItemCount() {
        return mValues.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {



        @BindView(R.id.llMain)
        LinearLayout llMain;
        @BindView(R.id.tvPdfLink)
        TextView tvPdfLink;


        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);



            llMain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listListner.onItemClick(mValues.get(getLayoutPosition()),getLayoutPosition(),tvPdfLink.getText().toString().trim());
                }
            });
        }

    }
}
