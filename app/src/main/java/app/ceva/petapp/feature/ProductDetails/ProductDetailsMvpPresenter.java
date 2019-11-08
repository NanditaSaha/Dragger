package app.ceva.petapp.feature.ProductDetails;

import app.ceva.petapp.share.base.MvpPresenter;

public interface ProductDetailsMvpPresenter<V extends ProductDetailsMvpView>extends MvpPresenter<V> {

 void onGetProductDetaild(String productId,String productUniqueId);
 void onGetAllPets(String userId);
 void onSubmitSchedule(String petId,String productId,String productUniqueId,String date);
}
