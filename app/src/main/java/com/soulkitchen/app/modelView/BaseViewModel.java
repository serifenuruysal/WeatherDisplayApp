package com.soulkitchen.app.modelView;

public interface BaseViewModel {

  void loadData();

  void subscribe();

  void unsubscribe();

  void onDestroy();
}
