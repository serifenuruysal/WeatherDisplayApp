package com.soulkitchen.app;

import com.soulkitchen.app.models.CareResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by S.Nur Uysal on 26.11.2018.
 */
public interface ApiService {

    @GET("weather")
    Call<CareResponse> getWheatherByName(@Query(value = "q") String cityName);

}
