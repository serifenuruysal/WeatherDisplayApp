package com.soulkitchen.app;

import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;

import com.soulkitchen.app.domain.ApiService;
import com.soulkitchen.app.domain.DataSource;
import com.soulkitchen.app.model.CareResponse;
import java.io.IOException;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;
import org.mockito.stubbing.Answer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(MockitoJUnitRunner.class)
public class ExampleUnitTest extends TestCase {

  @InjectMocks
  DataSource service;
  @Rule
  public MockitoRule mockitoRule = MockitoJUnit.rule();

  @Before
  public void init() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void getWheatherOfBerlin() {

    service.getWheatherByName("Berlin").enqueue(new Callback<CareResponse>() {
      @Override
      public void onResponse(Call<CareResponse> call, Response<CareResponse> response) {
        assertEquals(true, response.isSuccessful());
        assertThat(response.body().getMain().getTemp(), notNullValue());
      }

      @Override
      public void onFailure(Call<CareResponse> call, Throwable t) {

      }
    });
  }

  @Test
  public void getWheatherOfWaltham() {
    CareResponse response = Mockito.mock(CareResponse.class);
    Mockito.doReturn(notNullValue()).when(response).getMain().getTemp();

    service.getWheatherByName("Waltham").enqueue(new Callback<CareResponse>() {
      @Override
      public void onResponse(Call<CareResponse> call, Response<CareResponse> response) {
        assertEquals(true, response.isSuccessful());
        assertThat(response.body().getMain().getTemp(), notNullValue());
      }

      @Override
      public void onFailure(Call<CareResponse> call, Throwable t) {
      }
    });
  }

  @Test
  public void fetchNotNulldData() {
    CareResponse response = Mockito.mock(CareResponse.class);

    Mockito.doReturn(notNullValue()).when(response).getMain().getTemp();
    ApiService mockedApiInterface = Mockito.mock(ApiService.class);
    final Call<CareResponse> mockedCall = Mockito.mock(Call.class);
    Mockito.when(mockedApiInterface.getWheatherByName("Berlin")).thenReturn(mockedCall
    );

    Mockito.doAnswer(new Answer() {
      @Override
      public Void answer(InvocationOnMock invocation) throws Throwable {
        Callback<CareResponse> callback = invocation.getArgument(0);//ArgumentAt(0, Callback.class);

        callback.onResponse(mockedCall, Response.success(new CareResponse()));
        callback.onFailure(mockedCall, new IOException());

        return null;
      }
    }).when(mockedCall).enqueue(any(Callback.class));
  }
}