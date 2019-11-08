package app.ceva.petapp.feature.MyHistory;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import javax.inject.Inject;

import app.ceva.petapp.R;
import app.ceva.petapp.data.network.model.History.Historyresponse;
import app.ceva.petapp.di.components.ActivityComponent;
import app.ceva.petapp.feature.ProductDetails.ProductDetailsActivity;
import app.ceva.petapp.share.baseFragment.BaseFragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HistoryFragments extends BaseFragment implements HistoryFragmentMvpView ,View.OnClickListener{
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    @BindView(R.id.rvHistory)
    RecyclerView rvHistory;

    @BindView(R.id.rlPets)
    RelativeLayout rlPets;


    @Inject
    HistoryAdapter adapter;




    public HistoryFragments() {
        // Required empty public constructor
    }

    @Inject
    HistoryFragmentPresenter<HistoryFragmentMvpView> presenter;


    @Override
    protected void setUp(View view) {

    }

    public static HistoryFragments newInstance(String param1, String param2) {
        HistoryFragments fragment = new HistoryFragments();
        System.out.println("called items"+" "+param1+" "+param2);
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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
        View v= inflater.inflate(R.layout.fragment_history, container, false);
        ButterKnife.bind(this, v);
        //setUnBinder(ButterKnife.bind(this, v));
        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
        }

        setUp(v);
        presenter.onAttach(this);
        setRecylerView();
        return v;
    }

    @OnClick()
    public void onClick(View v) {

        switch (v.getId())
        {


        }
    }


    private void setRecylerView() {
        rvHistory.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvHistory.setAdapter(adapter);
        presenter.ongetHistory();
        adapter.setAdapterListner(new HistoryAdapter.HistoryListListner() {
            @Override
            public void onItemClick(Historyresponse.ResponseDataBean item, int position, String PetId,String ProductId,String vacineUniqueId) {
                Intent ie = new Intent(getActivity(), ProductDetailsActivity.class);
                ie.putExtra("productId",ProductId );
                ie.putExtra("productUniqueId",vacineUniqueId);
                startActivity(ie);

            }
        });


    }

    @Override
    public void successfullyGetHistory(Historyresponse historyresponse) {


        if(historyresponse.getResponseData().size()>0)
        {
            adapter.loadList(historyresponse.getResponseData());
            rvHistory.setVisibility(View.VISIBLE);
            rlPets.setVisibility(View.GONE);
        }
        else
        {
            rvHistory.setVisibility(View.GONE);
            rlPets.setVisibility(View.VISIBLE);
        }
    }
}
