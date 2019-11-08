package app.ceva.petapp.feature.AddAnimal;

import javax.inject.Inject;

import app.ceva.petapp.data.DataManager;
import app.ceva.petapp.data.network.model.AddPet.AddPetsRequest;
import app.ceva.petapp.data.network.model.AddPet.AddPetsResponse;
import app.ceva.petapp.data.network.model.PetCategory.PetCategoryResponse;
import app.ceva.petapp.share.base.BasePresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddAnimalPresenter <V extends AddAnimalMvpView> extends BasePresenter<V> implements AddAnimalMvpPresenter<V> {
    @Inject
    public AddAnimalPresenter(DataManager mdataManager) {
        super(mdataManager);
    }

    @Override
    public void onPetCategoryList() {

        getPetCatList();

    }

    @Override
    public void onAddPetsClick(String userId,String categoryId,String petName) {

        addPets(userId,categoryId,petName);
    }

    private void addPets(String userId,String categoryId,String petName)
    {
        if(petName==null||petName.isEmpty())
        {
            getMvpView().onError("Please Enter Pet Name");
        }
        else
        {
            if(getMvpView().isNetworkConnected())
            {
                getMvpView().showLoading();

                AddPetsRequest addPetsRequest=new AddPetsRequest(getDataManager().getUserId(),categoryId,petName);
                getDataManager().getAddPets(addPetsRequest).enqueue(new Callback<AddPetsResponse>() {
                    @Override
                    public void onResponse(Call<AddPetsResponse> call, Response<AddPetsResponse> response) {

                        if(response.isSuccessful())
                        {
                            getMvpView().hideLoading();
                            if(response.body().getResponseCode()==1)
                            {
                                getMvpView().onError(response.body().getResponseText());
                                getMvpView().successfullyAddPets("Success");
                            }
                            else
                            {
                                getMvpView().successfullyAddPets("Failed");
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
                    public void onFailure(Call<AddPetsResponse> call, Throwable t) {

                    }
                });


            }
            else
            {
                getMvpView().showAlert("No internet Connection");
            }
        }
    }


    private void getPetCatList()
    {
        if(getMvpView().isNetworkConnected())
        {
            getMvpView().showLoading();
            getDataManager().getPetCateroryList().enqueue(new Callback<PetCategoryResponse>() {
                @Override
                public void onResponse(Call<PetCategoryResponse> call, Response<PetCategoryResponse> response) {
                    if(response.isSuccessful())
                    {
                        getMvpView().hideLoading();
                        if(response.body().getResponseCode()==1)
                        {

                            getMvpView().successfullyPetCatList(response.body().getResponseData());

                        }
                        else
                        {
                            getMvpView().successfullyPetCatList(response.body().getResponseData());
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
                public void onFailure(Call<PetCategoryResponse> call, Throwable t) {
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
