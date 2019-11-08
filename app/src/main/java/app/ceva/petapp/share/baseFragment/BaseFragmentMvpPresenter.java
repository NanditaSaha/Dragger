package app.ceva.petapp.share.baseFragment;

public interface BaseFragmentMvpPresenter <V extends BaseFragmentMvpView> {

    void onAttach(V mvpView);

    void onDetach();
}
