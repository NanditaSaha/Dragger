package app.ceva.petapp.feature.Calendar;

import app.ceva.petapp.data.network.model.FetchScheduling.FetchSchedulingResponse;
import app.ceva.petapp.share.base.MvpView;

public interface CalendarMvpView extends MvpView {


    void succeessfullyGetScheduling(FetchSchedulingResponse fetchSchedulingResponse);
}


