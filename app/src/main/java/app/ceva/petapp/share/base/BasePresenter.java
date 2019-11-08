package app.ceva.petapp.share.base;

import javax.inject.Inject;

import app.ceva.petapp.data.DataManager;

public class BasePresenter<V extends MvpView>implements MvpPresenter<V> {


    private static final String TAG = "BasePresenter";
    private final DataManager mdataManager;

    private V mMvpView;

   @Inject
    public BasePresenter(DataManager mdataManager) {
        this.mdataManager = mdataManager;
    }

    @Override
    public void onAttached(V mvpView) {
        mMvpView=mvpView;
    }

    @Override
    public void onDettached() {
        mMvpView=null;
    }

    public boolean isViewAttached(){
       return mMvpView!=null;
    }

    public V getMvpView(){
       return mMvpView;
    }

   public void checkViewAttached()
   {
       if (!isViewAttached()) throw new MvpViewNotAttachedException();
   }


   public DataManager getDataManager()
   {
       return mdataManager;
   }
    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Please call Presenter.onAttach(MvpView) before" +
                    " requesting data to the Presenter");
        }
    }

}
