package app.ceva.petapp.data.pref;

public interface PreferencesHelper {

    String getUserId();

    void setUserId(String userId);

    String getEmail();

    void setEmail(String Email);

    String getFirstName();

    void setFirstName(String FirstName);

    String getLastName();

    void setLastName(String LastName);

    String getFullName();

    void setFullName(String FullName);

    String getPhoneNumber();

    void setPhoneNumber(String PhoneNumber);

    String getAgentId();

    void setAgentId(String AgentId);

    String getAddress();

    void setAddress(String Address);

    String getRegion();

    void setRegion(String Region);

    String getPassWord();

    void setPassword(String Password);


    String getCartCount();
    void setCartCount(String cartcount);

    String getSearch();
    void setSearch(String search);

    String getEnqury();
    void setEnqury(String enqey);

    String getWhatsapp();
    void setWhatsapp(String whatsapp);

    String getGstNumber();
    void setGstNumber(String gstnumber);

    void destroyPref();
}
