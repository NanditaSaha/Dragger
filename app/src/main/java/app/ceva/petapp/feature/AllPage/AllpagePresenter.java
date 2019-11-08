package app.ceva.petapp.feature.AllPage;

import javax.inject.Inject;

import app.ceva.petapp.data.DataManager;
import app.ceva.petapp.data.network.model.Allpages.AllpagesRequest;
import app.ceva.petapp.data.network.model.Allpages.AllpagesResponse;
import app.ceva.petapp.share.base.BasePresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllpagePresenter <V extends AllPagesMvpView> extends BasePresenter<V> implements AllPagesMvpPresenter<V> {

    @Inject
    public AllpagePresenter(DataManager mdataManager) {
        super(mdataManager);
    }

    @Override
    public void ongetContent(String tag) {


        if(getMvpView().isNetworkConnected())
        {
            getMvpView().showLoading();
            AllpagesRequest allpagesRequest=new AllpagesRequest(tag);
            getDataManager().getAllPages(allpagesRequest).enqueue(new Callback<AllpagesResponse>() {
                @Override
                public void onResponse(Call<AllpagesResponse> call, Response<AllpagesResponse> response) {

                    if(response.isSuccessful())
                    {
                        getMvpView().hideLoading();
                        if(response.body().getResponseCode()==1)
                        {
                           // getMvpView().onError(response.body().getResponseText());
                            getMvpView().onsuccessfullyGetAllPeg(response.body());

                        }
                        else
                        {
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
                public void onFailure(Call<AllpagesResponse> call, Throwable t) {
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
