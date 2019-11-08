package app.ceva.petapp.di.components;


import app.ceva.petapp.feature.AllPage.AllPagesActivity;
import dagger.Component;
import app.ceva.petapp.di.PerActivity;
import app.ceva.petapp.di.modules.ActivityModule;
import app.ceva.petapp.feature.AddAnimal.AddAnimalActivity;
import app.ceva.petapp.feature.Calendar.CalenderActivity;
import app.ceva.petapp.feature.Dashboard.DashboardActivity;
import app.ceva.petapp.feature.EditProfile.EditProfileActivity;
import app.ceva.petapp.feature.Login.LoginActivity;
import app.ceva.petapp.feature.LoginPrevious.LoginPreviousPreviousActivity;
import app.ceva.petapp.feature.MyHistory.HistoryFragments;
import app.ceva.petapp.feature.MyPets.HomeFragment;
import app.ceva.petapp.feature.MyProfile.ProfileFragment;
import app.ceva.petapp.feature.MyQrScan.QrscanFragment;
import app.ceva.petapp.feature.MySettings.SettingsFragment;
import app.ceva.petapp.feature.Notification.NotificationActivity;
import app.ceva.petapp.feature.PetDetails.PetDetailsActivity;
import app.ceva.petapp.feature.ProductDetails.ProductDetailsActivity;
import app.ceva.petapp.feature.Registration.RegistrationActivity;
import app.ceva.petapp.feature.SendOtp.SendOtpActivity;
import app.ceva.petapp.feature.splash.SplashActivity;

@PerActivity
@Component(dependencies = ApplicationComponent.class,modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(SplashActivity activity);
    void inject(LoginPreviousPreviousActivity activity);
    void inject(LoginActivity activity);
    void inject(SendOtpActivity activity);
    void inject(RegistrationActivity activity);
    void inject(AddAnimalActivity activity);
    void inject(DashboardActivity activity);
    void inject(HomeFragment fragment);
    void inject(PetDetailsActivity activity);
    void inject(HistoryFragments fragments);
    void inject(ProfileFragment fragment);
    void inject(SettingsFragment fragment);
    void inject(QrscanFragment fragment);
    void inject(CalenderActivity activity);
    void inject(ProductDetailsActivity activity);
    void inject(EditProfileActivity activity);
    void inject(NotificationActivity activity);
    void inject(AllPagesActivity activity);
}
