package app.ceva.petapp.feature.Dashboard;

import app.ceva.petapp.di.PerActivity;
import app.ceva.petapp.share.base.MvpPresenter;

@PerActivity
public interface DashboardMvpPresenter<V extends DashboardMvpView> extends MvpPresenter<V> {

    void onLogoutClick();
    void onScanQr(String regionId,String productId,String productUniqueId);
}
