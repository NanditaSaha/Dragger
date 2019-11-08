package app.ceva.petapp.feature.MyHistory;

import javax.inject.Inject;

import app.ceva.petapp.data.DataManager;
import app.ceva.petapp.data.network.model.History.HistoryRequest;
import app.ceva.petapp.data.network.model.History.Historyresponse;
import app.ceva.petapp.share.baseFragment.BaseFragmentPresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryFragmentPresenter <V extends HistoryFragmentMvpView>extends BaseFragmentPresenter<V>implements HistoryFragmentMvpPresenter<V> {
    @Inject
    public HistoryFragmentPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void ongetHistory() {

        if(getMvpView().isNetworkConnected())
        {
            getMvpView().showLoading();
            HistoryRequest HistoryRequest=new HistoryRequest(getDataManager().getUserId());
            getDataManager().getHistory(HistoryRequest).enqueue(new Callback<Historyresponse>() {
                @Override
                public void onResponse(Call<Historyresponse> call, Response<Historyresponse> response) {

                    if(response.isSuccessful())
                    {
                        getMvpView().hideLoading();
                        if(response.body().getResponseCode()==1)
                        {
                            getMvpView().onError(response.body().getResponseText());
                            getMvpView().successfullyGetHistory(response.body());

                        }
                        else
                        {
                            getMvpView().onError(response.body().getResponseText());
                            getMvpView().successfullyGetHistory(response.body());
                        }
                    }
                    else
                    {
                        getMvpView().hideLoading();
                        getMvpView().onError("Server Error");
                    }

                }

                @Override
                public void onFailure(Call<Historyresponse> call, Throwable t) {
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
