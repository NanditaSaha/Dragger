package app.ceva.petapp.share.base;

public interface MvpPresenter<V extends MvpView> {

    void onAttached(V mvpView);
    void onDettached();
}
