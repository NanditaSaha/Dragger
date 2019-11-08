package app.ceva.petapp.feature.Calendar;

import javax.inject.Inject;

import app.ceva.petapp.data.DataManager;
import app.ceva.petapp.data.network.model.FetchScheduling.FetchSchedulingResponse;
import app.ceva.petapp.data.network.model.FetchScheduling.FetchschedulingRequest;
import app.ceva.petapp.share.base.BasePresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CalendarPresenter <V extends CalendarMvpView> extends BasePresenter<V> implements CalendarMvpPresenter<V> {

    @Inject
    public CalendarPresenter(DataManager mdataManager) {
        super(mdataManager);
    }

    @Override
    public void getscheduling(String petId, String productId,String productUniqueId) {
        if(getMvpView().isNetworkConnected())
        {
            getMvpView().showLoading();
            FetchschedulingRequest fetchschedulingRequest=new FetchschedulingRequest(getDataManager().getUserId(),petId,productId,productUniqueId);
            getDataManager().getScheduling(fetchschedulingRequest).enqueue(new Callback<FetchSchedulingResponse>() {
                @Override
                public void onResponse(Call<FetchSchedulingResponse> call, Response<FetchSchedulingResponse> response) {

                    if(response.isSuccessful())
                    {
                        getMvpView().hideLoading();
                        if(response.body().getResponseCode()==1)
                        {
                           // getMvpView().onError(response.body().getResponseText());
                            getMvpView().succeessfullyGetScheduling(response.body());

                        }
                        else
                        {
                            getMvpView().onError(response.body().getResponseText());
                            getMvpView().succeessfullyGetScheduling(response.body());
                        }
                    }
                    else
                    {
                        getMvpView().hideLoading();
                        getMvpView().onError("Server Error");
                    }

                }

                @Override
                public void onFailure(Call<FetchSchedulingResponse> call, Throwable t) {
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
