package app.ceva.petapp.feature.ProductDetails;

import app.ceva.petapp.data.network.model.GetPet.GetPetsResponse;
import app.ceva.petapp.data.network.model.ProductDetails.ProductDetailsResponse;
import app.ceva.petapp.data.network.model.SubmitScheduling.SubmitSchedulingResponse;
import app.ceva.petapp.share.base.MvpView;

public interface ProductDetailsMvpView  extends MvpView {
    void successfullyPetList(GetPetsResponse response);
    void successfullyGetProductDetails(ProductDetailsResponse productDetailsResponse);
    void successfullyGetSchedule(SubmitSchedulingResponse submitSchedulingResponse);
}
