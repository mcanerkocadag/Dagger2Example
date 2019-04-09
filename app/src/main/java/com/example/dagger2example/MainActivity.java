package com.example.dagger2example;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.dagger2example.api.BitfinexService;
import com.example.dagger2example.model.Ticker;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//TODO 5-
// Injection işlemi 31. satırda tetikleniyor.
// Uygulama Dagger tarafından inject edilen değerleri 33. ve 42. satırlarda kullanıyor.
public class MainActivity extends AppCompatActivity {

    @Inject
    SharedPreferences preferences;
    @Inject
    BitfinexService bitfinexService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((MyApp) this.getApplication()).getComponent().inject(this);

        Call<Ticker> call = bitfinexService.getTicker("btcusd");
        call.enqueue(new Callback<Ticker>() {
            @Override
            public void onResponse(Call<Ticker> call, Response<Ticker> response) {
                if (response.code() == 200) {
                    Toast.makeText(
                            MainActivity.this,
                            "" + response.body().getLastPrice(),
                            Toast.LENGTH_SHORT).show();
                    preferences.edit().putFloat("btcusd", response.body().getLastPrice()).apply();
                }
            }

            @Override
            public void onFailure(Call<Ticker> call, Throwable t) {

                Toast.makeText(MainActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
