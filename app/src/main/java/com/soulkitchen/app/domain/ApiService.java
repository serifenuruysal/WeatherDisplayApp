package com.soulkitchen.app.domain;

import com.soulkitchen.app.model.CareResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by S.Nur Uysal on 07.04.2019.
 */
public interface ApiService {

    @GET("weather")
    Call<CareResponse> getWheatherByName(@Query(value = "q") String cityName);

}
