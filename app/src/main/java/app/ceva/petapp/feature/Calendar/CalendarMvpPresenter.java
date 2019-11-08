package app.ceva.petapp.feature.Calendar;

import app.ceva.petapp.share.base.MvpPresenter;

public interface CalendarMvpPresenter <V extends CalendarMvpView> extends MvpPresenter<V> {

    void getscheduling(String petId,String productId,String productUniqueId);
}
