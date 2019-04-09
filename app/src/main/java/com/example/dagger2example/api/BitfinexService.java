package com.example.dagger2example.api;

import com.example.dagger2example.model.Ticker;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface BitfinexService {

    @GET("pubticker/{symbol}")
    Call<Ticker> getTicker(@Path("symbol") String symbol);
}
