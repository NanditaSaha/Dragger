package app.ceva.petapp.data;

import android.content.Context;

import javax.inject.Inject;

import app.ceva.petapp.data.network.ApiHelper;
import app.ceva.petapp.data.network.model.AddPet.AddPetsRequest;
import app.ceva.petapp.data.network.model.AddPet.AddPetsResponse;
import app.ceva.petapp.data.network.model.AddSettings.AddsettingsRequest;
import app.ceva.petapp.data.network.model.AddSettings.AddsettingsResponse;
import app.ceva.petapp.data.network.model.Allpages.AllpagesRequest;
import app.ceva.petapp.data.network.model.Allpages.AllpagesResponse;
import app.ceva.petapp.data.network.model.ChangePassword.CgangePasswordRequest;
import app.ceva.petapp.data.network.model.ChangePassword.ChangePasswordResponse;
import app.ceva.petapp.data.network.model.CheckOtp.CheckOtpRequest;
import app.ceva.petapp.data.network.model.CheckOtp.CheckOtpResponse;
import app.ceva.petapp.data.network.model.DeletePets.DeletePetRequest;
import app.ceva.petapp.data.network.model.DeletePets.DeletePetResponse;
import app.ceva.petapp.data.network.model.EditPet.EditPetRequest;
import app.ceva.petapp.data.network.model.EditPet.EditPetResponse;
import app.ceva.petapp.data.network.model.FetchScheduling.FetchSchedulingResponse;
import app.ceva.petapp.data.network.model.FetchScheduling.FetchschedulingRequest;
import app.ceva.petapp.data.network.model.FetchSettings.FetchSettingsRequest;
import app.ceva.petapp.data.network.model.FetchSettings.FetchSettingsResponse;
import app.ceva.petapp.data.network.model.ForgetPassword.ForgetPasswordRequest;
import app.ceva.petapp.data.network.model.ForgetPassword.ForgetPasswordResponse;
import app.ceva.petapp.data.network.model.GetPet.GetPetsRequest;
import app.ceva.petapp.data.network.model.GetPet.GetPetsResponse;
import app.ceva.petapp.data.network.model.GetPetDetails.GetPetDetailsRequest;
import app.ceva.petapp.data.network.model.GetPetDetails.GetPetDetailsResponse;
import app.ceva.petapp.data.network.model.GetProfile.GetProfileRequest;
import app.ceva.petapp.data.network.model.GetProfile.GetProfileResponse;
import app.ceva.petapp.data.network.model.History.HistoryRequest;
import app.ceva.petapp.data.network.model.History.Historyresponse;
import app.ceva.petapp.data.network.model.Login.LoginRequest;
import app.ceva.petapp.data.network.model.Login.LoginResponse;
import app.ceva.petapp.data.network.model.PetCategory.PetCategoryResponse;
import app.ceva.petapp.data.network.model.ProductDetails.ProductDetailsRequest;
import app.ceva.petapp.data.network.model.ProductDetails.ProductDetailsResponse;
import app.ceva.petapp.data.network.model.Region.RegionResponse;
import app.ceva.petapp.data.network.model.Registration.RegistrationRequest;
import app.ceva.petapp.data.network.model.Registration.RegistrationResponse;
import app.ceva.petapp.data.network.model.ScanQr.ScanQrRequest;
import app.ceva.petapp.data.network.model.ScanQr.ScanQrResponse;
import app.ceva.petapp.data.network.model.SendOtp.SendOtpRequest;
import app.ceva.petapp.data.network.model.SendOtp.SendOtpResponse;
import app.ceva.petapp.data.network.model.SubmitScheduling.SubmitSchedulingRequest;
import app.ceva.petapp.data.network.model.SubmitScheduling.SubmitSchedulingResponse;
import app.ceva.petapp.data.network.model.UpdateProfile.UpdateProfileRequest;
import app.ceva.petapp.data.network.model.UpdateProfile.UpdateProfileResponse;
import app.ceva.petapp.data.pref.PreferencesHelper;
import app.ceva.petapp.di.ApplicationContext;
import retrofit2.Call;

public class AppDataManager implements DataManager {

    ApiHelper mApiHelper;
    PreferencesHelper mPreferencesHelper;
    Context mContext;

    @Inject
    public AppDataManager(ApiHelper mApiHelper, PreferencesHelper mPreferencesHelper,@ApplicationContext Context mContext) {
        this.mApiHelper = mApiHelper;
        this.mPreferencesHelper = mPreferencesHelper;
        this.mContext = mContext;
    }

    @Override
    public void logout() {
        destroyPref();
    }

    @Override
    public String getUserId() {
        return mPreferencesHelper.getUserId();
    }

    @Override
    public void setUserId(String userId) {
        mPreferencesHelper.setUserId(userId);
    }

    @Override
    public String getEmail() {
        return mPreferencesHelper.getEmail();
    }

    @Override
    public void setEmail(String Email) {
        mPreferencesHelper.setEmail(Email);
    }

    @Override
    public String getFirstName() {
        return mPreferencesHelper.getFirstName();
    }

    @Override
    public void setFirstName(String FirstName) {
        mPreferencesHelper.setFirstName(FirstName);
    }

    @Override
    public String getLastName() {
        return mPreferencesHelper.getLastName();
    }

    @Override
    public void setLastName(String LastName) {
        mPreferencesHelper.setLastName(LastName);
    }

    @Override
    public String getFullName() {
        return mPreferencesHelper.getFullName();
    }

    @Override
    public void setFullName(String FullName) {
        mPreferencesHelper.setFullName(FullName);
    }

    @Override
    public String getPhoneNumber() {
        return mPreferencesHelper.getPhoneNumber();
    }

