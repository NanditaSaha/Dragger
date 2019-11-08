package app.ceva.petapp.feature.AllPage;

import app.ceva.petapp.data.network.model.Allpages.AllpagesResponse;
import app.ceva.petapp.share.base.MvpView;

public interface AllPagesMvpView extends MvpView {
    void onsuccessfullyGetAllPeg(AllpagesResponse allpagesResponse);
}
