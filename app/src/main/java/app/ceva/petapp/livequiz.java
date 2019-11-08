package app.ceva.petapp;

import android.app.Application;
import android.content.Context;

import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;
import javax.inject.Inject;

import app.ceva.petapp.data.DataManager;
import app.ceva.petapp.di.components.ApplicationComponent;
import app.ceva.petapp.di.components.DaggerApplicationComponent;
import app.ceva.petapp.di.modules.ApplicationModule;
import app.ceva.petapp.di.modules.NetworkModule;
import app.ceva.petapp.utils.AppData;

public class livequiz extends Application {

    ApplicationComponent applicationComponent;

    @Inject
    DataManager mDataManager;


    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
       // Fabric.with(this, new Crashlytics());




        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .networkModule(new NetworkModule(AppData.BASE_URL)).build();

        applicationComponent.inject(this);
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    public DataManager getDataManager() {
        return mDataManager;
    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        /*MultiDex.install(this);*/
    }
}
