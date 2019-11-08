package app.ceva.petapp.di.modules;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import app.ceva.petapp.data.network.model.GetPet.GetPetsResponse;
import app.ceva.petapp.data.network.model.GetPetDetails.GetPetDetailsResponse;
import app.ceva.petapp.data.network.model.History.Historyresponse;
import app.ceva.petapp.data.network.model.ProductDetails.ProductDetailsResponse;
import app.ceva.petapp.di.ActivityContext;
import app.ceva.petapp.di.PerActivity;
import app.ceva.petapp.feature.AddAnimal.AddAnimalMvpPresenter;
import app.ceva.petapp.feature.AddAnimal.AddAnimalMvpView;
import app.ceva.petapp.feature.AddAnimal.AddAnimalPresenter;
import app.ceva.petapp.feature.AllPage.AllPagesMvpPresenter;
import app.ceva.petapp.feature.AllPage.AllPagesMvpView;
import app.ceva.petapp.feature.AllPage.AllpagePresenter;
import app.ceva.petapp.feature.Calendar.CalendarMvpPresenter;
import app.ceva.petapp.feature.Calendar.CalendarMvpView;
import app.ceva.petapp.feature.Calendar.CalendarPresenter;
import app.ceva.petapp.feature.Dashboard.DashboardMvpPresenter;
import app.ceva.petapp.feature.Dashboard.DashboardMvpView;
import app.ceva.petapp.feature.Dashboard.DashboardPresenter;
import app.ceva.petapp.feature.EditProfile.EditProfileMvpPresenter;
import app.ceva.petapp.feature.EditProfile.EditProfileMvpView;
import app.ceva.petapp.feature.EditProfile.EditProfilePresenter;
import app.ceva.petapp.feature.Login.LoginMvpPresenter;
import app.ceva.petapp.feature.Login.LoginMvpView;
import app.ceva.petapp.feature.Login.LoginPresenter;
import app.ceva.petapp.feature.LoginPrevious.LoginPreviousMvpPresenter;
import app.ceva.petapp.feature.LoginPrevious.LoginPreviousMvpView;
import app.ceva.petapp.feature.LoginPrevious.LoginPreviousPresenter;
import app.ceva.petapp.feature.MyHistory.HistoryAdapter;
import app.ceva.petapp.feature.MyHistory.HistoryFragments;
import app.ceva.petapp.feature.MyPets.HomeFragment;
import app.ceva.petapp.feature.MyPets.PetListAdapter;
import app.ceva.petapp.feature.MyProfile.ProfileFragment;
import app.ceva.petapp.feature.MyQrScan.QrscanFragment;
import app.ceva.petapp.feature.MySettings.SettingsFragment;
import app.ceva.petapp.feature.Notification.NotificationMvpPresenter;
import app.ceva.petapp.feature.Notification.NotificationMvpView;
import app.ceva.petapp.feature.Notification.NotificationPresenter;
import app.ceva.petapp.feature.PetDetails.PetDetailsMvpPresenter;
import app.ceva.petapp.feature.PetDetails.PetDetailsMvpView;
import app.ceva.petapp.feature.PetDetails.PetDetailsPresenter;
import app.ceva.petapp.feature.PetDetails.VaccineListAdapter;
import app.ceva.petapp.feature.ProductDetails.PdfListAdapter;
import app.ceva.petapp.feature.ProductDetails.ProductDetailsMvpPresenter;
import app.ceva.petapp.feature.ProductDetails.ProductDetailsMvpView;
import app.ceva.petapp.feature.ProductDetails.ProductDetailsPresenter;
import app.ceva.petapp.feature.SendOtp.SendOtpMvpPresenter;
import app.ceva.petapp.feature.SendOtp.SendOtpMvpView;
import app.ceva.petapp.feature.SendOtp.SendOtpPresenter;
import app.ceva.petapp.feature.splash.SplashMvpPresenter;
import app.ceva.petapp.feature.splash.SplashMvpView;
import app.ceva.petapp.feature.splash.SplashPresenter;
import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    private AppCompatActivity mActivity;

    public ActivityModule(AppCompatActivity activity) {
        this.mActivity = activity;
    }


    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }

    @Provides
    AppCompatActivity provideActivity() {
        return mActivity;
    }


    @Provides
    @PerActivity
    SplashMvpPresenter<SplashMvpView> provideSplashPresenter(SplashPresenter<SplashMvpView> presenter) {
        return presenter;
    }
    @Provides
    @PerActivity
    LoginPreviousMvpPresenter<LoginPreviousMvpView> provideLoginPreviousPresenter(LoginPreviousPresenter<LoginPreviousMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    LoginMvpPresenter<LoginMvpView> provideLoginPresenter(LoginPresenter<LoginMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    SendOtpMvpPresenter<SendOtpMvpView> provideSendOtpPresenter(SendOtpPresenter<SendOtpMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    AddAnimalMvpPresenter<AddAnimalMvpView> provideAddAnimalPresenter(AddAnimalPresenter<AddAnimalMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    DashboardMvpPresenter<DashboardMvpView> provideDashboardPresenter(DashboardPresenter<DashboardMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    PetDetailsMvpPresenter<PetDetailsMvpView> providePetDetailsPresenter(PetDetailsPresenter<PetDetailsMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    CalendarMvpPresenter<CalendarMvpView> provideCalendarPresenter(CalendarPresenter<CalendarMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    ProductDetailsMvpPresenter<ProductDetailsMvpView> provideProductDetailsPresenter(ProductDetailsPresenter<ProductDetailsMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    EditProfileMvpPresenter<EditProfileMvpView> provideEditProfilePresenter(EditProfilePresenter<EditProfileMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    NotificationMvpPresenter<NotificationMvpView> provideNotificationPresenter(NotificationPresenter<NotificationMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    AllPagesMvpPresenter<AllPagesMvpView> provideAllPagesPresenter(AllpagePresenter<AllPagesMvpView> presenter) {
        return presenter;
    }



    @Provides
    @PerActivity
    HomeFragment providesHomeFragment()
    {
        return new HomeFragment();
    }

    @Provides
    @PerActivity
    HistoryFragments providesHistoryFragment()
    {
        return new HistoryFragments();
    }

    @Provides
    @PerActivity
    ProfileFragment providesProfileFragment()
    {
        return new ProfileFragment();
    }

    @Provides
    @PerActivity
    SettingsFragment providesSettingsFragment()
    {
        return new SettingsFragment();
    }

    @Provides
    @PerActivity
    QrscanFragment providesQrScanFragment()
    {
        return new QrscanFragment();
    }

    @Provides
    PetListAdapter provideListAdapter() {
        return new PetListAdapter(new ArrayList<GetPetsResponse.ResponseDataBean>());
    }
    @Provides
    PdfListAdapter providePdfListAdapter() {
        return new PdfListAdapter(new ArrayList<ProductDetailsResponse.ResponseDataBean.ProductPdfBean>());
    }
    @Provides
    HistoryAdapter provideHistoryAdapter() {
        return new HistoryAdapter(new ArrayList<Historyresponse.ResponseDataBean>());
    }
    @Provides
    VaccineListAdapter provideVaccineAdapter() {
        return new VaccineListAdapter(new ArrayList<GetPetDetailsResponse.ResponseDataBean.VaccineListBean>());
    }
}
