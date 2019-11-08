package app.ceva.petapp.feature.Notification;

import app.ceva.petapp.di.PerActivity;
import app.ceva.petapp.share.base.MvpPresenter;

@PerActivity
public interface NotificationMvpPresenter <V extends NotificationMvpView> extends MvpPresenter<V> {
}
