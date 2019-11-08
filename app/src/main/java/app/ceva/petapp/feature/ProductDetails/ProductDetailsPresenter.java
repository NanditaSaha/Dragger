package app.ceva.petapp.feature.ProductDetails;

import javax.inject.Inject;

import app.ceva.petapp.data.DataManager;
import app.ceva.petapp.data.network.model.GetPet.GetPetsRequest;
import app.ceva.petapp.data.network.model.GetPet.GetPetsResponse;
import app.ceva.petapp.data.network.model.ProductDetails.ProductDetailsRequest;
import app.ceva.petapp.data.network.model.ProductDetails.ProductDetailsResponse;
import app.ceva.petapp.data.network.model.SubmitScheduling.SubmitSchedulingRequest;
import app.ceva.petapp.data.network.model.SubmitScheduling.SubmitSchedulingResponse;
import app.ceva.petapp.share.base.BasePresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailsPresenter <V extends ProductDetailsMvpView> extends BasePresenter<V> implements ProductDetailsMvpPresenter<V> {
    @Inject
    public ProductDetailsPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void onGetProductDetaild(String productId,String productUniqueId) {
        if(getMvpView().isNetworkConnected())
        {
            getMvpView().showLoading();
            ProductDetailsRequest productDetailsRequest=new ProductDetailsRequest(getDataManager().getUserId(),productId, productUniqueId);
            getDataManager().getProductDetails(productDetailsRequest).enqueue(new Callback<ProductDetailsResponse>() {
                @Override
                public void onResponse(Call<ProductDetailsResponse> call, Response<ProductDetailsResponse> response) {

                    if(response.isSuccessful())
                    {
                        getMvpView().hideLoading();
                        if(response.body().getResponseCode()==1)
                        {
                           // getMvpView().onError(response.body().getResponseText());
                            getMvpView().successfullyGetProductDetails(response.body());

                        }
                        else
                        {
                           // getMvpView().onError(response.body().getResponseText());
                            getMvpView().successfullyGetProductDetails(response.body());
                        }
                    }
                    else
                    {
                        getMvpView().hideLoading();
                        getMvpView().onError("Server Error");
                    }

                }

                @Override
                public void onFailure(Call<ProductDetailsResponse> call, Throwable t) {
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

    @Override
    public void onGetAllPets(String userId) {
        getAllPets("");
    }

    @Override
    public void onSubmitSchedule(String petId, String productId,String productUniqueId, String date) {

        if(date==null||date.isEmpty())
        {
            getMvpView().onError("Please Enter Date");
        }
        else
        {
            if(getMvpView().isNetworkConnected())
            {
                getMvpView().showLoading();
                SubmitSchedulingRequest submitSchedulingRequest=new SubmitSchedulingRequest(getDataManager().getUserId(),petId,productId,productUniqueId,date);
                getDataManager().getSubmitScheduling(submitSchedulingRequest).enqueue(new Callback<SubmitSchedulingResponse>() {
                    @Override
                    public void onResponse(Call<SubmitSchedulingResponse> call, Response<SubmitSchedulingResponse> response) {

                        if(response.isSuccessful())
                        {
                            getMvpView().hideLoading();
                            if(response.body().getResponseCode()==1)
                            {
                               // getMvpView().onError(response.body().getResponseText());
                                getMvpView().successfullyGetSchedule(response.body());

                            }
                            else
                            {
                                getMvpView().onError(response.body().getResponseText());
                                getMvpView().successfullyGetSchedule(response.body());
                            }
                        }
                        else
                        {
                            getMvpView().hideLoading();
                            getMvpView().onError("Server Error");
                        }

                    }

                    @Override
                    public void onFailure(Call<SubmitSchedulingResponse> call, Throwable t) {
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

                          //  getMvpView().onError(response.body().getResponseText());
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
