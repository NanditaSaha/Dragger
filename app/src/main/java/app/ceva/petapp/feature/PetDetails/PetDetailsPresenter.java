package app.ceva.petapp.feature.PetDetails;

import javax.inject.Inject;

import app.ceva.petapp.data.DataManager;
import app.ceva.petapp.data.network.model.EditPet.EditPetRequest;
import app.ceva.petapp.data.network.model.EditPet.EditPetResponse;
import app.ceva.petapp.data.network.model.GetPetDetails.GetPetDetailsRequest;
import app.ceva.petapp.data.network.model.GetPetDetails.GetPetDetailsResponse;
import app.ceva.petapp.data.network.model.PetCategory.PetCategoryResponse;
import app.ceva.petapp.share.base.BasePresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PetDetailsPresenter <V extends PetDetailsMvpView> extends BasePresenter<V> implements PetDetailsMvpPresenter<V> {

    @Inject
    public PetDetailsPresenter(DataManager mdataManager) {
        super(mdataManager);
    }

    @Override
    public void onPetCategoryList() {
        getPetCatList();
    }

    @Override
    public void onEditPets(String petId, String categoryId, String petName) {
        getEditPets(petId,categoryId,petName);
    }

    @Override
    public void onGetPetDetails(String petId) {
     getPetDetails(petId);
    }


    private void getPetDetails(String petId)
    {
        if(getMvpView().isNetworkConnected())
        {
            getMvpView().showLoading();
            GetPetDetailsRequest getPetDetailsRequest=new GetPetDetailsRequest(petId,getDataManager().getUserId());
            getDataManager().getPetsDetails(getPetDetailsRequest).enqueue(new Callback<GetPetDetailsResponse>() {
                @Override
                public void onResponse(Call<GetPetDetailsResponse> call, Response<GetPetDetailsResponse> response) {
                    if(response.isSuccessful())
                    {
                        getMvpView().hideLoading();
                        if(response.body().getResponseCode()==1)
                        {

                            getMvpView().successfullyGetPetDetails(response.body());
                        }
                        else
                        {
                            getMvpView().onError(response.body().getResponseText());
                            getMvpView().successfullyGetPetDetails(response.body());
                        }


                    }

                    else
                    {
                        getMvpView().hideLoading();
                        getMvpView().onError("Server Error");
                    }
                }

                @Override
                public void onFailure(Call<GetPetDetailsResponse> call, Throwable t) {
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

    private void getEditPets(String petId,String categoryId,String petName)
    {

        if(petName==null||petName.isEmpty())
        {
            getMvpView().onError("Please Enter Pet Name");
        }
        else {
            if (getMvpView().isNetworkConnected()) {
                getMvpView().showLoading();
                EditPetRequest editPetRequest = new EditPetRequest(petId, categoryId, petName);
                getDataManager().getEditPets(editPetRequest).enqueue(new Callback<EditPetResponse>() {
                    @Override
                    public void onResponse(Call<EditPetResponse> call, Response<EditPetResponse> response) {
                        if (response.isSuccessful()) {
                            getMvpView().hideLoading();
                            if (response.body().getResponseCode() == 1) {
                                getMvpView().onError(response.body().getResponseText());
                                getMvpView().successfullyEditPetList(response.body());
                            } else {
                                getMvpView().onError(response.body().getResponseText());
                                getMvpView().successfullyEditPetList(response.body());
                            }


                        } else {
                            getMvpView().hideLoading();
                            getMvpView().onError("Server Error");
                        }
                    }

                    @Override
                    public void onFailure(Call<EditPetResponse> call, Throwable t) {
                        getMvpView().hideLoading();
                        getMvpView().showAlert("Server Error");
                    }
                });

            } else {
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
