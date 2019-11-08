package app.ceva.petapp.feature.MyHistory;

import app.ceva.petapp.data.network.model.History.Historyresponse;
import app.ceva.petapp.share.baseFragment.BaseFragmentMvpView;

public interface HistoryFragmentMvpView  extends BaseFragmentMvpView {

    void successfullyGetHistory(Historyresponse historyresponse);
}
