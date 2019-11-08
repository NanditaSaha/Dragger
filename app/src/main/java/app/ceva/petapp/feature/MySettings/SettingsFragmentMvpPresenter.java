package app.ceva.petapp.feature.MySettings;

import app.ceva.petapp.share.baseFragment.BaseFragmentMvpPresenter;

public interface SettingsFragmentMvpPresenter<V extends SettingsFragmentMvpView>extends BaseFragmentMvpPresenter<V> {
  void onSubmitSettings(String nofiticationBefor);
  void ongetSettings();
}
