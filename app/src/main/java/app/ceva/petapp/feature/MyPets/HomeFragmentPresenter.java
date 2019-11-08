package app.ceva.petapp.feature.MyPets;

import javax.inject.Inject;

import app.ceva.petapp.data.DataManager;
import app.ceva.petapp.data.network.model.DeletePets.DeletePetRequest;
import app.ceva.petapp.data.network.model.DeletePets.DeletePetResponse;
import app.ceva.petapp.data.network.model.GetPet.GetPetsRequest;
import app.ceva.petapp.data.network.model.GetPet.GetPetsResponse;
import app.ceva.petapp.share.baseFragment.BaseFragmentPresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragmentPresenter<V extends HomeFragmentMvpView>extends BaseFragmentPresenter<V> implements HomeFragmentMvpPresenter<V> {

    @Inject
    public HomeFragmentPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void onGetAllPets(String userId) {

        getAllPets(userId);

    }

    @Override
    public void onDeletePets(String petId) {
        getDeletePets(petId);
    }

    private void getDeletePets(String petId)
    {
        if(getMvpView().isNetworkConnected())
        {
            getMvpView().onError("enter");
            getMvpView().showLoading();

            DeletePetRequest deletePetRequest=new DeletePetRequest(petId);
            getDataManager().getPetsDelete(deletePetRequest).enqueue(new Callback<DeletePetResponse>() {
                @Override
                public void onResponse(Call<DeletePetResponse> call, Response<DeletePetResponse> response) {
                    if(response.isSuccessful())
                    {
                        getMvpView().hideLoading();
                        if(response.body().getResponseCode()==1)
                        {
                            getMvpView().onError(response.body().getResponseText());
                            getMvpView().successfullyDeletePetList("Success");

                        }
                        else
                        {
                            getMvpView().onError(response.body().getResponseText());
                            getMvpView().successfullyDeletePetList("Failed");
                        }
                    }
                    else
                    {
                        getMvpView().hideLoading();
                        getMvpView().onError("Server Error");
                    }

                }

                @Override
                public void onFailure(Call<DeletePetResponse> call, Throwable t) {
                    getMvpView().hideLoading();
                    getMvpView().showAlert("Server Error");
                }
            });
        }
        else
        {
            getMvpView().showAlert("No internet Connection");
        }
    }


    private void getAllPets(String userId)
    {
        if(getMvpView().isNetworkConnected())
        {
            getMvpView().showLoading();
            GetPetsRequest getPetsRequest=new GetPetsRequest(getDataManager().getUserId());
            getDataManager().getAllPets(getPetsRequest).enqueue(new Callback<GetPetsResponse>() {
                @Override
                public void onResponse(Call<GetPetsResponse> call, Response<GetPetsResponse> response) {
                    if(response.isSuccessful())
                    {

                        getMvpView().hideLoading();
                        if(response.body().getResponseCode()==1)
                        {

                            getMvpView().onError(response.body().getResponseText());
                            getMvpView().successfullyPetList(response.body());

                        }
                        else
                        {

                            getMvpView().successfullyPetList(response.body());
                            getMvpView().onError(response.body().getResponseText());
                        }
                    }
                    else
                    {
                        getMvpView().hideLoading();
                        getMvpView().onError("Server Error");
                    }

                }

                @Override
                public void onFailure(Call<GetPetsResponse> call, Throwable t) {
                    getMvpView().hideLoading();
                    getMvpView().showAlert("Server Error");
                }
            });

        }
        else
        {
            getMvpView().showAlert("No internet Connection");
        }
    }
}
