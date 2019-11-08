package app.ceva.petapp.feature.AllPage;

import app.ceva.petapp.share.base.MvpPresenter;

public interface AllPagesMvpPresenter<V extends AllPagesMvpView>extends MvpPresenter<V> {

    void ongetContent(String tag);
}
