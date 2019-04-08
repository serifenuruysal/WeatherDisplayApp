package com.soulkitchen.app.domain;

import com.soulkitchen.app.model.CareResponse;
import retrofit2.Call;

public class DataSource implements ApiService {

  @Override
  public Call<CareResponse> getWheatherByName(String cityName) {
    return ApiClient.getInstance().getWheatherByName(cityName);
  }
}
