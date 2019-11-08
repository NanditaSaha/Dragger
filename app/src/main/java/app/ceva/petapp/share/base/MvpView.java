package app.ceva.petapp.share.base;

import android.os.Bundle;

import androidx.annotation.StringRes;

public interface MvpView {

    void showLoading();

    void hideLoading();

    void onError(@StringRes int resId);

    void onError(String message);

    void showMessage(String message);


    void showMessage(@StringRes int resId);

    boolean isNetworkConnected();

    void hideKeyboard();

    void logOut();

    void gotoNext(Class<?> cls, Bundle b);

    void gotoNextWithfinish(Class<?> cls, Bundle b);

    void showAlert(String msg);



}
