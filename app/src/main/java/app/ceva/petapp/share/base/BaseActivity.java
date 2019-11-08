/*
 * Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://mindorks.com/license/apache-v2
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package app.ceva.petapp.share.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.Unbinder;
import app.ceva.petapp.di.components.ActivityComponent;
import app.ceva.petapp.di.components.DaggerActivityComponent;
import app.ceva.petapp.di.modules.ActivityModule;
import app.ceva.petapp.livequiz;
import app.ceva.petapp.utils.CommonUtils;
import app.ceva.petapp.utils.NetworkUtils;




public abstract class BaseActivity extends AppCompatActivity
        implements MvpView  {

    public static final String TAG = BaseActivity.class.getSimpleName();
    private ProgressDialog mProgressDialog;
    private ActivityComponent mActivityComponent;
    private Unbinder mUnBinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityComponent = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .applicationComponent(((livequiz) getApplication()).getApplicationComponent())
                .build();

    }

    public ActivityComponent getActivityComponent() {
        return mActivityComponent;
    }


    public void showErrorToast(String message) {
        hideKeyboard();
        if (message != null) {
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Some error", Toast.LENGTH_LONG).show();
        }
    }


    @Override
    public void showLoading() {
        hideLoading();
        mProgressDialog = CommonUtils.showLoadingDialog(this);
    }

    @Override
    public void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
    }

    @Override
    public void gotoNext(Class<?> cls,Bundle b) {
        CommonUtils.openNewScreen( cls,b,getApplicationContext());
    }

    @Override
    public void gotoNextWithfinish(Class<?> cls, Bundle b) {
        CommonUtils.openNewScreenWithFinish( cls,b,getApplicationContext());
    }

    @Override
    public void showAlert(String msg) {

        CommonUtils.showAlrt(this,msg);
    }

    @Override
    public void onError(String message) {
        hideKeyboard();
        if (message != null) {
            showErrorToast(message);
        } else {
            showErrorToast("Error Message is null");
        }
    }

    @Override
    public void onError(@StringRes int resId) {
        onError(getString(resId));
    }

    @Override
    public void showMessage(@StringRes int resId) {
        showMessage(getString(resId));
    }


    @Override
    public void showMessage(String message) {
        hideKeyboard();
        if (message != null) {
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Some error", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean isNetworkConnected() {
        return NetworkUtils.isNetworkConnected(getApplicationContext());
    }


    public void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public void logOut(){



    }

    @Override
    protected void onDestroy() {

        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
        super.onDestroy();
    }


    public void setUnBinder(Unbinder unBinder) {
        mUnBinder = unBinder;
    }

}
