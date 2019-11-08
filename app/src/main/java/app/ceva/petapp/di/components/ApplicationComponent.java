package app.ceva.petapp.di.components;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import app.ceva.petapp.data.DataManager;
import app.ceva.petapp.di.ApplicationContext;
import app.ceva.petapp.di.modules.ApplicationModule;
import app.ceva.petapp.di.modules.NetworkModule;
import app.ceva.petapp.livequiz;

@Singleton
@Component(modules={ApplicationModule.class, NetworkModule.class})
public interface ApplicationComponent {


    void inject(livequiz app);

    @ApplicationContext
    Context context();

    Application application();

    DataManager getDataManager();

}
