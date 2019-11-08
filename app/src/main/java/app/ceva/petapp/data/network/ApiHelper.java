package app.ceva.petapp.data.network;

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
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiHelper {

    @POST("api/login")
    Call<LoginResponse> getLogin(@Body LoginRequest loginRequest);

    @POST("api/sendOtp")
    Call<SendOtpResponse> getSendOtp(@Body SendOtpRequest sendOtpRequest);

    @POST("api/checkOtp")
    Call<CheckOtpResponse> getCheckOtp(@Body CheckOtpRequest checkOtpRequest);

    @POST("api/register")
    Call<RegistrationResponse> getRegistration(@Body RegistrationRequest registrationRequest);

    @POST("api/region")
    Call<RegionResponse>getRegionList();


    @POST("api/getCatecory")
    Call<PetCategoryResponse>getPetCateroryList();

    @POST("api/petsAdd")
    Call<AddPetsResponse> getAddPets(@Body AddPetsRequest addPetsRequest);

    @POST("api/allPets")
    Call<GetPetsResponse> getAllPets(@Body GetPetsRequest getPetsRequest);

    @POST("api/editPets")
    Call<EditPetResponse> getEditPets(@Body EditPetRequest editPetRequest);

    @POST("api/getPets")
    Call<GetPetDetailsResponse> getPetsDetails(@Body GetPetDetailsRequest editPetRequest);

    @POST("api/deletePets")
    Call<DeletePetResponse> getPetsDelete(@Body DeletePetRequest deletePetRequest);

    @POST("api/myProfile")
    Call<GetProfileResponse> getProfile(@Body GetProfileRequest getProfileRequest);

    @POST("api/editProfile")
    Call<UpdateProfileResponse> getEditProfile(@Body UpdateProfileRequest updateProfileRequest);

    @POST("api/changePassword")
    Call<ChangePasswordResponse> getChangePassword(@Body CgangePasswordRequest cgangePasswordRequest);

    @POST("api/forgotPass")
    Call<ForgetPasswordResponse>getForgetPassword(@Body ForgetPasswordRequest forgetPasswordRequest);

    @POST("api/scanQr")
    Call<ScanQrResponse>getScanQr(@Body ScanQrRequest scanQrRequest);

    @POST("api/productDetails")
    Call<ProductDetailsResponse>getProductDetails(@Body ProductDetailsRequest productDetailsRequest);

    @POST("api/submitScheduling")
    Call<SubmitSchedulingResponse>getSubmitScheduling(@Body SubmitSchedulingRequest submitSchedulingRequest);


    @POST("api/scheduleDate")
    Call<FetchSchedulingResponse>getScheduling(@Body FetchschedulingRequest fetchschedulingRequest);

    @POST("api/history")
    Call<Historyresponse>getHistory(@Body HistoryRequest historyRequest);


    @POST("api/addSettings")
    Call<AddsettingsResponse> addSettings(@Body AddsettingsRequest addsettingsRequest);

    @POST("api/getSettings")
    Call<FetchSettingsResponse> getSettings(@Body FetchSettingsRequest fetchSettingsRequest);


    @POST("api/allPage")
    Call<AllpagesResponse>getAllPages(@Body AllpagesRequest allpagesRequest);
}
