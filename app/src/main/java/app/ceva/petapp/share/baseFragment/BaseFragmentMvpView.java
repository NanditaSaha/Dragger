package app.ceva.petapp.share.baseFragment;

import androidx.annotation.StringRes;

public interface BaseFragmentMvpView {
    //void dismissFragment();

    void showLoading();

    void hideLoading();

    void onError(@StringRes int resId);

    void onError(String message);

    void showMessage(String message);

    void showAlert(String message);

    void showInactiveUserAlert(String message);

    void showMessage(@StringRes int resId);

    boolean isNetworkConnected();
}
