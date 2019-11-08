package app.ceva.petapp.data.pref;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import javax.inject.Inject;
import javax.inject.Singleton;

import app.ceva.petapp.di.ApplicationContext;

@Singleton
public class AppPreferencesHelper implements PreferencesHelper {

    private static final String PREF_CURRENT_USER_ID="userid";
    private static final String PREF_CURRENT_USER_EMAIL="userEmail";
    private static final String PREF_CURRENT_USER_PASSWORD="userPassword";
    private static final String PREF_CURRENT_USER_FIRSTNAME="userFirstName";
    private static final String PREF_CURRENT_USER_FULLNAME="userFullName";
    private static final String PREF_CURRENT_USER_LASTNAME="userLastName";
    private static final String PREF_CURRNT_USER_PHONENUMBER="userPhoneNumber";
    private static final String PREF_CURRENT_USER_AGENTID="userAgentId";
    private static final String PREF_CURRENT_USER_GSTNUMBER="userGstNumber";
    public static final String PREF_CART_COUNT="0";
    public static final String PREF_SEARCH="search";
    public static final String PREF_ENQURY_NUMBER="number";
    public static final String PREF_WHATSAPP_NUMBER="number";
    public static final String PREF_CONTACT_NUMBER_ONE="number";
    public static final String PREF_CONTACT_NUMBER_TWO="number";
    public static final String PREF_NOTIFICATION_COUNT="number";


    private final SharedPreferences mPrefs;

    @Inject
    public AppPreferencesHelper(@ApplicationContext Context context) {
        mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Override
    public String getUserId() {

        String userId = mPrefs.getString(PREF_CURRENT_USER_ID, "");
        return userId;
    }

    @Override
    public void setUserId(String userId) {
        mPrefs.edit().putString(PREF_CURRENT_USER_ID, userId).apply();
    }

    @Override
    public String getEmail() {

        String userEmail = mPrefs.getString(PREF_CURRENT_USER_EMAIL, "");
        return userEmail;
    }

    @Override
    public void setEmail(String Email) {
        mPrefs.edit().putString(PREF_CURRENT_USER_EMAIL,Email).apply();
    }

    @Override
    public String getFirstName() {

        String userFirstName = mPrefs.getString(PREF_CURRENT_USER_FIRSTNAME, "");
        return userFirstName;
    }

    @Override
    public void setFirstName(String FirstName) {
        mPrefs.edit().putString(PREF_CURRENT_USER_FIRSTNAME, FirstName).apply();
    }

    @Override
    public String getLastName() {

        String userLastName = mPrefs.getString(PREF_CURRENT_USER_LASTNAME, "");
        return userLastName;
    }

    @Override
    public void setLastName(String LastName) {
        mPrefs.edit().putString(PREF_CURRENT_USER_LASTNAME, LastName).apply();
    }

    @Override
    public String getFullName() {
        String userFullame = mPrefs.getString(PREF_CURRENT_USER_FULLNAME, "");
        return userFullame;
    }

    @Override
    public void setFullName(String FullName) {
        mPrefs.edit().putString(PREF_CURRENT_USER_FULLNAME, FullName).apply();
    }

    @Override
    public String getPhoneNumber() {
        String userPhoneNumber = mPrefs.getString(PREF_CURRNT_USER_PHONENUMBER, "");
        return userPhoneNumber;
    }

    @Override
    public void setPhoneNumber(String PhoneNumber) {
        mPrefs.edit().putString(PREF_CURRNT_USER_PHONENUMBER, PhoneNumber).apply();
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
        return null;
    }

    @Override
    public void setAddress(String Address) {

    }

    @Override
    public String getRegion() {
        return null;
    }

    @Override
    public void setRegion(String Region) {

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
        mPrefs.edit().clear().commit();
    }
}
