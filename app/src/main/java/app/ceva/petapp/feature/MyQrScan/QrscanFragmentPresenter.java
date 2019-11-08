package app.ceva.petapp.feature.MyQrScan;

import javax.inject.Inject;

import app.ceva.petapp.data.DataManager;
import app.ceva.petapp.share.baseFragment.BaseFragmentPresenter;

public class QrscanFragmentPresenter <V extends QrscanFragmentMvpView> extends BaseFragmentPresenter<V> implements QrscanFrgamentMvpPresenter<V> {
    @Inject
    public QrscanFragmentPresenter(DataManager dataManager) {
        super(dataManager);
    }
}
