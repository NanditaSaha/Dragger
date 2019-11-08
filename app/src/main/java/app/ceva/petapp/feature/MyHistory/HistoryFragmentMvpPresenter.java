package app.ceva.petapp.feature.MyHistory;

import app.ceva.petapp.share.baseFragment.BaseFragmentMvpPresenter;

public interface HistoryFragmentMvpPresenter <V extends HistoryFragmentMvpView>  extends BaseFragmentMvpPresenter<V> {
 void ongetHistory();
}
