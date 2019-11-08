package app.ceva.petapp.feature.MyPets;

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
import app.ceva.petapp.data.network.model.GetPet.GetPetsResponse;
import app.ceva.petapp.di.components.ActivityComponent;
import app.ceva.petapp.feature.AddAnimal.AddAnimalActivity;
import app.ceva.petapp.feature.PetDetails.PetDetailsActivity;
import app.ceva.petapp.share.baseFragment.BaseFragment;
import app.ceva.petapp.share.wigeds.TextViewRegular;
import app.ceva.petapp.utils.AppData;
import app.ceva.petapp.utils.L;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeFragment extends BaseFragment implements HomeFragmentMvpView, View.OnClickListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;


    @BindView(R.id.tvAddPets)
    TextViewRegular tvAddPets;

    @BindView(R.id.rv_petlist)
    RecyclerView rv_petlist;

    @BindView(R.id.rlPets)
    RelativeLayout rlPets;


    private DeletePetDialog deletePetDialog;

    @Inject
    PetListAdapter petListAdapter;

    private String PetsId;


    public HomeFragment() {

        // Required empty public constructor
    }

    @Override
    protected void setUp(View view) {
        presenter.onGetAllPets(AppData.USER_ID);
    }

    @Inject
    HomeFragmentPresenter<HomeFragmentMvpView> presenter;

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        System.out.println("called items" + " " + param1 + " " + param2);
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

       final View v = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, v);
        //setUnBinder(ButterKnife.bind(this, v));
        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
        }
        setRecylerView();
        presenter.onAttach(this);
        setUp(v);




        deletePetDialougSet();
        return v;
    }


    private void deletePetDialougSet() {
        deletePetDialog = new DeletePetDialog();
        deletePetDialog.setListner(new DeletePetDialog.byDeletePetInterface() {
                                       @Override
                                       public void sendDeletePet(String result) {

                                          presenter.onDeletePets(PetsId);

                                       }
                                   }

        );
    }

    @OnClick({R.id.tvAddPets})
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.tvAddPets:
                Intent ai = new Intent(getActivity(), AddAnimalActivity.class);
                ai.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                getActivity().startActivity(ai);

                break;

        }
    }

    @Override
    public void successfullyPetList(GetPetsResponse response) {


        if (response.getResponseCode()==1) {
            petListAdapter.loadList(response.getResponseData());
            rv_petlist.setVisibility(View.VISIBLE);
            rlPets.setVisibility(View.GONE);

        }
        else if(response.getResponseCode()==0)
        {
            rv_petlist.setVisibility(View.GONE);
            rlPets.setVisibility(View.VISIBLE);
            L.e(response.getResponseText()+" enter "+response.getResponseCode());

        }

    }

    @Override
    public void successfullyDeletePetList(String yes) {

        presenter.onGetAllPets(AppData.USER_ID);

    }


    private void setRecylerView() {
        rv_petlist.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_petlist.setAdapter(petListAdapter);
       // presenter.onGetAllPets(AppData.USER_ID);
        petListAdapter.setAdapterListner(new PetListAdapter.PetListListner() {
            @Override
            public void onItemClick(GetPetsResponse.ResponseDataBean item, int position, String PetId) {
                Intent ie = new Intent(getActivity(), PetDetailsActivity.class);
                ie.putExtra("Type", "View");
                ie.putExtra("PetId", PetId);
                startActivity(ie);
            }
        });
        petListAdapter.setEditAdapterListner(new PetListAdapter.PetEditListner() {
            @Override
            public void onEditPetClick(GetPetsResponse.ResponseDataBean item, int position, String PetId) {
                Intent ie = new Intent(getActivity(), PetDetailsActivity.class);
                ie.putExtra("Type", "Edit");
                ie.putExtra("PetId", PetId);
                startActivity(ie);
            }
        });
        petListAdapter.setDeleteAdapterListner(new PetListAdapter.PetDeleteListner() {
            @Override
            public void onDeletePetClick(GetPetsResponse.ResponseDataBean item, int position, String PetId) {
                deletePetDialog.show(getFragmentManager(), "byprice");

                PetsId=PetId;
            }
        });

    }



    @Override
    public void onResume() {
        super.onResume();

    }
}
