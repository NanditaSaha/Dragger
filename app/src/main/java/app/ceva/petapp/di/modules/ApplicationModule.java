package app.ceva.petapp.di.modules;


import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import app.ceva.petapp.data.AppDataManager;
import app.ceva.petapp.data.DataManager;
import app.ceva.petapp.data.pref.AppPreferencesHelper;
import app.ceva.petapp.data.pref.PreferencesHelper;
import app.ceva.petapp.di.ApplicationContext;

@Module
public class ApplicationModule {

    private final Application mApplication;

    public ApplicationModule(Application mApplication) {
        this.mApplication = mApplication;
    }

    @Provides
    @ApplicationContext
    Context provideContext()
    {
        return mApplication;
    }

    @Provides
    Application provideApplication()
    {
        return mApplication;
    }


    @Singleton
    @Provides
    PreferencesHelper providePrefHelper(AppPreferencesHelper mAppPreferencesHelper){
        return mAppPreferencesHelper;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager)
    {return  appDataManager;}
}
