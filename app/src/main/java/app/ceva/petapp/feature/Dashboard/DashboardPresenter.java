package app.ceva.petapp.feature.Dashboard;

import javax.inject.Inject;

import app.ceva.petapp.data.DataManager;
import app.ceva.petapp.data.network.model.ScanQr.ScanQrRequest;
import app.ceva.petapp.data.network.model.ScanQr.ScanQrResponse;
import app.ceva.petapp.share.base.BasePresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardPresenter<V extends DashboardMvpView> extends BasePresenter<V>implements DashboardMvpPresenter<V> {

    @Inject
    public DashboardPresenter(DataManager mdataManager) {
        super(mdataManager);
    }

    @Override
    public void onLogoutClick() {

        getDataManager().logout();
        getMvpView().navigateToLogout();
    }

    @Override
    public void onScanQr(String regionId, String productId,String productUniqueId) {

        if(getMvpView().isNetworkConnected())
        {
            getMvpView().showLoading();
            ScanQrRequest scanQrRequest=new ScanQrRequest(getDataManager().getUserId(),regionId,productId,productUniqueId);
            getDataManager().getScanQr(scanQrRequest).enqueue(new Callback<ScanQrResponse>() {
                @Override
                public void onResponse(Call<ScanQrResponse> call, Response<ScanQrResponse> response) {

                    if(response.isSuccessful())
                    {
                        getMvpView().hideLoading();
                        if(response.body().getResponseCode()==1)
                        {
                           // getMvpView().onError(response.body().getResponseText());
                            getMvpView().onSuccessfullScanQr(response.body());

                        }
                        else
                        {
                            getMvpView().onSuccessfullScanQr(response.body());
                           // getMvpView().onError(response.body().getResponseText());
                        }
                    }
                    else
                    {
                        getMvpView().hideLoading();
                        getMvpView().onError("Server Error");
                    }

                }

                @Override
                public void onFailure(Call<ScanQrResponse> call, Throwable t) {
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