    @Override
    public void setPhoneNumber(String PhoneNumber) {
        mPreferencesHelper.setPhoneNumber(PhoneNumber);
    }

    @Override
    public String getAgentId() {
        return null;
    }

    @Override
    public void setAgentId(String AgentId) {

    }

    @Override
    public String getAddress() {
        return mPreferencesHelper.getAddress();
    }

    @Override
    public void setAddress(String Address) {

        mPreferencesHelper.setAddress(Address);
    }

    @Override
    public String getRegion() {
        return mPreferencesHelper.getRegion();
    }

    @Override
    public void setRegion(String Region) {

        mPreferencesHelper.setRegion(Region);
    }

    @Override
    public String getPassWord() {
        return null;
    }

    @Override
    public void setPassword(String Password) {

    }

    @Override
    public String getCartCount() {
        return null;
    }

    @Override
    public void setCartCount(String cartcount) {

    }

    @Override
    public String getSearch() {
        return null;
    }

    @Override
    public void setSearch(String search) {

    }

    @Override
    public String getEnqury() {
        return null;
    }

    @Override
    public void setEnqury(String enqey) {

    }

    @Override
    public String getWhatsapp() {
        return null;
    }

    @Override
    public void setWhatsapp(String whatsapp) {

    }

    @Override
    public String getGstNumber() {
        return null;
    }

    @Override
    public void setGstNumber(String gstnumber) {

    }

    @Override
    public void destroyPref() {
        mPreferencesHelper.destroyPref();
    }

    @Override
    public Call<LoginResponse> getLogin(LoginRequest loginRequest) {
        return mApiHelper.getLogin(loginRequest);
    }

    @Override
    public Call<SendOtpResponse> getSendOtp(SendOtpRequest sendOtpRequest) {
        return mApiHelper.getSendOtp(sendOtpRequest);
    }

    @Override
    public Call<CheckOtpResponse> getCheckOtp(CheckOtpRequest checkOtpRequest) {
        return mApiHelper.getCheckOtp(checkOtpRequest);
    }

    @Override
    public Call<RegistrationResponse> getRegistration(RegistrationRequest registrationRequest) {
        return mApiHelper.getRegistration(registrationRequest);
    }

    @Override
    public Call<RegionResponse> getRegionList() {
        return mApiHelper.getRegionList();
    }

    @Override
    public Call<PetCategoryResponse> getPetCateroryList() {
        return mApiHelper.getPetCateroryList();
    }

    @Override
    public Call<AddPetsResponse> getAddPets(AddPetsRequest addPetsRequest) {
        return mApiHelper.getAddPets(addPetsRequest);
    }

    @Override
    public Call<GetPetsResponse> getAllPets(GetPetsRequest getPetsRequest) {
        return mApiHelper.getAllPets(getPetsRequest);
    }

    @Override
    public Call<EditPetResponse> getEditPets(EditPetRequest editPetRequest) {
        return mApiHelper.getEditPets(editPetRequest);
    }

    @Override
    public Call<GetPetDetailsResponse> getPetsDetails(GetPetDetailsRequest editPetRequest) {
        return mApiHelper.getPetsDetails(editPetRequest);
    }

    @Override
    public Call<DeletePetResponse> getPetsDelete(DeletePetRequest deletePetRequest) {
        return mApiHelper.getPetsDelete(deletePetRequest);
    }

    @Override
    public Call<GetProfileResponse> getProfile(GetProfileRequest getProfileRequest) {
        return mApiHelper.getProfile(getProfileRequest);
    }

    @Override
    public Call<UpdateProfileResponse> getEditProfile(UpdateProfileRequest updateProfileRequest) {
        return mApiHelper.getEditProfile(updateProfileRequest);
    }

    @Override
    public Call<ChangePasswordResponse> getChangePassword(CgangePasswordRequest cgangePasswordRequest) {
        return mApiHelper.getChangePassword(cgangePasswordRequest);
    }

    @Override
    public Call<ForgetPasswordResponse> getForgetPassword(ForgetPasswordRequest forgetPasswordRequest) {
        return mApiHelper.getForgetPassword(forgetPasswordRequest);
    }

    @Override
    public Call<ScanQrResponse> getScanQr(ScanQrRequest scanQrRequest) {
        return mApiHelper.getScanQr(scanQrRequest);
    }

    @Override
    public Call<ProductDetailsResponse> getProductDetails(ProductDetailsRequest productDetailsRequest) {
        return mApiHelper.getProductDetails(productDetailsRequest);
    }

    @Override
    public Call<SubmitSchedulingResponse> getSubmitScheduling(SubmitSchedulingRequest submitSchedulingRequest) {
        return mApiHelper.getSubmitScheduling(submitSchedulingRequest);
    }



    @Override
    public Call<FetchSchedulingResponse> getScheduling(FetchschedulingRequest fetchschedulingRequest) {
        return mApiHelper.getScheduling(fetchschedulingRequest);
    }

    @Override
    public Call<Historyresponse> getHistory(HistoryRequest historyRequest) {
        return mApiHelper.getHistory(historyRequest);
    }

    @Override
    public Call<AddsettingsResponse> addSettings(AddsettingsRequest addsettingsRequest) {
        return mApiHelper.addSettings(addsettingsRequest);
    }

    @Override
    public Call<FetchSettingsResponse> getSettings(FetchSettingsRequest fetchSettingsRequest) {
        return mApiHelper.getSettings(fetchSettingsRequest);
    }

    @Override
    public Call<AllpagesResponse> getAllPages(AllpagesRequest allpagesRequest) {
        return mApiHelper.getAllPages(allpagesRequest);
    }


}
