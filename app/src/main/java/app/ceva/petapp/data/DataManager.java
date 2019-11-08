package app.ceva.petapp.data;

import app.ceva.petapp.data.network.ApiHelper;
import app.ceva.petapp.data.pref.PreferencesHelper;

public interface DataManager extends ApiHelper, PreferencesHelper {

    void logout();
}
