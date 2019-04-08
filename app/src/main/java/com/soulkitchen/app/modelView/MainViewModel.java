package com.soulkitchen.app.modelView;

import android.databinding.BaseObservable;
import com.soulkitchen.app.domain.ApiClient;
import com.soulkitchen.app.domain.DataSource;
import com.soulkitchen.app.model.CareResponse;
import java.text.DecimalFormat;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Subscription;

/**
 * Created by S.Nur Uysal on 07.04.2019.
 */
public class MainViewModel extends BaseObservable implements BaseViewModel {

  private static final String FORMATTER = "##.##";
  private static final String CELCIUS = "°C";
  private static final Double FAHRENHEIT = -273.15;
  private String tempreatureRight;
  private String tempreatureLeft;

  public MainViewModel() {
    loadData();
  }

  private void callTempreatureLeft() {

    ApiClient.getInstance().getWheatherByName("Berlin").enqueue(new Callback<CareResponse>() {
      @Override
      public void onResponse(Call<CareResponse> call, Response<CareResponse> response) {
        CareResponse res = response.body();
        tempreatureLeft =
            new DecimalFormat(FORMATTER).format(res.getMain().getTemp() + FAHRENHEIT) + CELCIUS;

        notifyChange();
      }

      @Override
      public void onFailure(Call<CareResponse> call, Throwable t) {

      }
    });
  }

  private void callTempreatureRight() {

    ApiClient.getInstance().getWheatherByName("Waltham").enqueue(new Callback<CareResponse>() {
      @Override
      public void onResponse(Call<CareResponse> call, Response<CareResponse> response) {
        CareResponse res = response.body();
        tempreatureRight =
            new DecimalFormat(FORMATTER).format(res.getMain().getTemp() + FAHRENHEIT) + CELCIUS;

        notifyChange();
      }

      @Override
      public void onFailure(Call<CareResponse> call, Throwable t) {

      }
    });
  }

  public String getTempreatureRight() {
    return tempreatureRight;
  }

  public String getTempreatureLeft() {
    return tempreatureLeft;
  }

  @Override
  public void loadData() {
    callTempreatureLeft();
    callTempreatureRight();
  }

  @Override
  public void subscribe() {

  }

  @Override
  public void unsubscribe() {

  }

  @Override
  public void onDestroy() {

  }
}
