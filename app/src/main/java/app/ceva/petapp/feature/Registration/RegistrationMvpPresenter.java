package app.ceva.petapp.feature.Registration;

public interface RegistrationMvpPresenter <V extends RegistrationMvpView>{
    void onSubmitClick(String Name,String Address,String Phone,String Region,String Email,String Password);
    void onGetRegionList();
    void onInit(String phoneNumber);

}
