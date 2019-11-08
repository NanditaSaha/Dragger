package app.ceva.petapp.feature.Dashboard;

import app.ceva.petapp.data.network.model.ScanQr.ScanQrResponse;
import app.ceva.petapp.share.base.MvpView;

public interface DashboardMvpView extends MvpView {

    void navigateToLogout();
    void onSuccessfullScanQr(ScanQrResponse scanQrResponse);

}
