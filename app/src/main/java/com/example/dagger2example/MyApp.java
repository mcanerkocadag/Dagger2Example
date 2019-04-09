package com.example.dagger2example;

import android.app.Application;

import com.example.dagger2example.module.ApplicationComponent;
import com.example.dagger2example.module.ApplicationModule;
import com.example.dagger2example.module.DaggerApplicationComponent;

//TODO 4
// Module sınıfı içinde tanımlanan bağımlılıkların hepsi uygulama seviyesi bağımlılıklar
// ve uygulama hayatta kaldığı sürece kullanılabilirler.
// bu nedenle bu nesnelerin Application sınıfı içinde yaratılması uygun olacaktır.
public class MyApp extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();

    }

    public ApplicationComponent getComponent() {
        return applicationComponent;
    }
}
