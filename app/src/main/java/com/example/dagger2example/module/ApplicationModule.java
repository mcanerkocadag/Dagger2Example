package com.example.dagger2example.module;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.dagger2example.api.BitfinexService;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static com.example.dagger2example.module.AppConstants.BASE_URL;

//TODO 2 - inject edilecek nesneleri provide eden bir Module sınıfına ihtiyacımız var.
@Module
public class ApplicationModule {
    private final Context context;

    public ApplicationModule(Application application) {
        context = application;
    }

    @Provides
    @Named("app")
    Context proviceContext() {
        return context;
    }

    @Provides
    @Singleton
    public OkHttpClient provideHttpClient() {
        return new OkHttpClient().newBuilder().build();
    }


    @Provides
    @Named("gson")
    @Singleton
    public Retrofit provideRetrofitGson(OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder().addConverterFactory(
                GsonConverterFactory.create()).baseUrl(BASE_URL).client(okHttpClient).build();
        return retrofit;
    }

    @Provides
    @Named("scalar")
    @Singleton
    public Retrofit provideRetrofitXml(OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder().addConverterFactory(
                ScalarsConverterFactory.create()).baseUrl(BASE_URL).client(okHttpClient).build();
        return retrofit;
    }

    @Provides
    @Singleton
    public SharedPreferences provideSharedPreferences(){
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Provides
    @Singleton
    public BitfinexService providesBitfinexService(@Named("gson") Retrofit retrofit){

        return retrofit.create(BitfinexService.class);
    }
}
