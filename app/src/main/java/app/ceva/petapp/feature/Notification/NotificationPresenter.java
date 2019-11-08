package app.ceva.petapp.feature.Notification;

import javax.inject.Inject;

import app.ceva.petapp.data.DataManager;
import app.ceva.petapp.share.base.BasePresenter;

public class NotificationPresenter <V extends NotificationMvpView> extends BasePresenter<V> implements NotificationMvpPresenter<V> {

    @Inject
    public NotificationPresenter(DataManager mdataManager) {
        super(mdataManager);
    }
}
